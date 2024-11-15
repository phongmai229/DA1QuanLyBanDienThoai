/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Kin
 */
public class CongSac {

    Integer ID_Cong_Sac, Cong_Nghe, Trang_Thai;
    String Ten_Cong_Sac, Ngay_Tao, Ngay_Sua;

    public CongSac() {
    }

    public CongSac(Integer ID_Cong_Sac, Integer Cong_Nghe, Integer Trang_Thai, String Ten_Cong_Sac, String Ngay_Tao, String Ngay_Sua) {
        this.ID_Cong_Sac = ID_Cong_Sac;
        this.Cong_Nghe = Cong_Nghe;
        this.Trang_Thai = Trang_Thai;
        this.Ten_Cong_Sac = Ten_Cong_Sac;
        this.Ngay_Tao = Ngay_Tao;
        this.Ngay_Sua = Ngay_Sua;
    }

    public CongSac(Integer ID_Cong_Sac, Integer Cong_Nghe, Integer Trang_Thai, String Ten_Cong_Sac) {
        this.ID_Cong_Sac = ID_Cong_Sac;
        this.Cong_Nghe = Cong_Nghe;
        this.Trang_Thai = Trang_Thai;
        this.Ten_Cong_Sac = Ten_Cong_Sac;

    }

    public Integer getID_Cong_Sac() {
        return ID_Cong_Sac;
    }

    public void setID_Cong_Sac(Integer ID_Cong_Sac) {
        this.ID_Cong_Sac = ID_Cong_Sac;
    }

    public Integer getCong_Nghe() {
       return Cong_Nghe;

    }

    public void setCong_Nghe(Integer Cong_Nghe) {
        this.Cong_Nghe = Cong_Nghe;
    }

    public String getTrang_Thai() {
        if (Trang_Thai == 0) {
            return "Hoat dong";
        } else if (Trang_Thai == 1) {
            return "Khong hoat dong";
        } else {
            return "";
        }
    }

    public CongSac(String Ten_Cong_Sac, Integer Cong_Nghe, Integer Trang_Thai, String Ngay_Tao) {
        this.Ten_Cong_Sac = Ten_Cong_Sac;
        this.Cong_Nghe = Cong_Nghe;
        this.Trang_Thai = Trang_Thai;

        this.Ngay_Tao = Ngay_Tao;

    }

    public void setTrang_Thai(Integer Trang_Thai) {
        this.Trang_Thai = Trang_Thai;
    }

    public String getTen_Cong_Sac() {
        return Ten_Cong_Sac;
    }

    public void setTen_Cong_Sac(String Ten_Cong_Sac) {
        this.Ten_Cong_Sac = Ten_Cong_Sac;
    }

    public String getNgay_Tao() {
        return Ngay_Tao;
    }

    public void setNgay_Tao(String Ngay_Tao) {
        this.Ngay_Tao = Ngay_Tao;
    }

    public String getNgay_Sua() {
        return Ngay_Sua;
    }

    public void setNgay_Sua(String Ngay_Sua) {
        this.Ngay_Sua = Ngay_Sua;
    }

}
