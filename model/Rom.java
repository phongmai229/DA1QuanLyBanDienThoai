/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thanh Huyen Pham
 */
public class Rom {
    Integer idRom;
    Integer dungLuong;
    Integer trangThai;
    String ngayTao;
    String ngaySua;

    public Rom() {
    }

    public Rom(Integer idRom, Integer dungLuong, Integer trangThai, String ngayTao, String ngaySua) {
        this.idRom = idRom;
        this.dungLuong = dungLuong;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public Integer getIdRom() {
        return idRom;
    }

    public Integer getDungLuong() {
        return dungLuong;
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

    public void setIdRom(Integer idRom) {
        this.idRom = idRom;
    }

    public void setDungLuong(Integer dungLuong) {
        this.dungLuong = dungLuong;
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
