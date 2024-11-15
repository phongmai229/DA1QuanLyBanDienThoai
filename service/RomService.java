/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Rom;

/**
 *
 * @author Thanh Huyen Pham
 */
public class RomService {
    List<Rom> ls = new ArrayList<>();
    
    public List<Rom> getAll() {
        ls.clear();
        try{
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Rom, Dung_Luong, Trang_Thai, Ngay_Tao, Ngay_Sua from ROM order by Trang_Thai asc, ID_Rom desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Rom r = new Rom();
                r.setIdRom(rs.getInt(1));
                r.setDungLuong(rs.getInt(2));
                r.setTrangThai(rs.getInt(3));
                r.setNgayTao(rs.getString(4));
                r.setNgaySua(rs.getString(5));
                ls.add(r);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ls;
    }
    
    public List<Rom> getDangHoatDong() {
        ls.clear();
        try{
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Rom, Dung_Luong, Trang_Thai, Ngay_Tao, Ngay_Sua from ROM where Trang_Thai = 0 order by ID_Rom desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Rom r = new Rom();
                r.setIdRom(rs.getInt(1));
                r.setDungLuong(rs.getInt(2));
                r.setTrangThai(rs.getInt(3));
                r.setNgayTao(rs.getString(4));
                r.setNgaySua(rs.getString(5));
                ls.add(r);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ls;
    }
    
    public List<Rom> getKhongHoatDong() {
        ls.clear();
        try{
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Rom, Dung_Luong, Trang_Thai, Ngay_Tao, Ngay_Sua from ROM where Trang_Thai = 1 order by ID_Rom desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Rom r = new Rom();
                r.setIdRom(rs.getInt(1));
                r.setDungLuong(rs.getInt(2));
                r.setTrangThai(rs.getInt(3));
                r.setNgayTao(rs.getString(4));
                r.setNgaySua(rs.getString(5));
                ls.add(r);
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return ls;
    }
    
    public Rom findByID(int id) {

        try{
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Rom, Dung_Luong, Trang_Thai, Ngay_Tao, Ngay_Sua from ROM where ID_Rom="+id;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Rom r = new Rom();
                r.setIdRom(rs.getInt(1));
                r.setDungLuong(rs.getInt(2));
                r.setTrangThai(rs.getInt(3));
                r.setNgayTao(rs.getString(4));
                r.setNgaySua(rs.getString(5));
                return r;
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
   
    
    public void add(Rom r) {
        try {
            String sql = "Insert into Rom values (?,?,?,?)";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, r.getDungLuong());
            stm.setInt(2, r.getTrangThai());
            stm.setString(3, java.time.LocalDate.now().toString());
            stm.setString(4, r.getNgaySua());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(Rom r) {
        try {
            String sql = "Update Rom set Dung_Luong=?, Trang_Thai=?, Ngay_Sua=? where ID_Rom=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(4, r.getIdRom());
            stm.setInt(1, r.getDungLuong());
            stm.setInt(2, r.getTrangThai());
            stm.setString(3, java.time.LocalDate.now().toString());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete(Rom r) {
        try {
            String sql = "Update Rom set Trang_Thai = 1 where ID_Rom=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, r.getIdRom());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void restore(Rom r) {
        try {
            String sql = "Update Rom set Trang_Thai = 0 where ID_Rom=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, r.getIdRom());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Rom getRow(int index) {
        return ls.get(index);
    }
  
}
