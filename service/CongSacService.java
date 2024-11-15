/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.CongSac;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import service.*;

/**
 *
 * @author Kin
 */
public class CongSacService {

    List<CongSac> list;
    Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    String sql = null;

//    public List<CongSac> getAll() {
//        list = new ArrayList<>();
//        try {
//
//            sql = "Select ID_Cong_Sac,Ten_Cong_Sac,Cong_Nghe,Trang_Thai from dbo.CONGSAC";
//            con = DBConnect.getConnection();
//            pstm = con.prepareStatement(sql);
//            rs = pstm.executeQuery();
//            while (rs.next()) {
//
//                CongSac cs = new CongSac(Integer.valueOf(rs.getInt(1)), rs.getInt(3), rs.getInt(4), rs.getString(2));
//                list.add(cs);
//
//            }
//            return list;
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                con.close();
//                pstm.close();
//                rs.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//
//        }
//
//    }
    public int them(CongSac cs) {
        list = new ArrayList<>();
        try {
            sql = "Insert into dbo.CongSac(Ten_Cong_Sac,Cong_Nghe,Trang_Thai,Ngay_Tao) values (?,?,?,?)";
            con = DBConnect.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, cs.getTen_Cong_Sac());
            pstm.setInt(2, cs.getCong_Nghe());
            pstm.setInt(3, 0);
            pstm.setString(4, cs.getNgay_Tao());

            return pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                con.close();
                pstm.close();

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }

    public int sua(CongSac cs, int id) {
        int kq = 0;
        sql = "Update dbo.CONGSAC set Ten_Cong_Sac=?,Cong_nghe=?,Ngay_Tao =? where ID_Cong_Sac=? ";
        try {

            con = DBConnect.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, cs.getTen_Cong_Sac());
            pstm.setInt(2, cs.getCong_Nghe());
            pstm.setString(3, LocalDate.now().toString());
            pstm.setInt(4, id);

            return kq = pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return kq = 0;
        } finally {
            try {
                con.close();
                pstm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public int xoa(int id) {
        int kq = 0;
        sql = "Update dbo.CONGSAC set Trang_Thai=1,Ngay_Tao =? where ID_Cong_Sac=? ";
        try {

            con = DBConnect.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, LocalDateTime.now().toString());
            pstm.setInt(2, id);

            return kq = pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return kq = 0;
        } finally {
            try {
                con.close();
                pstm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public int khoiPhuc(int id) {
        int kq = 0;
        sql = "Update dbo.CONGSAC set Trang_Thai=0,Ngay_Tao =? where ID_Cong_Sac=? ";
        try {

            con = DBConnect.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, LocalDate.now().toString());
            pstm.setInt(2, id);

            return kq = pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return kq = 0;
        } finally {
            try {
                con.close();
                pstm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public List<CongSac> sapXepHoatDong() {
        list = new ArrayList<>();
        try {

            sql = "Select ID_Cong_Sac,Ten_Cong_Sac,Cong_Nghe,Trang_Thai from dbo.CONGSAC where Trang_Thai = 0 order by ID_Cong_Sac desc ";
            con = DBConnect.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {

                CongSac cs = new CongSac(Integer.valueOf(rs.getInt(1)), rs.getInt(3), rs.getInt(4), rs.getString(2));
                list.add(cs);

            }
            return list;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
                pstm.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();

            }

        }

    }

    public List<CongSac> sapXepKhongHoatDong() {
        list = new ArrayList<>();
        try {

            sql = "Select ID_Cong_Sac,Ten_Cong_Sac,Cong_Nghe,Trang_Thai from dbo.CONGSAC where Trang_Thai like 1 order by ID_Cong_Sac desc ";
            con = DBConnect.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {

                CongSac cs = new CongSac(Integer.valueOf(rs.getInt(1)), rs.getInt(3), rs.getInt(4), rs.getString(2));
                list.add(cs);

            }
            return list;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
                pstm.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();

            }

        }

    }

    public CongSac getCongSacTheoIDCongSac(int IDCongSac) {
        try {
            sql = "Select ID_Cong_Sac,Ten_Cong_Sac,Cong_Nghe,Trang_Thai from dbo.CONGSAC where ID_Cong_Sac = ? ";
            con = DBConnect.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, IDCongSac);
            rs = pstm.executeQuery();
            while (rs.next()) {
                CongSac cs = new CongSac(rs.getInt(1), rs.getInt(3), rs.getInt(4), rs.getString(2));
                return cs;
            }
            return null;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
                pstm.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();

            }

        }

    }

}
