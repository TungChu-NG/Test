package com.example.test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.R;
import com.example.test.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.example.test.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterTopMonVietDoAn extends RecyclerView.Adapter<AdapterTopMonVietDoAn.ViewHolderTopMonViet> {

    Context context;
    List<SanPham> sanPhamList;
    int layout;


    public AdapterTopMonVietDoAn(Context context,int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout =layout;
    }

    @NonNull
    @Override
    public ViewHolderTopMonViet onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout,parent,false);

        ViewHolderTopMonViet viewHolderTopMonViet = new ViewHolderTopMonViet(view);

        return viewHolderTopMonViet;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTopMonViet holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getANHLON()).resize(130,135).centerInside().into(holder.imTopMonVietDoAn);

        holder.txtTenSP.setText(sanPham.getTENSP());

        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();

        int giatien = sanPham.getGIA();

        if(chiTietKhuyenMai != null){

            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();

            NumberFormat numberFormat = new DecimalFormat("###,###");
            String gia = numberFormat.format(giatien);

            holder.txtGiamGia.setVisibility(View.VISIBLE);
            holder.txtGiamGia.setPaintFlags(holder.txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtGiamGia.setText(gia +" VNĐ");
            giatien = giatien *  phantramkm / 100;
        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);

        holder.txtGiaTien.setText(gia +" VNĐ");

        holder.cardView.setTag(sanPham.getMASP());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietSP = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSP.putExtra("masp", (int) v.getTag());
                context.startActivity(iChiTietSP);
            }
        });



    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolderTopMonViet extends RecyclerView.ViewHolder {
        ImageView imTopMonVietDoAn;
        TextView txtTenSP,txtGiaTien,txtGiamGia;
        CardView cardView;

        public ViewHolderTopMonViet(@NonNull View itemView) {
            super(itemView);

            imTopMonVietDoAn = itemView.findViewById(R.id.imgTopMonVietDoAn);
            txtTenSP = itemView.findViewById(R.id.txtTieuDeTopMonVietDoAn);
            txtGiaTien = itemView.findViewById(R.id.txtGiaMonVietDoAn);
            txtGiamGia = itemView.findViewById(R.id.txtGiamGiaMonViet);
            cardView =   itemView.findViewById(R.id.cardView);

        }
    }
}
