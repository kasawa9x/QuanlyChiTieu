package com.nguyenhongson.quanlythuchi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.nguyenhongson.quanlythuchi.database.Database;
import com.nguyenhongson.quanlythuchi.model.ThuChi;

import java.util.ArrayList;

public class DaoThuChi {
    Database dtb;
    SQLiteDatabase dtTC;

    public DaoThuChi(Context context) {
        dtb = new Database(context);
        dtTC = dtb.getReadableDatabase();
    }


    public ArrayList<ThuChi> getTC(String sql, String... a) {
        ArrayList<ThuChi> list = new ArrayList<>();
        Cursor cs = dtTC.rawQuery(sql, a);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int maTc = Integer.parseInt(cs.getString(0));
            String tenTc = cs.getString(1);
            int loaiTc = Integer.parseInt(cs.getString(2));

            ThuChi tc = new ThuChi(maTc, tenTc, loaiTc);
            list.add(tc);
            cs.moveToNext();
        }
        cs.close();
        return list;
    }


    public boolean themTC(ThuChi tc) {
        SQLiteDatabase db = dtb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKhoan", tc.getTenKhoan());
        contentValues.put("loaiKhoan", tc.isLoaiKhoan());
        long r = db.insert("THUCHI", null, contentValues);
        if (r <= 0) {
            return false;
        }
        return true;
    }

    public boolean xoaTC(ThuChi tc) {
        SQLiteDatabase db = dtb.getWritableDatabase();
        int r = db.delete("THUCHI", "maKhoan=?", new String[]{String.valueOf(tc.getMaKhoan())});
        int s = db.delete("GIAODICH", "maKhoan=?", new String[]{String.valueOf(tc.getMaKhoan())});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    public boolean suaTC(ThuChi tc) {
        SQLiteDatabase db = dtb.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKhoan", tc.getTenKhoan());
        contentValues.put("loaiKhoan", tc.isLoaiKhoan());
        int r = db.update("THUCHI", contentValues, "maKhoan=?", new String[]{String.valueOf(tc.getMaKhoan())});
        if (r <= 0) {
            return false;
        }
        return true;
    }

    public ArrayList<ThuChi> getAll() {
        String sql = "SELECT * FROM THUCHI";
        return getTC(sql);
    }

    public ArrayList<ThuChi> getThuChi(int loaiKhoan) {
        String sql = "SELECT * FROM THUCHI WHERE loaiKhoan=?";
        ArrayList<ThuChi> list = getTC(sql, String.valueOf(loaiKhoan));
        return list;
    }


    public String getTen(int maKhoan) {
        String tenKhoan = "";

        String sql = "SELECT * FROM THUCHI WHERE maKhoan=?";
        ArrayList<ThuChi> list = getTC(sql, String.valueOf(maKhoan));
        return list.get(0).getTenKhoan();
    }
}
