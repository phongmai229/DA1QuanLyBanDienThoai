/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Ram;


/**
 *
 * @author Thanh Huyen Pham
 */
public class RamService {
    List<Ram> ls = new ArrayList<>();
    
    public List<Ram> getAll() {
        ls.clear();
        try{
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Ram, Dung_Luong, Trang_Thai, Ngay_Tao, Ngay_Sua from RAM order by Trang_Thai asc, ID_Ram desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Ram r = new Ram();
                r.setIdRam(rs.getInt(1));
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
    
    public List<Ram> getDangHoatDong() {
        ls.clear();
        try{
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Ram, Dung_Luong, Trang_Thai, Ngay_Tao, Ngay_Sua from RAM where Trang_Thai = 0 order by ID_Ram desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Ram r = new Ram();
                r.setIdRam(rs.getInt(1));
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
    
    public List<Ram> getKhongHoatDong() {
        ls.clear();
        try{
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Ram, Dung_Luong, Trang_Thai, Ngay_Tao, Ngay_Sua from RAM where Trang_Thai = 1 order by ID_Ram desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Ram r = new Ram();
                r.setIdRam(rs.getInt(1));
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
    
    public Ram findByID(int id) {
        try{
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_Ram, Dung_Luong, Trang_Thai, Ngay_Tao, Ngay_Sua from RAM where ID_Ram="+id;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Ram r = new Ram();
                r.setIdRam(rs.getInt(1));
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
   

    public void add(Ram r) {
        try {
            String sql = "Insert into Ram values (?,?,?,?)";
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
    
    public void update(Ram r) {
        try {
            String sql = "Update Ram set Dung_Luong=?, Trang_Thai=?, Ngay_Sua=? where ID_Ram=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(4, r.getIdRam());
            stm.setInt(1, r.getDungLuong());
            stm.setInt(2, r.getTrangThai());
            stm.setString(3, java.time.LocalDate.now().toString());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    public void delete(Ram r) {
        try {
            String sql = "Update Ram set Trang_Thai = 1 where ID_Ram=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, r.getIdRam());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void restore(Ram r) {
        try {
            String sql = "Update Ram set Trang_Thai = 0 where ID_Ram=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, r.getIdRam());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Ram getRow(int index) {
        return ls.get(index);
    }
  
}
