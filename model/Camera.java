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
public class Camera {
    private int id;
    private int soluong;
    private int loaicamera;
    private int thongsokithuat;
    private int trangthai;
    private Date ngaysua;
    private Date ngaytao;

    public Camera() {
    }

    public Camera(int id, int soluong, int loaicamera, int thongsokithuat, int trangthai, Date ngaysua, Date ngaytao) {
        this.id = id;
        this.soluong = soluong;
        this.loaicamera = loaicamera;
        this.thongsokithuat = thongsokithuat;
        this.trangthai = trangthai;
        this.ngaysua = ngaysua;
        this.ngaytao = ngaytao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getLoaicamera() {
        return loaicamera;
    }

    public void setLoaicamera(int loaicamera) {
        this.loaicamera = loaicamera;
    }

    public int getThongsokithuat() {
        return thongsokithuat;
    }

    public void setThongsokithuat(int thongsokithuat) {
        this.thongsokithuat = thongsokithuat;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public Date getNgaysua() {
        return ngaysua;
    }

    public void setNgaysua(Date ngaysua) {
        this.ngaysua = ngaysua;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

}
