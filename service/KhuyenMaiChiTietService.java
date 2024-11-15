/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author PHONG
 */
public class KhuyenMaiChiTietService {
    
    List<KhuyenMaiChiTiet> listKhuyenMaiChiTiet;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    
    public List<KhuyenMaiChiTiet> getAllKhuyenMaiChiTietTheoIDKM(int idkm) {
        listKhuyenMaiChiTiet = new ArrayList<>();
        sql = "select ID_KMCT,ID_KM,Dieu_Kien_SP,So_Luong,Dieu_Kien_Gia,Gia_Khuyen_Mai,Don_Vi_Khuyen_Mai,Giam_Toi_Da,Dieu_Kien_So_Luong,Trang_Thai,Ngay_Tao,Ngay_Sua,Ma_Voucher,Loai_Khuyen_Mai from KHUYENMAICT where ID_KM = ? order by ID_KMCT desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idkm);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMaiChiTiet khuyenMaiChiTiet = new KhuyenMaiChiTiet(
                        rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
                        getNullChoGetInt(rs.getString(5)), rs.getInt(6), rs.getInt(7),
                        getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getInt(10),
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14));
                listKhuyenMaiChiTiet.add(khuyenMaiChiTiet);
            }
            return listKhuyenMaiChiTiet;
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
    
    public Integer getNullChoGetInt(String text) {
        if (text == null) {
            return null;
        } else {
            return Integer.valueOf(text);
        }
    }
    
    public KhuyenMaiChiTiet getKhuyenMaiChiTietTheoMaVoucher(String maVoucher) {
        listKhuyenMaiChiTiet = new ArrayList<>();
        sql = "select ID_KMCT,ID_KM,Dieu_Kien_SP,So_Luong,Dieu_Kien_Gia,Gia_Khuyen_Mai,Don_Vi_Khuyen_Mai,Giam_Toi_Da,Dieu_Kien_So_Luong,Trang_Thai,Ngay_Tao,Ngay_Sua,Ma_Voucher,Loai_Khuyen_Mai from KHUYENMAICT where Ma_Voucher = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maVoucher);
            //ps.setInt(2, idkm);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMaiChiTiet khuyenMaiChiTiet = new KhuyenMaiChiTiet(
                        rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
                        getNullChoGetInt(rs.getString(5)), rs.getInt(6), rs.getInt(7),
                        getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getInt(10),
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14));
                return khuyenMaiChiTiet;
            }
            return null;
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
    public KhuyenMaiChiTiet getKhuyenMaiChiTietTheoIDKMCTT(int IDKMCT) {
        listKhuyenMaiChiTiet = new ArrayList<>();
        sql = "select ID_KMCT,ID_KM,Dieu_Kien_SP,So_Luong,Dieu_Kien_Gia,Gia_Khuyen_Mai,Don_Vi_Khuyen_Mai,Giam_Toi_Da,Dieu_Kien_So_Luong,Trang_Thai,Ngay_Tao,Ngay_Sua,Ma_Voucher,Loai_Khuyen_Mai from KHUYENMAICT where ID_KMCT = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDKMCT);
            //ps.setInt(2, idkm);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMaiChiTiet khuyenMaiChiTiet = new KhuyenMaiChiTiet(
                        rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
                        getNullChoGetInt(rs.getString(5)), rs.getInt(6), rs.getInt(7),
                        getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getInt(10),
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14));
                return khuyenMaiChiTiet;
            }
            return null;
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
    
    public KhuyenMaiChiTiet getKhuyenMaiChiTietTheoMaVoucherVaKiemTraKhuyenMaiDangDienRa(String maVoucher) {
        sql = "select ID_KMCT,KHUYENMAICT.ID_KM,Dieu_Kien_SP,So_Luong,Dieu_Kien_Gia,Gia_Khuyen_Mai,Don_Vi_Khuyen_Mai,\n"
                + "Giam_Toi_Da,Dieu_Kien_So_Luong,KHUYENMAICT.Trang_Thai,KHUYENMAICT.Ngay_Tao,KHUYENMAICT.Ngay_Sua,Ma_Voucher,Loai_Khuyen_Mai \n"
                + "from KHUYENMAI join KHUYENMAICT on KHUYENMAI.ID_KM = KHUYENMAICT.ID_KM  where KHUYENMAICT.Ma_Voucher = ? \n"
                + "and DATEDIFF(DAY,GETDATE(),KHUYENMAI.Ngay_Bat_Dau) <= 0 and DATEDIFF(DAY,GETDATE(),KHUYENMAI.Ngay_Ket_Thuc) >= 0 ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maVoucher);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMaiChiTiet khuyenMaiChiTiet = new KhuyenMaiChiTiet(
                        rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
                        getNullChoGetInt(rs.getString(5)), rs.getInt(6), rs.getInt(7),
                        getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getInt(10),
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14));
                return khuyenMaiChiTiet;
            }
            return null;
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
    public List<KhuyenMaiChiTiet> getKhuyenMaiChiTietCacMaVoucherDangDienRa() {
        List<KhuyenMaiChiTiet> list = new ArrayList<>();
        sql = "select ID_KMCT,KHUYENMAICT.ID_KM,Dieu_Kien_SP,So_Luong,Dieu_Kien_Gia,Gia_Khuyen_Mai,Don_Vi_Khuyen_Mai,\n"
                + "Giam_Toi_Da,Dieu_Kien_So_Luong,KHUYENMAICT.Trang_Thai,KHUYENMAICT.Ngay_Tao,KHUYENMAICT.Ngay_Sua,Ma_Voucher,Loai_Khuyen_Mai \n"
                + "from KHUYENMAI join KHUYENMAICT on KHUYENMAI.ID_KM = KHUYENMAICT.ID_KM  where "
                + "DATEDIFF(DAY,GETDATE(),KHUYENMAI.Ngay_Bat_Dau) <= 0 and DATEDIFF(DAY,GETDATE(),KHUYENMAI.Ngay_Ket_Thuc) >= 0 ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMaiChiTiet khuyenMaiChiTiet = new KhuyenMaiChiTiet(
                        rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
                        getNullChoGetInt(rs.getString(5)), rs.getInt(6), rs.getInt(7),
                        getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getInt(10),
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14));
                list.add(khuyenMaiChiTiet);
            }
            return list;
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
    
    public int insertKhuyenMaiChiTiet(KhuyenMaiChiTiet khuyenMaiChiTiet) {
        sql = "insert into khuyenmaict(ID_KM,Dieu_Kien_SP,So_Luong,Dieu_Kien_Gia,Gia_Khuyen_Mai,Don_Vi_Khuyen_Mai,Giam_Toi_Da,Dieu_Kien_So_Luong,Trang_Thai,Ngay_Tao,Ma_Voucher,Loai_Khuyen_Mai)\n"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?) ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, khuyenMaiChiTiet.getIDKM());
            if (khuyenMaiChiTiet.getDieuKienSP().equals("")) {
                ps.setNull(2, java.sql.Types.VARCHAR);
            } else {
                ps.setString(2, khuyenMaiChiTiet.getDieuKienSP());
            }
            ps.setInt(3, khuyenMaiChiTiet.getSoLuong());
            if (khuyenMaiChiTiet.getDieuKienGia() == null) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setInt(4, khuyenMaiChiTiet.getDieuKienGia());
            }
            ps.setInt(5, khuyenMaiChiTiet.getGiaKhuyenMai());
            ps.setInt(6, khuyenMaiChiTiet.getDonViKhuyenMai());
            if (khuyenMaiChiTiet.getGiamToiDa() == null) {
                ps.setNull(7, java.sql.Types.INTEGER);
            } else {
                ps.setInt(7, khuyenMaiChiTiet.getGiamToiDa());
            }
            ps.setInt(8, khuyenMaiChiTiet.getDieuKienSoLuong());
            ps.setInt(9, khuyenMaiChiTiet.getTrangThai());
            ps.setString(10, LocalDate.now().toString());
            ps.setString(11, khuyenMaiChiTiet.getMa_Voucher());
            ps.setInt(12, khuyenMaiChiTiet.getLoaiKhuyenMai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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
    
    public int updateKhuyenMaiChiTiet(KhuyenMaiChiTiet khuyenMaiChiTiet, String maVoucher) {
        sql = "update khuyenmaict\n"
                + "set Dieu_Kien_SP = ?,So_Luong= ?,Dieu_Kien_Gia=?,Gia_Khuyen_Mai=?,Don_Vi_Khuyen_Mai=?,Giam_Toi_Da= ?,\n"
                + "Dieu_Kien_So_Luong=?,Ngay_Sua =?,Ma_Voucher =?,Loai_Khuyen_Mai =?\n"
                + "where Ma_Voucher =?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            if (khuyenMaiChiTiet.getDieuKienSP().equals("")) {
                ps.setNull(1, java.sql.Types.VARCHAR);
            } else {
                ps.setString(1, khuyenMaiChiTiet.getDieuKienSP());
            }
            ps.setInt(2, khuyenMaiChiTiet.getSoLuong());
            if (khuyenMaiChiTiet.getDieuKienGia() == null) {
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setInt(3, khuyenMaiChiTiet.getDieuKienGia());
            }
            ps.setInt(4, khuyenMaiChiTiet.getGiaKhuyenMai());
            ps.setInt(5, khuyenMaiChiTiet.getDonViKhuyenMai());
            if (khuyenMaiChiTiet.getGiamToiDa() == null) {
                ps.setNull(6, java.sql.Types.INTEGER);
            } else {
                ps.setInt(6, khuyenMaiChiTiet.getGiamToiDa());
            }
            ps.setInt(7, khuyenMaiChiTiet.getDieuKienSoLuong());
            ps.setString(8, LocalDate.now().toString());
            ps.setString(9, khuyenMaiChiTiet.getMa_Voucher());
            ps.setInt(10, khuyenMaiChiTiet.getLoaiKhuyenMai());
            ps.setString(11, maVoucher);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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
    
    public int updateSoLuongKhuyenMaiChiTietTheoMaVoucher(String maVoucher, int IDKM) {
        sql = "update KHUYENMAICT\n"
                + "set So_Luong = So_Luong -1\n"
                + "where Ma_Voucher = ? and ID_KM = ? and So_Luong > 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, maVoucher);
            ps.setInt(2, IDKM);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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
    public int updateSoLuongKhuyenMaiChiTietTheoIDKMCT(int IDKMCT) {
        sql = "update KHUYENMAICT\n"
                + "set So_Luong = So_Luong -1\n"
                + "where ID_KMCT = ? and So_Luong > 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDKMCT);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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
