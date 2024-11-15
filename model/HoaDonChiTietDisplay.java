/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PHONG
 */
public class HoaDonChiTietDisplay {
    private int IDSPCT;
    private int soLuong;
    private int donGia;
    private int thanhTien;

    public HoaDonChiTietDisplay() {
    }

    public HoaDonChiTietDisplay(int IDSPCT, int soLuong, int donGia, int thanhTien) {
        this.IDSPCT = IDSPCT;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getIDSPCT() {
        return IDSPCT;
    }

    public void setIDSPCT(int IDSPCT) {
        this.IDSPCT = IDSPCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
    
}
