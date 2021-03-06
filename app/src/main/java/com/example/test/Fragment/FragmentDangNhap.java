package com.example.test.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.MainActivity;
import com.example.test.Model.DangKyDangNhap.ModelDangNhap;
import com.example.test.R;

public class FragmentDangNhap extends Fragment implements View.OnClickListener {

    Button btnDangnhap;
    EditText edTenDangNhap,edMatKhau;
    ModelDangNhap modelDangNhap;
    public FragmentDangNhap() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_nhap, container, false);

        modelDangNhap = new ModelDangNhap();
        btnDangnhap = view.findViewById(R.id.btDangNhap);
        edTenDangNhap = view.findViewById(R.id.edDiaChiDangNhap);
        edMatKhau = view.findViewById(R.id.edMatKhauDangNhap);
        btnDangnhap.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btDangNhap:
                String tendangnhap = edTenDangNhap.getText().toString();
                String matkhau =  edMatKhau.getText().toString();
            boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(),tendangnhap,matkhau);

            if(kiemtra){
                Intent iDangNhap = new Intent(getActivity(), MainActivity.class);
                startActivity(iDangNhap);
            }else{
                Toast.makeText(getActivity(),"Kiểm tra lại tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
            }

            break;
        }
    }
}