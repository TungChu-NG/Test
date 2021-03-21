package com.example.test.View.GioHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test.Adapter.AdapterGioHang;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.Presenter.GioHang.PresenterLogicGioHang;
import com.example.test.R;
import com.example.test.View.ThanhToan.ThanhToanActivity;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang , View.OnClickListener {
    RecyclerView recyclerView;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;
    Button btnMuaNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        recyclerView = findViewById(R.id.recyclerGioHang);
        toolbar =findViewById(R.id.toolbar);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);
        toolbar.setTitle("Giỏ Hàng");

        setSupportActionBar(toolbar);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);

        btnMuaNgay.setOnClickListener(this);

    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this,sanPhamList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        int id =  v.getId();
        switch (id){
            case R.id.btnMuaNgay:
                Intent iThanhToan = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                startActivity(iThanhToan);
                break;
        }

    }
}