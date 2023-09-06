package com.example.connect_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class sendattendance extends AppCompatActivity {

    public static final String SHARED_PREFS="sharedPrefs";
//    Switch live = findViewById(R.id.switch1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendattendance);


        Button addbtn = findViewById(R.id.add);
        EditText ed = findViewById(R.id.editText2);


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String str = ed.getText().toString();
                    if(!str.isEmpty()) {
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                    Log.d("edgo",str);
                        editor.putString("gform", str);
                        editor.apply();
                        Toast.makeText(sendattendance.this, "G-Form updated successfully", Toast.LENGTH_SHORT).show();
                        ed.setText("");
                    }else{
                        Toast.makeText(sendattendance.this, "Fill the attendance link", Toast.LENGTH_SHORT).show();
                    }


            }
        });
    }

}
