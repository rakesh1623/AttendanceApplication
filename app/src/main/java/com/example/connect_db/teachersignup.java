package com.example.connect_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class teachersignup extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference databaseReference;

    String name , dept,email,pass , id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachersignup);
        EditText tname , tdept , temail , tpass , tid;
        Button submitbtn;
        tname = findViewById(R.id.getname);
        tdept = findViewById(R.id.getsec);
        temail = findViewById(R.id.getemail);
        tpass = findViewById(R.id.getpass);
        tid = findViewById(R.id.getteacherid);
        submitbtn = findViewById(R.id.teacherregister);

        auth = FirebaseAuth.getInstance();

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = tname.getText().toString();
                dept = tdept.getText().toString();
                email = temail.getText().toString();
                pass = tpass.getText().toString();
                id = tid.getText().toString();

                if(!name.isEmpty() && !dept.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !id.isEmpty() && pass.length()>=6){
                    teacherhelper teacher = new teacherhelper(id,name,dept,email,pass);
                    db = FirebaseDatabase.getInstance();
                    databaseReference = db.getReference("Teachers");

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(id)){
                                Toast.makeText(teachersignup.this,"Teacher is already present",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(teachersignup.this,MainActivity.class);
                                startActivity(intent);
//                                finish();
                            }
                            else{
                                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            auth.getCurrentUser().sendEmailVerification();
                                        }
                                    }
                                });

                                databaseReference.child(id).setValue(teacher).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(teachersignup.this, "Teacher registration successful", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(teachersignup.this,teacherlogin.class);
                                            startActivity(intent);
//                                            finish();

                                        }
                                        else{
                                            Toast.makeText(teachersignup.this, "Teacher registration failed", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
                else{
                    Toast.makeText(teachersignup.this,"Please enter password more than 5 characters",Toast.LENGTH_SHORT).show();
                }

            }
        });






    }
}