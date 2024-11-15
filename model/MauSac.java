/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thanh Huyen Pham
 */
public class MauSac {
    Integer idMau;
    String tenMau;
    Integer trangThai;
    String ngayTao;
    String ngaySua;

    public MauSac() {
    }

    public MauSac(Integer idMau, String tenMau, Integer trangThai, String ngayTao, String ngaySua) {
        this.idMau = idMau;
        this.tenMau = tenMau;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public Integer getIdMau() {
        return idMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public String getNgaySua() {
        return ngaySua;
    }

    public void setIdMau(Integer idMau) {
        this.idMau = idMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public void setNgaySua(String ngaySua) {
        this.ngaySua = ngaySua;
    }

}
