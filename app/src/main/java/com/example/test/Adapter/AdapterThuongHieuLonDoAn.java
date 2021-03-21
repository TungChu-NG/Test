package com.example.test.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Model.ObjectClass.ThuongHieu;
import com.example.test.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLonDoAn extends RecyclerView.Adapter<AdapterThuongHieuLonDoAn.ViewHolderThuongHieuLon> {

    Context context;
    List<ThuongHieu> thuongHieus;

    public AdapterThuongHieuLonDoAn(Context context, List<ThuongHieu> thuongHieus) {
        this.context = context;
        this.thuongHieus = thuongHieus;
    }

    @NonNull
    @Override
    public AdapterThuongHieuLonDoAn.ViewHolderThuongHieuLon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_topthuonghieulondoan,parent,false);



        return new ViewHolderThuongHieuLon(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterThuongHieuLonDoAn.ViewHolderThuongHieuLon holder, int position) {
        ThuongHieu thuongHieu =thuongHieus.get(position);

        Picasso.with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(170,80).centerInside().into(holder.imLogoThuongHieuLon);

    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }

    public class ViewHolderThuongHieuLon extends RecyclerView.ViewHolder {
        ImageView imLogoThuongHieuLon;
        public ViewHolderThuongHieuLon(@NonNull View itemView) {
            super(itemView);
            imLogoThuongHieuLon = itemView.findViewById(R.id.imLogoTopThuongHieuLon);
        }
    }
}
