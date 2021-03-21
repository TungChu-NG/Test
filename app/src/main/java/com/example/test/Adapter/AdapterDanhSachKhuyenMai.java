package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.ObjectClass.KhuyenMai;
import com.example.test.R;

import java.util.List;

public class AdapterDanhSachKhuyenMai extends RecyclerView.Adapter<AdapterDanhSachKhuyenMai.ViewHolderKhuyenMai> {
   Context context;
   List<KhuyenMai> khuyenMaiList;

    public AdapterDanhSachKhuyenMai(Context context, List<KhuyenMai> khuyenMaiList) {
        this.context = context;
        this.khuyenMaiList = khuyenMaiList;
    }

    @NonNull
    @Override
    public ViewHolderKhuyenMai onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_itemkhuyenmai,parent,false);

        return new ViewHolderKhuyenMai(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderKhuyenMai holder, int position) {
        KhuyenMai khuyenMai = khuyenMaiList.get(position);

        holder.txtTieuDeKhuyenMai.setText(khuyenMai.getTENLOAISP());
        AdapterTopMonVietDoAn adapterTopMonVietDoAn = new AdapterTopMonVietDoAn(context,R.layout.custom_layout_topmonviet,khuyenMai.getDanhSachSanPhamKhuyenMai());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);

        holder.recyclerViewItemKhuyenMai.setLayoutManager(layoutManager);
        holder.recyclerViewItemKhuyenMai.setAdapter(adapterTopMonVietDoAn);

    }

    @Override
    public int getItemCount() {
        return khuyenMaiList.size();
    }

    public class ViewHolderKhuyenMai extends RecyclerView.ViewHolder {
        RecyclerView recyclerViewItemKhuyenMai;
        TextView txtTieuDeKhuyenMai;
        public ViewHolderKhuyenMai(@NonNull View itemView) {
            super(itemView);
            txtTieuDeKhuyenMai = itemView.findViewById(R.id.txtTieuDeKhuyenMai);
            recyclerViewItemKhuyenMai = itemView.findViewById(R.id.recyclerItemKhuyenMai);
        }
    }
}
