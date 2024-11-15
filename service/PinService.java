/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Chip;
import model.Pin;
import model.ThongSoManHinh;

/**
 *
 * @author Thanh Huyen Pham
 */
public class PinService {

    List<Pin> ls = new ArrayList<>();

    public List<Pin> getAll() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Pin, Loai_Pin, Dung_Luong_Pin, Trang_Thai, Ngay_Tao, Ngay_Sua from PIN where Trang_Thai = 0 order by ID_Pin desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Pin r = new Pin();
                r.setIdPin(rs.getInt(1));
                r.setLoaiPin(rs.getInt(2));
                r.setDungLuongPin(rs.getInt(3));
                r.setTrangThai(rs.getInt(4));
                r.setNgayTao(rs.getString(5));
                r.setNgaySua(rs.getString(6));
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<Pin> getKhongHoatDong() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Pin, Loai_Pin, Dung_Luong_Pin, Trang_Thai, Ngay_Tao, Ngay_Sua from PIN where Trang_Thai = 1 order by ID_Pin desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Pin r = new Pin();
                r.setIdPin(rs.getInt(1));
                r.setLoaiPin(rs.getInt(2));
                r.setDungLuongPin(rs.getInt(3));
                r.setTrangThai(rs.getInt(4));
                r.setNgayTao(rs.getString(5));
                r.setNgaySua(rs.getString(6));
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public void add(Pin p) {
        try {
            String sql = "Insert into Pin values (?,?,?,?,?)";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, p.getLoaiPin());
            stm.setInt(2, p.getDungLuongPin());
            stm.setInt(3, p.getTrangThai());
            stm.setString(5, java.time.LocalDate.now().toString());
            stm.setString(4, p.getNgaySua());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Pin p) {
        try {
            String sql = "Update PIN set Loai_Pin=?, Dung_Luong_Pin=?, Trang_Thai=?, Ngay_Sua=? where ID_Pin=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, p.getLoaiPin());
            stm.setInt(2, p.getDungLuongPin());
            stm.setInt(3, p.getTrangThai());
            stm.setString(4, java.time.LocalDate.now().toString());
            stm.setInt(5, p.getIdPin());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Pin p) {
        try {
            String sql = "Update PIN set Trang_Thai=1 where ID_Pin=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, p.getIdPin());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restore(Pin p) {
        try {
            String sql = "Update PIN set Trang_Thai=0 where ID_Pin=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, p.getIdPin());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pin getRow(int index) {
        return ls.get(index);
    }

    public Pin getPinTuIDPin(int idPin) {
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Pin, Loai_Pin, Dung_Luong_Pin, Trang_Thai, Ngay_Tao, Ngay_Sua from PIN where ID_Pin = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, idPin);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Pin r = new Pin(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                return r;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
