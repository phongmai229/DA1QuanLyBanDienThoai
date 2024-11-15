/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.IMEI;
import model.*;

/**
 *
 * @author PHONG
 */
public class KhuyenMaiService {

    List<KhuyenMai> listKhuyenMai;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<KhuyenMai> getAllKhuyenMaiTuMoiXuongCu() {
        listKhuyenMai = new ArrayList<>();
        sql = "select ID_KM,Ten_KM,Ngay_Bat_Dau,Ngay_Ket_Thuc,Ngay_Tao,ID_NV,Ghi_Chu,Ngay_Sua,dbo.F_GetTextTrangThai(Ngay_Bat_Dau,Ngay_Ket_Thuc) from KHUYENMAI order by ID_KM desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai khuyenMai = new KhuyenMai(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9));
                listKhuyenMai.add(khuyenMai);
            };
            return listKhuyenMai;
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

    public KhuyenMai getKhuyenMaiTheoIDKM(int idkm) {
        listKhuyenMai = new ArrayList<>();
        sql = "select ID_KM,Ten_KM,Ngay_Bat_Dau,Ngay_Ket_Thuc,Ngay_Tao,ID_NV,Ghi_Chu,Ngay_Sua,dbo.F_GetTextTrangThai(Ngay_Bat_Dau,Ngay_Ket_Thuc) from KHUYENMAI WHERE ID_KM = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idkm);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai khuyenMai = new KhuyenMai(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9));
                return khuyenMai;
            };
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

    public List<KhuyenMai> getAllKhuyenMaiDangDienRa() {
        listKhuyenMai = new ArrayList<>();
        sql = "select ID_KM,Ten_KM,Ngay_Bat_Dau,Ngay_Ket_Thuc,Ngay_Tao,ID_NV,Ghi_Chu,Ngay_Sua,dbo.F_GetTextTrangThai(Ngay_Bat_Dau,Ngay_Ket_Thuc) from KHUYENMAI where DATEDIFF(DAY,GETDATE(),Ngay_Bat_Dau) <= 0 and DATEDIFF(DAY,GETDATE(),Ngay_Ket_Thuc) >= 0  order by ID_KM desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai khuyenMai = new KhuyenMai(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9));
                listKhuyenMai.add(khuyenMai);
            };
            return listKhuyenMai;
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

    public List<KhuyenMai> getAllKhuyenMaiSapDienRa() {
        listKhuyenMai = new ArrayList<>();
        sql = "select ID_KM,Ten_KM,Ngay_Bat_Dau,Ngay_Ket_Thuc,Ngay_Tao,ID_NV,Ghi_Chu,Ngay_Sua,dbo.F_GetTextTrangThai(Ngay_Bat_Dau,Ngay_Ket_Thuc) from KHUYENMAI where DATEDIFF(DAY,GETDATE(),Ngay_Bat_Dau) > 0 order by ID_KM desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai khuyenMai = new KhuyenMai(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9));
                listKhuyenMai.add(khuyenMai);
            };
            return listKhuyenMai;
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

    public List<KhuyenMai> getAllKhuyenMaiDaKetThuc() {
        listKhuyenMai = new ArrayList<>();
        sql = "select ID_KM,Ten_KM,Ngay_Bat_Dau,Ngay_Ket_Thuc,Ngay_Tao,ID_NV,Ghi_Chu,Ngay_Sua,dbo.F_GetTextTrangThai(Ngay_Bat_Dau,Ngay_Ket_Thuc) from KHUYENMAI where DATEDIFF(DAY,GETDATE(),Ngay_Ket_Thuc) < 0  order by ID_KM desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai khuyenMai = new KhuyenMai(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9));
                listKhuyenMai.add(khuyenMai);
            };
            return listKhuyenMai;
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

    public int insertKhuyenMai(KhuyenMai khuyenMai) {
        sql = "insert into KHUYENMAI(Ten_KM,Ngay_Bat_Dau,Ngay_Ket_Thuc,Ngay_Tao,ID_NV,Ghi_Chu,Trang_Thai)\n"
                + "values(?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, khuyenMai.getTenKM());
            ps.setString(2, khuyenMai.getNgayBatDau());
            ps.setString(3, khuyenMai.getNgayKetThuc());
            ps.setString(4, LocalDateTime.now().toString());
            ps.setInt(5, 2); // chưa cập nhật id nhân viên
            if (khuyenMai.getGhiChu().equals("") || khuyenMai.getGhiChu() == null) {
                ps.setNull(6, java.sql.Types.VARCHAR);
            } else {
                ps.setString(6, khuyenMai.getGhiChu());
            }
            ps.setInt(7, 0);
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

    public int updateKhuyenMai(KhuyenMai khuyenMai, int idkm) {
        sql = "update KHUYENMAI\n"
                + "set Ten_KM = ?,Ngay_Bat_Dau = ?,Ngay_Ket_Thuc = ?,Ghi_Chu= ?,Ngay_Sua =? where ID_KM = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, khuyenMai.getTenKM());
            ps.setString(2, khuyenMai.getNgayBatDau());
            ps.setString(3, khuyenMai.getNgayKetThuc());
            if (khuyenMai.getGhiChu().equals("") || khuyenMai.getGhiChu() == null) {
                ps.setNull(4, java.sql.Types.VARCHAR);
            } else {
                ps.setString(4, khuyenMai.getGhiChu());
            }
            ps.setString(5, LocalDateTime.now().toString());
            ps.setInt(6, idkm);
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

    public KhuyenMai timKiemKhuyenMaiTheoIDKhuyenMai(int idkm) {
        sql = "select ID_KM,Ten_KM,Ngay_Bat_Dau,Ngay_Ket_Thuc,Ngay_Tao,ID_NV,Ghi_Chu,Ngay_Sua,dbo.F_GetTextTrangThai(Ngay_Bat_Dau,Ngay_Ket_Thuc) from KHUYENMAI where ID_KM = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idkm);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMai khuyenMai = new KhuyenMai(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9));
                return khuyenMai;
            };
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
    
}
