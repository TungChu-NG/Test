package com.example.test.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.test.Model.ChiTietSanPham.ModelChiTietSanPham;

import com.example.test.Model.GioHang.ModelGioHang;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterLogicChiTietSanPham  implements  IPresenterChiTietSanPham{

   ViewChiTietSanPham viewChiTietSanPham;
   ModelChiTietSanPham modelChiTietSanPham;
   ModelGioHang modelGioHang;

    public PresenterLogicChiTietSanPham() {
        modelGioHang = new ModelGioHang();
    }

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();

        modelGioHang =new ModelGioHang();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSanPham("LaySanPhamVsChitietTheoMaSP","CHITIETSANPHAM",masp);
        if(sanPham.getMASP() > 0){
            String[] linkhinhanh = sanPham.getANHNHO().split(",");
            viewChiTietSanPham.HienSliderSanPham(linkhinhanh);
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
        }
    }

    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        modelGioHang.MoKetNoiSQL(context);
        boolean kiemtra = modelGioHang.ThemGioHang(sanPham);
        if(kiemtra){
            viewChiTietSanPham.ThemGioHangThanhCong();
        }else {
            viewChiTietSanPham.ThemGioHangThatBai();
        }

    }


    public int DemSanPhamCoTrongGioHang(Context context) {
      modelGioHang.MoKetNoiSQL(context);

      List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
      int dem = sanPhamList.size();

      return dem;
    }

}
