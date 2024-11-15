/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PHONG
 */
public class KhuyenMaiChiTiet {

    private int ID_KMCT;
    private int IDKM;
    private String dieuKienSP;
    private int soLuong;
    private Integer dieuKienGia;
    private int giaKhuyenMai;
    private int donViKhuyenMai;
    private Integer giamToiDa;
    private Integer dieuKienSoLuong;
    private int trangThai;
    private String ngayTao;
    private String ngaySua;
    private String Ma_Voucher;
    private int loaiKhuyenMai;

    public KhuyenMaiChiTiet(int ID_KMCT, int IDKM, String dieuKienSP, int soLuong, Integer dieuKienGia, int giaKhuyenMai, int donViKhuyenMai, Integer giamToiDa, Integer dieuKienSoLuong, int trangThai, String ngayTao, String ngaySua, String Ma_Voucher, int loaiKhuyenMai) {
        this.ID_KMCT = ID_KMCT;
        this.IDKM = IDKM;
        this.dieuKienSP = dieuKienSP;
        this.soLuong = soLuong;
        this.dieuKienGia = dieuKienGia;
        this.giaKhuyenMai = giaKhuyenMai;
        this.donViKhuyenMai = donViKhuyenMai;
        this.giamToiDa = giamToiDa;
        this.dieuKienSoLuong = dieuKienSoLuong;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.Ma_Voucher = Ma_Voucher;
        this.loaiKhuyenMai = loaiKhuyenMai;
    }

    

    public int getLoaiKhuyenMai() {
        return loaiKhuyenMai;
    }

    public void setLoaiKhuyenMai(int loaiKhuyenMai) {
        this.loaiKhuyenMai = loaiKhuyenMai;
    }

    public Integer getGiamToiDa() {
        return giamToiDa;
    }

    public void setGiamToiDa(Integer giamToiDa) {
        this.giamToiDa = giamToiDa;
    }

    public KhuyenMaiChiTiet() {
    }

    public int getID_KMCT() {
        return ID_KMCT;
    }

    public void setID_KMCT(int ID_KMCT) {
        this.ID_KMCT = ID_KMCT;
    }

    public int getIDKM() {
        return IDKM;
    }

    public void setIDKM(int IDKM) {
        this.IDKM = IDKM;
    }

    public String getDieuKienSP() {
        return dieuKienSP;
    }

    public void setDieuKienSP(String dieuKienSP) {
        this.dieuKienSP = dieuKienSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getDieuKienGia() {
        return dieuKienGia;
    }

    public void setDieuKienGia(Integer dieuKienGia) {
        this.dieuKienGia = dieuKienGia;
    }

    public int getGiaKhuyenMai() {
        return giaKhuyenMai;
    }

    public void setGiaKhuyenMai(int giaKhuyenMai) {
        this.giaKhuyenMai = giaKhuyenMai;
    }

    public int getDonViKhuyenMai() {
        return donViKhuyenMai;
    }

    public void setDonViKhuyenMai(int donViKhuyenMai) {
        this.donViKhuyenMai = donViKhuyenMai;
    }

    public Integer getDieuKienSoLuong() {
        return dieuKienSoLuong;
    }

    public void setDieuKienSoLuong(Integer dieuKienSoLuong) {
        this.dieuKienSoLuong = dieuKienSoLuong;
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

    public String getMa_Voucher() {
        return Ma_Voucher;
    }

    public void setMa_Voucher(String Ma_Voucher) {
        this.Ma_Voucher = Ma_Voucher;
    }

}
