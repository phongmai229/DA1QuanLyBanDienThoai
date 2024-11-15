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
import model.MODELNUMBER;

/**
 *
 * @author Dell
 */
public class MODELNUMBERservice {
         public List<MODELNUMBER> getAll(){
        String sql = """
                    SELECT [ID_Model_Number]
                           ,[Ky_Hieu_Model_Number]
                           ,[Dat_Nuoc]
                           ,[Trang_Thai]
                           ,[Ngay_Tao]
                           ,[Ngay_Sua]
                       FROM [dbo].[MODELNUMBER]  order by [Trang_Thai] desc ,[ID_Model_Number] desc
                     """;
        try(Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
         List<MODELNUMBER> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                MODELNUMBER mn = new MODELNUMBER();
                mn.setId(rs.getInt(1));
                mn.setKihieu(rs.getString(2));
                mn.setTrangthai(rs.getInt(4));
               mn.setNgaytao(rs.getDate(5));
             mn.setDatnuoc(rs.getString(3));
             mn.setNgaysua(rs.getDate(6));
                list.add(mn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
         public List<MODELNUMBER> getAll1(Integer trangthai){
        String sql = """
                    SELECT [ID_Model_Number]
                           ,[Ky_Hieu_Model_Number]
                           ,[Dat_Nuoc]
                           ,[Trang_Thai]
                           ,[Ngay_Tao]
                           ,[Ngay_Sua]
                       FROM [dbo].[MODELNUMBER] where [Trang_Thai] =? order by [Trang_Thai] desc ,[ID_Model_Number] desc
                     """;
        try{
            
                Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,""+trangthai+"");
         List<MODELNUMBER> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                MODELNUMBER mn = new MODELNUMBER();
                mn.setId(rs.getInt(1));
                mn.setKihieu(rs.getString(2));
                mn.setTrangthai(rs.getInt(4));
               mn.setNgaytao(rs.getDate(5));
             mn.setDatnuoc(rs.getString(3));
             mn.setNgaysua(rs.getDate(6));
                list.add(mn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
       public boolean add(MODELNUMBER mn) {
        String sql = """
                   INSERT INTO [dbo].[MODELNUMBER]
                                          ([Ky_Hieu_Model_Number]
                                          ,[Dat_Nuoc]
                                           ,[Trang_Thai]
                                          ,[Ngay_Tao]
                                          ,[Ngay_Sua])
                                    VALUES
                                          (?,?,0,getdate(),null)
                     """;
        int check = 0;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, mn.getKihieu());
         ps.setObject(2, mn.getDatnuoc());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
         public boolean sua(MODELNUMBER mn , int id){
        String sql = """                   
                    UPDATE [dbo].[MODELNUMBER]
                        SET [Ky_Hieu_Model_Number] = ?
                           ,[Dat_Nuoc] = ?
                 
                        
                           ,[Ngay_Sua] = getdate()
                      WHERE [ID_Model_Number] = ?
                     """;
        int check = 0;
        try(Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
           ps.setObject(1, mn.getKihieu());
         ps.setObject(2, mn.getDatnuoc());
        ps.setObject(3, id);
        check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
         public boolean sua1(MODELNUMBER mn , int id){
        String sql = """                   
                    UPDATE [dbo].[MODELNUMBER]
                        SET 
                           [Trang_Thai] = 1
                        
                           ,[Ngay_Sua] = getdate()
                      WHERE [ID_Model_Number] = ?
                     """;
        int check = 0;
        try(Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
           
        ps.setObject(1, id);
        check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
          public boolean sua2(MODELNUMBER mn , int id){
        String sql = """                   
                    UPDATE [dbo].[MODELNUMBER]
                        SET 
                           [Trang_Thai] = 0
                        
                           ,[Ngay_Sua] = getdate()
                      WHERE [ID_Model_Number] = ?
                     """;
        int check = 0;
        try(Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
           
        ps.setObject(1, id);
        check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
           public boolean delete(int id){
        String sql = """
                     DELETE FROM [dbo].[MODELNUMBER]
                           WHERE  [ID_Model_Number] = ?
                     """;
        int check =0;
        try(Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
//    public List<MODELNUMBER> timkiem(String id ){
//        String sql = """
//                    SELECT [ID_Model_Number]
//                                                ,[Ky_Hieu_Model_Number]
//                                                ,[Dat_Nuoc]
//                                                ,[Trang_Thai]
//                                                ,[Ngay_Tao]
//                                                ,[Ngay_Sua]
//                                            FROM [dbo].[MODELNUMBER]
//                     where [ID_Model_Number] = ? 
//                     """;
//        try(Connection con = DBConnect.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, id);
//        
//           
//            List<MODELNUMBER> lists = new ArrayList<>();
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {                
//                MODELNUMBER mn = new MODELNUMBER();
//                mn.setId(rs.getInt(1));
//                mn.setKihieu(rs.getString(2));
//                mn.setTrangthai(rs.getInt(4));
//               mn.setNgaytao(rs.getDate(5));
//             mn.setDatnuoc(rs.getString(3));
//             mn.setNgaysua(rs.getDate(6));
//                lists.add(mn);
//            }
//            return lists;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        return null;
//    }
        
           
   //======================PHONG======================
           public MODELNUMBER getById(int id) {
    String sql = """
               SELECT [ID_Model_Number]
                     ,[Ky_Hieu_Model_Number]
                     ,[Dat_Nuoc]
                     ,[Trang_Thai]
                     ,[Ngay_Tao]
                     ,[Ngay_Sua]
                 FROM [dbo].[MODELNUMBER]
                 WHERE [ID_Model_Number] = ?
             """;
    try(Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {                
            MODELNUMBER mn = new MODELNUMBER();
            mn.setId(rs.getInt(1));
            mn.setKihieu(rs.getString(2));
            mn.setDatnuoc(rs.getString(3));
            mn.setTrangthai(rs.getInt(4));
            mn.setNgaytao(rs.getDate(5));
            mn.setNgaysua(rs.getDate(6));
            return mn;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return null;
}

}
