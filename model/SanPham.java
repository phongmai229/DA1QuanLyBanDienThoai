/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PHONG
 */
public class SanPham {
    private int ID_San_Pham;
    private int ID_Chip;
    private int ID_Pin;
    private int ID_Man_Hinh;
    private int ID_Cong_Sac;
    private int ho_Tro_Mang;
    private int the_He;
    private int so_Luong_Sim;

    private int trang_Thai;
    private String ngay_Tao;
    private String ngay_Sua;
    private int ID_Dong;
    private int ID_NV;

    public SanPham(int ID_San_Pham, int ID_Chip, int ID_Pin, int ID_Man_Hinh, int ID_Cong_Sac, int ho_Tro_Mang, int the_He, int so_Luong_Sim, int trang_Thai, String ngay_Tao, String ngay_Sua, int ID_Dong, int ID_NV) {
        this.ID_San_Pham = ID_San_Pham;
        this.ID_Chip = ID_Chip;
        this.ID_Pin = ID_Pin;
        this.ID_Man_Hinh = ID_Man_Hinh;
        this.ID_Cong_Sac = ID_Cong_Sac;
        this.ho_Tro_Mang = ho_Tro_Mang;
        this.the_He = the_He;
        this.so_Luong_Sim = so_Luong_Sim;
 
        this.trang_Thai = trang_Thai;
        this.ngay_Tao = ngay_Tao;
        this.ngay_Sua = ngay_Sua;
        this.ID_Dong = ID_Dong;
        this.ID_NV = ID_NV;
    }

    public SanPham() {
    }

    public int getID_San_Pham() {
        return ID_San_Pham;
    }

    public void setID_San_Pham(int ID_San_Pham) {
        this.ID_San_Pham = ID_San_Pham;
    }

    public int getID_Chip() {
        return ID_Chip;
    }

    public void setID_Chip(int ID_Chip) {
        this.ID_Chip = ID_Chip;
    }

    public int getID_Pin() {
        return ID_Pin;
    }

    public void setID_Pin(int ID_Pin) {
        this.ID_Pin = ID_Pin;
    }

    public int getID_Man_Hinh() {
        return ID_Man_Hinh;
    }

    public void setID_Man_Hinh(int ID_Man_Hinh) {
        this.ID_Man_Hinh = ID_Man_Hinh;
    }

    public int getID_Cong_Sac() {
        return ID_Cong_Sac;
    }

    public void setID_Cong_Sac(int ID_Cong_Sac) {
        this.ID_Cong_Sac = ID_Cong_Sac;
    }

    public int getHo_Tro_Mang() {
        return ho_Tro_Mang;
    }

    public void setHo_Tro_Mang(int ho_Tro_Mang) {
        this.ho_Tro_Mang = ho_Tro_Mang;
    }

    public int getThe_He() {
        return the_He;
    }

    public void setThe_He(int the_He) {
        this.the_He = the_He;
    }

    public int getSo_Luong_Sim() {
        return so_Luong_Sim;
    }

    public void setSo_Luong_Sim(int so_Luong_Sim) {
        this.so_Luong_Sim = so_Luong_Sim;
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

    public int getID_Dong() {
        return ID_Dong;
    }

    public void setID_Dong(int ID_Dong) {
        this.ID_Dong = ID_Dong;
    }

    public int getID_NV() {
        return ID_NV;
    }

    public void setID_NV(int ID_NV) {
        this.ID_NV = ID_NV;
    }

    
    
}
