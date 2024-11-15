/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author Thanh Huyen Pham
 */
public class ThongSoManHinhService {

    List<ThongSoManHinh> ls = new ArrayList<>();

    public List<ThongSoManHinh> getAll() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Thong_So_Man_Hinh, Loai_Man_Hinh, Do_Phan_Giai, Tan_So_Quet, Trang_Thai, Ngay_Tao, Ngay_Sua from THONGSOMANHINH where Trang_Thai = 0 order by ID_Thong_So_Man_Hinh desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ThongSoManHinh r = new ThongSoManHinh();
                r.setID_ThongSoManHinh(rs.getInt(1));
                r.setLoaiManHinh(rs.getInt(2));
                r.setDoPhanGiai(rs.getInt(3));
                r.setTanSoQuet(rs.getInt(4));
                r.setTrangThai(rs.getInt(5));
                r.setNgayTao(rs.getString(6));
                r.setNgaySua(rs.getString(7));
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<ThongSoManHinh> getKhongHoatDong() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Thong_So_Man_Hinh, Loai_Man_Hinh, Do_Phan_Giai, Tan_So_Quet, Trang_Thai, Ngay_Tao, Ngay_Sua from THONGSOMANHINH where Trang_Thai = 1 order by ID_Thong_So_Man_Hinh desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ThongSoManHinh r = new ThongSoManHinh();
                r.setID_ThongSoManHinh(rs.getInt(1));
                r.setLoaiManHinh(rs.getInt(2));
                r.setDoPhanGiai(rs.getInt(3));
                r.setTanSoQuet(rs.getInt(4));
                r.setTrangThai(rs.getInt(5));
                r.setNgayTao(rs.getString(6));
                r.setNgaySua(rs.getString(7));
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public void add(ThongSoManHinh t) {
        try {
            String sql = "Insert into THONGSOMANHINH values (?,?,?,?,?,?)";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, t.getLoaiManHinh());
            stm.setInt(2, t.getDoPhanGiai());
            stm.setInt(3, t.getTanSoQuet());
            stm.setInt(4, t.getTrangThai());
            stm.setString(5, t.getNgaySua());
            stm.setString(6, java.time.LocalDate.now().toString());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(ThongSoManHinh t) {
        try {
            String sql = "Update THONGSOMANHINH set Loai_Man_Hinh=?, Do_Phan_Giai=?, Tan_So_Quet=?, Trang_Thai=?, Ngay_Sua=? where ID_Thong_So_Man_Hinh=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, t.getLoaiManHinh());
            stm.setInt(2, t.getDoPhanGiai());
            stm.setInt(3, t.getTanSoQuet());
            stm.setInt(4, t.getTrangThai());
            stm.setString(5, java.time.LocalDate.now().toString());
            stm.setInt(6, t.getID_ThongSoManHinh());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(ThongSoManHinh t) {
        try {
            String sql = "Update THONGSOMANHINH set Trang_Thai=1 where ID_Thong_So_Man_Hinh=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, t.getID_ThongSoManHinh());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restore(ThongSoManHinh t) {
        try {
            String sql = "Update THONGSOMANHINH set Trang_Thai=0 where ID_Thong_So_Man_Hinh=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, t.getID_ThongSoManHinh());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ThongSoManHinh getRow(int index) {
        return ls.get(index);
    }

    
    public ThongSoManHinh getThongSoManHinhTheoIDThongSo(int idThongSo) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Thong_So_Man_Hinh, Loai_Man_Hinh, Do_Phan_Giai, Tan_So_Quet, Trang_Thai, Ngay_Tao, Ngay_Sua from THONGSOMANHINH where ID_Thong_So_Man_Hinh = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, idThongSo);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ThongSoManHinh r = new ThongSoManHinh();
                r.setID_ThongSoManHinh(rs.getInt(1));
                r.setLoaiManHinh(rs.getInt(2));
                r.setDoPhanGiai(rs.getInt(3));
                r.setTanSoQuet(rs.getInt(4));
                r.setTrangThai(rs.getInt(5));
                r.setNgayTao(rs.getString(6));
                r.setNgaySua(rs.getString(7));
                return r;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
