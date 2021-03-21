package com.example.test.View.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Adapter.AdapterViewPagerSlider;
import com.example.test.MainActivity;
import com.example.test.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.test.Model.ObjectClass.ChiTietSanPham;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.example.test.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.test.R;
import com.example.test.View.GioHang.GioHangActivity;
import com.example.test.View.ThanhToan.ThanhToanActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener, View.OnClickListener {

    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView[] txtDauCham;
    LinearLayout layoutDots;
    List<Fragment> fragmentList;
    TextView txtGiamGia,txtTenSanPham,txtTenGiaTien,txtTenCHDongGoi,txtThongTinChiTiet,txtGioHang;
    Toolbar toolbar;
    ImageView imXemThemChiTiet;
    ImageButton imThemGioHang;
    Boolean kiemtraXoChiTiet =false;
    LinearLayout lnThongSo;
    SanPham sanPhamGioHang;
    Menu menu;
    int masp;
    boolean onPause = false;
    Button btnMuaNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        viewPager = findViewById(R.id.viewpagerSlider);
        layoutDots = findViewById(R.id.layoutDots);
        txtTenSanPham = findViewById(R.id.txtTenSanPham);
        txtTenGiaTien = findViewById(R.id.txtGiaTien);
        txtTenCHDongGoi =  findViewById(R.id.txtTenCHDongGoi);
        txtThongTinChiTiet = findViewById(R.id.txtThongTinChiTiet);
        lnThongSo = findViewById(R.id.lnThongSoKyThuat);
        imThemGioHang= findViewById(R.id.imThemGioHang);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);
        txtGiamGia = findViewById(R.id.txtGiamGia);
        btnMuaNgay.setOnClickListener(this);

        imXemThemChiTiet = findViewById(R.id.imXemThemChiTiet);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        masp = getIntent().getIntExtra("masp",0);

        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);
        imThemGioHang.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        this.menu = menu;

        MenuItem iGiohang = menu.findItem(R.id.itemGioHang);
        FrameLayout giaoDienCustomGioHang = (FrameLayout) MenuItemCompat.getActionView(iGiohang);
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(iGioHang);

            }
        });

        txtGioHang = (TextView) giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
        return true;
    }

    @Override
    public void HienThiChiTietSanPham(SanPham sanPham) {

        masp = sanPham.getMASP();
        sanPhamGioHang =sanPham;

        sanPhamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());

        txtTenSanPham.setText(sanPham.getTENSP());

        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();
        int giatien = sanPham.getGIA();

        if(chiTietKhuyenMai != null){

            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();

            if(phantramkm != 0) {
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(giatien);

                txtGiamGia.setVisibility(View.VISIBLE);
                txtGiamGia.setPaintFlags(txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txtGiamGia.setText(gia + " VNĐ");
                giatien = giatien *  phantramkm / 100;
            }

        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        txtTenGiaTien.setText(gia+" VNĐ");
        txtTenCHDongGoi.setText(sanPham.getTENNV());

        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,100));

        if(sanPham.getTHONGTIN().length() < 100){
            imXemThemChiTiet.setVisibility(View.GONE);
        }else {
            imXemThemChiTiet.setVisibility(View.VISIBLE);
            imXemThemChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kiemtraXoChiTiet = !kiemtraXoChiTiet;
                    if(kiemtraXoChiTiet){
                        //sau khi xo chi tiet load full thong tin chi tiet
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN());
                        imXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_baseline_keyboard_arrow_up_24));
                        lnThongSo.setVisibility(View.VISIBLE);
                        HienThiThongSo(sanPham);
                    }else {
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0,100));
                        imXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_baseline_keyboard_arrow_down_24));
                        lnThongSo.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    private  void HienThiThongSo(SanPham sanPham){
        List<ChiTietSanPham> chiTietSanPhams = sanPham.getChiTietSanPhamList();
        for(int i= 0;i<chiTietSanPhams.size();i++){
            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            //Log.d("chitiet",chiTietSanPhams.get(i).getTENCHITIET());

            TextView txtTenThongSo =new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtTenThongSo.setText(chiTietSanPhams.get(i).getTENCHITIET());
            txtTenThongSo.setTextColor(getIdColor(R.color.black));

            TextView txtGiaTriThongSo =new TextView(this);
            txtGiaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtGiaTriThongSo.setText(chiTietSanPhams.get(i).getGIATRI());
            txtGiaTriThongSo.setTextColor(getIdColor(R.color.black));

            lnChiTiet.addView(txtTenThongSo);
            lnChiTiet.addView(txtGiaTriThongSo);

            lnThongSo.addView(lnChiTiet);
        }
    }



    @Override
    public void HienSliderSanPham(String[] linkhinhsanpham) {
        fragmentList = new ArrayList<>();

        for (int i=0; i<linkhinhsanpham.length;i++){
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh",MainActivity.SERVER + linkhinhsanpham[i]);

            fragmentSliderChiTietSanPham.setArguments(bundle);

            fragmentList.add(fragmentSliderChiTietSanPham);
        }

        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();

        ThemDauChamSlider(0);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
    }

    @Override
    public void ThemGioHangThatBai() {
        Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
    }

    private void ThemDauChamSlider(int viTriHienTai){
        txtDauCham = new TextView[fragmentList.size()];
        layoutDots.removeAllViews();

        for(int i=0;i<fragmentList.size();i++){
            txtDauCham[i] = new TextView(this);
            // thiet lap dau Cham
            txtDauCham[i].setText(Html.fromHtml("&#8226;"));
            txtDauCham[i].setTextSize(40);
            txtDauCham[i].setTextColor(getIdColor(R.color.colorSliderInActivie));

            layoutDots.addView(txtDauCham[i]);
        }

        txtDauCham[viTriHienTai].setTextColor(getIdColor(R.color.bgToolbar));

    }

    private int getIdColor(int idColor){
        // kiểm tra phiên bản android
        int color = 0;
        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this,idColor);
        }else{
            color = getResources().getColor(idColor);
        }
        return color;
    }

    private Drawable getHinhChiTiet(int idDrawable){
        Drawable drawable;
        if(Build.VERSION.SDK_INT > 21){
            drawable = ContextCompat.getDrawable(this,idDrawable);
        }else {
            drawable = getResources().getDrawable(idDrawable);
        }
        return drawable;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDauChamSlider(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imThemGioHang:
                Fragment fragment = fragmentList.get(viewPager.getCurrentItem());
                View view = fragment.getView();
                ImageView imageView = view.findViewById(R.id.imHinhSlider);
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinSPGioHang  = byteArrayOutputStream.toByteArray();

                sanPhamGioHang.setHinhgiohang(hinSPGioHang);
                sanPhamGioHang.setSOLUONG(1);
                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang,this);
                break;
            case R.id.btnMuaNgay:
                Fragment fragment1 = fragmentList.get(viewPager.getCurrentItem());
                View view1 = fragment1.getView();
                ImageView imageView1 = view1.findViewById(R.id.imHinhSlider);
                Bitmap bitmap1 = ((BitmapDrawable)imageView1.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream1);
                byte[] hinSPGioHang1  = byteArrayOutputStream1.toByteArray();

                sanPhamGioHang.setHinhgiohang(hinSPGioHang1);
                sanPhamGioHang.setSOLUONG(1);

                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang,this);

                Intent  iThanhToan = new Intent(ChiTietSanPhamActivity.this, ThanhToanActivity.class);
                startActivity(iThanhToan);
                break;

        }
    }
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
}