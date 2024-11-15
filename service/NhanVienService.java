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
 * @author Thanh Huyen Pham
 */
public class NhanVienService {

    List<NhanVien> ls = new ArrayList<>();

    public List<NhanVien> getAll() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_NV, Ho_Ten, SDT, Ngay_Sinh, CCCD, Dia_Chi, Gioi_Tinh , Trang_Thai, Chuc_Vu, "
                    + "Ngay_Tao, Pass_word, Ngay_Sua, anh from NHANVIEN order by ID_NV desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
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
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<NhanVien> getDangLam() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_NV, Ho_Ten, SDT, Ngay_Sinh, CCCD, Dia_Chi, Gioi_Tinh , Trang_Thai, Chuc_Vu, "
                    + "Ngay_Tao, Pass_word, Ngay_Sua, anh from NHANVIEN where Trang_Thai = 0 order by ID_NV desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
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
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<NhanVien> getNghiLam() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_NV, Ho_Ten, SDT, Ngay_Sinh, CCCD, Dia_Chi, Gioi_Tinh , Trang_Thai, Chuc_Vu, "
                    + "Ngay_Tao, Pass_word, Ngay_Sua, anh from NHANVIEN where Trang_Thai = 1 order by ID_NV desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
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
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<NhanVien> findDangLam(String s) {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select * from NHANVIEN where Trang_Thai = 0 and SDT like '" + s + "%' order by ID_NV desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
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
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<NhanVien> findNghiLam(String s) {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select * from NHANVIEN where Trang_Thai = 1 and SDT like '" + s + "%' order by ID_NV desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
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
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }
    
    public NhanVien findByID(int id) {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select * from NHANVIEN where ID_NV=" + id;
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
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
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NhanVien> orderByID() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_NV, Ho_Ten, SDT, Ngay_Sinh, CCCD, Dia_Chi, Gioi_Tinh , Trang_Thai, Chuc_Vu, "
                    + "Ngay_Tao, Pass_word, Ngay_Sua, anh from NHANVIEN order by ID_NV";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
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
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<NhanVien> orderByHoTen() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_NV, Ho_Ten, SDT, Ngay_Sinh, CCCD, Dia_Chi, Gioi_Tinh , Trang_Thai, Chuc_Vu, "
                    + "Ngay_Tao, Pass_word, Ngay_Sua, anh from NHANVIEN order by Ho_Ten";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
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
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<NhanVien> orderByNgaySinh() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_NV, Ho_Ten, SDT, Ngay_Sinh, CCCD, Dia_Chi, Gioi_Tinh , Trang_Thai, Chuc_Vu, "
                    + "Ngay_Tao, Pass_word, Ngay_Sua, anh from NHANVIEN order by Ngay_Sinh asc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
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
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public List<NhanVien> orderByNgayTao() {
        ls.clear();
        try {
            Connection conn = DBConnect.getConnection();
            String sql = "select ID_NV, Ho_Ten, SDT, Ngay_Sinh, CCCD, Dia_Chi, Gioi_Tinh , Trang_Thai, Chuc_Vu, "
                    + "Ngay_Tao, Pass_word, Ngay_Sua, anh from NHANVIEN order by Ngay_Tao desc, ID_NV desc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
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
                ls.add(r);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ls;
    }

    public void add(NhanVien nv) {
        try {
            String sql = "Insert into NhanVien values (?,?,?,?,?,?,?,?,?,?,?,?)";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nv.getHoTen());
            stm.setString(2, nv.getSdt());
            stm.setString(3, nv.getNgaySinh());
            stm.setString(4, nv.getCCCD());
            stm.setString(5, nv.getDiaChi());
            stm.setBoolean(6, nv.getGioiTinh());
            stm.setInt(7, nv.getTrangThai());
            stm.setInt(8, nv.getChucVu());
            stm.setString(9, java.time.LocalDate.now().toString());
            stm.setString(10, nv.getPassword());
            stm.setString(11, nv.getNgaySua());
            stm.setString(12, nv.getAnh());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(NhanVien nv) {
        try {
            String sql = "Update NhanVien set Ho_Ten=?, SDT=?, Ngay_Sinh=?, Dia_Chi=?, Gioi_Tinh=?, "
                    + "Chuc_Vu=?, Pass_word=?, Ngay_Sua=?, anh=? where ID_NV=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, nv.getHoTen());
            stm.setString(2, nv.getSdt());
            stm.setString(3, nv.getNgaySinh());
            stm.setString(4, nv.getDiaChi());
            stm.setBoolean(5, nv.getGioiTinh());
            stm.setInt(6, nv.getChucVu());
            stm.setString(7, nv.getPassword());
            stm.setString(8, java.time.LocalDate.now().toString());
            stm.setString(9, nv.getAnh());
            stm.setInt(10, nv.getIdNV());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(NhanVien nv) {
        try {
            String sql = "Update NhanVien set Trang_Thai = 1 where ID_NV=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, nv.getIdNV());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restore(NhanVien nv) {
        try {
            String sql = "Update NhanVien set Trang_Thai = 0 where ID_NV=?";
            Connection conn = DBConnect.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, nv.getIdNV());
            stm.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NhanVien getRow(int index) {
        return ls.get(index);
    }
    
    // Hàm tôi tạo để đấy cho tôi
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public NhanVien getNhanVienByIDNVVaMatKhau(String IDNV, String matKhau) {
        sql = "select ID_NV, Ho_Ten, SDT, Ngay_Sinh, CCCD, Dia_Chi, Gioi_Tinh , Trang_Thai, Chuc_Vu, "
                + "Ngay_Tao, Pass_word, Ngay_Sua, anh from NHANVIEN where ID_NV = ? and pass_word = ?";
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
