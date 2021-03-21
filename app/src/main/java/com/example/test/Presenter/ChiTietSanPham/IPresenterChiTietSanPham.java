package com.example.test.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.test.Model.ObjectClass.SanPham;

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void ThemGioHang(SanPham sanPham, Context context);

}
