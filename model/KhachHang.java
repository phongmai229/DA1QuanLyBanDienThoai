/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class KhachHang {

    Integer id;
    Integer idNhanVien;
    String hoTen;
    String SDT;
    String ngaySinh;
    String tuoi;
    Integer gioiTinh;
    Integer trangThai;
    String ngayTao;
    String ngaySua;

    public KhachHang() {
    }

    public KhachHang(Integer id, Integer idNhanVien, String hoTen, String SDT, String ngaySinh, String tuoi, Integer gioiTinh, Integer trangThai, String ngayTao, String ngaySua) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.hoTen = hoTen;
        this.SDT = SDT;
        this.ngaySinh = ngaySinh;
        this.tuoi = tuoi;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public KhachHang(Integer id, Integer idNhanVien, String hoTen, String SDT, String ngaySinh, Integer gioiTinh, Integer trangThai, String ngayTao, String ngaySua) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.hoTen = hoTen;
        this.SDT = SDT;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Integer idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTuoi() {
        return tuoi;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }

    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

    
}
