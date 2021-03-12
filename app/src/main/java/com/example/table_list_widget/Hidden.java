package com.example.table_list_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hidden extends AppCompatActivity {

    private BkgAnimation bkgAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);

        bkgAnimation = (BkgAnimation) findViewById(R.id.bkgAnimation);
        Button back = (Button) findViewById(R.id.homeButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hidden.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}