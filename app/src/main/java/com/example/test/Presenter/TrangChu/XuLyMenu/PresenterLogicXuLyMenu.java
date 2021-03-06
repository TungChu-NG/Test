package com.example.test.Presenter.TrangChu.XuLyMenu;

import com.example.test.ConnectInternet.DownloadJSON;
import com.example.test.MainActivity;
import com.example.test.Model.ObjectClass.LoaiSanPham;
import com.example.test.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.test.View.TrangChu.ViewXuLyMenu;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu{

    ViewXuLyMenu viewXuLyMenu;
    public  PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu){
        this.viewXuLyMenu=viewXuLyMenu;
    }
    @Override
    public void LayDanhSachMenu() {

        List<LoaiSanPham>  loaiSanPhamList;
        String dataJSON ="";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        //GET
        //String duongdan = "http://192.168.0.108:7882/webapp/loaisanpham.php?maloaicha=0";
        //DownloadJSON downloadJSON = new DownloadJSON(duongdan);

        //POST
        String duongdan = "http://192.168.0.108:7882/webapp/loaisanpham.php";

        HashMap<String,String>hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachMenu");

        HashMap<String,String>hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha","0");

        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);


        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParseJSONMenu(dataJSON);
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
