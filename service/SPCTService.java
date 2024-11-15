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
public class SPCTService {
    
    List<SPCT> listSPCT;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

//    public List<SPCT> getAllSPCTMacDinh() {
//        List<SPCT> listSPCTGetAllMacDinh = new ArrayList<>();
//        for (SPCT spct : getAllSPCTDangHoatDongVaTuMoiXuongCu()) {
//            listSPCTGetAllMacDinh.add(spct);
//        }
//        for (SPCT spct : getAllSPCTDungHoatDongVaTuMoiXuongCu()) {
//            listSPCTGetAllMacDinh.add(spct);
//        }
//        return listSPCTGetAllMacDinh;
//    }
    public List<SPCT> getAllSPCTDangHoatDongVaTuMoiXuongCu() {
        listSPCT = new ArrayList<>();
        sql = "SELECT ID_SPCT,ID_San_Pham,ID_Ram,ID_Rom,ID_Mau,ID_Model_Number,Version_HDH,Anh,Tinh_Trang,Phien_Ban,Gia_Nhap,Don_Gia,Trang_Thai,Ngay_Tao,Ngay_Sua,ID_NV FROM SANPHAMCHITIET Where Trang_Thai = 0 order by ID_SPCT desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SPCT spct = new SPCT(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12),
                        rs.getInt(13), rs.getString(14), rs.getString(15), rs.getInt(16)
                );
                listSPCT.add(spct);
            }
            return listSPCT;
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

    
    
    public List<SPCT> getAllSPCTDungHoatDongVaTuMoiXuongCu() {
        listSPCT = new ArrayList<>();
        sql = "SELECT ID_SPCT,ID_San_Pham,ID_Ram,ID_Rom,ID_Mau,ID_Model_Number,Version_HDH,Anh,Tinh_Trang,Phien_Ban,Gia_Nhap,Don_Gia,Trang_Thai,Ngay_Tao,Ngay_Sua,ID_NV FROM SANPHAMCHITIET Where Trang_Thai = 1 order by ID_SPCT desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SPCT spct = new SPCT(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12),
                        rs.getInt(13), rs.getString(14), rs.getString(15), rs.getInt(16)
                );
                listSPCT.add(spct);
            }
            return listSPCT;
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
    
    public SPCT getSPCTTheoIDSPCT(int IDSPCT) {
        sql = "SELECT ID_SPCT,ID_San_Pham,ID_Ram,ID_Rom,ID_Mau,ID_Model_Number,Version_HDH,Anh,Tinh_Trang,Phien_Ban,Gia_Nhap,Don_Gia,Trang_Thai,Ngay_Tao,Ngay_Sua,ID_NV FROM SANPHAMCHITIET Where ID_SPCT = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDSPCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                SPCT spct = new SPCT(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12),
                        rs.getInt(13), rs.getString(14), rs.getString(15), rs.getInt(16)
                );
                return spct;
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
    
    public List<SPCT> getListSapXepDonGiaTuCaoXuogThap() {
        listSPCT = new ArrayList<>();
        sql = "select * from SANPHAMCHITIET where Trang_Thai = 0 order by Don_Gia desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SPCT spct = new SPCT(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12),
                        rs.getInt(13), rs.getString(14), rs.getString(15), rs.getInt(16)
                );
                listSPCT.add(spct);
            }
            return listSPCT;
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
    
    public List<SPCT> getListSapXepDonGiaTuThapLenCao() {
        listSPCT = new ArrayList<>();
        sql = "select * from SANPHAMCHITIET where Trang_Thai = 0 order by Don_Gia";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SPCT spct = new SPCT(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12),
                        rs.getInt(13), rs.getString(14), rs.getString(15), rs.getInt(16)
                );
                listSPCT.add(spct);
            }
            return listSPCT;
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
    
    public int insertSPCT(SPCT spct) {
        sql = "insert into SANPHAMCHITIET(ID_San_Pham,ID_Ram,ID_Rom,ID_Mau,ID_Model_Number,Version_HDH,Anh,Tinh_Trang,Phien_Ban,Gia_Nhap,Don_Gia,Trang_Thai,Ngay_Tao,ID_NV)  values\n"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, spct.getID_San_Pha());
            ps.setInt(2, spct.getID_Ram());
            ps.setInt(3, spct.getID_Rom());
            ps.setInt(4, spct.getID_Mau());
            ps.setInt(5, spct.getID_Model_Number());
            if (spct.getVersion_HDH() == null || spct.getVersion_HDH().equals("")) {
                ps.setString(6, null);
            } else {
                ps.setInt(6, spct.getVersion_HDH());
            }
            if (spct.getID_Anh() == null || spct.getID_Anh().equals("")) {
                ps.setString(7, null);
            } else {
                ps.setString(7, spct.getID_Anh());
            }
            ps.setInt(8, spct.getTinh_Trang());
            ps.setInt(9, spct.getPhien_Ban());
            ps.setInt(10, spct.getGia_Nhap());
            ps.setInt(11, spct.getDon_Gia());
            ps.setInt(12, 0);
            ps.setString(13, spct.getNgay_Tao());
            ps.setInt(14, spct.getID_NV()); // chưa cập nhật id nhân viên
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
    
    public int updateSPCT(SPCT spctFromForm, int IDSPCT) {
        sql = "update SANPHAMCHITIET\n"
                + "set \n"
                + "ID_San_Pham=?,ID_Ram=?,ID_Rom=?,ID_Mau=?,ID_Model_Number=?,Version_HDH=?,Anh=?,Tinh_Trang=?,Phien_Ban=?,Gia_Nhap=?,Don_Gia=?,Ngay_Sua=? where id_SPCT = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, spctFromForm.getID_San_Pha());
            ps.setInt(2, spctFromForm.getID_Ram());
            ps.setInt(3, spctFromForm.getID_Rom());
            ps.setInt(4, spctFromForm.getID_Mau());
            ps.setInt(5, spctFromForm.getID_Model_Number());
            if (spctFromForm.getVersion_HDH() == null || spctFromForm.getVersion_HDH().equals("")) {
                ps.setString(6, null);
            } else {
                ps.setInt(6, spctFromForm.getVersion_HDH());
            }
            if (spctFromForm.getID_Anh() == null || spctFromForm.getID_Anh().equals("")) {
                ps.setString(7, null);
            } else {
                ps.setString(7, spctFromForm.getID_Anh());
            }
            ps.setInt(8, spctFromForm.getTinh_Trang());
            ps.setInt(9, spctFromForm.getPhien_Ban());
            ps.setInt(10, spctFromForm.getGia_Nhap());
            ps.setInt(11, spctFromForm.getDon_Gia());
            ps.setString(12, spctFromForm.getNgay_Sua());
            ps.setInt(13, IDSPCT);
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
    
    public int dungHoatDongSPCT(int IDSPCT) {
        sql = "update SANPHAMCHITIET\n"
                + "set Trang_Thai = 1 where ID_SPCT = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDSPCT);
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
    
    public int khoiPhucSPCT(int IDSPCT) {
        sql = "update SANPHAMCHITIET\n"
                + "set Trang_Thai = 0 where ID_SPCT = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDSPCT);
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
    
    public int getTongSoLuongOfSPCT(int IDSPCT) {
        sql = "SELECT COUNT(IMEI) FROM IMEI WHERE ID_SPCT = ? AND Trang_Thai != 3 GROUP BY ID_SPCT";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDSPCT);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
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
    
    public int getSoLuongTonKhoOfSPCT(int IDSPCT) {
        sql = "SELECT COUNT(IMEI) FROM IMEI WHERE ID_SPCT = ? AND Trang_Thai = 0 GROUP BY ID_SPCT";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDSPCT);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
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
    
    public int setTrangThaiIMEIKhiXoaSPCT(int IDSPCT) {
        sql = "update IMEI\n"
                + "set Trang_Thai = 3\n"
                + "where ID_SPCT = ? and Trang_Thai = 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDSPCT);
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
