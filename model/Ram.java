/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thanh Huyen Pham
 */
public class Ram {
    Integer idRam;
    Integer dungLuong;
    Integer trangThai;
    String ngayTao;
    String ngaySua;

    public Ram() {
    }

    public Ram(Integer idRam, Integer dungLuong, Integer trangThai, String ngayTao, String ngaySua) {
        this.idRam = idRam;
        this.dungLuong = dungLuong;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public Integer getIdRam() {
        return idRam;
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

    public void setIdRam(Integer idRam) {
        this.idRam = idRam;
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
