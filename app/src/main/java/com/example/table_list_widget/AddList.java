package com.example.table_list_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddList extends AppCompatActivity {

    private ArrayList<String> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        //Creates a pop up window
        //https://www.youtube.com/watch?v=fn5OlqQuOCk
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        int width = display.widthPixels;
        int height = display.heightPixels;
        getWindow().setLayout((int)(width*.6),(int)(height*.3));

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            myList = extra.getStringArrayList("LIST");
        }

        Button addTask = (Button) findViewById(R.id.button4);
        EditText editTask = (EditText) findViewById(R.id.editTask);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddList.this, MainActivity2.class);
                String task = editTask.getText().toString();
                if(!task.isEmpty()) {
                    myList.add(task);
                }
                intent.putExtra("LIST", myList);
                startActivity(intent);
            }
        });

    }
}