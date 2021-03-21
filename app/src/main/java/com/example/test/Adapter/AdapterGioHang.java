package com.example.test.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.GioHang.ModelGioHang;
import com.example.test.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterGioHang  extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {
    Context  context;
    List<SanPham>  sanPhamList;
    ModelGioHang modelGioHang;
    int soluong;


    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    @NonNull
    @Override
    public AdapterGioHang.ViewHolderGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View  view = layoutInflater.inflate(R.layout.custom_layout_giohang,parent,false);

        return new ViewHolderGioHang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGioHang.ViewHolderGioHang holder,final int position) {
        final SanPham sanPham = sanPhamList.get(position);
        holder.txtTenTieuDeGioHang.setText(sanPham.getTENSP());


        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();

//        if(chiTietKhuyenMai != null){
//
//            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();
//            Log.d("kmpt",String.valueOf(phantramkm));
//            giahienkm = giatien *  phantramkm / 100;
//            giaht = giatien - giahienkm;
//            Log.d("giaht", String.valueOf(giaht));
//            NumberFormat numberFormat = new DecimalFormat("###,###");
//            String gia = numberFormat.format(giaht);
//
//
//
//            holder.txtGiaTienGioHang.setText(gia +" VNĐ");
//
//        }



            NumberFormat numberFormat = new DecimalFormat("###,###");
            String gia = numberFormat.format(sanPham.getGIA()).toString();
            holder.txtGiaTienGioHang.setText(gia+ " VNĐ");




        byte[] hinhsanpham = sanPham.getHinhgiohang();
        Bitmap bitmapHinhGioHang = BitmapFactory.decodeByteArray(hinhsanpham,0,hinhsanpham.length);
        holder.imHinhGioHang.setImageBitmap(bitmapHinhGioHang);

        holder.imXoaSanPhamGioHang.setTag(sanPham.getMASP());

        holder.imXoaSanPhamGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.MoKetNoiSQL(context);
                modelGioHang.DeleteSanPhamTrongGioHang((int)v.getTag());

                sanPhamList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.txtSoLuongSanPham.setText(String.valueOf(sanPham.getSOLUONG()));

       holder.imTangSoLuongSPGioHang.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                soluong =Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());
               int soluongtonkho = sanPham.getSOLUONGTONKHO();

                if(soluong < soluongtonkho){
                    soluong++;
                }else {
                    Toast.makeText(context, "Số lượng sản phẩm tồn kho đã hết", Toast.LENGTH_SHORT).show();
                }

               modelGioHang.UpdateSoLuongSanPhamGioHang(sanPham.getMASP(),soluong);
               holder.txtSoLuongSanPham.setText(String.valueOf(soluong));

           }
       });

        holder.imGiamSoLuongSPGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 soluong =Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());

                if(soluong > 1){
                    soluong--;
                }

                modelGioHang.UpdateSoLuongSanPhamGioHang(sanPham.getMASP(),soluong);
                holder.txtSoLuongSanPham.setText(String.valueOf(soluong));

            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        TextView txtGiaGiamGioHang,txtTenTieuDeGioHang,txtGiaTienGioHang,txtSoLuongSanPham;
        ImageView imHinhGioHang,imXoaSanPhamGioHang;
        ImageButton imTangSoLuongSPGioHang,imGiamSoLuongSPGioHang;

        public ViewHolderGioHang(@NonNull View itemView) {
            super(itemView);
            txtGiaGiamGioHang = itemView.findViewById(R.id.txtGiamGiaGioHang);
            txtTenTieuDeGioHang = itemView.findViewById(R.id.txtTieuDeGiohang);
            txtGiaTienGioHang = itemView.findViewById(R.id.txtGiaGioHang);
            imHinhGioHang = itemView.findViewById(R.id.imHinhGioHang);
            imXoaSanPhamGioHang = itemView.findViewById(R.id.imXoaSPGioHang);
            txtSoLuongSanPham =itemView.findViewById(R.id.txtSoLuongSanPham);
            imTangSoLuongSPGioHang = itemView.findViewById(R.id.imTangSoLuongSPTrongGioHang);
            imGiamSoLuongSPGioHang = itemView.findViewById(R.id.imGiamSoLuongSPTrongGioHang);
        }
    }
}
