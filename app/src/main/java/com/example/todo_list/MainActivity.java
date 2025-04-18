package com.example.todo_list;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTask;
    private Button buttonAdd;
    private RecyclerView recyclerViewTasks;
    private TaskAdapter adapter;
    private List<Task> taskList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        taskList = dbHelper.getAllTasks();
        adapter = new TaskAdapter(this, taskList);
        recyclerViewTasks.setAdapter(adapter);

        buttonAdd.setOnClickListener(v -> {
            String taskDescription = editTextTask.getText().toString().trim();
            if (!taskDescription.isEmpty()) {
                long newId = dbHelper.addTask(taskDescription);
                Task newTask = new Task((int) newId, taskDescription, false);
                taskList.add(newTask);
                adapter.notifyItemInserted(taskList.size() - 1);
                editTextTask.getText().clear();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        taskList.clear();
        taskList.addAll(dbHelper.getAllTasks());
        adapter.setTasks(taskList);
    }
}