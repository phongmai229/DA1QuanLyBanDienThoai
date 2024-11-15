/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author PHONG
 */
public class IMEIService {

    List<IMEI> listIMEI;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<IMEI> getAllIMEIDangBanVaTuMoiXuongCu() {
        listIMEI = new ArrayList<>();
        sql = "select id,IMEI,ID_SPCT,ID_NV,Trang_Thai,Ngay_Tao,Ngay_Sua from IMEI where Trang_Thai = 0 order by id desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                listIMEI.add(imei);
            }
            return listIMEI;
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

    public List<IMEI> getListIMEIDangBanTheoIDSPCT(int idspct) {
        listIMEI = new ArrayList<>();
        sql = "select IMEI.id, IMEI.IMEI,IMEI.ID_SPCT,IMEI.ID_NV,IMEI.Trang_Thai,IMEI.Ngay_Tao,IMEI.Ngay_Sua  from IMEI where ID_SPCT = ? and Trang_Thai = 0 order by IMEI.id desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idspct);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                listIMEI.add(imei);
            }
            return listIMEI;
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

    public List<IMEI> getListIMEIDangTrongGioHangTheoIDSPCT(int idspct) {
        listIMEI = new ArrayList<>();
        sql = "select IMEI.id, IMEI.IMEI,IMEI.ID_SPCT,IMEI.ID_NV,IMEI.Trang_Thai,IMEI.Ngay_Tao,IMEI.Ngay_Sua  from IMEI where ID_SPCT = ? and Trang_Thai = 1 order by IMEI.id desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idspct);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                listIMEI.add(imei);
            }
            return listIMEI;
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

    public List<IMEI> getListIMEIDaBanTheoIDSPCT(int idspct) {
        listIMEI = new ArrayList<>();
        sql = "select IMEI.id, IMEI.IMEI,IMEI.ID_SPCT,IMEI.ID_NV,IMEI.Trang_Thai,IMEI.Ngay_Tao,IMEI.Ngay_Sua  from IMEI where ID_SPCT = ? and Trang_Thai = 2 order by IMEI.id desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idspct);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                listIMEI.add(imei);
            }
            return listIMEI;
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

    public List<IMEI> getListIMEIDaHuyYheoIDSPCT(int idspct) {
        listIMEI = new ArrayList<>();
        sql = "select IMEI.id, IMEI.IMEI,IMEI.ID_SPCT,IMEI.ID_NV,IMEI.Trang_Thai,IMEI.Ngay_Tao,IMEI.Ngay_Sua  from IMEI where ID_SPCT = ? and Trang_Thai = 3 order by IMEI.id desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idspct);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                listIMEI.add(imei);
            }
            return listIMEI;
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

    public List<IMEI> getAllIMEIDangTrongGioHangVaTuMoiXuongCu() {
        listIMEI = new ArrayList<>();
        sql = "select id,IMEI,ID_SPCT,ID_NV,Trang_Thai,Ngay_Tao,Ngay_Sua from IMEI where Trang_Thai = 1 order by id desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                listIMEI.add(imei);
            }
            return listIMEI;
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

    public List<IMEI> getAllIMEIDaBanVaTuMoiXuongCu() {
        listIMEI = new ArrayList<>();
        sql = "select id,IMEI,ID_SPCT,ID_NV,Trang_Thai,Ngay_Tao,Ngay_Sua from IMEI where Trang_Thai = 2 order by id desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                listIMEI.add(imei);
            }
            return listIMEI;
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

    public List<IMEI> getAllIMEIDaHuyVaTuMoiXuongCu() {
        listIMEI = new ArrayList<>();
        sql = "select id,IMEI,ID_SPCT,ID_NV,Trang_Thai,Ngay_Tao,Ngay_Sua from IMEI where Trang_Thai = 3 order by id desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                listIMEI.add(imei);
            }
            return listIMEI;
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

    public IMEI getIMEITheoIMEI(String IMEI) {
        listIMEI = new ArrayList<>();
        sql = "select id,IMEI,ID_SPCT,ID_NV,Trang_Thai,Ngay_Tao,Ngay_Sua from IMEI where IMEI = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, IMEI);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                return imei;
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

    public IMEI getIMEITheoIMEIVaIDSPCTDeThemVaoGioHang(String IMEI, int idspct) {
        listIMEI = new ArrayList<>();
        sql = "select id,IMEI,ID_SPCT,ID_NV,Trang_Thai,Ngay_Tao,Ngay_Sua from IMEI where IMEI = ? and ID_SPCT = ? and Trang_Thai = 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, IMEI);
            ps.setInt(2, idspct);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                return imei;
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

    public List<IMEI> getAllIMEI() {
        listIMEI = new ArrayList<>();
        sql = "select id,IMEI,ID_SPCT,ID_NV,Trang_Thai,Ngay_Tao,Ngay_Sua from IMEI";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                listIMEI.add(imei);
            }
            return listIMEI;
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

    public int insertIMEI(String IMEI, int IDSPCT, int IDNV) {
        sql = "INSERT INTO IMEI(IMEI,ID_SPCT,Ngay_Tao,ID_NV,Trang_Thai)\n"
                + "VALUES(?,?,?,?,0)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, IMEI);
            ps.setInt(2, IDSPCT);
            ps.setString(3, LocalDateTime.now().toString());
            ps.setInt(4, IDNV); // chưa cập nhật id nhân viên
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

    public int updateIMEI(String IMEIMoi, String IMEICu) {
        sql = " update IMEI\n"
                + "  set imei = ?, Ngay_Sua = ?  where imei = ? and Trang_Thai != 3";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, IMEIMoi);
            ps.setString(2, LocalDateTime.now().toString());
            ps.setString(2, IMEICu);
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

    public int deleteIMEI(String IMEI) {
        sql = "update IMEI\n"
                + "set Trang_Thai = 3 where imei = ? and Trang_Thai = 0";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, IMEI);
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

    public int khoiPhucIMEI(String IMEI) {
        sql = "update IMEI\n"
                + "set Trang_Thai = 0 where imei = ? and Trang_Thai = 3";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, IMEI);
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

    public int updateTrangThaiIMEI(int trangThai, String IMEI) {
        sql = "update IMEI\n"
                + "set Trang_Thai = ? where imei = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, trangThai);
            ps.setString(2, IMEI);
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
