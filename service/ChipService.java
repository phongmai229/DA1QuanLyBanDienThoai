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
import model.Chip;

/**
 *
 * @author Dell
 */
public class ChipService {

    public List<Chip> getAll() {
        String sql = """
                     SELECT [ID_Chip]
                           ,[Trang_Thai]
                           ,[Ten_Chip]
                           ,[Ngay_Sua]
                           ,[Ngay_Tao]
                       FROM [dbo].[CHIP] order by [Trang_Thai] desc ,[ID_Chip] desc 
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<Chip> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chip bn = new Chip();
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

    public List<Chip> getAll1(Integer trangthai) {
        String sql = """
                     SELECT [ID_Chip]
                           ,[Trang_Thai]
                           ,[Ten_Chip]
                           ,[Ngay_Sua]
                           ,[Ngay_Tao]
                       FROM [dbo].[CHIP] where [Trang_Thai] = ? order by [Trang_Thai] desc ,[ID_Chip] desc
                     """;
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "" + trangthai + "");
            List<Chip> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chip bn = new Chip();
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

    public boolean add(Chip bn) {
        String sql = """
                   INSERT INTO [dbo].[CHIP]
                               ([Trang_Thai]
                               ,[Ten_Chip]
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

    public boolean sua(Chip bn, int id) {
        String sql = """                   
                     UPDATE [dbo].[CHIP]
                        SET [Ten_Chip] = ?
                           ,[Ngay_Sua] = getdate()
                           
                      WHERE   ID_Chip = ?
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

    public boolean suatrangthai(Chip bn, int id) {
        String sql = """                   
                     UPDATE [dbo].[CHIP]
                        SET [Trang_Thai] = 1
                           ,[Ngay_Sua] = getdate() 
                      WHERE   ID_Chip = ?
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

    public boolean suatrangthai1(Chip bn, int id) {
        String sql = """                   
                     UPDATE [dbo].[CHIP]
                        SET [Trang_Thai] = 0
                           ,[Ngay_Sua] = getdate()
                           
                      WHERE   ID_Chip = ?
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
                     DELETE FROM [dbo].[CHIP]
                           WHERE  ID_Chip = ?
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

    public Chip timkiemTheoID(int id) {
        String sql = """
                     SELECT [ID_Chip]
                           ,[Trang_Thai]
                           ,[Ten_Chip]
                           ,[Ngay_Sua]
                           ,[Ngay_Tao]
                       FROM [dbo].[CHIP]
                     where [ID_Chip] = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chip bn = new Chip();
                bn.setId(rs.getInt(1));
                bn.setTrangthai(rs.getInt(2));
                bn.setTen(rs.getString(3));
                bn.setNgaysua(rs.getDate(4));
                bn.setNgaytao(rs.getDate(5));

                return bn;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
