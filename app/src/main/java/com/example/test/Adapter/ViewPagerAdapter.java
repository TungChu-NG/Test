package com.example.test.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.test.Fragment.FragmentChuongTrinhKhuyenMai;
import com.example.test.Fragment.FragmentDoAn;
import com.example.test.Fragment.FragmentNoiBat;
import com.example.test.Fragment.FragmentTapHoa;
import com.example.test.Fragment.FragmentThucPham;
import com.example.test.Fragment.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragment = new ArrayList<>();
    List<String> tilteFragment = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        listFragment.add(new FragmentNoiBat());
        listFragment.add(new FragmentChuongTrinhKhuyenMai());
        listFragment.add(new FragmentDoAn());
        listFragment.add(new FragmentThucPham());
        listFragment.add(new FragmentTapHoa());
        listFragment.add(new FragmentThuongHieu());

        tilteFragment.add("Nổi bật");
        tilteFragment.add("Chương trình khuyến mãi");
        tilteFragment.add("Đồ ăn");
        tilteFragment.add("Thực phẩm");
        tilteFragment.add("Tạp hóa");
        tilteFragment.add("Thương hiệu");




    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tilteFragment.get(position);
    }
}
