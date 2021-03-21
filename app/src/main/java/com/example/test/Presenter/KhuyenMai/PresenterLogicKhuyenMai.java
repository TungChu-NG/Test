package com.example.test.Presenter.KhuyenMai;

import com.example.test.Model.KhuyenMai.ModelKhuyenMai;
import com.example.test.Model.ObjectClass.KhuyenMai;
import com.example.test.View.KhuyenMai.ViewKhuyenMai;

import java.util.List;

public class PresenterLogicKhuyenMai implements  IPresenterKhuyenMai{


    ViewKhuyenMai viewKhuyenMai;
    ModelKhuyenMai modelKhuyenMai;

    public PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai) {
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai = new ModelKhuyenMai();
    }

    @Override
    public void LayDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList  = modelKhuyenMai.LayDanhSachKhuyenMai("LayDanhSachKhuyenMai","DANHSACHKHUYENMAI");


        if(khuyenMaiList.size() > 0){
            viewKhuyenMai.HienThiDanhSachKhuyenMai(khuyenMaiList);
        }

    }
}
