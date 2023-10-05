package com.example.todolistnew.ui.TaskletPage;

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
import com.example.todolistnew.database.Tasklet.TaskletDao;
import com.example.todolistnew.database.Tasklet.TaskletDatabase;
import com.example.todolistnew.databinding.FragmentTaskletBinding;
import com.example.todolistnew.model.Tasklet;

import java.util.ArrayList;
import java.util.List;


public class TaskletFragment extends Fragment {

    FragmentTaskletBinding binding;
    ShowTaskletAdapter showTaskletAdapter;
    List<Tasklet> taskletList;
    int taskId;
    TaskletDatabase db;
    TaskletDao taskletDao;
    FragmentActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTaskletBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        taskletList = new ArrayList<>();
        activity = getActivity();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskId = getArguments().getInt("taskId");
        String title = getArguments().getString("title");
        String description = getArguments().getString("description");
        boolean isDone = getArguments().getBoolean("isDone");

        binding.titleTextView.setText(title);
        binding.descriptionTextView.setText(description);

        getDataFromTaskletDatabase();

        binding.taskletRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        showTaskletAdapter=new ShowTaskletAdapter(taskletList, (FragmentActivity) activity);
        binding.taskletRecyclerView.setAdapter(showTaskletAdapter);


        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_taskletFragment_to_taskFragment);
            }
        });



    }

    public void getDataFromTaskletDatabase(){
         db = Room.databaseBuilder(activity,TaskletDatabase.class,"Tasklet").allowMainThreadQueries().build();
         taskletDao = db.taskletDao();
        taskletList = taskletDao.getTasklet(taskId);

    }



}