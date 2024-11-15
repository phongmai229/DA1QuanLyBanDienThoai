/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;

/**
 *
 * @author PHONG
 */
public class DangNhapService {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public NhanVien getNhanVienByIDNVVaMatKhau(String IDNV, String matKhau) {
        sql = "select ID_NV, Ho_Ten, SDT, Ngay_Sinh, CCCD, Dia_Chi, Gioi_Tinh , Trang_Thai, Chuc_Vu, "
                + "Ngay_Tao, Pass_word, Ngay_Sua, anh from NHANVIEN where ID_NV = ? and matKhau = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, IDNV);
            ps.setString(2, matKhau);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien r = new NhanVien();
                r.setIdNV(rs.getInt(1));
                r.setHoTen(rs.getString(2));
                r.setSdt(rs.getString(3));
                r.setNgaySinh(rs.getString(4));
                r.setCCCD(rs.getString(5));
                r.setDiaChi(rs.getString(6));
                r.setGioiTinh(rs.getBoolean(7));
                r.setTrangThai(rs.getInt(8));
                r.setChucVu(rs.getInt(9));
                r.setNgayTao(rs.getString(10));
                r.setPassword(rs.getString(11));
                r.setNgaySua(rs.getString(12));
                r.setAnh(rs.getString(13));
                return r;
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
