package com.example.quanlyvatracuubhxh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQL extends SQLiteOpenHelper {
    public static final String DATABASENAME="BHXH";
    public static final String TABLE_NAME="KHACH_HANG";
    public static final String MA_BHXH="MA_BHXH";
    public static final String HO_TEN="HO_TEN";
    public static final String NGAYSINH="NGAY_SINH";
    public static final String GIOITINH="SEX";
    public static final String ADDRESS="ADDRESS";
    public static final String NOICAP="NOI_CAP";

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;
    Context context;
    public SQL(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreaTable = "CREATE TABLE "+TABLE_NAME+" ( " +
                MA_BHXH+" text NOT NULL PRIMARY KEY , "+
                HO_TEN+" Text, "+
                NGAYSINH+" Text, "+
                GIOITINH+" Text, "+
                ADDRESS+" Text, "+
                NOICAP+" text "+")";
        //Chạy câu lệnh tạo bảng product
        db.execSQL(queryCreaTable);
    }
    public void insert(QuanLyBHXH item) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(MA_BHXH,item.getMaBH());
        contentValues.put(HO_TEN,item.getTenKH());
        contentValues.put(NGAYSINH,item.getNgaysinh());
        contentValues.put(GIOITINH,item.getSex());
        contentValues.put(ADDRESS,item.getDiachi());
        contentValues.put(NOICAP,item.getNoicap());
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        closeDB();
    }

    public int delete(String id) {
        sqLiteDatabase = getWritableDatabase();
        return Long.valueOf(sqLiteDatabase.delete(TABLE_NAME, MA_BHXH+" =?", new String[]{id})).intValue();
    }

    public boolean deleteAll() {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, null, null);
        closeDB();
        return true;
    }

    public void update(QuanLyBHXH item) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(MA_BHXH,item.getMaBH());
        contentValues.put(HO_TEN,item.getTenKH());
        contentValues.put(NGAYSINH,item.getNgaysinh());
        contentValues.put(GIOITINH,item.getSex());
        contentValues.put(ADDRESS,item.getDiachi());
        contentValues.put(NOICAP,item.getNoicap());

        sqLiteDatabase.update(TABLE_NAME, contentValues, MA_BHXH +" =?",
                new String[]{String.valueOf(item.getMaBH())});
        closeDB();
    }

    public List<QuanLyBHXH> getAll() {
        List<QuanLyBHXH> videos = new ArrayList<>();
        String select = "SELECT * FROM " + TABLE_NAME;
        sqLiteDatabase = getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery(select, null);
        if(cursor.moveToFirst()) {
            do {

                String mabhxh = cursor.getString(0);
                String hoten = cursor.getString(1);
                String ngaysinh = cursor.getString(2);
                String gt = cursor.getString(3);
                String diachi = cursor.getString(4);
                String noicap = cursor.getString(5);
                videos.add(new QuanLyBHXH(mabhxh, hoten, ngaysinh, gt, diachi, noicap));
            } while (cursor.moveToNext());
        }
        closeDB();
        return videos;
    }
    public boolean ifExistsItem(String ma) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor;
        String checkQuery = "SELECT "+MA_BHXH+" FROM " + TABLE_NAME + " WHERE "+MA_BHXH+"= '" + ma+"'";
        cursor = database.rawQuery(checkQuery, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    private void closeDB() {
        //dong
        if (sqLiteDatabase != null) sqLiteDatabase.close();
        if (contentValues != null) contentValues.clear();
        if (cursor != null) cursor.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
