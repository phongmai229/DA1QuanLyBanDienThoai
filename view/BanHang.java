/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.*;
import service.*;

/**
 *
 * @author PHONG
 */
public class BanHang extends javax.swing.JFrame {

    /**
     * Creates new form BanHang
     */
    //SỬA LẠI ID KHACH HÀNG KHÁCH VÃNG LAI
    //Khai báo 3 model của bảng
    DefaultTableModel defaultTableModelHoaDon;
    DefaultTableModel defaultTableModelGioHang;
    DefaultTableModel defaultTableModelSanPham;
    // Khai báo các đối tượng service
    HoaDonService hoaDonService = new HoaDonService();
    SPCTService spctService = new SPCTService();
    SanPhamService sanPhamService = new SanPhamService();
    DongService dongService = new DongService();
    MauSacService mauSacService = new MauSacService();
    RamService ramService = new RamService();
    ChipService chipService = new ChipService();
    RomService romService = new RomService();
    ManHinhService manHinhService = new ManHinhService();
    ThongSoManHinhService thongSoManHinhService = new ThongSoManHinhService();
    MODELNUMBERservice modelnumberService = new MODELNUMBERservice();
    CongSacService congSacService = new CongSacService();
    CameraService cameraService = new CameraService();
    PinService pinService = new PinService();
    IMEIService imeiService = new IMEIService();
    NhanVienService nhanVienService = new NhanVienService();
    HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();
    KhuyenMaiChiTietService khuyenMaiChiTietService = new KhuyenMaiChiTietService();
    KhachHangService khachHangService = new KhachHangService();
    //Khai báo listOfTable của sản phẩm
    List<SPCT> listSPCTOfTable;
    //Khai báo biến để nhận IDNV
    int IDNV = 2; // sửa lại
    // Tạo ra định dạng ngày
    SimpleDateFormat sdfNgayThangNam = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdfNamThangNgay = new SimpleDateFormat("yyyy-MM-dd");

    public BanHang() {
        initComponents();
        setLocationRelativeTo(null);
        // lấy 3 model bảng
        defaultTableModelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        defaultTableModelGioHang = (DefaultTableModel) tblGioHang.getModel();
        defaultTableModelSanPham = (DefaultTableModel) tblSanPham.getModel();
        // Load lên form những bảng mặc định
        loadTableHoaDon(hoaDonService.getAllHoaDonTheoTrangThai(0));// bảng hóa đơn trạng thái chờ thanh toán
        loadTableSanPham(spctService.getAllSPCTDangHoatDongVaTuMoiXuongCu(), 1);// bảng sản phẩm những spct trạng thái đang hoạt động(Có phân trang)
        // Xóa và vô hiệu hóa toàn bộ panel sản phẩm và thông tin hóa đơn
        xoaFormHoaDon();
    }

    //Hàm loadTable có phân trang 5 sản phẩm / 1 trang
    public void loadTableSanPham(List<SPCT> list, int soTrang) {
        listSPCTOfTable = new ArrayList<>(list);// lấy list mỗi khi loadTable
        defaultTableModelSanPham.setRowCount(0);//set về 0
        int dem = (soTrang - 1) * 5; // tính phần tử của mỗi trang
        for (int i = dem; i < soTrang * 5; i++) {
            if (i < list.size()) {
                SPCT spct = list.get(i);
                int soLuongTonKho = spctService.getSoLuongTonKhoOfSPCT(spct.getID_SPCT());
                SanPham sanPham = sanPhamService.getSanPhamFromIDSP(spct.getID_San_Pha());
                Dong dong = dongService.getById(sanPham.getID_Dong());
                MauSac mauSac = mauSacService.findByID(spct.getID_Mau());
                Ram ram = ramService.findByID(spct.getID_Ram());
                Rom rom = romService.findByID(spct.getID_Rom());
                Chip chip = chipService.timkiemTheoID(sanPham.getID_Chip());
                MODELNUMBER modelnumber = modelnumberService.getById(spct.getID_Model_Number());
                String tenSanPham = "IPhone " + sanPham.getThe_He() + " " + getTextTenDong(dong.getTen()) + " " + getTextPhienBan(spct.getPhien_Ban()) + " " + modelnumber.getKihieu();
                defaultTableModelSanPham.addRow(new Object[]{
                    ++dem,
                    spct.getID_SPCT(),
                    tenSanPham.replace("  ", " "),
                    mauSac.getTenMau(),
                    getTextTinhTrangFromInt(spct.getTinh_Trang()),
                    getTextRamFromDungLuong(ram.getDungLuong()),
                    getTextRomFromDungLuong(rom.getDungLuong()),
                    soLuongTonKho,// lấy số lượng tồn kho của spct theo IMEI
                    getTextGia(spct.getDon_Gia()) + "đ"
                });
            }
        }
        lblTrang.setText(soTrang + "/" + (int) Math.ceil((double) list.size() / 5));
    }

    // Hàm loadTableHoaDon
    public void loadTableHoaDon(List<HoaDon> list) {
        defaultTableModelHoaDon.setRowCount(0);
        int dem = 0;
        // xóa form hóa đơn
        xoaFormHoaDon();
        try {
            for (HoaDon hoaDon : list) {
                Date ngayTaoHoaDonChuyenDoi = sdfNamThangNgay.parse(hoaDon.getNgayTao());//lấy date và chuyển đổi
                defaultTableModelHoaDon.addRow(new Object[]{
                    ++dem,
                    hoaDon.getIDHD(),
                    sdfNgayThangNam.format(ngayTaoHoaDonChuyenDoi),//chuyển đổi
                    getTextTrangThaiHoaDon(hoaDon.getTrangThai()),});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm loadTableGioHang
    public void loadTableGioHang(List<HoaDonChiTiet> list) {
        defaultTableModelGioHang.setRowCount(0);
        int dem = 0;
        // mặc định btnXoaTatCa disable
        btnXoaTatCaGioHang.setEnabled(false);
        // Tạo ra một số biến để lấy thông tin cho form hóa đơn
        int tongTienHang = 0;
        for (int i = 0; i < list.size(); i++) {
            // Khai báo các đội tượng model để lấy thông tin
            SPCT spct = spctService.getSPCTTheoIDSPCT(imeiService.getIMEITheoIMEI(list.get(i).getIMEI()).getID_SPCT());
            SanPham sanPham = sanPhamService.getSanPhamFromIDSP(spct.getID_San_Pha());
            Ram ram = ramService.findByID(spct.getID_Ram());
            Rom rom = romService.findByID(spct.getID_Rom());
            MODELNUMBER modelnumber = modelnumberService.getById(spct.getID_Model_Number());
            MauSac mauSac = mauSacService.findByID(spct.getID_Mau());
            Dong dong = dongService.getById(sanPham.getID_Dong());
            Chip chip = chipService.timkiemTheoID(sanPham.getID_Chip());
            String tenFullSanPham = "IPhone "
                    + sanPham.getThe_He() + " "
                    + getTextTenDong(dong.getTen()) + " "
                    + getTextPhienBan(spct.getPhien_Ban()) + " "
                    + modelnumber.getKihieu() + " "
                    + mauSac.getTenMau() + " "
                    + getTextTinhTrangFromInt(spct.getTinh_Trang()) + " "
                    + getTextRamFromDungLuong(ram.getDungLuong()) + " "
                    + getTextRomFromDungLuong(rom.getDungLuong());
            defaultTableModelGioHang.addRow(new Object[]{
                ++dem,
                list.get(i).getIMEI(),
                spct.getID_SPCT(),
                tenFullSanPham.replace("  ", " "),
                getTextGia(list.get(i).getDonGia()) + "đ",});
            // Nếu là hóa đơn đang ở trạng thái chờ hoạt động thì mới enable btnXoaTatCa và tính tiền và tính lại giảm giá nếu có
            if (cboLocTrangThaiHoaDon.getSelectedIndex() == 0) {
                // vì có addPhanTu nên btnXoaTatCaGioHang được bỏ disable
                btnXoaTatCaGioHang.setEnabled(true);
                // tính một số biến
                tongTienHang += list.get(i).getDonGia();
                lblTongTienHang.setText(String.valueOf(tongTienHang));
                lblTienThanhToan.setText(String.valueOf(tongTienHang));
                loadCboVoucherThoaManDieuKien();
            }
        }
        // Kiểm tra disable thanh toán nếu giỏ hàng thay đổi
        kiemTraDisableBtnThanhToan();
    }

    // Hàm xóa Form
    public void xoaFormHoaDon() {
        //xóa form của hóa đơn
        txtSDT.setText("");
        lblTenKhachHang.setText("");
        cboVoucher.setSelectedIndex(-1);
        cboPTTT.setSelectedIndex(-1);
        lblMaHoaDon.setText("");
        lblTongTienHang.setText("");
        lblGiamGia.setText("");
        lblTienThanhToan.setText("");
        txtTienKhachDua.setText("");
        lblTienThua.setText("");
        txtGhiChu.setText("");
        //load lại table giỏ hàng
        loadTableGioHang(new ArrayList<>());
        //disable các nút
        btnThanhToan.setEnabled(false);
        btnHuy.setEnabled(false);
        btnCapNhat.setEnabled(false);
        btnXoaTatCaGioHang.setEnabled(false);
        btnXemChiTietVoucher.setEnabled(false);
        //disable panel hóa đơn
        cboPTTT.setEnabled(false);
        txtTienKhachDua.setEnabled(false);
        txtGhiChu.setEnabled(false);
        // disable panel khách hàng và voucher
        pnlKhachHang.setEnabled(false);
        cboVoucher.setEnabled(false);
        cboVoucher.removeAllItems();
        cboVoucher.setSelectedIndex(-1);
        // disable panel sản phẩm
        txtTimKiem.setEnabled(false);
        tblSanPham.setEnabled(false);
        btnTimKiem.setEnabled(false);
        btnReset.setEnabled(false);
        // disable giỏ hàng
        tblGioHang.setEnabled(false);
    }

    // Hàm getCacVoucher thỏa mãn điều kiện
    public void loadCboVoucherThoaManDieuKien() {
        cboVoucher.removeAllItems();
        for (KhuyenMaiChiTiet khuyenMaiChiTiet : khuyenMaiChiTietService.getKhuyenMaiChiTietCacMaVoucherDangDienRa()) {
            if (kiemTraDieuKienVoucher(khuyenMaiChiTiet)) {
                cboVoucher.addItem(khuyenMaiChiTiet.getMa_Voucher());
            }
        }
        cboVoucher.setSelectedIndex(-1); // trả về như ch từng quen
        lblGiamGia.setText("");
        lblTienThanhToan.setText(String.valueOf(Integer.parseInt(lblTongTienHang.getText().trim())));
    }

//-----------------------------VOUCHER
    //Hàm áp dụng voucher
    public void apDungVoucher(String maVoucher) {
        //Kiểm tra điều kiện
        KhuyenMaiChiTiet khuyenMaiChiTiet = khuyenMaiChiTietService.getKhuyenMaiChiTietTheoMaVoucher(maVoucher);
        if (kiemTraDieuKienVoucher(khuyenMaiChiTiet)) {
            List<HoaDonChiTietDisplay> listHoaDonChiTiet = hoaDonChiTietService.getListHienThiCuaGioHang(getIntIDHDOfSelectedTableHoaDon());
            int tongTien = 0;
            int giaGiamSauKhiApDungVoucher = 0;
            //Chạy for tính tiền
            for (HoaDonChiTietDisplay hoaDonChiTiet : listHoaDonChiTiet) {
                tongTien += hoaDonChiTiet.getThanhTien();
            }
            // áp dụng lấy giá giảm cuối cùng
            if (khuyenMaiChiTiet.getDonViKhuyenMai() == 0) {
                giaGiamSauKhiApDungVoucher = khuyenMaiChiTiet.getGiaKhuyenMai();
            } else if (khuyenMaiChiTiet.getDonViKhuyenMai() == 1) {
                giaGiamSauKhiApDungVoucher = tongTien * khuyenMaiChiTiet.getGiaKhuyenMai() / 100;
                if (giaGiamSauKhiApDungVoucher > khuyenMaiChiTiet.getGiamToiDa()) {
                    giaGiamSauKhiApDungVoucher = khuyenMaiChiTiet.getGiamToiDa();
                }
            }
            lblGiamGia.setText(String.valueOf(giaGiamSauKhiApDungVoucher));
        }
        //Tính lại tiền
        if (lblGiamGia.getText().trim().equals("")) {
            lblTienThanhToan.setText(String.valueOf(Integer.parseInt(lblTongTienHang.getText().trim())));
            btnXemChiTietVoucher.setEnabled(false);
        } else {
            lblTienThanhToan.setText(String.valueOf(Integer.parseInt(lblTongTienHang.getText().trim()) - Integer.parseInt(lblGiamGia.getText().trim())));
            btnXemChiTietVoucher.setEnabled(true);
        }
    }

    // Hàm kiểm tra điều kiện voucher
    public boolean kiemTraDieuKienVoucher(KhuyenMaiChiTiet khuyenMaiChiTiet) {
        // lấy list ở giỏ hàng để lấy thông tin
        List<HoaDonChiTietDisplay> listHoaDonChiTiet = hoaDonChiTietService.getListHienThiCuaGioHang(getIntIDHDOfSelectedTableHoaDon());
        // Khuyến mãi theo hóa đơn
        if (khuyenMaiChiTiet.getLoaiKhuyenMai() == 0) {
            int tongTien = 0;
            int soLuong = 0;
            // chạy vòng for để lấy thông tin cần thiết đi so sánh
            for (HoaDonChiTietDisplay hoaDonChiTiet : listHoaDonChiTiet) {
                tongTien += hoaDonChiTiet.getThanhTien();
                soLuong += hoaDonChiTiet.getSoLuong();
            }
            // check điều kiện giá
            if (khuyenMaiChiTiet.getDieuKienGia() != null) {
                if (tongTien < khuyenMaiChiTiet.getDieuKienGia()) {
                    return false;
                }
            }
            //check điều kiện số lượng
            if (khuyenMaiChiTiet.getDieuKienSoLuong() != null) {
                if (soLuong < khuyenMaiChiTiet.getDieuKienSoLuong()) {
                    return false;
                }
            }
            // Khuyễn mãi theo sản phẩm
        } else if (khuyenMaiChiTiet.getLoaiKhuyenMai() == 1) {
            int soLuong = 0;
            // chạy for lấy một số thông tin đi so sánh
            for (HoaDonChiTietDisplay hoaDonChiTiet : listHoaDonChiTiet) {
                String[] dieuKienSP = khuyenMaiChiTiet.getDieuKienSP().split(",");
                for (String sp : dieuKienSP) {
                    if (hoaDonChiTiet.getIDSPCT() == Integer.parseInt(sp)) {
                        soLuong += hoaDonChiTiet.getSoLuong();
                    }
                }
            }
            // check điều kiện số lượng sản phẩm
            if (khuyenMaiChiTiet.getDieuKienSoLuong() != null) {
                if (soLuong < khuyenMaiChiTiet.getDieuKienSoLuong()) {
                    return false;
                }
            }
        }
        if (khuyenMaiChiTiet.getSoLuong() <= 0) {
            return false;
        }
        return true;
    }
//------------------------------------------------
    // Hàm tìm kiếm của txtTimKiemTheoTenSanPham

    public void timKiemTheoTenSP() {
        if (txtTimKiem.getText().trim().equals("") == false) {
            List<SPCT> listTimKiemTheoTenSP = new ArrayList<>();
            //Chạy for lần lượt theo spct đang hoạt động
            for (SPCT spct : spctService.getAllSPCTDangHoatDongVaTuMoiXuongCu()) {
                SanPham sanPham = sanPhamService.getSanPhamFromIDSP(spct.getID_San_Pha());
                Dong dong = dongService.getById(sanPham.getID_Dong());
                MODELNUMBER modelnumber = modelnumberService.getById(spct.getID_Model_Number());
                Rom rom = romService.findByID(spct.getID_Rom());// sửa
                String tenFullSPCT = "IPhone " + sanPham.getThe_He() + " " + getTextTenDong(dong.getTen()) + " " + getTextRomFromDungLuong(rom.getDungLuong());
                if (tenFullSPCT.replace("  ", " ").toLowerCase().contains(txtTimKiem.getText().trim().trim().toLowerCase())) {
                    listTimKiemTheoTenSP.add(spct);
                    txtTimKiem.setEnabled(false);
                }
            }
            if (listTimKiemTheoTenSP.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không có sản phẩm nào", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                loadTableSanPham(listTimKiemTheoTenSP, 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên tìm kiếm", "Chú ý", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Hàm kiểm tra để bỏ vô hiệu hóa thanh toán với hóa đơn khi thỏa mãn hết điều kiện
    public void kiemTraDisableBtnThanhToan() {
        try {
            // nếu ch chọn hóa đơn
            if (tblHoaDon.getSelectedRow() == -1) {
                btnThanhToan.setEnabled(false);
                return;
            }
            // nếu hóa đơn đã thanh toán
            if (cboLocTrangThaiHoaDon.getSelectedIndex() == 1 || cboLocTrangThaiHoaDon.getSelectedIndex() == 2) {
                btnThanhToan.setEnabled(false);
                return;
            }
            //nếu giỏ hàng ch có sản phẩm
            if (tblGioHang.getRowCount() == 0) {
                btnThanhToan.setEnabled(false);
                return;
            }
            // nếu chwua nhập khách hàng
            if (lblTenKhachHang.getText().trim().equals("")) {
                btnThanhToan.setEnabled(false);
                return;
            }
            // nếu chưa chọn phương thức thanh toán
            if (cboPTTT.getSelectedIndex() == -1) {
                btnThanhToan.setEnabled(false);
                return;
            }
            // nếu phương thức thanht toán tiền mặt thì tiền khách đưa >= tiền thanh toán
            if (cboPTTT.getSelectedIndex() == 0) {
                if (Integer.parseInt(txtTienKhachDua.getText().trim()) < Integer.parseInt(lblTienThanhToan.getText().trim())) {
                    btnThanhToan.setEnabled(false);
                    return;
                }
            }

        } catch (Exception e) {
            btnThanhToan.setEnabled(false);
            return;
        }
        btnThanhToan.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaTatCaGioHang = new javax.swing.JButton();
        pnlThongTinHoaDon = new javax.swing.JPanel();
        pnlKhachHang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTenKhachHang = new javax.swing.JLabel();
        txtSDT = new javax.swing.JLabel();
        pnlVoucher = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnXemChiTietVoucher = new javax.swing.JButton();
        cboVoucher = new javax.swing.JComboBox<>();
        btnThanhToan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblMaHoaDon = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboPTTT = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        lblTongTienHang = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTienThanhToan = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnCapNhat = new javax.swing.JButton();
        pnlSanPham = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnPrevious = new javax.swing.JButton();
        lblTrang = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnTaoHoaDon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        cboLocTrangThaiHoaDon = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "IMEI", "IDSP", "Tên sản phẩm", "Đơn giá"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        btnXoaTatCaGioHang.setText("Xóa tất cả");
        btnXoaTatCaGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTatCaGioHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXoaTatCaGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(btnXoaTatCaGioHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlThongTinHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        pnlKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder("Khách hàng"));
        pnlKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlKhachHangMouseClicked(evt);
            }
        });

        jLabel2.setText("SDT");

        jLabel3.setText("Tên");

        lblTenKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txtSDT.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pnlVoucher.setBorder(javax.swing.BorderFactory.createTitledBorder("Voucher"));

        jLabel7.setText("Mã voucher");

        btnXemChiTietVoucher.setText("Xem voucher");
        btnXemChiTietVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietVoucherActionPerformed(evt);
            }
        });

        cboVoucher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboVoucherItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlVoucherLayout = new javax.swing.GroupLayout(pnlVoucher);
        pnlVoucher.setLayout(pnlVoucherLayout);
        pnlVoucherLayout.setHorizontalGroup(
            pnlVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVoucherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlVoucherLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboVoucher, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlVoucherLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXemChiTietVoucher)))
                .addContainerGap())
        );
        pnlVoucherLayout.setVerticalGroup(
            pnlVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVoucherLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pnlVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXemChiTietVoucher)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHuy.setText("HỦY");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        lblMaHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setText("Mã hóa đơn");

        jLabel5.setText("PTTT");

        cboPTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản" }));
        cboPTTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPTTTItemStateChanged(evt);
            }
        });

        jLabel12.setText("Tổng tiền hàng");

        lblTongTienHang.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel11.setText("Giảm giá");

        lblGiamGia.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel8.setText("Tiền thanh toán");

        lblTienThanhToan.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel9.setText("Tiền khách đưa");

        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        jLabel13.setText("Tiền thừa");

        lblTienThua.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel10.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane5.setViewportView(txtGhiChu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboPTTT, 0, 213, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTongTienHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTienThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienKhachDua))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(lblMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(lblTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(lblGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(lblTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCapNhat.setText("CẬP NHẬT");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThongTinHoaDonLayout = new javax.swing.GroupLayout(pnlThongTinHoaDon);
        pnlThongTinHoaDon.setLayout(pnlThongTinHoaDonLayout);
        pnlThongTinHoaDonLayout.setHorizontalGroup(
            pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                        .addComponent(btnThanhToan)
                        .addGap(24, 24, 24)
                        .addComponent(btnHuy)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlVoucher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlThongTinHoaDonLayout.setVerticalGroup(
            pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(pnlVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        pnlSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "IDSP", "Tên sản phẩm", "Màu", "Loại", "Ram", "Rom", "Số lượng tồn kho", "Đơn giá"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        jLabel1.setText("Tìm kiếm");

        txtTimKiem.setToolTipText("");
        txtTimKiem.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        btnPrevious.setText("<<");
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        lblTrang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrang.setText("0/0");

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(btnPrevious)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTrang, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnPrevious)
                .addComponent(lblTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnNext))
        );

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addGroup(pnlSanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReset)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem)
                    .addComponent(btnReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Ngày tạo", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        cboLocTrangThaiHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ thanh toán", "Đã thanh toán", "Đã hủy" }));
        cboLocTrangThaiHoaDon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocTrangThaiHoaDonItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cboLocTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTaoHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(cboLocTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlThongTinHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlThongTinHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        //Thanh toán hóa đơn đổi imei thành đã bán
        if (kiemTraDisableBtnThanhToanDeTrongBtnThanhToan()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thanh toán hóa đơn?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.OK_OPTION) {
                if (hoaDonService.thanhToanHoaDon(getFormHoaDon()) > 0) {
                    if (getIDKMCTTheoMaVoucherNeuDuocApDungThanhCongRoi() != null) {
                        khuyenMaiChiTietService.updateSoLuongKhuyenMaiChiTietTheoIDKMCT(getIDKMCTTheoMaVoucherNeuDuocApDungThanhCongRoi());
                    }
                    JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                    loadTableHoaDon(hoaDonService.getAllHoaDonTheoTrangThai(0));
                    // cập nhật số lượng voucher

                    xoaFormHoaDon();
                } else {
                    JOptionPane.showMessageDialog(this, "Thanh toán thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed
    public boolean kiemTraDisableBtnThanhToanDeTrongBtnThanhToan() {
        try {
            if (tblHoaDon.getSelectedRow() == -1) {
                btnThanhToan.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (cboLocTrangThaiHoaDon.getSelectedIndex() == 1 || cboLocTrangThaiHoaDon.getSelectedIndex() == 2) {
                btnThanhToan.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Hóa đơn này đã thanh toán", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (tblGioHang.getRowCount() == 0) {
                btnThanhToan.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Chưa có sản phẩm trong giỏ hàng", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (lblTenKhachHang.getText().trim().equals("")) {
                btnThanhToan.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Chưa nhập khách hàng", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (cboPTTT.getSelectedIndex() == -1) {
                btnThanhToan.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Chưa chọn phương thức thanh toán", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if (cboPTTT.getSelectedIndex() == 0) {
                if (Integer.parseInt(txtTienKhachDua.getText().trim()) < Integer.parseInt(lblTienThanhToan.getText().trim())) {
                    btnThanhToan.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "Số tiền khách đưa không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }

        } catch (Exception e) {
            btnThanhToan.setEnabled(false);
            return false;
        }
        btnThanhToan.setEnabled(true);
        return true;
    }

    public HoaDon getFormHoaDon() {
        return new HoaDon(
                getIntIDHDOfSelectedTableHoaDon(),// get IDHD
                IDNV, // get IDNV
                getIDKHFromFormHoaDon(), //getIDKH từ nhập hóa đơn
                getIDKMCTTheoMaVoucherNeuDuocApDungThanhCongRoi(),// getKMCT nếu được áp dụng voucher
                Integer.parseInt(lblTienThanhToan.getText().trim()),
                getTienKhachDuaNeuNull(),
                txtGhiChu.getText().trim(),
                cboPTTT.getSelectedIndex(),
                0,
                LocalDate.now().toString(),
                LocalDate.now().toString());
    }

    // lấy ID KH nếu là khách hàng vãng lai thì id khách hàng để trống
    public int getIDKHFromFormHoaDon() {
        if (txtSDT.getText().trim().equals("")) {
            return 6;// sửa lại khách hàng chỗ này
        } else {
            return khachHangService.getKhachHangBySDTKhachHang(txtSDT.getText().trim()).getId();
        }
    }

    public Integer getIDKMCTTheoMaVoucherNeuDuocApDungThanhCongRoi() {
        // Dựa vào lbl Giảm giá có giảm giá hay k sẽ biết voucher có được áp dụng hay không
        if (lblGiamGia.getText().trim().equals("") == false) {
            KhuyenMaiChiTiet khuyenMaiChiTiet = khuyenMaiChiTietService.getKhuyenMaiChiTietTheoMaVoucherVaKiemTraKhuyenMaiDangDienRa(cboVoucher.getSelectedItem().toString());
             return khuyenMaiChiTiet.getID_KMCT();
        } else {
            return null;
        }
    }

    // Hàm set null tienKhachDua nếu chọn phương thức chuyển khoản
    public Integer getTienKhachDuaNeuNull() {
        if (cboPTTT.getSelectedIndex() == 0) {
            return Integer.parseInt(txtTienKhachDua.getText().trim());
        } else {
            return null;
        }
    }
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        // Hủy hóa đơn thay đổi imei về đang bán
        if (tblHoaDon.getSelectedRow() >= 0) {
            if (txtGhiChu.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Chưa nhập lý do hủy hóa đơn");
            } else {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hủy hóa đơn?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.OK_OPTION) {
                    if (hoaDonService.huyHoaDon(getIntIDHDOfSelectedTableHoaDon(), txtGhiChu.getText().trim()) > 0) {
                        JOptionPane.showMessageDialog(this, "Hủy hóa đơn thành công");
                        loadTableHoaDon(hoaDonService.getAllHoaDonTheoTrangThai(0));
                        xoaFormHoaDon();
                    } else {
                        JOptionPane.showMessageDialog(this, "Hủy hóa đơn thất bại");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn", "Chú ý", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:  
        if (hoaDonService.insertHoaDon(IDNV) > 0) {
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công");// hiện thông báo
            loadTableHoaDon(hoaDonService.getAllHoaDonTheoTrangThai(0));// trạng thái chờ thanh toán
            tblHoaDon.setRowSelectionInterval(0, 0);//chọn ngay hóa đơn vừa thêm           
            hamTblHoaDonMouseClicked(); // chạy mouseclick hàm hóa đơn
        } else {
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn thất bại");
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void cboLocTrangThaiHoaDonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocTrangThaiHoaDonItemStateChanged
        // TODO add your handling code here:
        xoaFormHoaDon();
        getLoadTableHoaDonTheoIndexCboLocTrangThaiHoaDon();
    }//GEN-LAST:event_cboLocTrangThaiHoaDonItemStateChanged
    public void getLoadTableHoaDonTheoIndexCboLocTrangThaiHoaDon() {
        if (cboLocTrangThaiHoaDon.getSelectedIndex() == 0) {
            loadTableHoaDon(hoaDonService.getAllHoaDonTheoTrangThai(0));
            btnTaoHoaDon.setEnabled(true);
        } else if (cboLocTrangThaiHoaDon.getSelectedIndex() == 1) {
            loadTableHoaDon(hoaDonService.getAllHoaDonTheoTrangThai(1));
            btnTaoHoaDon.setEnabled(false);
        } else if (cboLocTrangThaiHoaDon.getSelectedIndex() == 2) {
            loadTableHoaDon(hoaDonService.getAllHoaDonTheoTrangThai(2));
            btnTaoHoaDon.setEnabled(false);
        }
    }
    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
        String[] tachSoTrang = lblTrang.getText().trim().split("/");
        if (tachSoTrang[0].equals("1") == false) {
            loadTableSanPham(listSPCTOfTable, Integer.parseInt(tachSoTrang[0]) - 1);
        }
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        String[] tachSoTrang = lblTrang.getText().trim().split("/");
        if (tachSoTrang[0].equals(tachSoTrang[1]) == false) {
            loadTableSanPham(listSPCTOfTable, Integer.parseInt(tachSoTrang[0]) + 1);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        hamTblHoaDonMouseClicked();
    }//GEN-LAST:event_tblHoaDonMouseClicked
    public void hamTblHoaDonMouseClicked() {
        //Xóa form set lại từ đầu
        if (tblHoaDon.getSelectedRow() >= 0) {
            xoaFormHoaDon();// reset lại từ đầu
            loadTableGioHang(hoaDonChiTietService.getListHienThiCuaGioHangCoIMEI(getIntIDHDOfSelectedTableHoaDon()));
            // Nếu là hóa đơn chờ hoạt động mới cho nhập form hóa đơn và tìm kiếm thêm sản phẩm
            if (cboLocTrangThaiHoaDon.getSelectedIndex() == 0) {
                // bỏ vô hiệu hóa form thông tin hóa đơn
                pnlKhachHang.setEnabled(true);
                cboVoucher.setEnabled(true);
                cboPTTT.setEnabled(true);
                txtGhiChu.setEnabled(true);
                //
                btnHuy.setEnabled(true);
                // bỏ vô hiệu hóa form sản phẩm
                txtTimKiem.setEnabled(true);
                tblSanPham.setEnabled(true);
                btnTimKiem.setEnabled(true);
                btnReset.setEnabled(true);
                //bỏ vô hiệu hóa giỏ hàng
                tblGioHang.setEnabled(true);
                // set mã hóa đơn
                lblMaHoaDon.setText(String.valueOf(getIntIDHDOfSelectedTableHoaDon()));
            } else if (cboLocTrangThaiHoaDon.getSelectedIndex() == 1) {
                txtGhiChu.setEnabled(true);
                btnCapNhat.setEnabled(true);
                setFormHoaDon();
            } else if (cboLocTrangThaiHoaDon.getSelectedIndex() == 2) {
                txtGhiChu.setEnabled(true);
                btnCapNhat.setEnabled(true);
                setFormHoaDon();
            }
        }
    }

    public void setFormHoaDon() {
        HoaDon hoaDon = hoaDonService.getHoaDonTheoIDHD(getIntIDHDOfSelectedTableHoaDon());
        KhachHang khachHang = khachHangService.getKhachHangByIDKH(hoaDon.getIDKH());
        txtSDT.setText(khachHang.getSDT());
        lblTenKhachHang.setText(khachHang.getHoTen());
        //
        if (hoaDon.getID_KMCT() == null) {
            cboVoucher.setSelectedIndex(-1);
            btnXemChiTietVoucher.setEnabled(false);
        } else {
            KhuyenMaiChiTiet khuyenMaiChiTiet = khuyenMaiChiTietService.getKhuyenMaiChiTietTheoIDKMCTT(hoaDon.getID_KMCT());
            cboVoucher.addItem(khuyenMaiChiTiet.getMa_Voucher());
            cboVoucher.setSelectedItem(khuyenMaiChiTiet.getMa_Voucher());
            btnXemChiTietVoucher.setEnabled(true);
        }
        int tongTienHang = 0;
        for (HoaDonChiTietDisplay hoaDonChiTietDisplay : hoaDonChiTietService.getListHienThiCuaGioHang(getIntIDHDOfSelectedTableHoaDon())) {
            tongTienHang += hoaDonChiTietDisplay.getThanhTien();
        }
        // Nếu được giảm giá thì set lblGiamGia và set enabled cho btnXemChiTietVoucher
        if (tongTienHang - hoaDon.getTongTienThanhToan() > 0) {
            lblGiamGia.setText(String.valueOf(tongTienHang - hoaDon.getTongTienThanhToan()));
            // cho xem chi tiết voucher vì coucher được áp dụng
            btnXemChiTietVoucher.setEnabled(true);
        }
        lblMaHoaDon.setText(String.valueOf(hoaDon.getIDHD()));
        cboPTTT.setSelectedIndex(hoaDon.getPhuongThucThanhToan());
        lblTongTienHang.setText(String.valueOf(tongTienHang));
        lblTienThanhToan.setText(String.valueOf(hoaDon.getTongTienThanhToan()));
        if (hoaDon.getPhuongThucThanhToan() == 0) {
            txtTienKhachDua.setText(String.valueOf(hoaDon.getTongTienKhachDua()));
            lblTienThua.setText(String.valueOf(hoaDon.getTongTienKhachDua() - Integer.parseInt(lblTienThanhToan.getText().trim())));
        }
        txtGhiChu.setText(hoaDon.getGhiChu());
    }

    public int getIntIDHDOfSelectedTableHoaDon() {
        return Integer.parseInt(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
    }
    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        if (tblHoaDon.getSelectedRow() != -1) {
            int viTri = tblSanPham.getSelectedRow();
            if (viTri >= 0) {
                // Khi click vào sản phẩm có số lượng bằng 0
                if (spctService.getSoLuongTonKhoOfSPCT(getIntIDSPOfSelectedTableSanPham()) == 0) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm đã hết hàng", "Chú ý", JOptionPane.WARNING_MESSAGE);
                } else {
                    String IMEICanThem = JOptionPane.showInputDialog(this, "Nhập IMEI cần thêm vào giỏ hàng", "Form", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (IMEICanThem != null) {
                        if (checkIMEIInput(IMEICanThem)) {
                            // Kiểm tra IMEI và ở trạng thái đang bán
                            IMEI imei = imeiService.getIMEITheoIMEIVaIDSPCTDeThemVaoGioHang(IMEICanThem, getIntIDSPOfSelectedTableSanPham());
                            if (imei == null) {
                                JOptionPane.showMessageDialog(this, "Không có IMEI này", "Chú ý", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                // tạo ra spct để lấy đơn giá hiện tại
                                SPCT spct = spctService.getSPCTTheoIDSPCT(imei.getID_SPCT());
                                // thêm vào giỏ hàng chi tiêt trong database
                                hoaDonChiTietService.insertHoaDonChiTiet(getIntIDHDOfSelectedTableHoaDon(), imei.getIMEI(), spct.getDon_Gia());
                                // cấp nhật trạng thái imei là 1 - đang trong giỏ hàng
                                imeiService.updateTrangThaiIMEI(1, IMEICanThem);
                                // load lại table giỏ hàng
                                loadTableGioHang(hoaDonChiTietService.getListHienThiCuaGioHangCoIMEI(getIntIDHDOfSelectedTableHoaDon()));
                                // cập nhật lại số lượng tồn kho của sản phẩm trong table sản phẩm
                                tblSanPham.setValueAt(spctService.getSoLuongTonKhoOfSPCT(getIntIDSPOfSelectedTableSanPham()), viTri, 7);
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
        if (tblGioHang.getSelectedRow() != -1) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa IMEI này khỏi giỏ hàng không?", "Chú ý", JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                //Lấy IMEI được chọn
                String imei = tblGioHang.getValueAt(tblGioHang.getSelectedRow(), 1).toString();
                //Xóa sản phẩm khỏi giở hàng
                hoaDonChiTietService.xoaKhoiGioHangTheoIMEIVaIDSPCTVaIDHD(imei, getIntIDSPOfSelectedTableGioHang(), getIntIDHDOfSelectedTableHoaDon());
                //load lại số lượng tồn kho của bảng sản phẩm trước
                for (int i = 0; i < tblSanPham.getRowCount(); i++) {
                    if (getIntIDSPOfSelectedTableGioHang() == Integer.parseInt(tblSanPham.getValueAt(i, 1).toString())) {
                        tblSanPham.setValueAt(spctService.getSoLuongTonKhoOfSPCT(getIntIDSPOfSelectedTableGioHang()), i, 7);
                    }
                }
                //Load lại table giỏ hàng
                loadTableGioHang(hoaDonChiTietService.getListHienThiCuaGioHangCoIMEI(getIntIDHDOfSelectedTableHoaDon()));
            }
        }
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        // TODO add your handling code here:
        try {
            // nếu tiền khách đưa k đủ sẽ hiện đỏ
            if (Integer.parseInt(txtTienKhachDua.getText().trim()) < Integer.parseInt(lblTienThanhToan.getText().trim())) {
                txtTienKhachDua.setForeground(Color.red);
                lblTienThua.setText("");
            } else { // còn đã đủ thì sẽ tính tiền thừa
                txtTienKhachDua.setForeground(Color.black);
                lblTienThua.setText(String.valueOf(Integer.parseInt(txtTienKhachDua.getText().trim()) - Integer.parseInt(lblTienThanhToan.getText().trim())));
            }
        } catch (Exception e) {
            txtTienKhachDua.setForeground(Color.red);
            txtTienKhachDua.setText(txtTienKhachDua.getText().trim().substring(0, txtTienKhachDua.getText().trim().length() - 1).trim());
            // Do nhập tiền kph là số nên tiền thừa trở về ""
            lblTienThua.setText("");
        }
        // kiểm tra disable kiểm thử
        kiemTraDisableBtnThanhToan();
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void cboPTTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPTTTItemStateChanged
        // TODO add your handling code here:
        if (cboPTTT.getSelectedIndex() == 0 && cboLocTrangThaiHoaDon.getSelectedIndex() == 0) {
            txtTienKhachDua.setEnabled(true);
        } else if (cboPTTT.getSelectedIndex() == 1 && cboLocTrangThaiHoaDon.getSelectedIndex() == 0) {
            txtTienKhachDua.setText("");
            lblTienThua.setText("");
            txtTienKhachDua.setEnabled(false);
            FrameDisplayQR frameDisplayQR = new FrameDisplayQR();
            frameDisplayQR.setVisible(true);
        }
        // kiểm tra disable kiểm thử
        kiemTraDisableBtnThanhToan();
    }//GEN-LAST:event_cboPTTTItemStateChanged

    private void btnXoaTatCaGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTatCaGioHangActionPerformed
        // TODO add your handling code here:
        if (cboLocTrangThaiHoaDon.getSelectedIndex() == 0) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn làm mới toàn bộ giỏ hàng", "Chú ý", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                // Gọi SQL xóa
                hoaDonChiTietService.xoaTatCaKhoiGioHang(getIntIDHDOfSelectedTableHoaDon());
                //Load lại table giỏ hàng
                loadTableGioHang(hoaDonChiTietService.getListHienThiCuaGioHangCoIMEI(getIntIDHDOfSelectedTableHoaDon()));
                // Load lại table sản phẩm
                loadTableSanPham(listSPCTOfTable, getSoTrangHienTaiTableSanPham());
            }
        } else {
            JOptionPane.showConfirmDialog(this, "Hóa đơn này không thể cập nhật giỏ hàng nữa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnXoaTatCaGioHangActionPerformed
    public int getSoTrangHienTaiTableSanPham() {
        String[] tachTrang = lblTrang.getText().trim().split("/");
        return Integer.parseInt(tachTrang[0]);
    }
    private void pnlKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlKhachHangMouseClicked
        // TODO add your handling code here:
        if (pnlKhachHang.isEnabled()) {
            String[] options = {"Nhập SDT khách hàng", "Khách vãng lai", "Thêm khách hàng"};
            int result = JOptionPane.showOptionDialog(this, null, "Thông báo", 0, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (result == 0) {
                String sdt = JOptionPane.showInputDialog(this, "Nhập vào số điện thoại khách hàng", "Thông báo", JOptionPane.YES_NO_CANCEL_OPTION);
                if (sdt != null) {
                    if (checkSDTInput(sdt)) {
                        KhachHang khachHang = khachHangService.getKhachHangBySDTKhachHang(sdt);
                        if (khachHang == null) {
                            JOptionPane.showMessageDialog(this, "Không có khách hàng nào có sdt trên", "Chú ý", JOptionPane.WARNING_MESSAGE);
                        } else {
                            txtSDT.setText(khachHang.getSDT());
                            lblTenKhachHang.setText(khachHang.getHoTen());
                        }
                    }
                }
            } else if (result == 1) {
                txtSDT.setText("");
                lblTenKhachHang.setText("Khách vãng lai");
            } else if (result == 2) {
                KhachHangView khachHangView = new KhachHangView();
                khachHangView.setVisible(true);
            }
            // kiểm tra disable kiểm thử
            kiemTraDisableBtnThanhToan();
        }
    }//GEN-LAST:event_pnlKhachHangMouseClicked
    //Hàm check sdt input
    public boolean checkSDTInput(String sdt) {
        if (sdt == null || sdt.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (sdt.length() != 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại 10 số", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        for (char kyTuSDT : sdt.toCharArray()) {
            if (Character.isDigit(kyTuSDT) == false) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        if (sdt.substring(0, 1).equals("0") == false) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    private void btnXemChiTietVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietVoucherActionPerformed
        // TODO add your handling code here:
        KhuyenMaiChiTiet khuyenMaiChiTiet = khuyenMaiChiTietService.getKhuyenMaiChiTietTheoMaVoucher(cboVoucher.getSelectedItem().toString());
        String thongTinVeVoucher
                = "Loại khuyến mãi: " + getTextLoaiKhuyenMai(khuyenMaiChiTiet.getLoaiKhuyenMai()) + "\n"
                + "Điều kiện giá phải lớn hơn(>=) " + khuyenMaiChiTiet.getDieuKienGia() + "\n"
                + getThongTinGiaKhuyenMaiDuocGiam(khuyenMaiChiTiet) + "\n"
                + getTextGiamToiDa(khuyenMaiChiTiet.getGiamToiDa()) + "\n"
                + getThongTinDieuKienSoLuong(khuyenMaiChiTiet) + "\n";
        JOptionPane.showMessageDialog(this, thongTinVeVoucher, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnXemChiTietVoucherActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        timKiemTheoTenSP();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtTimKiem.setEnabled(true);
        txtTimKiem.setText("");
        loadTableSanPham(spctService.getAllSPCTDangHoatDongVaTuMoiXuongCu(), 1);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        if (tblHoaDon.getSelectedRow() >= 0) {
            if (txtGhiChu.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Chưa nhập lý do");
            } else {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật hóa đơn?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.OK_OPTION) {
                    if (hoaDonService.capNhatGhiChu(getIntIDHDOfSelectedTableHoaDon(), txtGhiChu.getText().trim()) > 0) {
                        JOptionPane.showMessageDialog(this, "Cập nhật hóa đơn thành công");
                        loadTableHoaDon(hoaDonService.getAllHoaDonTheoTrangThai(cboLocTrangThaiHoaDon.getSelectedIndex()));
                        xoaFormHoaDon();
                    } else {
                        JOptionPane.showMessageDialog(this, "Cập nhật hóa đơn thất bại");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hóa đơn", "Chú ý", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void cboVoucherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboVoucherItemStateChanged
        // TODO add your handling code here:
        if (cboVoucher.getSelectedIndex() != -1 && cboLocTrangThaiHoaDon.getSelectedIndex() == 0) {
            apDungVoucher(cboVoucher.getSelectedItem().toString());
            btnXemChiTietVoucher.setEnabled(true);
        } else {
            btnXemChiTietVoucher.setEnabled(false);
        }
    }//GEN-LAST:event_cboVoucherItemStateChanged
    // Hàm getTextLoaiKhuyenMai
    public String getTextLoaiKhuyenMai(int loaiKhuyenMai) {
        if (loaiKhuyenMai == 0) {
            return "Khuyễn mãi theo hóa đơn";
        } else if (loaiKhuyenMai == 1) {
            return "Khuyến mãi theo sản phẩm";
        }
        return "Không biết";
    }

    //hàm getTextGiamToiDa
    public String getTextGiamToiDa(Integer giamToiDa) {
        String text = "Giá giảm tối đa: ";
        if (giamToiDa == null) {
            return text += "Không";
        } else {
            return text += giamToiDa + "đ";
        }
    }

    //hàm getTextDieuKienSoLuong
    public String getThongTinDieuKienSoLuong(KhuyenMaiChiTiet khuyenMaiChiTiet) {
        String text = "Phải có ít nhất: ";
        if (khuyenMaiChiTiet.getLoaiKhuyenMai() == 0) {
            return text += khuyenMaiChiTiet.getDieuKienSoLuong() + "sp bất kì";
        } else if (khuyenMaiChiTiet.getLoaiKhuyenMai() == 1) {
            return text += khuyenMaiChiTiet.getDieuKienSoLuong() + "sp thuộc IDSPCT(" + khuyenMaiChiTiet.getDieuKienSP() + ")";
        }
        return text;
    }

    //Hàm getTextThongTinGiaKhuyenMai
    public String getThongTinGiaKhuyenMaiDuocGiam(KhuyenMaiChiTiet khuyenMaiChiTiet) {
        String text = "Voucher được giảm ";
        text += khuyenMaiChiTiet.getGiaKhuyenMai();
        if (khuyenMaiChiTiet.getDonViKhuyenMai() == 0) {
            text += "VND";
        } else if (khuyenMaiChiTiet.getDonViKhuyenMai() == 1) {
            text += "%" + " giá trị hóa đơn";
        }
        return text;
    }

    // một số hàm lấy id cố định
    public int getIntIDSPOfSelectedTableGioHang() {
        return Integer.valueOf(tblGioHang.getValueAt(tblGioHang.getSelectedRow(), 2).toString());
    }

    public int getIntIDSPOfSelectedTableSanPham() {
        return Integer.valueOf(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 1).toString());
    }

    // Hàm check IMEI input nhập vào
    public boolean checkIMEIInput(String IMEI) {
        if (IMEI.length() != 15) {
            JOptionPane.showMessageDialog(this, "IMEI không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        for (char a : IMEI.toCharArray()) {
            if (Character.isDigit(a) == false) {
                JOptionPane.showMessageDialog(this, "IMEI không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    // Hàm chia các đơn vị nghìn
    public String getTextGia(int gia) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(gia);
        return formattedNumber.replace(",", ".");
    }

    // Các hàm getText
    public String getTextTinhTrangFromInt(int tinhTrang) {
        if (tinhTrang == 100) {
            return "New";
        } else {
            return tinhTrang + "%";
        }
    }

    public String getTextRamFromDungLuong(int dungLuongRam) {
        return dungLuongRam + "GB";
    }

    public String getTextRomFromDungLuong(int dungLongRom) {
        if (dungLongRom >= 1000) {
            return dungLongRom / 1000 + "T";
        } else {
            return dungLongRom + "GB";
        }
    }

    public String getTextPhienBan(int phienBan) {
        if (phienBan == 0) {
            return "Quốc tế";
        } else if (phienBan == 1) {
            return "Lock";
        } else {
            return "Không biết";
        }
    }

    public String getTextTenDong(String tenDong) {
        if (tenDong.equalsIgnoreCase("thường") || tenDong.equalsIgnoreCase("thuong")) {
            return "";
        } else {
            return tenDong;
        }
    }

    public String getTextTrangThaiHoaDon(int trangThaiHoaDon) {
        if (trangThaiHoaDon == 0) {
            return "Chờ thanh toán";
        } else if (trangThaiHoaDon == 1) {
            return "Đã thanh toán";
        } else if (trangThaiHoaDon == 2) {
            return "Đã hủy";
        } else {
            return "Không biết";
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXemChiTietVoucher;
    private javax.swing.JButton btnXoaTatCaGioHang;
    private javax.swing.JComboBox<String> cboLocTrangThaiHoaDon;
    private javax.swing.JComboBox<String> cboPTTT;
    private javax.swing.JComboBox<String> cboVoucher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTienThanhToan;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTienHang;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JPanel pnlThongTinHoaDon;
    private javax.swing.JPanel pnlVoucher;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JLabel txtSDT;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
