package com.example.connect_db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class attendance extends AppCompatActivity {

    Boolean res=true;
    TextView t1 ,t2;
    String s1 , s2;
    double longi , lati;
    EditText editText;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Button subbtn = findViewById(R.id.exam_code);
        editText = findViewById(R.id.txtcode);


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        Toast.makeText(attendance.this,"get long lat",Toast.LENGTH_SHORT).show();


        if (ContextCompat.checkSelfPermission(attendance.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(attendance.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(attendance.this,"pehla",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(attendance.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            else
            {
                ActivityCompat.requestPermissions(attendance.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                Toast.makeText(attendance.this,"dusra",Toast.LENGTH_SHORT).show();
            }
        }
        else{

            if(!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                Toast.makeText(attendance.this,"false",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(attendance.this);
                builder.setTitle("boom")
                        .setMessage("Location on kar le bhai")
                        .setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                    }
                                })
                        .setNegativeButton("no",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        res=false;
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                Toast.makeText(attendance.this,"true",Toast.LENGTH_SHORT).show();
            }
        }



        subbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Toast.makeText(attendance.this,"get long lat",Toast.LENGTH_SHORT).show();


                String code = editText.getText().toString();
                if (!code.isEmpty()) {
                    Boolean val;

                    LocationManager locationManager1 = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    val = locationManager1.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                    if (val == true) {
                        Toast.makeText(attendance.this, "get long lat", Toast.LENGTH_SHORT).show();
                        setlonglat();
//                        Log.d("longitude",s1);
//                        Log.d("Latitude",s2);


                    } else {
                        Toast.makeText(attendance.this, "chalak bro ha , chalak", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(attendance.this);
                        builder.setTitle("engineer hu bhai")
                                .setMessage("Your location was not on so we redirecting u to ur main page , THANK YOU !!")
                                .setPositiveButton("yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                startActivity(new Intent(attendance.this, info.class));
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
                else{
                    Toast.makeText(attendance.this, "Enter the examination code", Toast.LENGTH_SHORT).show();
                }
            }

        });

        }

    @SuppressLint("MissingPermission")
    private void setlonglat() {
        t1 = findViewById(R.id.long_txt);
        t2 = findViewById(R.id.lat_txt);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        double top_left_lat=22.570512 , top_left_longi=88.508759;
                        double bottom_left_lat = 22.570249 , bottom_left_longi = 88.508640;
                        double top_right_lat = 22.570251 , top_right_longi=88.509782;
                        double bottom_right_lat = 22.569947 , bottom_right_longi = 88.509663;
                        // Got last known location. In some rare situations this can be null.

                        longi = location.getLongitude();
                        lati = location.getLatitude();
                        s1 = String.valueOf(longi);
                        s2 = String.valueOf(lati);
                        t1.setText(s1);
                        t2.setText(s2);


                        Toast.makeText(attendance.this,"Yout attendance will be marked",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(attendance.this,gform.class);
                        startActivity(intent);
//                        finish();

//                        if(longi >= top_left_longi && longi <= top_right_longi){
//                            if(lati >=bottom_right_lat && lati<= bottom_left_lat){
//                                Toast.makeText(attendance.this,"ur attendance will be marked",Toast.LENGTH_SHORT).show();
//                            }
//                            else{
//                                Toast.makeText(attendance.this,"Too far from college",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        else{
//                            Toast.makeText(attendance.this,"Too far from college",Toast.LENGTH_SHORT).show();
//                        }




                    }
                });
    }
}