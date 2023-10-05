package com.example.todolistnew.ui.CreateNewTaskPage;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.todolistnew.database.Tasklet.TaskletDao;
import com.example.todolistnew.database.Tasklet.TaskletDatabase;
import com.example.todolistnew.databinding.AdapterTaskletBinding;
import com.example.todolistnew.model.Tasklet;

import java.util.ArrayList;

public class CreateTaskletAdapter extends RecyclerView.Adapter<CreateTaskletAdapter.ViewHolder> {

    private boolean isClickedButton = false;
    private int taskId;

    private ArrayList<Tasklet> arrayList;
    private FragmentActivity activity;

    public CreateTaskletAdapter(ArrayList<Tasklet> arrayList, FragmentActivity activity) {
        this.arrayList = arrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AdapterTaskletBinding binding = AdapterTaskletBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        if(isClickedButton){
            Tasklet tasklet = new Tasklet(holder.binding.descriptipnEditText.getText().toString(),taskId);
            TaskletDatabase db = Room.databaseBuilder(activity,TaskletDatabase.class,"Tasklet").allowMainThreadQueries().build();
            TaskletDao taskletDao = db.taskletDao();
            taskletDao.insert(tasklet);
        }

    }

    public void isClickedSaveButton(){
        isClickedButton = true;

    }


    public void setTaskId(int taskId){
        this.taskId = taskId;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterTaskletBinding binding;
        public ViewHolder(@NonNull AdapterTaskletBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
