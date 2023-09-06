package com.example.connect_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class teacherlogin extends AppCompatActivity {

    public static final String SHARED_PREFS="sharedPrefs";

    String id , pass;
    EditText tid , tpass;

    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherlogin);


        logincheck();
        Button signupbtn= findViewById(R.id.teachersignupbtn);
        Button forgetpassbtn = findViewById(R.id.teacherforgetpass);
        Button loginbtn = findViewById(R.id.teacherloginbtn);

        tid = findViewById(R.id.getidteacher);
        tpass = findViewById(R.id.getteacherpass);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teacherlogin.this,teachersignup.class);
                startActivity(intent);
//                finish();
            }
        });

        forgetpassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teacherlogin.this , forgetpass.class);
                startActivity(intent);
//                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = tid.getText().toString();
                pass = tpass.getText().toString();

                if(!id.isEmpty() && !pass.isEmpty()){
                    auth = FirebaseAuth.getInstance();
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Teachers");

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(id)){
                                String gid = snapshot.child(id).child("id").getValue(String.class);
                                String gname = snapshot.child(id).child("name").getValue(String.class);
                                String gdept = snapshot.child(id).child("dept").getValue(String.class);
                                String gemail = snapshot.child(id).child("email").getValue(String.class);
                                String gpass = snapshot.child(id).child("pass").getValue(String.class);

                                if(gpass.equals(pass)){
                                    auth.signInWithEmailAndPassword(gemail,gpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                if(auth.getCurrentUser().isEmailVerified()){
                                                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                                    editor.putString("teachername", "login");

                                                    editor.apply();
                                                    Toast.makeText(teacherlogin.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(teacherlogin.this,teachercontent.class);
                                                    startActivity(intent);
//                                                    finish();
                                                }
                                                else{
                                                    Toast.makeText(teacherlogin.this,"Please confirm your email",Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                        }
                                    });
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else{
                    if(id.length()<0){
                        Toast.makeText(teacherlogin.this,"Password should be more than 7 characters",Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(teacherlogin.this,"Please provide the credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void logincheck() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String check = sharedPreferences.getString("teachername","");
        if(check.equals("login")) {
            Toast.makeText(teacherlogin.this, "Login Sucessful", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(teacherlogin.this, teachercontent.class);
            startActivity(intent);
//            finish();
        }

    }
}