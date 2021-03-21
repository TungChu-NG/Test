package com.example.test.Model.ChiTietSanPham;

import com.example.test.ConnectInternet.DownloadJSON;
import com.example.test.MainActivity;
import com.example.test.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.test.Model.ObjectClass.ChiTietSanPham;
import com.example.test.Model.ObjectClass.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelChiTietSanPham {

    public SanPham LayChiTietSanPham(String tenham, String tenmang, int masp){
        SanPham sanPham = new SanPham();
        List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON="";

         //String duongdan = "http://192.168.42.44:7882/webapp/loaisanpham.php";
       // String duongdan = "http://192.168.108.108:7882/webapp/loaisanpham.php";
        String duongdan = MainActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);

        HashMap<String,String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp",String.valueOf(masp));



        attrs.add(hsHam);
        attrs.add(hsMaSP);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);

            int count = jsonArrayDanhSachSanPham.length();
            for (int i = 0 ; i<count ;i++){

                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));

                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHNHO(object.getString("ANHNHO"));
                sanPham.setSOLUONG(object.getInt("SOLUONG"));
                sanPham.setTHONGTIN(object.getString("THONGTIN"));
                sanPham.setMALOAISP(object.getInt("MALOAISP"));
                sanPham.setMATHUONGHIEU(object.getInt("MATHUONGHIEU"));
                sanPham.setMANV(object.getInt("MANV"));
                sanPham.setTENNV(object.getString("TENNV"));
                sanPham.setLUOTMUA(object.getInt("LUOTMUA"));

                JSONArray jsonArrayThongSo = object.getJSONArray("THONGSOKYTHUAT");

                for (int j= 0 ; j<jsonArrayThongSo.length();j++){
                    JSONObject jsonObject1 = jsonArrayThongSo.getJSONObject(j);

                    for(int k = 0; k<jsonObject1.names().length();k++){
                        String tenchitiet = jsonObject1.names().getString(k);

                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTENCHITIET(tenchitiet);
                        chiTietSanPham.setGIATRI(jsonObject1.getString(tenchitiet));
                        chiTietSanPhams.add(chiTietSanPham);
                    }
                }

                sanPham.setChiTietSanPhamList(chiTietSanPhams);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPham;

    }
}

