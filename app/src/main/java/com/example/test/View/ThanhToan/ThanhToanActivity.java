package com.example.test.View.ThanhToan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.MainActivity;
import com.example.test.Model.ObjectClass.ChiTietHoaDon;
import com.example.test.Model.ObjectClass.ChiTietSanPham;
import com.example.test.Model.ObjectClass.HoaDon;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements ViewThanhToan,View.OnClickListener {

    Toolbar toolbar;
    EditText edTenNguoiNhan,edDiaChi,edSoDT;
    ImageButton imNhanTienKhiThanhToan,imChuyenKhoan;
    TextView txtNhanTienKhiGiaoHang,txtChuyenKhoan;
    Button btnThanhToan;
    CheckBox cbThoaThuan;

    int chonHinhThuc = 0;

    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        toolbar = findViewById(R.id.toolbar);
        edTenNguoiNhan = findViewById(R.id.edTenNguoiNhan);
        edDiaChi = findViewById(R.id.edDiaChi);
        edSoDT = findViewById(R.id.edSoDT);
        imNhanTienKhiThanhToan = findViewById(R.id.imNhanTienKhiGiaoHang);
        imChuyenKhoan = findViewById(R.id.imChuyenKhoan);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        cbThoaThuan = findViewById(R.id.cbThoaThuan);

        txtNhanTienKhiGiaoHang = findViewById(R.id.txtNhanTienKhiGiaoHang);
        txtChuyenKhoan = findViewById(R.id.txtChuyenKhoan);

        presenterLogicThanhToan = new PresenterLogicThanhToan(this,this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang();
        setSupportActionBar(toolbar);

        btnThanhToan.setOnClickListener(this);
        imNhanTienKhiThanhToan.setOnClickListener(this);
        imChuyenKhoan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnThanhToan:
                String tennguoinhan = edTenNguoiNhan.getText().toString();
                String sodt = edSoDT.getText().toString();
                String diachi = edDiaChi.getText().toString();
                if(tennguoinhan.trim().length() > 0 && sodt.trim().length() > 0 && diachi.trim().length() > 0){
                    if(cbThoaThuan.isChecked()){
                        HoaDon hoaDon = new HoaDon();
                        hoaDon.setTenNguoiNhan(tennguoinhan);
                        hoaDon.setSoDT(sodt);
                        hoaDon.setDiaChi(diachi);
                        hoaDon.setChuyenKhoan(chonHinhThuc);
                        hoaDon.setChiTietHoaDonList(chiTietHoaDons);

                        presenterLogicThanhToan.ThemHoaDon(hoaDon);

                    }else {
                        Toast.makeText(this, "Bạn chưa chấp nhận thỏa thuận!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Bạn chưa nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imNhanTienKhiGiaoHang:
                ChonHinhThucThanhToan(txtNhanTienKhiGiaoHang,txtChuyenKhoan);
                chonHinhThuc = 0;
                break;
            case R.id.imChuyenKhoan:
                ChonHinhThucThanhToan(txtChuyenKhoan,txtNhanTienKhiGiaoHang);
                chonHinhThuc = 1;
                break;
        }
    }


    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
        Intent iMainActivity = new Intent(ThanhToanActivity.this, MainActivity.class);
        startActivity(iMainActivity);
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
    }

    private void ChonHinhThucThanhToan(TextView txtDuocChonMau,TextView txtHuyChonMau){
        txtDuocChonMau.setTextColor(getIdColor(R.color.Orange));
        txtHuyChonMau.setTextColor(getIdColor(R.color.black));
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

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {

        for(int i =0 ;i<sanPhamList.size();i++){
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMaSP(sanPhamList.get(i).getMASP());
            chiTietHoaDon.setSoLuong(sanPhamList.get(i).getSOLUONG());

            chiTietHoaDons.add(chiTietHoaDon);
        }
    }
}