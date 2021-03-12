package com.example.table_list_widget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class ToDoList extends AppCompatActivity {

    private ArrayList<String> myList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapter adapter;
    private Tasks tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        
        tasks = tasks.getInstance(this);
        myList = tasks.getTasks();

        Button add = (Button) findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToDoList.this, AddList.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new RecycleAdapter(this, myList);
        refresh();


        Button remove = (Button) findViewById(R.id.removeButton);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> rem = adapter.getRemove();
                myList = tasks.removeTask(rem);
                refresh();
            }
        });

        ImageButton back = (ImageButton) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ToDoList.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void refresh(){
        adapter = new RecycleAdapter(this, myList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}