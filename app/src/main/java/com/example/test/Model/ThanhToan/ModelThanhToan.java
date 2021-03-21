package com.example.test.Model.ThanhToan;

import android.util.Log;

import com.example.test.ConnectInternet.DownloadJSON;
import com.example.test.MainActivity;
import com.example.test.Model.ObjectClass.ChiTietHoaDon;
import com.example.test.Model.ObjectClass.HoaDon;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelThanhToan {

    public boolean ThemHoaDon(HoaDon hoaDon){
        String duongdan = MainActivity.SERVER_NAME;
        boolean kiemtra = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","ThemHoaDon");

        List<ChiTietHoaDon> chiTietHoaDonList = hoaDon.getChiTietHoaDonList();

        //chuyen dinh dang json
        String chuoijson = "{\"DANHSACHSANPHAM\" :[ ";
        for (int i=0; i<chiTietHoaDonList.size();i++){
            chuoijson += "{";
            chuoijson += "\"masp\" : " + chiTietHoaDonList.get(i).getMaSP() + ",";
            chuoijson += "\"soluong\" : " + chiTietHoaDonList.get(i).getSoLuong();

            if(i==chiTietHoaDonList.size() -1 ){
                chuoijson += "}";
            }else{
                chuoijson += "},";
            }

        }

        chuoijson += "]}";
        Log.d("chuoijson",chuoijson);


        HashMap<String,String> hsDanhSachSanPham = new HashMap<>();
        hsDanhSachSanPham.put("danhsachsanpham",chuoijson);

        HashMap<String,String> hsTenNguoiNhan = new HashMap<>();
        hsTenNguoiNhan.put("tennguoinhan",hoaDon.getTenNguoiNhan());

        HashMap<String,String> hsSoDT = new HashMap<>();
        hsSoDT.put("sodt",String.valueOf(hoaDon.getSoDT()));

        HashMap<String,String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("diachi", hoaDon.getDiaChi());

        HashMap<String,String> hsChuyenKhoan = new HashMap<>();
        hsChuyenKhoan.put("chuyenkhoan", String.valueOf(hoaDon.getChuyenKhoan()));

        attrs.add(hsHam);
        attrs.add(hsTenNguoiNhan);
        attrs.add(hsSoDT);
        attrs.add(hsDiaChi);
        attrs.add(hsChuyenKhoan);
       attrs.add(hsDanhSachSanPham);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            Log.d("kiemtra",ketqua);
            if(ketqua.equals("true")){
                kiemtra = true;
            }else{
                kiemtra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }

}
