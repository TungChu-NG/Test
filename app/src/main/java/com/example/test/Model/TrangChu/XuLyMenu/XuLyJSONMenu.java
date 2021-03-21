package com.example.test.Model.TrangChu.XuLyMenu;

import android.util.Log;

import com.example.test.ConnectInternet.DownloadJSON;
import com.example.test.MainActivity;
import com.example.test.Model.ObjectClass.LoaiSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class XuLyJSONMenu {

    public List<LoaiSanPham> ParseJSONMenu(String dulieujson)  {
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();

        try {
            //Log.d("kiemtra",dulieujson);
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisanpham.length();
            for (int i=0; i<count;i++){
                JSONObject value = loaisanpham.getJSONObject(i);

                LoaiSanPham dataLoaiSanPham = new LoaiSanPham();
                dataLoaiSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                dataLoaiSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                dataLoaiSanPham.setTENLOAISP(value.getString("TENLOAISP"));

                loaiSanPhamList.add(dataLoaiSanPham);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return loaiSanPhamList;
    }

    public List<LoaiSanPham> LayLoaiSanPhamTheoMaLoai(int maloaisp){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON="";

        //String duongdan = "http://192.168.42.44:7882/webapp/loaisanpham.php";
        //String duongdan = "http://192.168.108.108:7882/webapp/loaisanpham.php";
        String duongdan = MainActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachMenu");


        HashMap<String,String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha",String.valueOf(maloaisp));
        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParseJSONMenu(dataJSON);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return loaiSanPhamList;
    }

}
