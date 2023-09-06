package com.example.connect_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class gform extends AppCompatActivity {

    public static final String SHARED_PREFS="sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gform);

        EditText gform = findViewById(R.id.editText);
        Button subatt = findViewById(R.id.subatt);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String check = sharedPreferences.getString("gform","");
        gform.setText(check);
        subatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                System.out.println(check);

                if(check !=""){
                    if(!check.isEmpty()){
//                        gform.setText(check);
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(check));
                        startActivity(i);

                    }
                }else{
                    Toast.makeText(gform.this, "SESSION EXPIRED", Toast.LENGTH_SHORT).show();
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("gform","");
                editor.apply();
            }
        });


    }
}