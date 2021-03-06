package com.example.test.Model.DangKyDangNhap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.test.ConnectInternet.DownloadJSON;
import com.example.test.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangNhap {

    public  String CachedDangNhap(Context context){

             SharedPreferences luuDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
             String tennv = luuDangNhap.getString("tennv","");
             return tennv;
    }

    public void UpdateCachedDangNhap(Context context, String tennv){
        SharedPreferences luuDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = luuDangNhap.edit();
        editor.putString("tennv",tennv);

        editor.commit();
    }

    public  boolean KiemTraDangNhap(Context context,String tendangnhap, String matkhau){

        boolean kiemtra = false;
        String duongdan = "http://192.168.0.108:7882/webapp/loaisanpham.php";

        List<HashMap<String,String>> attrs = new ArrayList<>();
        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","KiemTraDangNhap");

        HashMap<String,String> hsTenDN = new HashMap<>();
        hsTenDN.put("tendangnhap",tendangnhap);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",matkhau);

        attrs.add(hsHam);
        attrs.add(hsTenDN);
        attrs.add(hsMatKhau);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            String ketqua = jsonObject.getString("ketqua");

            if(ketqua.equals("true")){
                kiemtra = true;
                String tennv = jsonObject.getString("tennv");
                UpdateCachedDangNhap(context,tennv);

            }else {
                kiemtra = false;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kiemtra;
    }


}
