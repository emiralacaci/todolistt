package com.example.todolistnew.ui.TaskPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistnew.R;
import com.example.todolistnew.database.Task.TaskDao;
import com.example.todolistnew.database.Task.TaskDatabase;
import com.example.todolistnew.databinding.FragmentTaskBinding;
import com.example.todolistnew.model.Task;

import java.util.List;

public class TaskFragment extends Fragment {

    private FragmentTaskBinding binding;
    private TaskAdapter taskAdapter;

    private List<Task> taskList;
    private View view;
    TaskDatabase db;
    TaskDao taskDao;
    FragmentActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTaskBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        activity=getActivity();
        binding.addNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_taskFragment_to_createNewTaskFragment);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         db = Room.databaseBuilder(activity,TaskDatabase.class,"Task").allowMainThreadQueries().build();
         taskDao = db.taskDao();
        taskList = taskDao.getAllTasks();


        binding.taskRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        taskAdapter = new TaskAdapter(taskList,view);
        binding.taskRecyclerView.setAdapter(taskAdapter);



    }

    public View getView(){
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}