/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHONG
 */
public class ThongKeService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    List<SPCT> listTop10SPBanChayNhat;

    public Integer getTongDoanhThuTrongNgay() {
        sql = "select SUM(Tong_Tien) \n"
                + "from HOADON where Trang_Thai = 1 and DATEDIFF(DAY,GETDATE(),Ngay_Sua) = 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public Integer getTongDoanhThuAll() {
        sql = "select SUM(Tong_Tien) \n"
                + "from HOADON where Trang_Thai = 1";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getTongDoanhThuTheoNgayDuocChon(String ngayBatDau, String ngayKetThuc) {
        sql = "select SUM(Tong_Tien) \n"
                + "from HOADON where Trang_Thai = 1 and DATEDIFF(DAY,Ngay_Sua,?) <= 0 and DATEDIFF(DAY,Ngay_Sua,?) >= 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ngayBatDau);
            ps.setString(2, ngayKetThuc);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getTongHoaDonThanhToanTrongNgay() {
        sql = "select count(ID_HD) \n"
                + "from HOADON where Trang_Thai = 1 and DATEDIFF(DAY,GETDATE(),Ngay_Sua) = 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getTongHoaDonThanhToanTheoNgayDuocChon(String ngayBatDau, String ngayKetThuc) {
        sql = "select count(ID_HD) \n"
                + "from HOADON where Trang_Thai = 1 and DATEDIFF(DAY,Ngay_Sua,?) <= 0 and DATEDIFF(DAY,Ngay_Sua,?) >= 0 ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ngayBatDau);
            ps.setString(2, ngayKetThuc);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public Integer getTongHoaDonThanhToanAll() {
        sql = "select count(ID_HD) \n"
                + "from HOADON where Trang_Thai = 1 ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getTongSanPhamBanRaTrongNgay() {
        sql = "select count(GIOHANGCT.ID) \n"
                + "from HOADON join GIOHANGCT on HOADON.ID_HD = GIOHANGCT.ID_HD \n"
                + "where HOADON.Trang_Thai = 1 and GIOHANGCT.Trang_Thai = 0 and DATEDIFF(DAY,GETDATE(),HOADON.Ngay_Sua) = 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public Integer getTongSanPhamBanRaAll() {
        sql = "select count(GIOHANGCT.ID) \n"
                + "from HOADON join GIOHANGCT on HOADON.ID_HD = GIOHANGCT.ID_HD \n"
                + "where HOADON.Trang_Thai = 1 and GIOHANGCT.Trang_Thai = 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getTongSanPhamBanRaTheoNgayDuocChon(String ngayBatDau, String ngayKetThuc) {
        sql = "select count(GIOHANGCT.ID) \n"
                + "from HOADON join GIOHANGCT on HOADON.ID_HD = GIOHANGCT.ID_HD \n"
                + "where HOADON.Trang_Thai = 1 and GIOHANGCT.Trang_Thai = 0 and DATEDIFF(DAY,hoaDon.Ngay_Sua,?) <= 0 and DATEDIFF(DAY,hoaDon.Ngay_Sua,?) >= 0 ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ngayBatDau);
            ps.setString(2, ngayKetThuc);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getTongLoiNhuanTrongNgay() {
        sql = "select HOADON.Tong_Tien - sum(SANPHAMCHITIET.Gia_Nhap)\n"
                + "from HOADON join GIOHANGCT on HOADON.ID_HD = GIOHANGCT.ID_HD join IMEI on IMEI.IMEI = GIOHANGCT.IMEI join SANPHAMCHITIET on SANPHAMCHITIET.ID_SPCT = IMEI.ID_SPCT\n"
                + "where HOADON.Trang_Thai = 1 and GIOHANGCT.Trang_Thai = 0 and DATEDIFF(DAY,GETDATE(),HOADON.Ngay_Sua) = 0\n"
                + "group by HOADON.Tong_Tien,HOADON.ID_HD,GIOHANGCT.ID_HD";
        int tong = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                tong += rs.getInt(1);
            }
            return tong;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public Integer getTongLoiNhuanAll() {
        sql = "select HOADON.Tong_Tien - sum(SANPHAMCHITIET.Gia_Nhap)\n"
                + "from HOADON join GIOHANGCT on HOADON.ID_HD = GIOHANGCT.ID_HD join IMEI on IMEI.IMEI = GIOHANGCT.IMEI join SANPHAMCHITIET on SANPHAMCHITIET.ID_SPCT = IMEI.ID_SPCT\n"
                + "where HOADON.Trang_Thai = 1 and GIOHANGCT.Trang_Thai = 0\n"
                + "group by HOADON.Tong_Tien,HOADON.ID_HD,GIOHANGCT.ID_HD";
        int tong = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                tong += rs.getInt(1);
            }
            return tong;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    

    public Integer getTongLoiNhuanTheoNgayDuocChon(String ngayBatDau, String ngayKetThuc) {
        sql = "select HOADON.Tong_Tien - sum(SANPHAMCHITIET.Gia_Nhap)\n"
                + "from HOADON join GIOHANGCT on HOADON.ID_HD = GIOHANGCT.ID_HD join IMEI on IMEI.IMEI = GIOHANGCT.IMEI join SANPHAMCHITIET on SANPHAMCHITIET.ID_SPCT = IMEI.ID_SPCT\n"
                + "where HOADON.Trang_Thai = 1 and GIOHANGCT.Trang_Thai = 0 and DATEDIFF(DAY,hoaDon.Ngay_Sua,?) <= 0 and DATEDIFF(DAY,hoaDon.Ngay_Sua,?) >= 0\n"
                + "group by HOADON.Tong_Tien,HOADON.ID_HD,GIOHANGCT.ID_HD";
        int tong = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ngayBatDau);
            ps.setString(2, ngayKetThuc);
            rs = ps.executeQuery();
            while (rs.next()) {
                tong += rs.getInt(1);
            }
            return tong;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<SPCT> getTop10SanPhamBanChayNhatTrongNgay() {
        listTop10SPBanChayNhat = new ArrayList<>();
        sql = "select top 10 SANPHAMCHITIET.ID_SPCT,SANPHAMCHITIET.ID_San_Pham,SANPHAMCHITIET.ID_Ram,SANPHAMCHITIET.ID_Rom,SANPHAMCHITIET.ID_Mau,SANPHAMCHITIET.ID_Model_Number,SANPHAMCHITIET.Version_HDH,SANPHAMCHITIET.Anh,SANPHAMCHITIET.Tinh_Trang,SANPHAMCHITIET.Phien_Ban,SANPHAMCHITIET.Gia_Nhap,SANPHAMCHITIET.Don_Gia,SANPHAMCHITIET.Trang_Thai,SANPHAMCHITIET.Ngay_Tao,SANPHAMCHITIET.Ngay_Sua,SANPHAMCHITIET.ID_NV,count(GIOHANGCT.IMEI) \n"
                + "from HOADON join GIOHANGCT on HOADON.ID_HD = GIOHANGCT.ID_HD join IMEI on GIOHANGCT.IMEI = IMEI.IMEI join SANPHAMCHITIET on IMEI.ID_SPCT = SANPHAMCHITIET.ID_SPCT \n"
                + "where HOADON.Trang_Thai = 1 and GIOHANGCT.Trang_Thai = 0 and DATEDIFF(DAY,GETDATE(),HOADON.Ngay_Sua) = 0\n"
                + "group by SANPHAMCHITIET.ID_SPCT,SANPHAMCHITIET.ID_San_Pham,SANPHAMCHITIET.ID_Ram,SANPHAMCHITIET.ID_Rom,SANPHAMCHITIET.ID_Mau,SANPHAMCHITIET.ID_Model_Number,SANPHAMCHITIET.Version_HDH,SANPHAMCHITIET.Anh,SANPHAMCHITIET.Tinh_Trang,SANPHAMCHITIET.Phien_Ban,SANPHAMCHITIET.Gia_Nhap,SANPHAMCHITIET.Don_Gia,SANPHAMCHITIET.Trang_Thai,SANPHAMCHITIET.Ngay_Tao,SANPHAMCHITIET.Ngay_Sua,SANPHAMCHITIET.ID_NV\n"
                + "order by count(GIOHANGCT.IMEI) desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SPCT spct = new SPCT(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12),
                        rs.getInt(13), rs.getString(14), rs.getString(15), rs.getInt(16), rs.getInt(17)
                );
                listTop10SPBanChayNhat.add(spct);
            }
            return listTop10SPBanChayNhat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public List<SPCT> getTop10SanPhamBanChayNhatAll() {
        listTop10SPBanChayNhat = new ArrayList<>();
        sql = "select top 10 SANPHAMCHITIET.ID_SPCT,SANPHAMCHITIET.ID_San_Pham,SANPHAMCHITIET.ID_Ram,SANPHAMCHITIET.ID_Rom,SANPHAMCHITIET.ID_Mau,SANPHAMCHITIET.ID_Model_Number,SANPHAMCHITIET.Version_HDH,SANPHAMCHITIET.Anh,SANPHAMCHITIET.Tinh_Trang,SANPHAMCHITIET.Phien_Ban,SANPHAMCHITIET.Gia_Nhap,SANPHAMCHITIET.Don_Gia,SANPHAMCHITIET.Trang_Thai,SANPHAMCHITIET.Ngay_Tao,SANPHAMCHITIET.Ngay_Sua,SANPHAMCHITIET.ID_NV,count(GIOHANGCT.IMEI) \n"
                + "from HOADON join GIOHANGCT on HOADON.ID_HD = GIOHANGCT.ID_HD join IMEI on GIOHANGCT.IMEI = IMEI.IMEI join SANPHAMCHITIET on IMEI.ID_SPCT = SANPHAMCHITIET.ID_SPCT \n"
                + "where HOADON.Trang_Thai = 1 and GIOHANGCT.Trang_Thai = 0\n"
                + "group by SANPHAMCHITIET.ID_SPCT,SANPHAMCHITIET.ID_San_Pham,SANPHAMCHITIET.ID_Ram,SANPHAMCHITIET.ID_Rom,SANPHAMCHITIET.ID_Mau,SANPHAMCHITIET.ID_Model_Number,SANPHAMCHITIET.Version_HDH,SANPHAMCHITIET.Anh,SANPHAMCHITIET.Tinh_Trang,SANPHAMCHITIET.Phien_Ban,SANPHAMCHITIET.Gia_Nhap,SANPHAMCHITIET.Don_Gia,SANPHAMCHITIET.Trang_Thai,SANPHAMCHITIET.Ngay_Tao,SANPHAMCHITIET.Ngay_Sua,SANPHAMCHITIET.ID_NV\n"
                + "order by count(GIOHANGCT.IMEI) desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SPCT spct = new SPCT(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12),
                        rs.getInt(13), rs.getString(14), rs.getString(15), rs.getInt(16), rs.getInt(17)
                );
                listTop10SPBanChayNhat.add(spct);
            }
            return listTop10SPBanChayNhat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<SPCT> getTop10SanPhamBanChayNhatTheoNgayDuocChon(String ngayBatDau, String ngayKetThuc) {
        listTop10SPBanChayNhat = new ArrayList<>();
        sql = "select top 10 SANPHAMCHITIET.ID_SPCT,SANPHAMCHITIET.ID_San_Pham,SANPHAMCHITIET.ID_Ram,SANPHAMCHITIET.ID_Rom,SANPHAMCHITIET.ID_Mau,SANPHAMCHITIET.ID_Model_Number,SANPHAMCHITIET.Version_HDH,SANPHAMCHITIET.Anh,SANPHAMCHITIET.Tinh_Trang,SANPHAMCHITIET.Phien_Ban,SANPHAMCHITIET.Gia_Nhap,SANPHAMCHITIET.Don_Gia,SANPHAMCHITIET.Trang_Thai,SANPHAMCHITIET.Ngay_Tao,SANPHAMCHITIET.Ngay_Sua,SANPHAMCHITIET.ID_NV,count(GIOHANGCT.IMEI) \n"
                + "from HOADON join GIOHANGCT on HOADON.ID_HD = GIOHANGCT.ID_HD join IMEI on GIOHANGCT.IMEI = IMEI.IMEI join SANPHAMCHITIET on IMEI.ID_SPCT = SANPHAMCHITIET.ID_SPCT \n"
                + "where HOADON.Trang_Thai = 1 and GIOHANGCT.Trang_Thai = 0 and DATEDIFF(DAY,hoaDon.Ngay_Sua,?) <= 0 and DATEDIFF(DAY,hoaDon.Ngay_Sua,?) >= 0\n"
                + "group by SANPHAMCHITIET.ID_SPCT,SANPHAMCHITIET.ID_San_Pham,SANPHAMCHITIET.ID_Ram,SANPHAMCHITIET.ID_Rom,SANPHAMCHITIET.ID_Mau,SANPHAMCHITIET.ID_Model_Number,SANPHAMCHITIET.Version_HDH,SANPHAMCHITIET.Anh,SANPHAMCHITIET.Tinh_Trang,SANPHAMCHITIET.Phien_Ban,SANPHAMCHITIET.Gia_Nhap,SANPHAMCHITIET.Don_Gia,SANPHAMCHITIET.Trang_Thai,SANPHAMCHITIET.Ngay_Tao,SANPHAMCHITIET.Ngay_Sua,SANPHAMCHITIET.ID_NV\n"
                + "order by count(GIOHANGCT.IMEI) desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ngayBatDau);
            ps.setString(2, ngayKetThuc);
            rs = ps.executeQuery();
            while (rs.next()) {
                SPCT spct = new SPCT(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12),
                        rs.getInt(13), rs.getString(14), rs.getString(15), rs.getInt(16), rs.getInt(17)
                );
                listTop10SPBanChayNhat.add(spct);
            }
            return listTop10SPBanChayNhat;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
