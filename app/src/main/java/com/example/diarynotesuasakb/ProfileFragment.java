package com.example.diarynotesuasakb;
/* Nama : Prasetyo Hade M. Winoto
   Kelas : IF-3
   NIM : 10119108
 */

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_profile_fragment, container, false);
    }
}