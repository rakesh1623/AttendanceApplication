package com.example.connect_db;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    String unamee , pass;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseAuth auth;

    public static final String SHARED_PREFS="sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.getidteacher);
        password = findViewById(R.id.pass);
        logincheck();
        Button btn ,btn2 ,btn3;
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 =findViewById(R.id.button3);

        auth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unamee = username.getText().toString();
                pass = password.getText().toString();

                if(!unamee.isEmpty() && !pass.isEmpty()){
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Students");


                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(unamee)){


                                String naam = snapshot.child(unamee).child("uname").getValue(String.class);
                                String rp = snapshot.child(unamee).child("upass").getValue(String.class);
                                String rr = snapshot.child(unamee).child("uroll").getValue(String.class);
                                String rs = snapshot.child(unamee).child("usec").getValue(String.class);
                                String ry = snapshot.child(unamee).child("uyear").getValue(String.class);
                                String re = snapshot.child(unamee).child("uemail").getValue(String.class);
//                                System.out.println(naam);
                                String getpass = snapshot.child(unamee).child("upass").getValue(String.class);
                                if(getpass.equals(pass)) {

                                    auth.signInWithEmailAndPassword(re,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {

                                            if(task.isSuccessful()){
                                                if(auth.getCurrentUser().isEmailVerified()){

                                                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                                        SharedPreferences.Editor editor = sharedPreferences.edit();

                                                        editor.putString("name", "login");
                                                        editor.putString("uname", unamee);
                                                        editor.putString("key", naam);
                                                        editor.putString("rp", rp);
                                                        editor.putString("rr", rr);
                                                        editor.putString("rs", rs);
                                                        editor.putString("ry", ry);
                                                        editor.putString("re", re);
                                                        editor.apply();

                                                        Toast.makeText(MainActivity.this, "Login Sucessful", Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(MainActivity.this, info.class);
                                                        startActivity(intent);

                                                }
                                                else{
                                                    Toast.makeText(MainActivity.this, "Please verify your email", Toast.LENGTH_LONG).show();
                                                }
                                            }

                                        }
                                    });



                                }
                                else{
                                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                                }

                            }
                            else{
                                Toast.makeText(MainActivity.this,"No user found with "+unamee,Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                username.setText("");
                password.setText("");

            }

        }

        );

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , registration.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,forgetpass.class);
                startActivity(intent);
//                finish();
            }
        });
    }

    private void logincheck() {

            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
            String check = sharedPreferences.getString("name","");
            if(check.equals("login")) {
                Toast.makeText(MainActivity.this, "Login Sucessful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, info.class);
                startActivity(intent);
                finish();
            }
    }


}
