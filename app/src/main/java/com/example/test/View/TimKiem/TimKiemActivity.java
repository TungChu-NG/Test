package com.example.test.View.TimKiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.test.Adapter.AdapterTopMonVietDoAn;
import com.example.test.Model.ObjectClass.SanPham;
import com.example.test.Presenter.TimKiem.PresenterLogicTimKiem;
import com.example.test.R;

import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem, SearchView.OnQueryTextListener{

    Toolbar toolbar;
    RecyclerView recyclerView;
    PresenterLogicTimKiem  presenterLogicTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerTimKiem);

        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);

        presenterLogicTimKiem = new PresenterLogicTimKiem(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);

        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itSearch);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);


        return true;
    }
    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterTopMonVietDoAn adapterTopMonVietDoAn = new AdapterTopMonVietDoAn(this,R.layout.custom_layout_list_top_doan,sanPhamList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopMonVietDoAn);
       // recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager,this));
        adapterTopMonVietDoAn.notifyDataSetChanged();
    }

    @Override
    public void TimKiemThatBai() {
        Toast.makeText(this, "Khong tim thay", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterLogicTimKiem.TimKiemSanPhamTheoTenSP(query,0);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}