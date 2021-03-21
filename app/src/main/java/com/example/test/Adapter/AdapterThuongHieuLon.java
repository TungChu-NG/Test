package com.example.test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.ObjectClass.ThuongHieu;
import com.example.test.R;
import com.example.test.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLon extends RecyclerView.Adapter<AdapterThuongHieuLon.ViewHolder> {
    Context context;
    List<ThuongHieu> thuongHieus;
    boolean kiemtra;
    public AdapterThuongHieuLon(Context context, List<ThuongHieu> thuongHieus,boolean kiemtra){
        this.context=context;
        this.thuongHieus=thuongHieus;
        this.kiemtra =kiemtra;
    }

    @NonNull
    @Override
    public AdapterThuongHieuLon.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_thuonghieulon,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterThuongHieuLon.ViewHolder holder, int position) {

        final ThuongHieu thuongHieu = thuongHieus.get(position);

        holder.txtTieuDeThuongHieu.setText(thuongHieu.getTENTHUONGHIEU());



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHienThiSPTheoDanhMuc = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);
                iHienThiSPTheoDanhMuc.putExtra("MALOAI",thuongHieu.getMATHUONGHIEU());
                iHienThiSPTheoDanhMuc.putExtra("TENLOAI",thuongHieu.getTENTHUONGHIEU());
                iHienThiSPTheoDanhMuc.putExtra("KIEMTRA",kiemtra);
                context.startActivity(iHienThiSPTheoDanhMuc);
            }
        });

        Picasso .with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(100,120).into(holder.imgHinhThuongHieu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressDownload.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });


    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTieuDeThuongHieu;
        ImageView imgHinhThuongHieu;
        ProgressBar progressDownload;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTieuDeThuongHieu = itemView.findViewById(R.id.txtTieuDeThuongHieuLonDoAn);
            imgHinhThuongHieu = itemView.findViewById(R.id.imgHinhThuongHieuLonDoAn);
            progressDownload = itemView.findViewById(R.id.process_download);
            linearLayout = itemView.findViewById(R.id.llThuongHieuLon);
        }
    }
}
