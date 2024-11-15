/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PHONG
 */
public class ManHinh {
    
    private int IDManHinh;
    private double kichThuoc;
    private int trangThai;
    private String ngaySua;
    private String ngayTao;
    private int IDThongSoManHinh;

    public ManHinh(int IDManHinh, double kichThuoc, int trangThai, String ngaySua, String ngayTao, int IDThongSoManHinh) {
        this.IDManHinh = IDManHinh;
        this.kichThuoc = kichThuoc;

        this.trangThai = trangThai;
        this.ngaySua = ngaySua;
        this.ngayTao = ngayTao;
        this.IDThongSoManHinh = IDThongSoManHinh;
    }

    public ManHinh() {
    }

    public int getIDManHinh() {
        return IDManHinh;
    }

    public void setIDManHinh(int IDManHinh) {
        this.IDManHinh = IDManHinh;
    }

    public double getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(double kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

   

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getIDThongSoManHinh() {
        return IDThongSoManHinh;
    }

    public void setIDThongSoManHinh(int IDThongSoManHinh) {
        this.IDThongSoManHinh = IDThongSoManHinh;
    }
}
