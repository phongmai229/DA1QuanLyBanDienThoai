/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHONG
 */
public class HoaDonService {

    List<HoaDon> listHoaDon;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public List<HoaDon> getAllHoaDonTheoTrangThai(int trangThai) {
        listHoaDon = new ArrayList<>();
        sql = "select ID_HD,ID_NV,ID_KH,ID_KMCT,Tong_Tien,Tong_Tien_khach_Dung,Ghi_Chu,Phuong_Thuc_TT,Trang_Thai,Ngay_Tao,Ngay_Sua from HOADON where Trang_Thai = ? order by ID_HD desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, trangThai);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon(rs.getInt(1), rs.getInt(2), getNullChoGetInt(rs.getString(3)),
                        getNullChoGetInt(rs.getString(4)), getNullChoGetInt(rs.getString(5)), getNullChoGetInt(rs.getString(6)), rs.getString(7), getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getString(10), rs.getString(11));
                listHoaDon.add(hoaDon);
            }
            return listHoaDon;
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

    public List<HoaDon> getAllHoaDon() {
        listHoaDon = new ArrayList<>();
        sql = "select ID_HD,ID_NV,ID_KH,ID_KMCT,Tong_Tien,Tong_Tien_khach_Dung,Ghi_Chu,Phuong_Thuc_TT,Trang_Thai,Ngay_Tao,Ngay_Sua from HOADON order by ID_HD desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon(rs.getInt(1), rs.getInt(2), getNullChoGetInt(rs.getString(3)),
                        getNullChoGetInt(rs.getString(4)), getNullChoGetInt(rs.getString(5)), getNullChoGetInt(rs.getString(6)), rs.getString(7), getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getString(10), rs.getString(11));
                listHoaDon.add(hoaDon);
            }
            return listHoaDon;
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

    public HoaDon getHoaDonTheoIDHD(int IDHD) {
        sql = "select ID_HD,ID_NV,ID_KH,ID_KMCT,Tong_Tien,Tong_Tien_khach_Dung,Ghi_Chu,Phuong_Thuc_TT,Trang_Thai,Ngay_Tao,Ngay_Sua from HOADON where ID_HD = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon(rs.getInt(1), rs.getInt(2), getNullChoGetInt(rs.getString(3)),
                        getNullChoGetInt(rs.getString(4)), getNullChoGetInt(rs.getString(5)), getNullChoGetInt(rs.getString(6)), rs.getString(7), getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getString(10), rs.getString(11));
                return hoaDon;
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

    public int insertHoaDon(int IDNV) {
        sql = "insert into HOADON(ID_NV,Trang_Thai,Ngay_Tao) \n"
                + "values(?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, IDNV);
            ps.setInt(2, 0);
            ps.setString(3, LocalDate.now().toString());
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

    public int thanhToanHoaDon(HoaDon hoaDon) {
        sql = "update HOADON \n"
                + "set ID_KH = ?,Tong_Tien = ?,Tong_Tien_khach_Dung=?,Ghi_Chu =?,Phuong_Thuc_TT =?,Trang_Thai = 1,ID_KMCT =?,Ngay_Sua =?\n"
                + "where ID_HD = ?\n"
                + "update IMEI\n"
                + "set Trang_Thai = 2, ngay_Sua = ?\n"
                + "where IMEI in (select IMEI from GIOHANGCT where ID_HD = ? and Trang_Thai = 0)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, hoaDon.getIDKH());
            ps.setInt(2, hoaDon.getTongTienThanhToan());
            if (hoaDon.getTongTienKhachDua() == null) {
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setInt(3, hoaDon.getTongTienKhachDua());
            }
            ps.setString(4, hoaDon.getGhiChu());
            ps.setInt(5, hoaDon.getPhuongThucThanhToan());

            if (hoaDon.getID_KMCT() == null) {
                ps.setNull(6, java.sql.Types.INTEGER);
            } else {
                ps.setInt(6, hoaDon.getID_KMCT());
            }
            ps.setString(7, LocalDate.now().toString());
            ps.setInt(8, hoaDon.getIDHD());
            ps.setString(9, LocalDate.now().toString());
            ps.setInt(10, hoaDon.getIDHD());
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

    public int huyHoaDon(int IDHD, String lyDo) {
        sql = "update IMEI set Trang_Thai = 0, ngay_Sua = ?\n"
                + "where IMEI in (select IMEI from GIOHANGCT where ID_HD = ? and Trang_Thai = 0)\n"
                + "update HOADON\n"
                + "set Trang_Thai = 2, ngay_Sua = ?, ghi_Chu = ?\n"
                + "where ID_HD = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, LocalDate.now().toString());
            ps.setInt(2, IDHD);
            ps.setString(3, LocalDate.now().toString());
            ps.setString(4, lyDo);
            ps.setInt(5, IDHD);
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

    public int capNhatGhiChu(int IDHD, String ghiChu) {
        sql = "update HOADON\n"
                + "set ghi_chu = ?,ngay_sua = ?\n"
                + "where ID_HD = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ghiChu);
            ps.setString(2, LocalDate.now().toString());
            ps.setInt(3, IDHD);
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

//    public List<HoaDon> getHoaDonTheoSDTKHVaTrangThai(String SDT, int trangThai) {
//        listHoaDon = new ArrayList<>();
//        sql = "select HOADON.ID_HD,HOADON.ID_NV,HOADON.ID_KH,HOADON.ID_KMCT,HOADON.Tong_Tien,HOADON.Tong_Tien_khach_Dung,HOADON.Ghi_Chu,HOADON.Phuong_Thuc_TT,HOADON.Trang_Thai,HOADON.Ngay_Tao,HOADON.Ngay_Sua \n"
//                + "from HOADON join KHACHHANG on HOADON.ID_KH  = KHACHHANG.ID_KH where KHACHHANG.SDT = ? and HOADON.Trang_thai = ? order by ID_HD desc";
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, SDT);
//            ps.setInt(2, trangThai);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDon hoaDon = new HoaDon(rs.getInt(1), rs.getInt(2), getNullChoGetInt(rs.getString(3)),
//                        getNullChoGetInt(rs.getString(4)), getNullChoGetInt(rs.getString(5)), getNullChoGetInt(rs.getString(6)), rs.getString(7), getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getString(10), rs.getString(11));
//                listHoaDon.add(hoaDon);
//            }
//            return listHoaDon;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                rs.close();
//                ps.close();
//                con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public List<HoaDon> getHoaDonTheoSDTKH(String SDT) {
//        listHoaDon = new ArrayList<>();
//        sql = "select HOADON.ID_HD,HOADON.ID_NV,HOADON.ID_KH,HOADON.ID_KMCT,HOADON.Tong_Tien,HOADON.Tong_Tien_khach_Dung,HOADON.Ghi_Chu,HOADON.Phuong_Thuc_TT,HOADON.Trang_Thai,HOADON.Ngay_Tao,HOADON.Ngay_Sua \n"
//                + "from HOADON join KHACHHANG on HOADON.ID_KH  = KHACHHANG.ID_KH where KHACHHANG.SDT = ? order by ID_HD desc";
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, SDT);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDon hoaDon = new HoaDon(rs.getInt(1), rs.getInt(2), getNullChoGetInt(rs.getString(3)),
//                        getNullChoGetInt(rs.getString(4)), getNullChoGetInt(rs.getString(5)), getNullChoGetInt(rs.getString(6)), rs.getString(7), getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getString(10), rs.getString(11));
//                listHoaDon.add(hoaDon);
//            }
//            return listHoaDon;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                rs.close();
//                ps.close();
//                con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public List<HoaDon> getHoaDonTheoSDTKHVaTrangThaiVaTheoNgay(String SDT, int trangThai, String ngayBatDau, String ngayKetThuc) {
//        listHoaDon = new ArrayList<>();
//        sql = "select HOADON.ID_HD,HOADON.ID_NV,HOADON.ID_KH,HOADON.ID_KMCT,HOADON.Tong_Tien,HOADON.Tong_Tien_khach_Dung,HOADON.Ghi_Chu,HOADON.Phuong_Thuc_TT,HOADON.Trang_Thai,HOADON.Ngay_Tao,HOADON.Ngay_Sua \n"
//                + "from HOADON join KHACHHANG on HOADON.ID_KH  = KHACHHANG.ID_KH where KHACHHANG.SDT = ? and HOADON.Trang_thai = ? and DATEDIFF(DAY,HOADON.Ngay_Tao,?) <= 0 and DATEDIFF(DAY,HOADON.Ngay_Tao,?) >= 0 order by ID_HD desc";
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, SDT);
//            ps.setInt(2, trangThai);
//            ps.setString(3, ngayBatDau);
//            ps.setString(4, ngayKetThuc);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDon hoaDon = new HoaDon(rs.getInt(1), rs.getInt(2), getNullChoGetInt(rs.getString(3)),
//                        getNullChoGetInt(rs.getString(4)), getNullChoGetInt(rs.getString(5)), getNullChoGetInt(rs.getString(6)), rs.getString(7), getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getString(10), rs.getString(11));
//                listHoaDon.add(hoaDon);
//            }
//            return listHoaDon;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                rs.close();
//                ps.close();
//                con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public List<HoaDon> getHoaDonTheoSDTKHTVaTheoNgay(String SDT, String ngayBatDau, String ngayKetThuc) {
//        listHoaDon = new ArrayList<>();
//        sql = "select HOADON.ID_HD,HOADON.ID_NV,HOADON.ID_KH,HOADON.ID_KMCT,HOADON.Tong_Tien,HOADON.Tong_Tien_khach_Dung,HOADON.Ghi_Chu,HOADON.Phuong_Thuc_TT,HOADON.Trang_Thai,HOADON.Ngay_Tao,HOADON.Ngay_Sua \n"
//                + "from HOADON join KHACHHANG on HOADON.ID_KH  = KHACHHANG.ID_KH where KHACHHANG.SDT = ? and DATEDIFF(DAY,HOADON.Ngay_Tao,?) <= 0 and DATEDIFF(DAY,HOADON.Ngay_Tao,?) >= 0 order by ID_HD desc";
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, SDT);
//            ps.setString(2, ngayBatDau);
//            ps.setString(3, ngayKetThuc);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDon hoaDon = new HoaDon(rs.getInt(1), rs.getInt(2), getNullChoGetInt(rs.getString(3)),
//                        getNullChoGetInt(rs.getString(4)), getNullChoGetInt(rs.getString(5)), getNullChoGetInt(rs.getString(6)), rs.getString(7), getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getString(10), rs.getString(11));
//                listHoaDon.add(hoaDon);
//            }
//            return listHoaDon;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                rs.close();
//                ps.close();
//                con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public List<HoaDon> getHoaDonTheoTrangThaiVaTheoNgay(int trangThai, String ngayBatDau, String ngayKetThuc) {
//        listHoaDon = new ArrayList<>();
//        sql = "select HOADON.ID_HD,HOADON.ID_NV,HOADON.ID_KH,HOADON.ID_KMCT,HOADON.Tong_Tien,HOADON.Tong_Tien_khach_Dung,HOADON.Ghi_Chu,HOADON.Phuong_Thuc_TT,HOADON.Trang_Thai,HOADON.Ngay_Tao,HOADON.Ngay_Sua \n"
//                + "from HOADON join KHACHHANG on HOADON.ID_KH  = KHACHHANG.ID_KH where HOADON.Trang_thai = ? and DATEDIFF(DAY,HOADON.Ngay_Tao,?) <= 0 and DATEDIFF(DAY,HOADON.Ngay_Tao,?) >= 0 order by ID_HD desc";
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setInt(1, trangThai);
//            ps.setString(2, ngayBatDau);
//            ps.setString(3, ngayKetThuc);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                HoaDon hoaDon = new HoaDon(rs.getInt(1), rs.getInt(2), getNullChoGetInt(rs.getString(3)),
//                        getNullChoGetInt(rs.getString(4)), getNullChoGetInt(rs.getString(5)), getNullChoGetInt(rs.getString(6)), rs.getString(7), getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getString(10), rs.getString(11));
//                listHoaDon.add(hoaDon);
//            }
//            return listHoaDon;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                rs.close();
//                ps.close();
//                con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public List<HoaDon> getAllHoaDonTheoNgay(String ngayBatDau, String ngayKetThuc) {
        listHoaDon = new ArrayList<>();
        sql = "select ID_HD,ID_NV,ID_KH,ID_KMCT,Tong_Tien,Tong_Tien_khach_Dung,Ghi_Chu,Phuong_Thuc_TT,Trang_Thai,Ngay_Tao,Ngay_Sua from HOADON where DATEDIFF(DAY,HOADON.Ngay_Tao,?) <= 0 and DATEDIFF(DAY,HOADON.Ngay_Tao,?) >= 0 order by ID_HD desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ngayBatDau);
            ps.setString(2, ngayKetThuc);
            rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon(rs.getInt(1), rs.getInt(2), getNullChoGetInt(rs.getString(3)),
                        getNullChoGetInt(rs.getString(4)), getNullChoGetInt(rs.getString(5)), getNullChoGetInt(rs.getString(6)), rs.getString(7), getNullChoGetInt(rs.getString(8)), rs.getInt(9), rs.getString(10), rs.getString(11));
                listHoaDon.add(hoaDon);
            }
            return listHoaDon;
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

    public Integer getNullChoGetInt(String text) {
        if (text == null) {
            return null;
        } else {
            return Integer.parseInt(text);
        }
    }
}
