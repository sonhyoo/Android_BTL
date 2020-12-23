package com.example.quanlyvatracuubhxh;

import java.io.Serializable;

public class QuanLyBHXH implements Serializable {
    public static final String  NAM="Nam";
    public static final String  NU="Nữ";
    public static final String TATCA="Tất Cả";
    public String getMaBH() {
        return maBH;
    }

    public void setMaBH(String maBH) {
        this.maBH = maBH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNoicap() {
        return noicap;
    }

    public void setNoicap(String noicap) {
        this.noicap = noicap;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    String maBH;
    String tenKH;
    String ngaysinh;
    String sex;
    String diachi;
    String noicap;

    public QuanLyBHXH(String maBH, String tenKH, String ngaysinh, String sex, String diachi, String noicap) {
        this.maBH = maBH;
        this.tenKH = tenKH;
        this.sex = sex;
        this.diachi = diachi;
        this.noicap = noicap;
        this.ngaysinh = ngaysinh;
    }
    public QuanLyBHXH(QuanLyBHXH ql) {
        this.maBH = ql.getMaBH();
        this.tenKH = ql.getTenKH();
        this.sex = ql.getSex();
        this.diachi = ql.getDiachi();
        this.noicap = ql.getNoicap();
        this.ngaysinh = ql.getNgaysinh();
    }
}
