package com.example.test.Presenter.DangKy;

import com.example.test.Model.DangKyDangNhap.ModelDangKy;
import com.example.test.Model.ObjectClass.NhanVien;
import com.example.test.View.ViewDangKy;

public class PresenterLogicDangKy implements IPresenterDangKy{
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterLogicDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        modelDangKy = new ModelDangKy();
    }

    @Override
    public void ThucHienDangky(NhanVien nhanvien) {

        boolean kiemtra = modelDangKy.DangKyThanhVien(nhanvien);
        if(kiemtra){
            viewDangKy.DangKyThanhCong();
        }else {
            viewDangKy.DangKyThatBai();
        }

    }
}
