package com.example.test.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.R;


public class FragmentChuongTrinhKhuyenMai extends Fragment {



    public FragmentChuongTrinhKhuyenMai() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chuong_trinh_khuyen_mai, container, false);
        return view;
    }
}