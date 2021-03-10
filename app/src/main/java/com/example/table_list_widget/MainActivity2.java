package com.example.table_list_widget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private ArrayList<String> myList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            myList = extra.getStringArrayList("LIST");
        }

        Button add = (Button) findViewById(R.id.button2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, AddList.class);
                intent.putExtra("LIST", myList);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new RecycleAdapter(this, myList);
        refresh();


        Button remove = (Button) findViewById(R.id.button3);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Integer> rem = adapter.getRemove();
                if(!myList.isEmpty() && !rem.isEmpty()) {
                    for (int i = 0; i < rem.size(); i++) {
                        myList.set(rem.get(i), "");
                    }
                    ArrayList<String> temp = new ArrayList<>();
                    for (int i = 0; i < myList.size(); i++) {
                        if (!myList.get(i).isEmpty()) {
                            temp.add(myList.get(i));
                        }
                    }
                    myList = temp;
                }
                refresh();
            }
        });

    }

    public void refresh(){
        adapter = new RecycleAdapter(this, myList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}