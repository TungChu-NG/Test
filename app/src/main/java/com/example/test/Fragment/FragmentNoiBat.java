package com.example.test.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.Adapter.AdapterNoiBat;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentNoiBat extends Fragment {


    RecyclerView recyclerView;
    AdapterNoiBat adapterNoiBat;


    public FragmentNoiBat() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_noi_bat, container, false);
        recyclerView = view.findViewById(R.id.recyclerNoiBat);

        List<String> duLieu = new ArrayList<>();
        for(int i=0;i<10;i++){
            String ter = "Noi bat "+ i;
            duLieu.add(ter);
        }


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);

        adapterNoiBat = new AdapterNoiBat(getActivity(),duLieu);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNoiBat);

        adapterNoiBat.notifyDataSetChanged();
        return view;
    }
}