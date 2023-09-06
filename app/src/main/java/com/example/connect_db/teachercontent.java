package com.example.connect_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class teachercontent extends AppCompatActivity {

    public static final String SHARED_PREFS="sharedPrefs";

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("teachername","");
        editor.apply();
        Intent intent = new Intent(teachercontent.this,teacherlogin.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachercontent);

        CardView teacheratt = findViewById(R.id.teacheratt);
        CardView logout = findViewById(R.id.teachercontentlogout);
        teacheratt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(teachercontent.this,sendattendance.class);
                startActivity(intent);
//                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("teachername","");
                editor.apply();
                Intent intent = new Intent(teachercontent.this,teacherlogin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}