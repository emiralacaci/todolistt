package com.example.todolistnew.ui.TaskPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.todolistnew.R;
import com.example.todolistnew.database.Tasklet.TaskletDao;
import com.example.todolistnew.database.Tasklet.TaskletDatabase;
import com.example.todolistnew.databinding.AdapterTaskBinding;
import com.example.todolistnew.model.Task;
import com.example.todolistnew.model.Tasklet;

import java.lang.ref.WeakReference;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    List<Task> taskList;
    View viewFragment;

    public TaskAdapter(List<Task> taskList, View viewFragment) {
        this.taskList = taskList;
        this.viewFragment = viewFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AdapterTaskBinding binding = AdapterTaskBinding.inflate(inflater,parent,false);
        WeakReference<AdapterTaskBinding> weakRef = new WeakReference<>(binding);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.title.setText(taskList.get(position).getTitle());
        holder.binding.description.setText(taskList.get(position).getDescription());

        int taskId=taskList.get(position).getUid();
        int counterTasklet = 0;
        int completedTaskletCounter = 0;

        Bundle bundle = new Bundle();
        bundle.putInt("taskId",taskList.get(position).getUid());
        bundle.putString("title",taskList.get(position).getTitle());
        bundle.putString("description",taskList.get(position).getDescription());
        bundle.putBoolean("isDone",taskList.get(position).isDone());

        TaskletDatabase db = Room.databaseBuilder(viewFragment.getContext(),TaskletDatabase.class,"Tasklet").allowMainThreadQueries().build();
        TaskletDao taskletDao = db.taskletDao();

        for(Tasklet tasklet : taskletDao.getTasklet(taskId)){
            counterTasklet++;
            if(tasklet.isDone()){
                completedTaskletCounter++;
            }
        }
        if(counterTasklet==completedTaskletCounter){
            holder.binding.completedImage.setVisibility(View.VISIBLE);
        }

        holder.binding.taskProgress.setText(completedTaskletCounter+"/"+counterTasklet);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(viewFragment).navigate(R.id.action_taskFragment_to_taskletFragment,bundle);
            }
        });

    }


    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AdapterTaskBinding binding;
        public ViewHolder(@NonNull AdapterTaskBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
