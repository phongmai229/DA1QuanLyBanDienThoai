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
 * @author PHONG
 */
public class SanPhamService {

    List<SanPham> listSanPham;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<SanPham> getAllSanPhamTheoTuMoiDenCu() {
        listSanPham = new ArrayList<>();
        sql = "SELECT * FROM SANPHAM order by ID_San_Pham desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13));
                listSanPham.add(sanPham);
            }
            return listSanPham;
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

//PHONG Tạo
    public SanPham getSanPhamFromIDSP(int idSanPham) {
        sql = "select * from SANPHAM where ID_San_Pham = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idSanPham);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new SanPham(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13));
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

    public List<SanPham> getAllSanPhamDangHoatDongTuMoiDenCu() {
        listSanPham = new ArrayList<>();
        sql = "SELECT * FROM SANPHAM WHERE Trang_Thai = 0 order by ID_San_Pham desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13));
                listSanPham.add(sanPham);
            }
            return listSanPham;
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
    public List<SanPham> getAllSanPhamTheoTrangThaiTuMoiDenCu(int trangThai) {
        listSanPham = new ArrayList<>();
        sql = "SELECT * FROM SANPHAM WHERE Trang_Thai = ? order by ID_San_Pham desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, trangThai);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13));
                listSanPham.add(sanPham);
            }
            return listSanPham;
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

    public List<SanPham> getAllSanPhamDungHoatDongTuMoiDenCu() {
        listSanPham = new ArrayList<>();
        sql = "SELECT * FROM SANPHAM WHERE Trang_Thai = 1 order by ID_San_Pham desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13));
                listSanPham.add(sanPham);
            }
            return listSanPham;
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

    public int insertSP(SanPham sp) {
        sql = "insert into SANPHAM(ID_Chip,ID_Pin,ID_Man_Hinh,ID_Cong_Sac,Ho_Tro_Mang,The_He,So_Luong_Sim,Trang_Thai,Ngay_Tao,ID_Dong,ID_NV)\n"
                + "values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sp.getID_Chip());
            ps.setInt(2, sp.getID_Pin());
            ps.setInt(3, sp.getID_Man_Hinh());
            ps.setInt(4, sp.getID_Cong_Sac());
            ps.setInt(5, sp.getHo_Tro_Mang());
            ps.setInt(6, sp.getThe_He());
            ps.setInt(7, sp.getSo_Luong_Sim());
            ps.setInt(8, 0);
            ps.setString(9, sp.getNgay_Tao());
            ps.setInt(10, sp.getID_Dong());
            ps.setInt(11, sp.getID_NV()); // chua cap nhat id nhanvien
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

    public int insertCamera(int IDCameraTrc, int IDCameraSau) {
        sql = "INSERT INTO CAMERA(ID_Camera,ID_San_Pham) VALUES (?,(select max(ID_San_Pham) from SANPHAM))"
                + "INSERT INTO CAMERA(ID_Camera,ID_San_Pham) VALUES (?,(select max(ID_San_Pham) from SANPHAM))";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDCameraTrc);
            ps.setInt(2, IDCameraSau);
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

    public int updateCamera(int IDSP, int IDCameraTrc, int IDCameraSau) {
        sql = "delete CAMERA where ID_San_Pham = ?\n"
                + "INSERT INTO CAMERA(ID_Camera,ID_San_Pham) VALUES (?,?)\n"
                + "INSERT INTO CAMERA(ID_Camera,ID_San_Pham) VALUES (?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDSP);
            ps.setInt(2, IDCameraTrc);
            ps.setInt(3, IDSP);
            ps.setInt(4, IDCameraSau);
            ps.setInt(5, IDSP);
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

    public int updateSP(SanPham sp, int idSP) {
        sql = "update SANPHAM\n"
                + "set ID_Chip = ?,ID_Pin =?,ID_Man_Hinh =?,ID_Cong_Sac = ?,Ho_Tro_Mang = ?,The_He =?,So_Luong_Sim = ?,Ngay_Sua = ?,ID_Dong =? where ID_San_Pham = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sp.getID_Chip());
            ps.setInt(2, sp.getID_Pin());
            ps.setInt(3, sp.getID_Man_Hinh());
            ps.setInt(4, sp.getID_Cong_Sac());
            ps.setInt(5, sp.getHo_Tro_Mang());
            ps.setInt(6, sp.getThe_He());
            ps.setInt(7, sp.getSo_Luong_Sim());
            ps.setString(8, sp.getNgay_Sua());
            ps.setInt(9, sp.getID_Dong());
            ps.setInt(10, idSP);
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

    public int dungHoatDongSP(int idSP) {
        sql = "update SANPHAM \n"
                + "set Trang_Thai = 1 where ID_San_Pham = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idSP);
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

    public int khoiPhucSP(int idSP) {
        sql = "update SANPHAM \n"
                + "set Trang_Thai = 0 where ID_San_Pham = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idSP);
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
