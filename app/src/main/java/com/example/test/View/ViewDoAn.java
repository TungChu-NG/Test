package com.example.test.View;

import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.Model.ObjectClass.ThuongHieu;

import java.util.List;

public interface ViewDoAn {
    void HienThiDanhSach(List<ThuongHieu> thuongHieus, List<SanPham> sanPhams);
}
