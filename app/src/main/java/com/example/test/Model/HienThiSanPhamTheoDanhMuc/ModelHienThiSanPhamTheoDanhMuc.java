package com.example.test.Model.HienThiSanPhamTheoDanhMuc;

import com.example.test.ConnectInternet.DownloadJSON;
import com.example.test.MainActivity;
import com.example.test.Model.ObjectClass.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelHienThiSanPhamTheoDanhMuc {

    public List<SanPham> LayDanhSachSanPhamTheoMaLoai(int masp,String tenmang,String tenham,int limit){
        List<SanPham> sanPhams = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON="";

        //String duongdan = "http://192.168.42.44:7882/webapp/loaisanpham.php";
        String duongdan = MainActivity.SERVER_NAME;
        //String duongdan = "http://192.168.108.108:7882/webapp/loaisanpham.php";

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);

        HashMap<String,String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("maloaisp",String.valueOf(masp));

        HashMap<String,String> hsLimits = new HashMap<>();
        hsLimits.put("limit",String.valueOf(limit));




        attrs.add(hsHam);
        attrs.add(hsMaLoai);
        attrs.add(hsLimits);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);

            int count = jsonArrayDanhSachSanPham.length();
            for (int i = 0 ; i<count ;i++){
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));
                sanPham.setANHNHO(object.getString("HINHSANPHAMNHO"));
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

}
