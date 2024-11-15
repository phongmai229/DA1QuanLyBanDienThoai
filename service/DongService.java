/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Dong;

/**
 *
 * @author Dell
 */
public class DongService {

    public List<Dong> getAll() {
        String sql = """
                     SELECT [ID_Dong]
                           ,[Trang_Thai]
                           ,[Ten_Dong]
                           ,[Ngay_Sua]
                           ,[Ngay_Tao]
                       FROM [dbo].[DONG] order by [Trang_Thai] desc ,[ID_Dong] desc 
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<Dong> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dong bn = new Dong();
                bn.setId(rs.getInt(1));
                bn.setTrangthai(rs.getInt(2));
                bn.setTen(rs.getString(3));
                bn.setNgaysua(rs.getDate(4));
                bn.setNgaytao(rs.getDate(5));

                list.add(bn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Dong> getAll1(Integer trangthai) {
        String sql = """
                     SELECT [ID_Dong]
                           ,[Trang_Thai]
                           ,[Ten_Dong]
                           ,[Ngay_Sua]
                           ,[Ngay_Tao]
                       FROM [dbo].[DONG] where [Trang_Thai] = ? order by [Trang_Thai] desc ,[ID_Dong] desc
                     """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "" + trangthai + "");
            List<Dong> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dong bn = new Dong();
                bn.setId(rs.getInt(1));
                bn.setTrangthai(rs.getInt(2));
                bn.setTen(rs.getString(3));
                bn.setNgaysua(rs.getDate(4));
                bn.setNgaytao(rs.getDate(5));

                list.add(bn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean add(Dong bn) {
        String sql = """
                   INSERT INTO [dbo].[DONG]
                               ([Trang_Thai]
                               ,[Ten_Dong]
                               ,[Ngay_Sua]
                               ,[Ngay_Tao])
                         VALUES
                               (0,?,null,getdate())
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, bn.getTen());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean sua(Dong bn, int id) {
        String sql = """                   
                     UPDATE [dbo].[DONG]
                        SET [Ten_Dong] = ?
                           ,[Ngay_Sua] = getdate()
                           
                      WHERE   ID_Dong = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, bn.getTen());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean suatrangthai(Dong bn, int id) {
        String sql = """                   
                     UPDATE [dbo].[DONG]
                        SET [Trang_Thai] = 1
                           ,[Ngay_Sua] = getdate() 
                      WHERE   ID_Dong = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean suatrangthai1(Dong bn, int id) {
        String sql = """                   
                     UPDATE [dbo].[DONG]
                        SET [Trang_Thai] = 0
                           ,[Ngay_Sua] = getdate()
                           
                      WHERE   ID_Dong = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean delete(int id) {
        String sql = """
                     DELETE FROM [dbo].[DONG]
                           WHERE  ID_Dong = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

//            public List<Dong> timkiem(String id , String ten){
//        String sql = """
//                     SELECT [ID_Dong]
//                           ,[Trang_Thai]
//                           ,[Ten_Dong]
//                           ,[Ngay_Sua]
//                           ,[Ngay_Tao]
//                       FROM [dbo].[DONG]
//                     where [ID_Dong] = ? or [Ten_Dong] = ?
//                     """;
//        try(Connection con = DBConnect.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, id);
//            ps.setObject(2, ten);
//           
//            List<Dong> lists = new ArrayList<>();
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {                
//                Dong bn = new Dong();
//                bn.setId(rs.getInt(1));
//                bn.setTrangthai(rs.getInt(2));
//                bn.setTen(rs.getString(3));
//                bn.setNgaysua(rs.getDate(4));
//                bn.setNgaytao(rs.getDate(5));
//             
//                lists.add(bn);
//            }
//            return lists;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        return null;
//    }
    //===========================PHONG===========================
    public Dong getById(int id) {
        String sql = """
               SELECT [ID_Dong]
                     ,[Trang_Thai]
                     ,[Ten_Dong]
                     ,[Ngay_Sua]
                     ,[Ngay_Tao]
                 FROM [dbo].[DONG]
                 WHERE [ID_Dong] = ?
             """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Dong dong = new Dong();
                dong.setId(rs.getInt(1));
                dong.setTrangthai(rs.getInt(2));
                dong.setTen(rs.getString(3));
                dong.setNgaysua(rs.getDate(4));
                dong.setNgaytao(rs.getDate(5));
                return dong;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
