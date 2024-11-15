/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class MODELNUMBER {
    private int id;
    private String kihieu;
    private String datnuoc;
    private int trangthai;
    private Date ngaytao;
    private Date ngaysua;

    public MODELNUMBER() {
    }

    public MODELNUMBER(int id, String kihieu, String datnuoc, int trangthai, Date ngaytao, Date ngaysua) {
        this.id = id;
        this.kihieu = kihieu;
        this.datnuoc = datnuoc;
        this.trangthai = trangthai;
        this.ngaytao = ngaytao;
        this.ngaysua = ngaysua;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKihieu() {
        return kihieu;
    }

    public void setKihieu(String kihieu) {
        this.kihieu = kihieu;
    }

    public String getDatnuoc() {
        return datnuoc;
    }

    public void setDatnuoc(String datnuoc) {
        this.datnuoc = datnuoc;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Date getNgaysua() {
        return ngaysua;
    }

    public void setNgaysua(Date ngaysua) {
        this.ngaysua = ngaysua;
    }
    
    
}
