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
import model.Camera;

/**
 *
 * @author Dell
 */
public class CameraService {

    public List<Camera> getAllCameraSau() {
        String sql = """
                   SELECT [ID_Camera]
                             ,[Loai_Camera]
                             ,[So_Luong]
                             ,[Thong_So_Ky_Thuat]
                             ,[Trang_Thai]
                             ,[Ngay_Sua]
                             ,[Ngay_Tao]
                         FROM [dbo].[CAMERAALL] where  Loai_Camera = 1
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<Camera> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Camera bn = new Camera();
                bn.setLoaicamera(rs.getInt(2));
                bn.setId(rs.getInt(1));
                bn.setThongsokithuat(rs.getInt(4));
                bn.setTrangthai(rs.getInt(5));
                bn.setSoluong(rs.getInt(3));
                bn.setNgaysua(rs.getDate(6));
                bn.setNgaytao(rs.getDate(7));

                list.add(bn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Camera> getAllCameraTruoc() {
        String sql = """
                   SELECT [ID_Camera]
                             ,[Loai_Camera]
                             ,[So_Luong]
                             ,[Thong_So_Ky_Thuat]
                             ,[Trang_Thai]
                             ,[Ngay_Sua]
                             ,[Ngay_Tao]
                         FROM [dbo].[CAMERAALL] where  Loai_Camera = 0
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<Camera> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Camera bn = new Camera();
                bn.setLoaicamera(rs.getInt(2));
                bn.setId(rs.getInt(1));
                bn.setThongsokithuat(rs.getInt(4));
                bn.setTrangthai(rs.getInt(5));
                bn.setSoluong(rs.getInt(3));
                bn.setNgaysua(rs.getDate(6));
                bn.setNgaytao(rs.getDate(7));

                list.add(bn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Camera> getAllCameraSauTheoTuMoiDenCu(int trangthai) {
        String sql = """
                   SELECT [ID_Camera]
                             ,[Loai_Camera]
                             ,[So_Luong]
                             ,[Thong_So_Ky_Thuat]
                             ,[Trang_Thai]
                             ,[Ngay_Sua]
                             ,[Ngay_Tao]
                         FROM [dbo].[CAMERAALL]
                     where  [Trang_Thai] = ? and Loai_Camera = 1 order by [ID_Camera] desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, trangthai);
            List<Camera> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Camera bn = new Camera();
                bn.setLoaicamera(rs.getInt(2));
                bn.setId(rs.getInt(1));
                bn.setThongsokithuat(rs.getInt(4));
                bn.setTrangthai(rs.getInt(5));
                bn.setSoluong(rs.getInt(3));
                bn.setNgaysua(rs.getDate(6));
                bn.setNgaytao(rs.getDate(7));

                list.add(bn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Camera> getAllCameraTruocTheoTuMoiDenCu(int trangthai) {
        String sql = """
                   SELECT [ID_Camera]
                             ,[Loai_Camera]
                             ,[So_Luong]
                             ,[Thong_So_Ky_Thuat]
                             ,[Trang_Thai]
                             ,[Ngay_Sua]
                             ,[Ngay_Tao]
                         FROM [dbo].[CAMERAALL]
                     where  [Trang_Thai] = ? and Loai_Camera = 0 order by [ID_Camera] desc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, trangthai);
            List<Camera> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Camera bn = new Camera();
                bn.setLoaicamera(rs.getInt(2));
                bn.setId(rs.getInt(1));
                bn.setThongsokithuat(rs.getInt(4));
                bn.setTrangthai(rs.getInt(5));
                bn.setSoluong(rs.getInt(3));
                bn.setNgaysua(rs.getDate(6));
                bn.setNgaytao(rs.getDate(7));

                list.add(bn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addCameraSau(Camera cm) {
        String sql = """
                   INSERT INTO [dbo].[CAMERAALL]
                              ([Loai_Camera]
                              ,[So_Luong]
                              ,[Thong_So_Ky_Thuat]
                              ,[Trang_Thai]
                              ,[Ngay_Sua]
                              ,[Ngay_Tao])
                        VALUES
                              (1,?,?,0,getdate(),getdate())
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, cm.getSoluong());
            ps.setObject(2, cm.getThongsokithuat());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean addCameraTruoc(Camera cm) {
        String sql = """
                   INSERT INTO [dbo].[CAMERAALL]
                              ([Loai_Camera]
                              ,[So_Luong]
                              ,[Thong_So_Ky_Thuat]
                              ,[Trang_Thai]
                              ,[Ngay_Sua]
                              ,[Ngay_Tao])
                        VALUES
                              (0,?,?,0,getdate(),getdate())
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, cm.getSoluong());
            ps.setObject(2, cm.getThongsokithuat());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean sua(Camera bn, int id) {
        String sql = """                   
                    UPDATE [dbo].[CAMERAALL]
                         SET 
                           [So_Luong] = ?
                            ,[Thong_So_Ky_Thuat] = ?
                            
                            ,[Ngay_Sua] = getdate()
                          
                       WHERE ID_Camera = ?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, bn.getSoluong());
            ps.setObject(2, bn.getThongsokithuat());
            ps.setObject(3, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean dungHoatDongCameraTheoID(int id) {
        String sql = """ 
                     UPDATE [dbo].[CAMERAALL]
                                              SET 
                                               [Trang_Thai] = 1
                                               ,[Ngay_Sua] = getdate()
                                            WHERE ID_Camera = ?
                   
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

    public boolean khoiPhucCameraTheoID(int id) {
        String sql = """                   
                       UPDATE [dbo].[CAMERAALL]
                         SET 
                         [Trang_Thai] = 0
                        ,[Ngay_Sua] = getdate()
                         WHERE ID_Camera = ?
                                         
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

    public boolean deleteCamera(int id) {
        String sql = """
                     DELETE FROM [dbo].[CAMERAALL]
                                 WHERE [ID_Camera] = ?
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

    public Camera getById(int id) {
        String sql = """
               SELECT [ID_Camera]
                         ,[Loai_Camera]
                         ,[So_Luong]
                         ,[Thong_So_Ky_Thuat]
                         ,[Trang_Thai]
                         ,[Ngay_Sua]
                         ,[Ngay_Tao]
                     FROM [dbo].[CAMERAALL]
                     WHERE [ID_Camera] = ?
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Camera bn = new Camera();
                bn.setLoaicamera(rs.getInt(2));
                bn.setId(rs.getInt(1));
                bn.setThongsokithuat(rs.getInt(4));
                bn.setTrangthai(rs.getInt(5));
                bn.setSoluong(rs.getInt(3));
                bn.setNgaysua(rs.getDate(6));
                bn.setNgaytao(rs.getDate(7));
                return bn;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Integer getIDCameraByIDSP(int idsp, int loaiCamera) {
        String sql = """
               SELECT Camera.ID_Camera FROM CAMERA Join CAMERAALL on CAMERA.ID_Camera = CAMERAALL.ID_Camera WHERE camera.ID_San_Pham = ? and CAMERAALL.Loai_Camera = ?
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idsp);
            ps.setInt(2, loaiCamera);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    

}
