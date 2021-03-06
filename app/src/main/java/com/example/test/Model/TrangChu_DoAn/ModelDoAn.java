package com.example.test.Model.TrangChu_DoAn;

import com.example.test.ConnectInternet.DownloadJSON;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.Model.ObjectClass.ThuongHieu;
import com.example.test.Model.TrangChu.XuLyMenu.XuLyJSONMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDoAn {

    public List<SanPham> LayDanhSachSanPhamTOP(){
        List<SanPham> sanPhams = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON="";

        String duongdan = "http://192.168.0.108:7882/webapp/loaisanpham.php";

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LAYDANHSACHTOPMONVIET");



        attrs.add(hsHam);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray("TOPMONVIET");

            int count = jsonArrayDanhSachSanPham.length();
            for (int i = 0 ; i<count ;i++){
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));

                sanPhams.add(sanPham);

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhams;

    }
    public List<ThuongHieu> LayDanhSachThuongHieuLon(){
        List<ThuongHieu> thuongHieuList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON="";

        String duongdan = "http://192.168.0.108:7882/webapp/loaisanpham.php";

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachCacThuongHieuLon");



        attrs.add(hsHam);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachThuongHieu = jsonObject.getJSONArray("DANHSACHTHUONGHIEU");

            int count = jsonArrayDanhSachThuongHieu.length();
            for (int i = 0 ; i<count ;i++){
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object = jsonArrayDanhSachThuongHieu.getJSONObject(i);

                thuongHieu.setMATHUONGHIEU(object.getInt("MATHUONGHIEU"));
                thuongHieu.setTENTHUONGHIEU(object.getString("TENTHUONGHIEU"));
                thuongHieu.setHINHTHUONGHIEU(object.getString("HINHTHUONGHIEU"));

                thuongHieuList.add(thuongHieu);

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thuongHieuList;
    }
}
