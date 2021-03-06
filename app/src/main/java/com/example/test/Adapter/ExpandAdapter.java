package com.example.test.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.Model.ObjectClass.LoaiSanPham;
import com.example.test.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.test.R;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {
    Context context;
    List<LoaiSanPham> loaiSanPhams;
    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhams){
        this.context=context;
        this.loaiSanPhams=loaiSanPhams;

        XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
        int count = loaiSanPhams.size();
        for(int i=0; i<count;i++){
            int maloaisp = loaiSanPhams.get(i).getMALOAISP();
            loaiSanPhams.get(i).setListCon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
        }


    }
    @Override
    public int getGroupCount() {
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {

        if (loaiSanPhams.get(vitriGroupCha).getListCon().size() != 0){
            return 1;
        }
        return 0;
    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha).getMALOAISP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int vitriGroupCha, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha,parent,false);
        TextView txtTenLoaiSP = viewGroupCha.findViewById(R.id.txtTenLoaiSP);
        txtTenLoaiSP.setText(loaiSanPhams.get(vitriGroupCha).getTENLOAISP());
        ImageView imgView = viewGroupCha.findViewById(R.id.imgMenuPlus);
        int demSPCon = loaiSanPhams.get(vitriGroupCha).getListCon().size();
        if (demSPCon > 0){
            imgView.setVisibility(View.VISIBLE);
        }else {
            imgView.setVisibility(View.INVISIBLE);
        }
        if(isExpanded){
            imgView.setImageResource(R.drawable.ic_remove);
            viewGroupCha.setBackgroundResource(R.color.Gray);
        }else {
            imgView.setImageResource(R.drawable.ic_add);
        }

        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("loasp",loaiSanPhams.get(vitriGroupCha).getTENLOAISP()+ "- "+ loaiSanPhams.get(vitriGroupCha).getMALOAISP());
                return false;
            }
        });

        return viewGroupCha;
    }

    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isLastChild, View convertView, ViewGroup parent) {
        //LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View viewGroupCon = layoutInflater.inflate(R.layout.custom_layout_group_cha,parent,false);
        //TextView txtTenLoaiSP = viewGroupCha.findViewById(R.id.txtTenLoaiSP);
        //txtTenLoaiSP.setText(loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon).getTENLOAISP());

        // da cap menu
        //ExpandableListView expandableListView = viewGroupCon.findViewById(R.id.epMenuCon);

        SecondExpanable secondExpanable = new SecondExpanable(context);
        ExpandAdapter secondAdapter= new ExpandAdapter(context,loaiSanPhams.get(vitriGroupCha).getListCon());
        secondExpanable.setAdapter(secondAdapter);
        secondExpanable.setGroupIndicator(null);
        notifyDataSetChanged();


        return secondExpanable;
    }

    public class SecondExpanable extends ExpandableListView{

        public SecondExpanable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

            //widthMeasureSpec = MeasureSpec.makeMeasureSpec(900,MeasureSpec.AT_MOST);
            // bỏ tự động co chiều rộng để các dấu dạt ra ngoài cùng bằng nhau
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(1500,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

//    public class SecondAdapter extends BaseExpandableListAdapter{
//
//        List<LoaiSanPham> listCon;
//        public SecondAdapter(List<LoaiSanPham> listCon){
//            this.listCon=listCon;
//            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
//            int count = listCon.size();
//            for(int i=0; i<count;i++){
//                int maloaisp = listCon.get(i).getMALOAISP();
//                listCon.get(i).setListCon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
//            }
//
//        }
//        @Override
//        public int getGroupCount() {
//            return listCon.size();
//        }
//
//        @Override
//        public int getChildrenCount(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha).getListCon().size();
//        }
//
//        @Override
//        public Object getGroup(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha);
//        }
//
//        @Override
//        public Object getChild(int vitriGroupCha, int vitriGroupCon) {
//            return listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon);
//        }
//
//        @Override
//        public long getGroupId(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha).getMALOAISP();
//        }
//
//        @Override
//        public long getChildId(int vitriGroupCha, int vitriGroupCon) {
//            return listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public View getGroupView(int vitriGroupCha, boolean isExpanded, View convertView, ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha,parent,false);
//            TextView txtTenLoaiSP = viewGroupCha.findViewById(R.id.txtTenLoaiSP);
//            txtTenLoaiSP.setText(listCon.get(vitriGroupCha).getTENLOAISP());
//            return viewGroupCha;
//        }
//
//        @Override
//        public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isLastChild, View convertView, ViewGroup parent) {
//            TextView tv = new TextView(context);
//            tv.setText(listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon).getTENLOAISP());
//            tv.setPadding(15,5,5,5);
//            tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
//            return tv;
//        }
//
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return false;
//        }
//    }


}
