package com.example.test.Model.ObjectClass;

import android.widget.ImageView;

import java.util.List;

public class DoAn {
    List<ThuongHieu> thuongHieus;
    List<SanPham> sanPhams;
    String HinhSanPham;

    public String getHinhSanPham() {
        return HinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        HinhSanPham = hinhSanPham;
    }

    public List<ThuongHieu> getThuongHieus() {
        return thuongHieus;
    }

    public void setThuongHieus(List<ThuongHieu> thuongHieus) {
        this.thuongHieus = thuongHieus;
    }

    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }
}
