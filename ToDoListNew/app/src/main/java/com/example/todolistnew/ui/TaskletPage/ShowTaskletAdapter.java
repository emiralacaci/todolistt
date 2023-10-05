package com.example.todolistnew.ui.TaskletPage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.todolistnew.database.Tasklet.TaskletDao;
import com.example.todolistnew.database.Tasklet.TaskletDatabase;
import com.example.todolistnew.databinding.AdapterShowTaskletBinding;
import com.example.todolistnew.model.Tasklet;

import java.lang.ref.WeakReference;
import java.util.List;

public class ShowTaskletAdapter extends RecyclerView.Adapter<ShowTaskletAdapter.ViewHolder> {

    private List<Tasklet> taskletList;
    private boolean isSaveButtonClicked=false;
    private  FragmentActivity activity;

    public ShowTaskletAdapter(List<Tasklet> taskletList, FragmentActivity activity) {
        this.taskletList = taskletList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AdapterShowTaskletBinding binding = AdapterShowTaskletBinding.inflate(inflater,parent,false);
        WeakReference<AdapterShowTaskletBinding> weakRef = new WeakReference<>(binding);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tasklet tasklet = taskletList.get(position);
        int uid = tasklet.getUid();
        String description = tasklet.getDescription();
        boolean isDone = tasklet.isDone();

        holder.binding.descriptionTextView.setText(description);
        holder.binding.checkBox.setChecked(isDone);

        holder.binding.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = holder.binding.checkBox.isChecked();
                updateData(uid,isChecked);
            }
        });


        /*
        if(isDone){
            holder.binding.checkBox.setChecked(isDone);
        }else {
            boolean isChecked = holder.binding.checkBox.isChecked();
            if(isSaveButtonClicked && isChecked){
                TaskletDatabase db = Room.databaseBuilder(activity,TaskletDatabase.class,"Tasklet").allowMainThreadQueries().build();
                TaskletDao taskletDao = db.taskletDao();
                taskletDao.update(uid,isChecked);
            }
        }

         */




    }
    public void updateData(int uid, boolean isChecked){
        if(isChecked){
            TaskletDatabase db = Room.databaseBuilder(activity,TaskletDatabase.class,"Tasklet").allowMainThreadQueries().build();
            TaskletDao taskletDao = db.taskletDao();
            taskletDao.update(uid,isChecked);
        }else {
            TaskletDatabase db = Room.databaseBuilder(activity,TaskletDatabase.class,"Tasklet").allowMainThreadQueries().build();
            TaskletDao taskletDao = db.taskletDao();
            taskletDao.update(uid,isChecked);
        }
    }

    public void isSaveButtonClicked(){
        isSaveButtonClicked=true;
    }

    @Override
    public int getItemCount() {
        return taskletList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        AdapterShowTaskletBinding binding;
        public ViewHolder(@NonNull AdapterShowTaskletBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
