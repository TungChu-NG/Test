package com.example.test.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.Model.ObjectClass.NhanVien;
import com.example.test.Presenter.DangKy.PresenterLogicDangKy;
import com.example.test.R;
import com.example.test.View.ViewDangKy;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentDangKy extends Fragment implements ViewDangKy, View.OnClickListener, View.OnFocusChangeListener {

    Boolean kiemtraThongtin = false;
    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    EditText edHoTen,edMatKhau,edNhapLaiMK,edDiaChiEmail;
    TextInputLayout input_edHoTen,input_edMatKhau,input_edNhapLaiMk,input_edDiaChiEmail;

    public FragmentDangKy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dang_ky, container, false);

        btnDangKy = view.findViewById(R.id.btnDangKy);
        edHoTen = view.findViewById(R.id.edHoTenDK);
        edMatKhau = view.findViewById(R.id.edMatKhauDK);
        edNhapLaiMK = view.findViewById(R.id.edNhapLaiMatKhauDK);
        edDiaChiEmail = view.findViewById(R.id.edDiaChiEmailDK);
        input_edHoTen = view.findViewById(R.id.input_edHoTenDK);
        input_edDiaChiEmail = view.findViewById(R.id.input_edDiaChiEmailDK);
        input_edMatKhau = view.findViewById(R.id.input_edMatKhauDK);
        input_edNhapLaiMk = view.findViewById(R.id.input_edNhapLaiMatKhauDK);

        presenterLogicDangKy = new PresenterLogicDangKy(this);
        btnDangKy.setOnClickListener(this);


        edHoTen.setOnFocusChangeListener(this);
        edMatKhau.setOnFocusChangeListener(this);
        edNhapLaiMK.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);
        return view;
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDangKy:
                btnDangKy();
                break;
        }


    }
    private  void btnDangKy(){
        String hoten = edHoTen.getText().toString();
        String email = edDiaChiEmail.getText().toString();
        String matkhau = edMatKhau.getText().toString();
        String nhaplaiMk = edNhapLaiMK.getText().toString();


        if(kiemtraThongtin){

            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTenDN(email);
            nhanVien.setMatKhau(matkhau);
            nhanVien.setMaLoaiNV(2);

            presenterLogicDangKy.ThucHienDangky(nhanVien);
        }else{
            Log.d("kiemtra","Dang ky that bai");
        }


    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id){
            case R.id.edHoTenDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edHoTen.setErrorEnabled(true);
                        input_edHoTen.setError("Bạn chưa nhập mục này");
                        kiemtraThongtin = false;
                    }else{
                        input_edHoTen.setErrorEnabled(false);
                        input_edHoTen.setError("");
                        kiemtraThongtin = true;
                    }
                }
                break;
            case R.id.edDiaChiEmailDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();


                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edDiaChiEmail.setErrorEnabled(true);
                        input_edDiaChiEmail.setError("Bạn chưa nhập mục Email này");
                        kiemtraThongtin = false;
                    }else{
                        Boolean kiemtraemail =  Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if(!kiemtraemail){
                            input_edDiaChiEmail.setErrorEnabled(true);
                            input_edDiaChiEmail.setError("Không phải dạng Email");
                            kiemtraThongtin = false;
                        }else{
                            input_edDiaChiEmail.setErrorEnabled(false);
                            input_edDiaChiEmail.setError("");
                            kiemtraThongtin = true;
                        }
                    }
                }
                break;
            case R.id.edMatKhauDK:
                break;
            case R.id.edNhapLaiMatKhauDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    String matkhau = edMatKhau.getText().toString();
                    if(!chuoi.equals(matkhau)){
                        input_edNhapLaiMk.setErrorEnabled(true);
                        input_edNhapLaiMk.setError("Mật khẩu không trùng khớp");
                        kiemtraThongtin = false;
                    }else{
                        input_edNhapLaiMk.setErrorEnabled(false);
                        input_edNhapLaiMk.setError("");
                        kiemtraThongtin = true;
                    }
                }

                break;

        }
    }
}