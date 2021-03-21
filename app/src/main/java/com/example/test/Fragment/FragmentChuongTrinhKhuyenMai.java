package com.example.test.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test.Adapter.AdapterDanhSachKhuyenMai;
import com.example.test.Model.ObjectClass.KhuyenMai;
import com.example.test.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.test.R;
import com.example.test.View.KhuyenMai.ViewKhuyenMai;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FragmentChuongTrinhKhuyenMai extends Fragment implements ViewKhuyenMai {


    LinearLayout lnHinhKhuyenMai;
    RecyclerView recyclerViewDanhSachKhuyenMai;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    public FragmentChuongTrinhKhuyenMai() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chuong_trinh_khuyen_mai, container, false);
        lnHinhKhuyenMai = view.findViewById(R.id.lnHinhKhuyenMai);
        recyclerViewDanhSachKhuyenMai=view.findViewById(R.id.recyclerDanhSachKhuyenMai);

        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDanhSachKhuyenMai();
        return view;
    }

    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList) {

       int demhinh = khuyenMaiList.size();
       if(demhinh > 5){
           demhinh =5;
       }else {
           demhinh = khuyenMaiList.size();
       }
       lnHinhKhuyenMai.removeAllViews();

        for (int i= 0 ; i< demhinh; i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);

            layoutParams.setMargins( 0,10,0,10);
            imageView.setLayoutParams(layoutParams);

            Picasso.with(getContext()).load(khuyenMaiList.get(i).getHINHKHUYENMAI()).resize(720,200).into(imageView);

            lnHinhKhuyenMai.addView(imageView);
        }
        AdapterDanhSachKhuyenMai adapterDanhSachKhuyenMai = new AdapterDanhSachKhuyenMai(getContext(),khuyenMaiList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewDanhSachKhuyenMai.setLayoutManager(layoutManager);


        recyclerViewDanhSachKhuyenMai.setAdapter(adapterDanhSachKhuyenMai);
        adapterDanhSachKhuyenMai.notifyDataSetChanged();

    }
}