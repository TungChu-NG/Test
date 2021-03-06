 package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TableLayout;

import com.example.test.Adapter.ExpandAdapter;
import com.example.test.Adapter.ViewPagerAdapter;
import com.example.test.Model.DangKyDangNhap.ModelDangNhap;
import com.example.test.Model.ObjectClass.LoaiSanPham;
import com.example.test.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.test.View.DangNhap.DangNhapActivity;
import com.example.test.View.TrangChu.ViewXuLyMenu;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

 public class MainActivity extends AppCompatActivity implements ViewXuLyMenu {

     public static final String SERVER_NAME ="http://192.168.0.108:7882/webapp/loaisanpham.php";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    ModelDangNhap modelDangNhap;
    MenuItem itemDangNhap,itemDangXuat;
    Menu menu;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        expandableListView = findViewById(R.id.epMenu);



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        modelDangNhap = new ModelDangNhap();
        PresenterLogicXuLyMenu logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        logicXuLyMenu.LayDanhSachMenu();
    }

     public  String tennv ="";
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        this.menu=menu;

        itemDangNhap = menu.findItem(R.id.itDangNhap);
        itemDangXuat = menu.findItem(R.id.itDangXuat);


        tennv= modelDangNhap.CachedDangNhap(this);


        if(!tennv.equals("")){
            itemDangNhap.setTitle(tennv);
        }

        if(!tennv.equals("")){
            itemDangXuat.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        int id = item.getItemId();
        switch (id){
            case R.id.itDangNhap:
                if(modelDangNhap.CachedDangNhap(this).equals("")){
                Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                startActivity(iDangNhap);
                }
                break;
            case R.id.itDangXuat:
                if(!modelDangNhap.CachedDangNhap(this).equals(null)){
                    modelDangNhap.UpdateCachedDangNhap(this,"");
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);

                }
                break;
        }
        return true;
    }

     @Override
     public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamsList) {
         ExpandAdapter adapter = new ExpandAdapter(this,loaiSanPhamsList);
         expandableListView.setAdapter(adapter);
         adapter.notifyDataSetChanged();
     }
 }