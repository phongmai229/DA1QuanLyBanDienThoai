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
public class ManHinhService {

    List<ManHinh> listManHinh;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<ManHinh> getAllManHinhDangHoatDongTuMoiXuongCu() {
        listManHinh = new ArrayList<>();
        sql = "select ID_Man_Hinh,Kich_Thuoc,Trang_Thai,Ngay_Sua,Ngay_Tao,ID_Thong_So_Man_Hinh from MANHINH where Trang_Thai = 0 order by ID_Man_Hinh desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ManHinh manHinh = new ManHinh(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                listManHinh.add(manHinh);
            }
            return listManHinh;
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

    public List<ManHinh> getAllManHinhDungHoatDongTuMoiXuongCu() {
        listManHinh = new ArrayList<>();
        sql = "select ID_Man_Hinh,Kich_Thuoc,Trang_Thai,Ngay_Sua,Ngay_Tao,ID_Thong_So_Man_Hinh from MANHINH where Trang_Thai = 1 order by ID_Man_Hinh desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ManHinh manHinh = new ManHinh(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                listManHinh.add(manHinh);
            }
            return listManHinh;
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

    public int insertManHinh(ManHinh manHinh) {
        listManHinh = new ArrayList<>();
        sql = "insert into MANHINH(Kich_Thuoc,Trang_Thai,Ngay_Tao,ID_Thong_So_Man_Hinh)\n"
                + "values (?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, manHinh.getKichThuoc());
            ps.setInt(2, 0);//set trạng thái
            ps.setString(3, LocalDate.now().toString());//set trạng thái
            ps.setInt(4, manHinh.getIDThongSoManHinh());//set trạng thái
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

    public int updateManHinh(ManHinh manHinh, int idmanhinh) {
        listManHinh = new ArrayList<>();
        sql = "update MANHINH\n"
                + "set Kich_Thuoc = ? , Ngay_Sua = ?,ID_Thong_So_Man_Hinh = ? where ID_Man_Hinh = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, manHinh.getKichThuoc());
            ps.setString(2, LocalDate.now().toString());
            ps.setInt(3, manHinh.getIDThongSoManHinh());
            ps.setInt(4, idmanhinh);//set trạng thái
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

    public int dungHoatDongManHinh(int idmanhinh) {
        listManHinh = new ArrayList<>();
        sql = "update MANHINH\n"
                + "set trang_thai = 1 where ID_Man_Hinh = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(4, idmanhinh);//set trạng thái
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

    public int khoiPhucManHinh(int idmanhinh) {
        listManHinh = new ArrayList<>();
        sql = "update MANHINH\n"
                + "set trang_thai = 0 where ID_Man_Hinh = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(4, idmanhinh);//set trạng thái
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

    public ManHinh getManHinhTheoIDManHinh(int IDManHinh) {
        sql = "select ID_Man_Hinh,Kich_Thuoc,Trang_Thai,Ngay_Sua,Ngay_Tao,ID_Thong_So_Man_Hinh from MANHINH where ID_Man_Hinh = ? ";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDManHinh);
            rs = ps.executeQuery();
            while (rs.next()) {
                ManHinh manHinh = new ManHinh(rs.getInt(1), rs.getDouble(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                return manHinh;
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

}
