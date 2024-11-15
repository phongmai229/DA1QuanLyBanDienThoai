/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.MauSac;

/**
 *
 * @author Thanh Huyen Pham
 */
public class MauSacService {
    List<MauSac> ls = new ArrayList<>();
    
    public List<MauSac> getAll() {
        ls.clear();
        try{
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Mau, Ten_Mau, Trang_Thai, Ngay_Tao, Ngay_Sua from MAUSAC order by Trang_Thai asc, ID_Mau desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                MauSac m = new MauSac();
                m.setIdMau(rs.getInt(1));
                m.setTenMau(rs.getString(2));
                m.setTrangThai(rs.getInt(3));
                m.setNgayTao(rs.getString(4));
                m.setNgaySua(rs.getString(5));
                ls.add(m);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ls;
    }
    
    public List<MauSac> getDangHoatDong() {
        ls.clear();
        try{
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Mau, Ten_Mau, Trang_Thai, Ngay_Tao, Ngay_Sua from MauSac where Trang_Thai = 0 order by ID_Mau desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                MauSac m = new MauSac();
                m.setIdMau(rs.getInt(1));
                m.setTenMau(rs.getString(2));
                m.setTrangThai(rs.getInt(3));
                m.setNgayTao(rs.getString(4));
                m.setNgaySua(rs.getString(5));
                ls.add(m);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ls;
    }
    
    public List<MauSac> getKhongHoatDong() {
        ls.clear();
        try{
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Mau, Ten_Mau, Trang_Thai, Ngay_Tao, Ngay_Sua from MauSac where Trang_Thai = 1 order by ID_Mau desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                MauSac m = new MauSac();
                m.setIdMau(rs.getInt(1));
                m.setTenMau(rs.getString(2));
                m.setTrangThai(rs.getInt(3));
                m.setNgayTao(rs.getString(4));
                m.setNgaySua(rs.getString(5));
                ls.add(m);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ls;
    }
    
    public MauSac findByID(int id) {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select * from MauSac where ID_Mau ="+id;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                MauSac r = new MauSac();
                r.setIdMau(rs.getInt(1));
                r.setTenMau(rs.getString(2));
                r.setTrangThai(rs.getInt(3));
                r.setNgayTao(rs.getString(4));
                r.setNgaySua(rs.getString(5));
                return r;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void add(MauSac m) {
        try {
            String sql = "Insert into MauSac values (?,?,?,?)";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, m.getTenMau());
            stm.setInt(2, m.getTrangThai());
            stm.setString(3, java.time.LocalDate.now().toString());
            stm.setString(4, m.getNgaySua());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(MauSac m) {
        try {
            String sql = "Update MauSac set Ten_Mau=?, Trang_Thai=?, Ngay_Sua=? where ID_Mau=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(4, m.getIdMau());
            stm.setString(1, m.getTenMau());
            stm.setInt(2, m.getTrangThai());
            stm.setString(3, java.time.LocalDate.now().toString());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(MauSac m) {
        try {
            String sql = "Update MauSac set Trang_Thai = 1 where ID_Mau=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, m.getIdMau());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void restore(MauSac m) {
        try {
            String sql = "Update MauSac set Trang_Thai = 0 where ID_Mau=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, m.getIdMau());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public MauSac getRow(int index) {
        return ls.get(index);
    }
}
