package com.nguyenhongson.quanlythuchi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nguyenhongson.quanlythuchi.fragment.Tab_KhoanThu_Fragment;
import com.nguyenhongson.quanlythuchi.fragment.Tab_LoaiThu_Fragment;


public class KhoanThu_ViewPagerAdapter extends FragmentStatePagerAdapter {
    int numberTab = 2;


    public KhoanThu_ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Tab_KhoanThu_Fragment tab_khoanThu_fragment = new Tab_KhoanThu_Fragment();
                return tab_khoanThu_fragment;
            case 1:
                Tab_LoaiThu_Fragment tab_loaiThu_fragment = new Tab_LoaiThu_Fragment();
                return tab_loaiThu_fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numberTab;
    }

}