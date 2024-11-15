/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PHONG
 */
public class IMEI {

    private int id;
    private String IMEI;
    private int ID_SPCT;
    private int ID_NV;
    private int trangThai;
    private String ngayTao;
    private String ngaySua;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IMEI(int id, String IMEI, int ID_SPCT, int ID_NV, int trangThai, String ngayTao, String ngaySua) {
        this.id = id;
        this.IMEI = IMEI;
        this.ID_SPCT = ID_SPCT;
        this.ID_NV = ID_NV;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public IMEI() {
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public int getID_SPCT() {
        return ID_SPCT;
    }

    public void setID_SPCT(int ID_SPCT) {
        this.ID_SPCT = ID_SPCT;
    }

    public int getID_NV() {
        return ID_NV;
    }

    public void setID_NV(int ID_NV) {
        this.ID_NV = ID_NV;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
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
