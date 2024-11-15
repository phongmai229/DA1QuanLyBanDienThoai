/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thanh Huyen Pham
 */
public class ThongSoManHinh {
    Integer ID_ThongSoManHinh;
    Integer loaiManHinh;
    Integer doPhanGiai;
    Integer tanSoQuet;
    Integer trangThai;
    String ngayTao;
    String ngaySua;

    public ThongSoManHinh() {
    }

    public ThongSoManHinh(Integer ID_ThongSoManHinh, Integer loaiManHinh, Integer doPhanGiai, Integer tanSoQuet, Integer trangThai, String ngayTao, String ngaySua) {
        this.ID_ThongSoManHinh = ID_ThongSoManHinh;
        this.loaiManHinh = loaiManHinh;
        this.doPhanGiai = doPhanGiai;
        this.tanSoQuet = tanSoQuet;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    } 

    public Integer getID_ThongSoManHinh() {
        return ID_ThongSoManHinh;
    }

    public Integer getLoaiManHinh() {
        return loaiManHinh;
    }

    public Integer getDoPhanGiai() {
        return doPhanGiai;
    }

    public Integer getTanSoQuet() {
        return tanSoQuet;
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

    public void setID_ThongSoManHinh(Integer ID_ThongSoManHinh) {
        this.ID_ThongSoManHinh = ID_ThongSoManHinh;
    }

    public void setLoaiManHinh(Integer loaiManHinh) {
        this.loaiManHinh = loaiManHinh;
    }

    public void setDoPhanGiai(Integer doPhanGiai) {
        this.doPhanGiai = doPhanGiai;
    }

    public void setTanSoQuet(Integer tanSoQuet) {
        this.tanSoQuet = tanSoQuet;
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
