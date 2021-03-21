package com.example.test.View.ChiTietSanPham;

import com.example.test.Model.ObjectClass.SanPham;

public interface ViewChiTietSanPham {
    void HienThiChiTietSanPham(SanPham sanPham);
    void HienSliderSanPham(String[] linkhinhsanpham);

    void ThemGioHangThanhCong();
    void ThemGioHangThatBai();
}
