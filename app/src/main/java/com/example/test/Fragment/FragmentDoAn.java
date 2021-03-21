package com.example.test.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.Adapter.AdapterDoAn;
import com.example.test.Adapter.AdapterThuongHieuLonDoAn;
import com.example.test.Model.ObjectClass.DoAn;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.Model.ObjectClass.ThuongHieu;
import com.example.test.Presenter.TrangChu_DoAN.PresenterLogicDoAn;
import com.example.test.R;
import com.example.test.View.ViewDoAn;

import java.util.ArrayList;
import java.util.List;


public class FragmentDoAn extends Fragment implements ViewDoAn {
    RecyclerView recyclerView,recyclerTopThuongHieuLon;
    List<DoAn> doAnList;
    PresenterLogicDoAn presenterLogicDoAn;

    public FragmentDoAn() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_do_an, container, false);
        recyclerView = view.findViewById(R.id.recyclerDoAn);
       //recyclerTopThuongHieuLon=view.findViewById(R.id.rcvTopCacThuongHieuLon);
        presenterLogicDoAn = new PresenterLogicDoAn(this);

        doAnList = new ArrayList<>();

        presenterLogicDoAn.LayDanhSachThuongDoAn();
       //presenterLogicDoAn.LayDanhSachLogoThuongHieu();



        return view;
    }

    @Override
    public void HienThiDanhSach(List<DoAn> doAns) {

        doAnList=doAns;


        AdapterDoAn adapterDoAn = new AdapterDoAn(getContext(),doAnList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDoAn);

        adapterDoAn.notifyDataSetChanged();


    }

//    @Override
//    public void HienThiLoGoThuongHieu(List<ThuongHieu> thuongHieus) {
//        AdapterThuongHieuLonDoAn adapterThuongHieuLonDoAn = new AdapterThuongHieuLonDoAn(getContext(),thuongHieus);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2,RecyclerView.HORIZONTAL,false);
//
//        recyclerTopThuongHieuLon.setLayoutManager(layoutManager);
//        recyclerTopThuongHieuLon.setAdapter(adapterThuongHieuLonDoAn);
//        adapterThuongHieuLonDoAn.notifyDataSetChanged();
//    }
//
//    @Override
//    public void LoiLayDuLieu() {
//
//    }
}