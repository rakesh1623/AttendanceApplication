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

public class registration extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference reference;

    FirebaseAuth auth;
    String uname , usec , uroll , uenroll , uyear , upass , uemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();

        EditText name , sec, roll , enroll , year , pass, email;
        name = findViewById(R.id.getname);
        sec = findViewById(R.id.getsec);
        roll = findViewById(R.id.getroll);
        enroll = findViewById(R.id.getenroll);
        year = findViewById(R.id.getyear);
        pass = findViewById(R.id.getpass);
        email = findViewById(R.id.getemail);

        Button btn;
        btn=findViewById(R.id.teacherregister);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = name.getText().toString();
                usec = sec.getText().toString();
                uenroll = enroll.getText().toString();
                uroll = roll.getText().toString();
                uyear = year.getText().toString();
                upass = pass.getText().toString();
                uemail = email.getText().toString();

                if(!uname.isEmpty() && !usec.isEmpty() && !uenroll.isEmpty() && !uroll.isEmpty() && !uyear.isEmpty() && !upass.isEmpty() && !uemail.isEmpty() && upass.length()>=6) {
                    reghelper user = new reghelper(uname , usec , uroll , uenroll , uyear , upass , uemail);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Students");


                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(uenroll)){
                                Toast.makeText(registration.this,uenroll+" is already present",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(registration.this , MainActivity.class);
                                startActivity(intent);
//                                finish();
                            }
                            else{
                                auth.createUserWithEmailAndPassword(uemail,upass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            auth.getCurrentUser().sendEmailVerification();
                                        }
                                    }
                                });

                                reference.child(uenroll).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(registration.this, "Please verify your email to login", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(registration.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(registration.this, "Registration Failed", Toast.LENGTH_LONG).show();
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
                    if(upass.length()<6)
                        Toast.makeText(registration.this,"Please enter password more than 5 characters",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}