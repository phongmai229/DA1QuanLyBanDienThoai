/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author PHONG
 */
public class HoaDonChiTietService {

    List<HoaDon> listHoaDon;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    List<HoaDonChiTiet> listHoaDonChiTiet;
    List<HoaDonChiTietDisplay> listHoaDonChiTietDisplay;

    public int insertHoaDonChiTiet(int IDHD, String IMEI, int donGia) {
        sql = "insert into GIOHANGCT(ID_HD,IMEI,Don_Gia,Trang_Thai) values(?,?,?,0)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDHD);
            ps.setString(2, IMEI);
            ps.setInt(3, donGia);
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

    public int xoaKhoiGioHangTheoIDSPCTVaIDHD(int idspct, int idhd) {
        sql = "update IMEI set Trang_Thai = 0 \n"
                + "where IMEI in (select GIOHANGCT.IMEI from GIOHANGCT join IMEI on GIOHANGCT.IMEI = IMEI.IMEI where GIOHANGCT.Trang_Thai = 0 and IMEI.ID_SPCT = ? and GIOHANGCT.ID_HD = ?)\n"
                + "UPDATE GIOHANGCT\n"
                + "set Trang_Thai = 1 where IMEI in\n"
                + "(select GIOHANGCT.IMEI from GIOHANGCT join IMEI on GIOHANGCT.IMEI = IMEI.IMEI where GIOHANGCT.Trang_Thai = 0 and IMEI.ID_SPCT = ? and GIOHANGCT.ID_HD = ? ) and ID_HD = ? and Trang_Thai = 0";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idspct);
            ps.setInt(2, idhd);
            ps.setInt(3, idspct);
            ps.setInt(4, idhd);
            ps.setInt(5, idhd);
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

    public int xoaTatCaKhoiGioHang(int idhd) {
        sql = "update IMEI set Trang_Thai = 0, ngay_Sua = ? where IMEI in \n"
                + "(select IMEI from GIOHANGCT where Trang_Thai = 0 and ID_HD = ?)\n"
                + "UPDATE GIOHANGCT set Trang_Thai = 1 where IMEI in\n"
                + "(select IMEI from GIOHANGCT where Trang_Thai = 0 and ID_HD = ?) and ID_HD = ? and Trang_Thai = 0";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, LocalDate.now().toString());
            ps.setInt(2, idhd);            
            ps.setInt(3, idhd);
            ps.setInt(4, idhd);
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

    public int getHoaDonChiTietTheoIDHDVaIDSPCTVaIMEI(String imei, int idspct, int idhd) {
        sql = "select GIOHANGCT.IMEI from GIOHANGCT join IMEI on GIOHANGCT.IMEI = IMEI.IMEI \n"
                + "where GIOHANGCT.Trang_Thai = 0 and IMEI.ID_SPCT = ? and GIOHANGCT.ID_HD = ? and GIOHANGCT.IMEI =?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idspct);
            ps.setInt(2, idhd);
            ps.setString(3, imei);
            rs = ps.executeQuery();
            while (rs.next()) {
                return 1;
            };
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

    public int xoaKhoiGioHangTheoIMEIVaIDSPCTVaIDHD(String imei, int idspct, int idhd) {
        sql = "update IMEI set Trang_Thai = 0, ngay_Sua = ? where IMEI = \n"
                + "(select GIOHANGCT.IMEI from GIOHANGCT join IMEI on GIOHANGCT.IMEI = IMEI.IMEI\n"
                + "where GIOHANGCT.Trang_Thai = 0 and IMEI.ID_SPCT = ? and GIOHANGCT.ID_HD = ? and GIOHANGCT.IMEI =?)\n"
                + "UPDATE GIOHANGCT\n"
                + "set Trang_Thai = 1 where IMEI = \n"
                + "(select GIOHANGCT.IMEI from GIOHANGCT join IMEI on GIOHANGCT.IMEI = IMEI.IMEI\n"
                + "where GIOHANGCT.Trang_Thai = 0 and IMEI.ID_SPCT = ? and GIOHANGCT.ID_HD = ? and GIOHANGCT.IMEI =?) and ID_HD = ? and Trang_Thai = 0";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, LocalDate.now().toString());
            ps.setInt(2, idspct);
            ps.setInt(3, idhd);
            ps.setString(4, imei);
            ps.setInt(5, idspct);
            ps.setInt(6, idhd);
            ps.setString(7, imei);
            ps.setInt(8, idhd);
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

    public List<HoaDonChiTietDisplay> getListHienThiCuaGioHang(int IDHD) {
        listHoaDonChiTietDisplay = new ArrayList<>();
        sql = "select SANPHAMCHITIET.ID_SPCT,  count(GIOHANGCT.IMEI),GIOHANGCT.Don_Gia, GIOHANGCT.Don_Gia*count(GIOHANGCT.IMEI)\n"
                + "from GIOHANGCT join IMEI on GIOHANGCT.IMEI = IMEI.IMEI join SANPHAMCHITIET on IMEI.ID_SPCT = SANPHAMCHITIET.ID_SPCT \n"
                + "where GIOHANGCT.ID_HD = ? and GIOHANGCT.Trang_Thai = 0\n"
                + "group by SANPHAMCHITIET.ID_SPCT,GIOHANGCT.Don_Gia";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietDisplay hoaDonChiTietDisplay = new HoaDonChiTietDisplay(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                listHoaDonChiTietDisplay.add(hoaDonChiTietDisplay);
            };
            return listHoaDonChiTietDisplay;
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

    public List<HoaDonChiTiet> getListHienThiCuaGioHangCoIMEI(int IDHD) {
        listHoaDonChiTiet = new ArrayList<>();
        sql = "select ID,ID_HD,IMEI,Don_Gia,Trang_Thai\n"
                + "from GIOHANGCT\n"
                + "where GIOHANGCT.ID_HD = ? and GIOHANGCT.Trang_Thai = 0 order by GIOHANGCT.ID_HD desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                listHoaDonChiTiet.add(hoaDonChiTiet);
            };
            return listHoaDonChiTiet;
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

    public List<IMEI> getListIMEITheoIDSPCTVaIDHD(int IDHD, int idspct) {
        List<IMEI> listIMEI = new ArrayList<>();
        sql = "select IMEI.id,IMEI.IMEI,IMEI.ID_SPCT,IMEI.ID_NV,IMEI.Trang_Thai,IMEI.Ngay_Tao,IMEI.Ngay_Sua \n"
                + "from GIOHANGCT join IMEI on GIOHANGCT.IMEI = IMEI.IMEI where GIOHANGCT.ID_HD = ? and IMEI.ID_SPCT = ? and GIOHANGCT.Trang_Thai = 0 order by GIOHANGCT.ID desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDHD);
            ps.setInt(2, idspct);
            rs = ps.executeQuery();
            while (rs.next()) {
                IMEI imei = new IMEI(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                listIMEI.add(imei);
            };
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
}
