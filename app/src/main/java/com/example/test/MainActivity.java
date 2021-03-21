 package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
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
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Adapter.ExpandAdapter;
import com.example.test.Adapter.ViewPagerAdapter;
import com.example.test.Model.DangKyDangNhap.ModelDangNhap;
import com.example.test.Model.ObjectClass.LoaiSanPham;
import com.example.test.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.test.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.test.View.DangNhap.DangNhapActivity;
import com.example.test.View.GioHang.GioHangActivity;
import com.example.test.View.TimKiem.TimKiemActivity;
import com.example.test.View.TrangChu.ViewXuLyMenu;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

 public class MainActivity extends AppCompatActivity implements ViewXuLyMenu, AppBarLayout.OnOffsetChangedListener{


     //public static final String SERVER_NAME ="http://192.168.0.102:7882/webapp/loaisanpham.php";
     public static final String SERVER_NAME ="http://192.168.42.44:7882/webapp/loaisanpham.php";
    public static final String SERVER = "http://192.168.42.44:7882/webapp";
    //public static final String SERVER = "http://192.168.0.102:7882/webapp";

     DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    ModelDangNhap modelDangNhap;
    MenuItem itemDangNhap,itemDangXuat;
    Menu menu;

    TextView txtGioHang;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    boolean onPause = false;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        expandableListView = findViewById(R.id.epMenu);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        appBarLayout = findViewById(R.id.appbar);




        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        modelDangNhap = new ModelDangNhap();
        PresenterLogicXuLyMenu logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        logicXuLyMenu.LayDanhSachMenu();

        appBarLayout.addOnOffsetChangedListener(this);



    }

     public  String tennv ="";
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        this.menu=menu;
         MenuItem iGiohang = menu.findItem(R.id.itemGioHang);
        FrameLayout giaoDienCustomGioHang = (FrameLayout) MenuItemCompat.getActionView(iGiohang);

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent iGioHang = new Intent(MainActivity.this, GioHangActivity.class);
              startActivity(iGioHang);

            }
        });
        txtGioHang = (TextView) giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));


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

            case R.id.itSearch:
                Intent iTimKiem = new Intent(this, TimKiemActivity.class);
                startActivity(iTimKiem);
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




     // cap nhat lai so luong trong gio hang khi back

     @Override
     protected void onResume() {
         super.onResume();

         if(onPause){
             PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
             txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));

         }

     }

     @Override
     protected void onPause() {
         super.onPause();
         onPause =true;
     }

     @Override
     public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
         if(collapsingToolbarLayout.getHeight() + verticalOffset <=  1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)){
             LinearLayout linearLayout = (LinearLayout) appBarLayout.findViewById(R.id.lnSearch);
             linearLayout.animate().alpha(0).setDuration(200);

             MenuItem itSearch = menu.findItem(R.id.itSearch);
             itSearch.setVisible(true);

         }else{
             LinearLayout linearLayout = (LinearLayout) appBarLayout.findViewById(R.id.lnSearch);
             linearLayout.animate().alpha(1).setDuration(200);
             try{
                 MenuItem itSearch = menu.findItem(R.id.itSearch);
                 itSearch.setVisible(false);
             }catch (Exception e){

             }

         }
     }

     // xu ly su kien khi cuon an thanh tim kiem

 }