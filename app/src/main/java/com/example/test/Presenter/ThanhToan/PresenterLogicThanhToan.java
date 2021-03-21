package com.example.test.Presenter.ThanhToan;

import android.content.Context;

import com.example.test.Model.GioHang.ModelGioHang;
import com.example.test.Model.ObjectClass.HoaDon;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.Model.ThanhToan.ModelThanhToan;
import com.example.test.View.ThanhToan.ViewThanhToan;

import java.util.List;

public class PresenterLogicThanhToan implements IPresenterThanhToan {

    ViewThanhToan viewThanhToan;
    ModelThanhToan modelThanhToan;
    ModelGioHang modelGioHang;
    List<SanPham> sanPhamList;


    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan, Context context) {
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = false;
         kiemtra = modelThanhToan.ThemHoaDon(hoaDon);
        if(kiemtra){
            viewThanhToan.DatHangThatBai();

        }else {
            viewThanhToan.DatHangThanhCong();
            int dem = sanPhamList.size();
            for(int i= 0; i < dem; i++){
                modelGioHang.DeleteSanPhamTrongGioHang(sanPhamList.get(i).getMASP());
            }
        }
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang() {
       sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        viewThanhToan.LayDanhSachSanPhamTrongGioHang(sanPhamList);
    }
}
