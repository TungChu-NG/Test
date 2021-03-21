package com.example.test.View.HienThiSanPhamTheoDanhMuc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.Adapter.AdapterTopMonVietDoAn;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSaPhamTheoDanhMuc;
import com.example.test.R;
import com.example.test.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener {

    RecyclerView recyclerView;
    Button btnThayDoiTrangThai;
    boolean dangGrid = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicHienThiSaPhamTheoDanhMuc saPhamTheoDanhMuc;
    int masp;
    boolean kiemtra;
    AdapterTopMonVietDoAn adapterTopMonVietDoAn;
    Toolbar  toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_san_pham_theo_danh_muc);

        recyclerView = findViewById(R.id.recyclerHienThiSanPham);
        btnThayDoiTrangThai = findViewById(R.id.btnThaydoitrangthai);
        toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
         masp = intent.getIntExtra("MALOAI",0);
        String tenloai = intent.getStringExtra("TENLOAI");
         kiemtra = intent.getBooleanExtra("KIEMTRA",false);

         saPhamTheoDanhMuc = new PresenterLogicHienThiSaPhamTheoDanhMuc(this);
        saPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp,kiemtra);

        btnThayDoiTrangThai.setOnClickListener(this);

        toolbar.setTitle(tenloai);
        setSupportActionBar(toolbar);

    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {

        if(dangGrid){
            layoutManager = new GridLayoutManager(HienThiSanPhamTheoDanhMucActivity.this,2);
            adapterTopMonVietDoAn = new AdapterTopMonVietDoAn(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_layout_topmonviet,sanPhamList);


        }else {
            layoutManager = new LinearLayoutManager(HienThiSanPhamTheoDanhMucActivity.this);
            adapterTopMonVietDoAn = new AdapterTopMonVietDoAn(HienThiSanPhamTheoDanhMucActivity.this,R.layout.custom_layout_list_top_doan,sanPhamList);

        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopMonVietDoAn);
        adapterTopMonVietDoAn.notifyDataSetChanged();

    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnThaydoitrangthai:
                dangGrid = !dangGrid;
                saPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp,kiemtra);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        return true;
    }
}