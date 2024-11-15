/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;

/**
 *
 * @author Admin
 */
public class KhachHangService {

    List<KhachHang> ls = new ArrayList<>();

    public List<KhachHang> getAll() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_KH, ID_NV, Ho_Ten, SDT, Ngay_Sinh, Gioi_Tinh ,"
                    + " Trang_Thai, Ngay_Tao, Ngay_Sua from KHACHHANG order by ID_KH desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                KhachHang r = new KhachHang();
                r.setId(rs.getInt(1));
                r.setIdNhanVien(rs.getInt(2));
                r.setHoTen(rs.getString(3));
                r.setSDT(rs.getString(4));
                r.setNgaySinh(rs.getString(5));
                r.setGioiTinh(rs.getInt(6));
                r.setTrangThai(rs.getInt(7));
                r.setNgayTao(rs.getString(8));
                r.setNgaySua(rs.getString(9));
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<KhachHang> getDangHoatDong() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_KH, ID_NV, Ho_Ten, SDT, Ngay_Sinh, Gioi_Tinh ,"
                    + " Trang_Thai, Ngay_Tao, Ngay_Sua from KHACHHANG where Trang_Thai = 0 order by ID_KH desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                KhachHang r = new KhachHang();
                r.setId(rs.getInt(1));
                r.setIdNhanVien(rs.getInt(2));
                r.setHoTen(rs.getString(3));
                r.setSDT(rs.getString(4));
                r.setNgaySinh(rs.getString(5));
                r.setGioiTinh(rs.getInt(6));
                r.setTrangThai(rs.getInt(7));
                r.setNgayTao(rs.getString(8));
                r.setNgaySua(rs.getString(9));
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    public List<KhachHang> getKhongHoatDong() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_KH, ID_NV, Ho_Ten, SDT, Ngay_Sinh, Gioi_Tinh ,"
                    + " Trang_Thai, Ngay_Tao, Ngay_Sua from KHACHHANG where Trang_Thai = 1 order by ID_KH desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                KhachHang r = new KhachHang();
                r.setId(rs.getInt(1));
                r.setIdNhanVien(rs.getInt(2));
                r.setHoTen(rs.getString(3));
                r.setSDT(rs.getString(4));
                r.setNgaySinh(rs.getString(5));
                r.setGioiTinh(rs.getInt(6));
                r.setTrangThai(rs.getInt(7));
                r.setNgayTao(rs.getString(8));
                r.setNgaySua(rs.getString(9));
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    public List<KhachHang> findDangHoatDong(String s) {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select * from KhachHang where Trang_Thai = 0 and SDT like '" + s + "%' order by ID_KH desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                KhachHang r = new KhachHang();
                r.setId(rs.getInt(1));
                r.setIdNhanVien(rs.getInt(2));
                r.setHoTen(rs.getString(3));
                r.setSDT(rs.getString(4));
                r.setNgaySinh(rs.getString(5));
                r.setGioiTinh(rs.getInt(6));
                r.setTrangThai(rs.getInt(7));
                r.setNgayTao(rs.getString(8));
                r.setNgaySua(rs.getString(9));
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    public List<KhachHang> findKhongHoatDong(String s) {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select * from KhachHang where Trang_Thai = 1 and SDT like '" + s + "%' order by ID_KH desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                KhachHang r = new KhachHang();
                r.setId(rs.getInt(1));
                r.setIdNhanVien(rs.getInt(2));
                r.setHoTen(rs.getString(3));
                r.setSDT(rs.getString(4));
                r.setNgaySinh(rs.getString(5));
                r.setGioiTinh(rs.getInt(6));
                r.setTrangThai(rs.getInt(7));
                r.setNgayTao(rs.getString(8));
                r.setNgaySua(rs.getString(9));
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    public void add(KhachHang kh) {
        try {
            String sql = "Insert into KhachHang values (?,?,?,?,?,?,?,?)";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, kh.getIdNhanVien());
            stm.setString(2, kh.getHoTen());
            stm.setString(3, kh.getSDT());
            stm.setString(4, kh.getNgaySinh());
            stm.setInt(5, kh.getGioiTinh());
            stm.setInt(6, kh.getTrangThai());
            stm.setString(7, java.time.LocalDate.now().toString());
            stm.setString(8, kh.getNgaySua());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(KhachHang kh) {
        try {
            String sql = "Update KhachHang set ID_NV=?, Ho_Ten=?, SDT=?, Ngay_Sinh=?, Gioi_Tinh=?, Ngay_Sua=? where ID_KH=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, kh.getIdNhanVien());
            stm.setString(2, kh.getHoTen());
            stm.setString(3, kh.getSDT());
            stm.setString(4, kh.getNgaySinh());
            stm.setInt(5, kh.getGioiTinh());
            stm.setString(6, java.time.LocalDate.now().toString());
            stm.setInt(7, kh.getId());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(KhachHang kh) {
        try {
            String sql = "Update KhachHang set Trang_Thai = 1 where ID_KH=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, kh.getId());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void restore(KhachHang kh) {
        try {
            String sql = "Update KhachHang set Trang_Thai = 0 where ID_KH=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, kh.getId());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public KhachHang getRow(int index) {
        return ls.get(index);
    }
    
//    //---------------------------------------------------------------------------------------------------
//
//    public List<KhachHang> getListKhachHangById(int id) {
//        List<KhachHang> list = new ArrayList<>();
//        try {
//            Connection conn = DBConnect.getConnection();
//            String sql = "SELECT [id_kh], [ho_ten], [SDT], [ngay_sinh], [gioi_tinh], [ngay_tao], [hang], [ngay_sua], [id_nv] FROM [dbo].[khachhang] WHERE [id_kh] <> -1";
//            PreparedStatement stm = conn.prepareStatement(sql);
//            stm.setInt(1, id);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                list.add(new KhachHang(rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getInt(5),
//                        rs.getString(6),
//                        rs.getInt(7),
//                        rs.getString(8),
//                        rs.getInt(9)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    // Tôi viết hàm này để đấy cho tôi
    public KhachHang getKhachHangBySDTKhachHang(String sdt) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_KH,ID_NV,Ho_Ten,SDT,Ngay_sinh,Gioi_Tinh,Trang_Thai, Ngay_Tao,Ngay_Sua from KHACHHANG where SDT = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, sdt);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new KhachHang(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        getNullChoGetInt(rs.getString(6)),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KhachHang getKhachHangByIDKH(int IDKH) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_KH,ID_NV,Ho_Ten,SDT,Ngay_sinh,Gioi_Tinh,Trang_Thai, Ngay_Tao,Ngay_Sua from KHACHHANG where ID_KH = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, IDKH);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new KhachHang(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        getNullChoGetInt(rs.getString(6)),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getNullChoGetInt(String text) {
        if (text == null) {
            return null;
        } else {
            return Integer.valueOf(text);
        }
    }
}
