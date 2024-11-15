/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thanh Huyen Pham
 */
public class Pin {
    Integer idPin;
    Integer loaiPin;
    Integer dungLuongPin;
    Integer trangThai;
    String ngayTao;
    String ngaySua;

    public Pin() {
    }

    public Pin(Integer idPin, Integer loaiPin, Integer dungLuongPin, Integer trangThai, String ngayTao, String ngaySua) {
        this.idPin = idPin;
        this.loaiPin = loaiPin;
        this.dungLuongPin = dungLuongPin;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public Integer getIdPin() {
        return idPin;
    }

    public void setIdPin(Integer idPin) {
        this.idPin = idPin;
    }

    public Integer getLoaiPin() {
        return loaiPin;
    }

    public void setLoaiPin(Integer loaiPin) {
        this.loaiPin = loaiPin;
    }

    public Integer getDungLuongPin() {
        return dungLuongPin;
    }

    public void setDungLuongPin(Integer dungLuongPin) {
        this.dungLuongPin = dungLuongPin;
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
