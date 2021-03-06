package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.ObjectClass.DoAn;
import com.example.test.R;

import java.util.List;

public class AdapterDoAn extends RecyclerView.Adapter<AdapterDoAn.ViewHolderDoAn> {


    Context context;
    List<DoAn> doAnList;

    public AdapterDoAn(Context context, List<DoAn> doAnList) {
        this.context = context;
        this.doAnList = doAnList;
    }

    @NonNull
    @Override
    public ViewHolderDoAn onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.custom_layout_recyclerview_doan,parent,false);


        return new ViewHolderDoAn(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDoAn holder, int position) {
        DoAn doAn = doAnList.get(position);

        //Xử lý hiển thị danh sách thương hiệu lớn ở recycler view Thương hiệu lớn
        AdapterThuongHieuLon adapterThuongHieuLon = new AdapterThuongHieuLon(context,doAn.getThuongHieus());

        RecyclerView.LayoutManager  layoutManager = new GridLayoutManager(context,3,RecyclerView.HORIZONTAL,false);


        holder.recyclerViewThuongHieuLon.setLayoutManager(layoutManager);
        holder.recyclerViewThuongHieuLon.setAdapter(adapterThuongHieuLon);
        adapterThuongHieuLon.notifyDataSetChanged();

        //Xử lý hiển thị danh sách thương hiệu lớn ở recycler view Top món ăn việt

        AdapterTopMonVietDoAn adapterTopMonVietDoAn = new AdapterTopMonVietDoAn(context,doAn.getSanPhams());

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);

        holder.recyclerViewTopSanPham.setLayoutManager(layoutManager1);
        holder.recyclerViewTopSanPham.setAdapter(adapterTopMonVietDoAn);
        adapterTopMonVietDoAn.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return doAnList.size();
    }

    public class ViewHolderDoAn extends RecyclerView.ViewHolder {
        ImageView imgKhuyenMaiDoAn;
        RecyclerView recyclerViewThuongHieuLon;
        RecyclerView recyclerViewTopSanPham;

        public ViewHolderDoAn(@NonNull View itemView) {
            super(itemView);
            recyclerViewTopSanPham = itemView.findViewById(R.id.recyclerTopDoAn);
            imgKhuyenMaiDoAn = itemView.findViewById(R.id.imKhuyenMaiDoAn);
            recyclerViewThuongHieuLon = itemView.findViewById(R.id.recyclerThuongHieuLon);

        }
    }
}
