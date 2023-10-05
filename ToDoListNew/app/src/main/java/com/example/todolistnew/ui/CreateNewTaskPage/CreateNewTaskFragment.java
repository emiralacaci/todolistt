package com.example.todolistnew.ui.CreateNewTaskPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistnew.R;
import com.example.todolistnew.database.Task.TaskDao;
import com.example.todolistnew.database.Task.TaskDatabase;
import com.example.todolistnew.databinding.FragmentCreateNewTaskBinding;
import com.example.todolistnew.model.Task;
import com.example.todolistnew.model.Tasklet;
import com.example.todolistnew.utils.Utils;

import java.util.ArrayList;


public class CreateNewTaskFragment extends Fragment {

    private FragmentCreateNewTaskBinding binding;
    private CreateTaskletAdapter createTaskletAdapter;
    int counter;

    private ArrayList<Tasklet> arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding=FragmentCreateNewTaskBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrayList=new ArrayList<>();
        counter=0;

        binding.taskletRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        createTaskletAdapter =new CreateTaskletAdapter(arrayList,getActivity());
        binding.taskletRecyclerView.setAdapter(createTaskletAdapter);

        binding.increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < 5){
                    counter++;
                    Tasklet tasklet=new Tasklet("",2);
                    arrayList.add(tasklet);
                    createTaskletAdapter.notifyItemInserted(counter-1);
                }
                binding.counter.setText(String.valueOf(counter));
            }
        });

        binding.decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter > 0){
                    counter--;
                    arrayList.remove(counter);
                    createTaskletAdapter.notifyItemRemoved(counter);
                }
                binding.counter.setText(String.valueOf(counter));
            }
        });

        binding.deneme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.titleEditText.getText().toString();
                String description = binding.descriptionEditText.getText().toString();

                if(!Utils.areNullOrEmpty(title,description)){
                    Task task = new Task(title,description);
                    TaskDatabase db = Room.databaseBuilder(getActivity(),TaskDatabase.class,"Task").allowMainThreadQueries().build();
                    TaskDao taskDao = db.taskDao();
                    taskDao.insert(task);

                    int taskId = taskDao.getAllTasks().get(taskDao.getAllTasks().size()-1).getUid();

                    createTaskletAdapter.setTaskId(taskId);
                    createTaskletAdapter.isClickedSaveButton();
                    createTaskletAdapter.notifyDataSetChanged();

                    Navigation.findNavController(view).navigate(R.id.action_createNewTaskFragment_to_taskFragment);
                }
            }
        });

    }
}