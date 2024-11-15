/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PHONG
 */
public class HoaDon {

    private int IDHD;
    private int IDNV;
    private Integer IDKH;
    private Integer ID_KMCT;
    private Integer tongTienThanhToan;
    private Integer tongTienKhachDua;
    private String ghiChu;
    private Integer phuongThucThanhToan;
    private int trangThai;
    private String ngayTao;
    private String ngaySua;

    public HoaDon(int IDHD, int IDNV, Integer IDKH, Integer ID_KMCT, Integer tongTienThanhToan, Integer tongTienKhachDua, String ghiChu, Integer phuongThucThanhToan, int trangThai, String ngayTao, String ngaySua) {
        this.IDHD = IDHD;
        this.IDNV = IDNV;
        this.IDKH = IDKH;
        this.ID_KMCT = ID_KMCT;
        this.tongTienThanhToan = tongTienThanhToan;
        this.tongTienKhachDua = tongTienKhachDua;
        this.ghiChu = ghiChu;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public HoaDon() {
    }

    public int getIDHD() {
        return IDHD;
    }

    public void setIDHD(int IDHD) {
        this.IDHD = IDHD;
    }

    public int getIDNV() {
        return IDNV;
    }

    public void setIDNV(int IDNV) {
        this.IDNV = IDNV;
    }

    public Integer getIDKH() {
        return IDKH;
    }

    public void setIDKH(Integer IDKH) {
        this.IDKH = IDKH;
    }

    public Integer getID_KMCT() {
        return ID_KMCT;
    }

    public void setID_KMCT(Integer ID_KMCT) {
        this.ID_KMCT = ID_KMCT;
    }

    public Integer getTongTienThanhToan() {
        return tongTienThanhToan;
    }

    public void setTongTienThanhToan(Integer tongTienThanhToan) {
        this.tongTienThanhToan = tongTienThanhToan;
    }

    public Integer getTongTienKhachDua() {
        return tongTienKhachDua;
    }

    public void setTongTienKhachDua(Integer tongTienKhachDua) {
        this.tongTienKhachDua = tongTienKhachDua;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(Integer phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
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
