package com.example.test.Model.KhuyenMai;

import com.example.test.ConnectInternet.DownloadJSON;
import com.example.test.MainActivity;
import com.example.test.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.test.Model.ObjectClass.KhuyenMai;
import com.example.test.Model.ObjectClass.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelKhuyenMai {

    public List<KhuyenMai> LayDanhSachKhuyenMai(String tenham, String tenmang){
        List<KhuyenMai> khuyenMaiList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON="";

        //String duongdan = "http://192.168.42.44:7882/webapp/loaisanpham.php";
        String duongdan = MainActivity.SERVER_NAME;
        //String duongdan = "http://192.168.108.108:7882/webapp/loaisanpham.php";

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);


        attrs.add(hsHam);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachKhuyenMai = jsonObject.getJSONArray(tenmang);

            int count = jsonArrayDanhSachKhuyenMai.length();
            for (int i = 0 ; i<count ;i++){
                KhuyenMai khuyenMai = new KhuyenMai();
                JSONObject object = jsonArrayDanhSachKhuyenMai.getJSONObject(i);

                khuyenMai.setMAKM(object.getInt("MAKM"));
                khuyenMai.setTENKM(object.getString("TENKM"));
                khuyenMai.setTENLOAISP(object.getString("TENLOAISP"));
                khuyenMai.setHINHKHUYENMAI(object.getString("HINHKHUYENMAI"));

                List<SanPham> sanPhamList = new ArrayList<>();

                JSONArray arrayDanhSachSanPham = object.getJSONArray("DANHSACHSANPHAM");
                int demsp = arrayDanhSachSanPham.length();
                for(int j = 0 ; j< demsp ; j++){
                    JSONObject objectSanPham = arrayDanhSachSanPham.getJSONObject(j);
                    SanPham sanPham = new SanPham();
                    sanPham.setMASP(objectSanPham.getInt("MASP"));
                    sanPham.setTENSP(objectSanPham.getString("TENSP"));
                    sanPham.setGIA(objectSanPham.getInt("GIA"));
                    sanPham.setANHLON(MainActivity.SERVER + objectSanPham.getString("ANHLON"));
                    sanPham.setANHNHO(objectSanPham.getString("ANHNHO"));

                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(objectSanPham.getInt("PHANTRAMKM"));

                    sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                    sanPhamList.add(sanPham);


                }

                khuyenMai.setDanhSachSanPhamKhuyenMai(sanPhamList);
                khuyenMaiList.add(khuyenMai);

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return khuyenMaiList;
    }
}
