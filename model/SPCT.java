/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.logging.Logger;

/**
 *
 * @author PHONG
 */
public class SPCT {

    private int ID_SPCT;
    private int ID_San_Pha;
    private int ID_Ram;
    private int ID_Rom;
    private int ID_Mau;
    private int ID_Model_Number;
    private Integer Version_HDH;
    private String ID_Anh;
    private int tinh_Trang;
    private int phien_Ban;
    private int gia_Nhap;
    private int don_Gia;
    private int trang_Thai;
    private String ngay_Tao;
    private String ngay_Sua;
    private int ID_NV;
    private int luuSoLuongLuotMua;

    public int getLuuSoLuongLuotMua() {
        return luuSoLuongLuotMua;
    }

    public void setLuuSoLuongLuotMua(int luuSoLuongLuotMua) {
        this.luuSoLuongLuotMua = luuSoLuongLuotMua;
    }

    public SPCT(int ID_SPCT, int ID_San_Pha, int ID_Ram, int ID_Rom, int ID_Mau, int ID_Model_Number, Integer Version_HDH, String ID_Anh, int tinh_Trang, int phien_Ban, int gia_Nhap, int don_Gia, int trang_Thai, String ngay_Tao, String ngay_Sua, int ID_NV, int luuSoLuongLuotMua) {
        this.ID_SPCT = ID_SPCT;
        this.ID_San_Pha = ID_San_Pha;
        this.ID_Ram = ID_Ram;
        this.ID_Rom = ID_Rom;
        this.ID_Mau = ID_Mau;
        this.ID_Model_Number = ID_Model_Number;
        this.Version_HDH = Version_HDH;
        this.ID_Anh = ID_Anh;
        this.tinh_Trang = tinh_Trang;
        this.phien_Ban = phien_Ban;
        this.gia_Nhap = gia_Nhap;
        this.don_Gia = don_Gia;
        this.trang_Thai = trang_Thai;
        this.ngay_Tao = ngay_Tao;
        this.ngay_Sua = ngay_Sua;
        this.ID_NV = ID_NV;
        this.luuSoLuongLuotMua = luuSoLuongLuotMua;
    }
    public SPCT() {
    }

    
    public SPCT(int ID_SPCT, int ID_San_Pha, int ID_Ram, int ID_Rom, int ID_Mau, int ID_Model_Number, Integer Version_HDH, String ID_Anh, int tinh_Trang, int phien_Ban, int gia_Nhap, int don_Gia, int trang_Thai, String ngay_Tao, String ngay_Sua, int ID_NV) {
        this.ID_SPCT = ID_SPCT;
        this.ID_San_Pha = ID_San_Pha;
        this.ID_Ram = ID_Ram;
        this.ID_Rom = ID_Rom;
        this.ID_Mau = ID_Mau;
        this.ID_Model_Number = ID_Model_Number;
        this.Version_HDH = Version_HDH;
        this.ID_Anh = ID_Anh;
        this.tinh_Trang = tinh_Trang;
        this.phien_Ban = phien_Ban;
        this.gia_Nhap = gia_Nhap;
        this.don_Gia = don_Gia;
        this.trang_Thai = trang_Thai;
        this.ngay_Tao = ngay_Tao;
        this.ngay_Sua = ngay_Sua;
        this.ID_NV = ID_NV;
    }

    public int getID_SPCT() {
        return ID_SPCT;
    }

    public void setID_SPCT(int ID_SPCT) {
        this.ID_SPCT = ID_SPCT;
    }

    public int getID_San_Pha() {
        return ID_San_Pha;
    }

    public void setID_San_Pha(int ID_San_Pha) {
        this.ID_San_Pha = ID_San_Pha;
    }

    public int getID_Ram() {
        return ID_Ram;
    }

    public void setID_Ram(int ID_Ram) {
        this.ID_Ram = ID_Ram;
    }

    public int getID_Rom() {
        return ID_Rom;
    }

    public void setID_Rom(int ID_Rom) {
        this.ID_Rom = ID_Rom;
    }

    public int getID_Mau() {
        return ID_Mau;
    }

    public void setID_Mau(int ID_Mau) {
        this.ID_Mau = ID_Mau;
    }

    public int getID_Model_Number() {
        return ID_Model_Number;
    }

    public void setID_Model_Number(int ID_Model_Number) {
        this.ID_Model_Number = ID_Model_Number;
    }

    public Integer getVersion_HDH() {
        return Version_HDH;
    }

    public void setVersion_HDH(Integer Version_HDH) {
        this.Version_HDH = Version_HDH;
    }

    public String getID_Anh() {
        return ID_Anh;
    }

    public void setID_Anh(String ID_Anh) {
        this.ID_Anh = ID_Anh;
    }

   

    public int getID_NV() {
        return ID_NV;
    }

    public void setID_NV(int ID_NV) {
        this.ID_NV = ID_NV;
    }

    public int getTinh_Trang() {
        return tinh_Trang;
    }

    public void setTinh_Trang(int tinh_Trang) {
        this.tinh_Trang = tinh_Trang;
    }

    public int getPhien_Ban() {
        return phien_Ban;
    }

    public void setPhien_Ban(int phien_Ban) {
        this.phien_Ban = phien_Ban;
    }

    public int getGia_Nhap() {
        return gia_Nhap;
    }

    public void setGia_Nhap(int gia_Nhap) {
        this.gia_Nhap = gia_Nhap;
    }

    public int getDon_Gia() {
        return don_Gia;
    }

    public void setDon_Gia(int don_Gia) {
        this.don_Gia = don_Gia;
    }

    public int getTrang_Thai() {
        return trang_Thai;
    }

    public void setTrang_Thai(int trang_Thai) {
        this.trang_Thai = trang_Thai;
    }

    public String getNgay_Tao() {
        return ngay_Tao;
    }

    public void setNgay_Tao(String ngay_Tao) {
        this.ngay_Tao = ngay_Tao;
    }

    public String getNgay_Sua() {
        return ngay_Sua;
    }

    public void setNgay_Sua(String ngay_Sua) {
        this.ngay_Sua = ngay_Sua;
    }

}
