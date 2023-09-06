package com.example.connect_db;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class detailFragment extends Fragment {
    public static final String SHARED_PREFS="sharedPrefs";



    public detailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
//
        SharedPreferences sd1 = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences sd2 = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences sd3 = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences sd4 = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
      SharedPreferences sd5 = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences sd6 = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences sd7 = this.getActivity().getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);


//     String check = sd1.getString("uname","");
//      String naam = sd2.getString("key","");
        EditText n , s , r, er , y ,p ,e;
       n=view.findViewById(R.id.dn);
        s=view.findViewById(R.id.ds);
        r=view.findViewById(R.id.dr);
       er=view.findViewById(R.id.der);
        y=view.findViewById(R.id.dy);
        p=view.findViewById(R.id.dp);
        e=view.findViewById(R.id.de);
        String pname , psec , proll,penroll,pyear , ppass , pemail;
        pname = sd1.getString("key","");
        psec = sd2.getString("rs","");
        proll = sd3.getString("rr","");
        penroll = sd4.getString("uname","");
        pyear = sd5.getString("ry","");
        ppass = sd6.getString("rp","");
        pemail = sd7.getString("re","");

        n.setText(pname);
        s.setText(psec);
        r.setText(proll);
        er.setText(penroll);
        y.setText(pyear);
        p.setText(ppass);
        e.setText(pemail);

        return view;
    }





}
