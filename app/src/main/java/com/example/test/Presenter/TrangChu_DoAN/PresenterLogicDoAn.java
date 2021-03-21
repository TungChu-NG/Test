package com.example.test.Presenter.TrangChu_DoAN;

import com.example.test.Model.ObjectClass.DoAn;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.Model.ObjectClass.ThuongHieu;
import com.example.test.Model.TrangChu_DoAn.ModelDoAn;
import com.example.test.View.ViewDoAn;

import java.util.ArrayList;
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
        List<DoAn> doAns = new ArrayList<>();
        List<ThuongHieu> thuongHieuList = modelDoAn.LayDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon","DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList = modelDoAn.LayDanhSachSanPhamTOP("LAYDANHSACHTOPMONVIET","TOPMONVIET");

        DoAn doAn = new DoAn();
        doAn.setThuongHieus(thuongHieuList);
        doAn.setSanPhams(sanPhamList);
        doAn.setTentopnoibat("Thương hiệu lớn");
        doAn.setTennoibat("Top các món VIỆT");
        doAn.setThuonghieu(true);
        doAns.add(doAn);


        List<ThuongHieu> spNhatList = modelDoAn.LayDanhSachThuongHieuLon("LayDanhSachMonNhat","DANHSACHMONNHAT");
        List<SanPham> sanPhamMonNhatList = modelDoAn.LayDanhSachSanPhamTOP("LayDanhSachTopMonNhat","TOPMONNHAT");

        DoAn doAn1 = new DoAn();
        doAn1.setThuongHieus(spNhatList);
        doAn1.setSanPhams(sanPhamMonNhatList);
        doAn1.setTentopnoibat("Món NHẬT");
        doAn1.setTennoibat("Top các món NHẬT");
       doAn1.setThuonghieu(false);
        doAns.add(doAn1);



        if(thuongHieuList.size() > 0 && sanPhamList.size()> 0 && spNhatList.size()>0 && sanPhamMonNhatList.size()>0){
            viewDoAn.HienThiDanhSach(doAns);
        }

    }

//    @Override
//    public void LayDanhSachLogoThuongHieu() {
//        List<ThuongHieu> thuongHieuList = modelDoAn.LayDanhSachThuongHieuLon("LayLogoThuongHieuLon","DANHSACHTHUONGHIEU");
//        if(thuongHieuList.size() > 0){
//            viewDoAn.HienThiLoGoThuongHieu(thuongHieuList);
//        }else{
//            viewDoAn.LoiLayDuLieu();
//        }
//    }
}
