package com.example.connect_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class choose_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_page);
        Button teacherbtn , studbtn;
        studbtn = findViewById(R.id.studbtn);
        teacherbtn = findViewById(R.id.teacherbtn);
        studbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choose_page.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        teacherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(choose_page.this, "teacher chosen", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(choose_page.this , teacherlogin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}