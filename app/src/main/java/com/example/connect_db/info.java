package com.example.connect_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class info extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView nav_view;
    Toolbar toolbar;
    public static final String SHARED_PREFS="sharedPrefs";
//    public static final String SHARED_PREFS2="sharedPrefs2";
//    public static final String SHARED_PREFS3="sharedPrefs3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        drawerLayout = findViewById(R.id.drawerlay);
        nav_view =findViewById(R.id.nav_view);
        toolbar =findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        View headerview = nav_view.getHeaderView(0);
        TextView t1 = (TextView) headerview.findViewById(R.id.username);
        TextView t2 = (TextView) headerview.findViewById(R.id.userenrollment);

        SharedPreferences sd1 = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences sd2 = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        String check = sd1.getString("uname","");
        String naam = sd2.getString("key","");

        t1.setText(naam);
        t2.setText(check);

//        t1.setText("DEMO");
//        t2.setText("1234567890");


        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id==R.id.details){

                    loadFrag();

                    Toast.makeText(info.this,"Details",Toast.LENGTH_LONG).show();

                }else if(id==R.id.attendance){
                    Intent intent =  new Intent(info.this,attendance.class);
                    startActivity(intent);
                    Toast.makeText(info.this,"Attendance",Toast.LENGTH_SHORT).show();

                }else if(id==R.id.sem_mark){
                    Toast.makeText(info.this,"Marks",Toast.LENGTH_LONG).show();

                }else if(id==R.id.logout){

                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name","");
                    editor.apply();

                    Intent intent = new Intent(info.this,MainActivity.class);
                    startActivity(intent);
//                    finish();

                    Toast.makeText(info.this,"Logout",Toast.LENGTH_LONG).show();

                }else if(id==R.id.change_pass){
                    Toast.makeText(info.this,"Change Pass",Toast.LENGTH_LONG).show();

                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    private void loadFrag() {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
//        ft.hide(R.id.container);
        ft.add(R.id.container , new detailFragment()).addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{

            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name","");
            editor.apply();
            Intent intent = new Intent(info.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}