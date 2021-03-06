package com.example.test.Presenter.TrangChu_DoAN;

import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.Model.ObjectClass.ThuongHieu;
import com.example.test.Model.TrangChu_DoAn.ModelDoAn;
import com.example.test.View.ViewDoAn;

import java.util.List;

public class PresenterLogicDoAn implements IPresenterDoAn {

    ViewDoAn viewDoAn;
    ModelDoAn modelDoAn;

    public PresenterLogicDoAn(ViewDoAn viewDoAn) {
        this.viewDoAn = viewDoAn;
        modelDoAn = new ModelDoAn();
    }

    @Override
    public void LayDanhSachThuongDoAn() {
        List<ThuongHieu> thuongHieuList = modelDoAn.LayDanhSachThuongHieuLon();
        List<SanPham> sanPhamList = modelDoAn.LayDanhSachSanPhamTOP();
        if(thuongHieuList.size() > 0){
            viewDoAn.HienThiDanhSach(thuongHieuList,sanPhamList);
        }

    }
}
