/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.*;
import service.*;

/**
 *
 * @author PHONG
 */
public class HoaDonView extends javax.swing.JFrame {

    /**
     * Creates new form HoaDon
     */
    // Khai báo 3 biến để lấy model bảng
    DefaultTableModel defaultTableModelHoaDon;
    DefaultTableModel defaultTableModelGiohang;
    DefaultTableModel defaultTableModelIMEI;
    //Khai báo list lấy list mỗi khi load lại tblHoaDon
    List<HoaDon> listOfTableHoaDon;
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
    //Tạo ra hai hàm định dạng ngày
    SimpleDateFormat sdfNgayThangNam = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdfNamThangNgay = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonView() {
        initComponents();
        // Lấy model các bảng
        defaultTableModelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        defaultTableModelGiohang = (DefaultTableModel) tblGioHang.getModel();
        defaultTableModelIMEI = (DefaultTableModel) tblIMEI.getModel();
        // Load mặc định table
        loadTable(hoaDonService.getAllHoaDon(), 1);
    }

    //Hàm tìm kiếm những hóa đơn theo sdt khách hàng
    public void timKiemHoaDonTheoSDTKhacHang() {
        if (checkSDTTxtTimKiem(txtTimKiem.getText().trim())) {
            // phải setEnable trước vì ảnh hưởng đến cái sau  
            txtTimKiem.setEnabled(false);
            if (getListKiemTra3Loc().isEmpty()) {
                txtTimKiem.setEnabled(true);
                if (txtDateBatDau.isEnabled() && txtDateKetThuc.isEnabled()) {
                    JOptionPane.showMessageDialog(this, "Không có hóa đơn nào có khách hàng có số điện thoại này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Không có hóa đơn nào có khách hàng có SDT này và trong khoảng ngày " + sdfNgayThangNam.format(txtDateBatDau.getDate()) + " - " + sdfNgayThangNam.format(txtDateKetThuc.getDate()), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                loadTable(getListKiemTra3Loc(), 1);
            }
        }
    }

    // Hàm check SDT tìm kiếm
    public boolean checkSDTTxtTimKiem(String sdt) {
        if (sdt.equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        for (char kyTuSDT : sdt.toCharArray()) {
            if (Character.isDigit(kyTuSDT) == false) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        if (sdt.length() != 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại 10 số", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (sdt.substring(0, 1).equals("0") == false) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void loadTable(List<HoaDon> list, int soTrang) {
        listOfTableHoaDon = new ArrayList<>(list);// lấy list mỗi khi loadTable
        defaultTableModelHoaDon.setRowCount(0);//set về 0
        int dem = (soTrang - 1) * 3; // tính phần tử của mỗi trang
        try {
            for (int i = dem; i < soTrang * 3; i++) {
                if (i < list.size()) {
                    HoaDon hoaDon = list.get(i);
                    NhanVien nhanVien = nhanVienService.findByID(hoaDon.getIDNV());
                    if (hoaDon.getTrangThai() == 1) {
                        KhachHang khachHang = khachHangService.getKhachHangByIDKH(hoaDon.getIDKH());
                        defaultTableModelHoaDon.addRow(new Object[]{
                            ++dem,
                            hoaDon.getIDHD(),
                            getThongTinKhachHang(khachHang),//xử lý nếu là khách vãng lai
                            nhanVien.getIdNV() + "-" + nhanVien.getHoTen(),
                            sdfNgayThangNam.format(sdfNamThangNgay.parse(hoaDon.getNgayTao())),
                            sdfNgayThangNam.format(sdfNamThangNgay.parse(hoaDon.getNgaySua())),
                            getSoLuongSanPhamHoaDonTheoIDHD(hoaDon.getIDHD()),
                            getTongTienHoaDonTheoIDHD(hoaDon.getIDHD()),
                            getTextPhuongThucThanhToanCuaHoaDonTheoIDHD(hoaDon.getPhuongThucThanhToan()),
                            getTongTienHoaDonTheoIDHD(hoaDon.getIDHD()) - hoaDon.getTongTienThanhToan(),// lấy số lượng tồn kho của spct theo IMEI
                            hoaDon.getTongTienThanhToan(),
                            hoaDon.getGhiChu(),
                            getTextTrangThai(hoaDon.getTrangThai())
                        });

                    } else {
                        defaultTableModelHoaDon.addRow(new Object[]{
                            ++dem,
                            hoaDon.getIDHD(),
                            null,
                            nhanVien.getIdNV() + "-" + nhanVien.getHoTen(),
                            sdfNgayThangNam.format(sdfNamThangNgay.parse(hoaDon.getNgayTao())),
                            hoaDon.getNgaySua(),
                            getSoLuongSanPhamHoaDonTheoIDHD(hoaDon.getIDHD()),
                            getTongTienHoaDonTheoIDHD(hoaDon.getIDHD()),
                            null,
                            null,
                            null,
                            null,
                            getTextTrangThai(hoaDon.getTrangThai())
                        });
                    }
                }
            }
        } catch (Exception e) {
        }
        lblTrang.setText(soTrang + "/" + (int) Math.ceil((double) list.size() / 3));
        // reset table giỏ hàng
        loadTableGioHang(new ArrayList<>());
    }

    public String getThongTinKhachHang(KhachHang khachHang) {
        if (khachHang.getHoTen().equalsIgnoreCase("Khách vãng lai")) {
            return "Khách vãng lai";
        } else {
            return khachHang.getSDT() + "-" + khachHang.getHoTen();
        }

    }

    // Hàm getTextTrangThai
    public String getTextTrangThai(int trangThai) {
        if (trangThai == 0) {
            return "Chờ thanh toán";
        } else if (trangThai == 1) {
            return "Đã thanh toán";
        } else if (trangThai == 2) {
            return "Đã hủy";
        } else {
            return "Không biết";
        }
    }

    // Hàm tính tổng tiền hóa đơn
    public int getTongTienHoaDonTheoIDHD(int IDHD) {
        int tongTien = 0;
        for (HoaDonChiTietDisplay hoaDonChiTietDisplay : hoaDonChiTietService.getListHienThiCuaGioHang(IDHD)) {
            tongTien += hoaDonChiTietDisplay.getThanhTien();
        }
        return tongTien;
    }

    // Hàm tính số lượng sản phẩm của hóa đơn hóa đơn
    public int getSoLuongSanPhamHoaDonTheoIDHD(int IDHD) {
        int soLuong = 0;
        for (HoaDonChiTietDisplay hoaDonChiTietDisplay : hoaDonChiTietService.getListHienThiCuaGioHang(IDHD)) {
            soLuong += hoaDonChiTietDisplay.getSoLuong();
        }
        return soLuong;
    }

    // Hàm getTextPhuongThucThanhToan
    public String getTextPhuongThucThanhToanCuaHoaDonTheoIDHD(int pttt) {
        if (pttt == 0) {
            return "Tiền mặt";
        } else if (pttt == 1) {
            return "Chuyển khoản";
        } else {
            return "Không biết";
        }
    }

    // Hàm chia các đơn vị nghìn
    public String getTextGia(int gia) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(gia);
        return formattedNumber.replace(",", ".");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnPrevious = new javax.swing.JButton();
        lblTrang = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cboLocTrangThaiHoaDon = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDateBatDau = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtDateKetThuc = new com.toedter.calendar.JDateChooser();
        txtTimKiem = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnResetLocTheoNgay = new javax.swing.JButton();
        btnLocTheoNgay = new javax.swing.JButton();
        btnTimKiemTheoSDTKH = new javax.swing.JButton();
        ResetTimKiemTheoSDTKH = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblIMEI = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hóa đơn"));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "IDHD", "SDT - KhachHang", "IDNV - TenNV", "Ngày tạo", "Ngày thanh toán", "Số lượng sản phẩm", "Tổng tiền hàng", "Phương thức thanh toán", "Giảm giá", "Tiền thanh toán", "Ghi chú", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(462, 462, 462)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(462, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cboLocTrangThaiHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chờ thanh toán", "Đã thanh toán", "Đã hủy" }));
        cboLocTrangThaiHoaDon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocTrangThaiHoaDonItemStateChanged(evt);
            }
        });

        jLabel2.setText("Lọc");

        jLabel3.setText("Từ");

        txtDateBatDau.setDateFormatString("dd-MM-yyyy");

        jLabel4.setText("Đến");

        txtDateKetThuc.setDateFormatString("dd-MM-yyyy");

        jLabel5.setText("Tìm kiếm");

        btnResetLocTheoNgay.setText("Reset");
        btnResetLocTheoNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetLocTheoNgayActionPerformed(evt);
            }
        });

        btnLocTheoNgay.setText("Lọc theo ngày");
        btnLocTheoNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocTheoNgayActionPerformed(evt);
            }
        });

        btnTimKiemTheoSDTKH.setText("Tìm kiếm");
        btnTimKiemTheoSDTKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTheoSDTKHActionPerformed(evt);
            }
        });

        ResetTimKiemTheoSDTKH.setText("Reset tìm kiếm");
        ResetTimKiemTheoSDTKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetTimKiemTheoSDTKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTimKiemTheoSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ResetTimKiemTheoSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cboLocTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDateBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLocTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetLocTheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDateKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboLocTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDateKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiemTheoSDTKH)
                    .addComponent(ResetTimKiemTheoSDTKH)
                    .addComponent(btnLocTheoNgay)
                    .addComponent(btnResetLocTheoNgay))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách giỏ hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "IDSPCT", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách IMEI"));

        tblIMEI.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "IMEI", "Trạng Thái"
            }
        ));
        jScrollPane3.setViewportView(tblIMEI);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
        String[] tachSoTrang = lblTrang.getText().trim().split("/");
        if (tachSoTrang[0].equals("1") == false) {
            loadTable(listOfTableHoaDon, Integer.parseInt(tachSoTrang[0]) - 1);
        }
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        String[] tachSoTrang = lblTrang.getText().trim().split("/");
        if (tachSoTrang[0].equals(tachSoTrang[1]) == false) {
            loadTable(listOfTableHoaDon, Integer.parseInt(tachSoTrang[0]) + 1);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    public boolean checkTT() {
        if (txtDateBatDau.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập thời gian bắt đầu của khuyến mãi", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtDateKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập thời gian kết thúc khuyến mãi", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtDateKetThuc.getDate().compareTo(txtDateBatDau.getDate()) < 0) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc đang nhỏ hơn ngày bắt đầu", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            sdfNamThangNgay.format(txtDateBatDau.getDate());
            sdfNamThangNgay.format(txtDateKetThuc.getDate());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ \n Vui lòng chỉ chọn lịch k nhập tay", "Chú ý", JOptionPane.WARNING_MESSAGE);
            txtDateBatDau.setDate(null);
            txtDateKetThuc.setDate(null);
            return false;
        }
        return true;
    }
    private void cboLocTrangThaiHoaDonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocTrangThaiHoaDonItemStateChanged
        // TODO add your handling code here:
        //hủy lọc tìm kiếm theo ngày
        txtTimKiem.setEnabled(true);
        txtTimKiem.setText("");
        // hủy lọc tìm kiếm theo sdtkh
        txtDateBatDau.setEnabled(true);
        txtDateKetThuc.setEnabled(true);
        txtDateBatDau.setDate(null);
        txtDateKetThuc.setDate(null);
        if (cboLocTrangThaiHoaDon.getSelectedIndex() != 0) {
            loadTable(hoaDonService.getAllHoaDonTheoTrangThai(
                    cboLocTrangThaiHoaDon.getSelectedIndex() - 1),
                    1);
        } else {
            loadTable(hoaDonService.getAllHoaDon(), 1);
        }
    }//GEN-LAST:event_cboLocTrangThaiHoaDonItemStateChanged

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        if (tblHoaDon.getSelectedRow() != -1) {
            loadTableGioHang(hoaDonChiTietService.getListHienThiCuaGioHang(getIDHDFromTableHoaDon()));
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:
        if (tblGioHang.getSelectedRow() != -1) {
            loadTableIMEI(hoaDonChiTietService.getListIMEITheoIDSPCTVaIDHD(getIDHDFromTableHoaDon(), getIntIDSPCTFromGioHang()));
        }
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnLocTheoNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocTheoNgayActionPerformed
        // TODO add your handling code here:
        if (checkTT()) {
            // phải setEnable trước vì ảnh hưởng đến cái sau
            txtDateBatDau.setEnabled(false);
            txtDateKetThuc.setEnabled(false);
            if (getListKiemTra3Loc().isEmpty()) {
                // trả lại
                txtDateBatDau.setEnabled(true);
                txtDateKetThuc.setEnabled(true);
                if (txtTimKiem.isEnabled()) {// nếu đang k có tìm kiếm
                    JOptionPane.showMessageDialog(this, "Không có hóa đơn nào trong khoảng ngày này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Không có hóa đơn nào có khách hàng có SDT " + txtTimKiem.getText().trim() + " và trong khoảng ngày này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                loadTable(getListKiemTra3Loc(), 1);
            }
        }
    }//GEN-LAST:event_btnLocTheoNgayActionPerformed

    public List<HoaDon> getListKiemTra3Loc() {
        List<HoaDon> list = hoaDonService.getAllHoaDon(); // lấy tất cả hóa đơn không có điều kiện
        if (txtDateBatDau.isEnabled() == false && txtDateKetThuc.isEnabled() == false) { // Nếu đang lọc theo ngày
            list = new ArrayList<>(hoaDonService.getAllHoaDonTheoNgay(
                    sdfNamThangNgay.format(txtDateBatDau.getDate()),
                    sdfNamThangNgay.format(txtDateKetThuc.getDate())));
        }

        if (txtTimKiem.isEnabled() == false) {// nếu đang lọc theo sdtkh
            List<HoaDon> listLocTimKiemTheoSDT = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                KhachHang khachHang = khachHangService.getKhachHangBySDTKhachHang(txtTimKiem.getText().trim());
                if (khachHang == null) {
                    return new ArrayList<>();
                }
                if (list.get(i).getIDKH() != null) {
                    if (list.get(i).getIDKH() == khachHang.getId()) {
                        listLocTimKiemTheoSDT.add(list.get(i));
                    }
                }
            }
            list = new ArrayList<>(listLocTimKiemTheoSDT);
        }

        if (cboLocTrangThaiHoaDon.getSelectedIndex() != 0) {// nếu đang lọc có set trạng thái
            List<HoaDon> listLocTimKiemTheoTrangThai = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getTrangThai() == cboLocTrangThaiHoaDon.getSelectedIndex() - 1) {
                    listLocTimKiemTheoTrangThai.add(list.get(i));
                }
            }
            list = new ArrayList<>(listLocTimKiemTheoTrangThai);
        }
        return list;
    }
    private void btnResetLocTheoNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetLocTheoNgayActionPerformed
        // TODO add your handling code here:
        txtDateBatDau.setDate(null);
        txtDateKetThuc.setDate(null);
        txtDateBatDau.setEnabled(true);
        txtDateKetThuc.setEnabled(true);
        loadTable(getListKiemTra3Loc(), 1);
    }//GEN-LAST:event_btnResetLocTheoNgayActionPerformed

    private void btnTimKiemTheoSDTKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTheoSDTKHActionPerformed
        // TODO add your handling code here:
        timKiemHoaDonTheoSDTKhacHang();
    }//GEN-LAST:event_btnTimKiemTheoSDTKHActionPerformed

    private void ResetTimKiemTheoSDTKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetTimKiemTheoSDTKHActionPerformed
        // TODO add your handling code here:
        txtTimKiem.setText("");
        txtTimKiem.setEnabled(true);
        loadTable(getListKiemTra3Loc(), 1);
    }//GEN-LAST:event_ResetTimKiemTheoSDTKHActionPerformed
    //Hàm getIDSPCT from giỏ hàng để xem những IMEI đã mua
    public int getIntIDSPCTFromGioHang() {
        return Integer.parseInt(tblGioHang.getValueAt(tblGioHang.getSelectedRow(), 1).toString());
    }

    //Load table IMEI
    public void loadTableIMEI(List<IMEI> list) {
        defaultTableModelIMEI.setRowCount(0);
        int dem = 0;
        for (IMEI imei : list) {
            defaultTableModelIMEI.addRow(new Object[]{
                ++dem,
                imei.getIMEI(),
                getTextTrangThaiCuaIMEI(imei.getTrangThai())
            });
        }
    }

    //Load table hóa đơn
    public void loadTableGioHang(List<HoaDonChiTietDisplay> list) {
        defaultTableModelGiohang.setRowCount(0);
        int dem = 0;
        for (int i = 0; i < list.size(); i++) {
            // Khai báo các đội tượng model để lấy thông tin
            SPCT spct = spctService.getSPCTTheoIDSPCT(list.get(i).getIDSPCT());
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
            defaultTableModelGiohang.addRow(new Object[]{
                ++dem,
                list.get(i).getIDSPCT(),
                tenFullSanPham.replace("  ", " "),
                list.get(i).getSoLuong(),
                getTextGia(list.get(i).getDonGia()) + "đ",
                getTextGia(list.get(i).getThanhTien()) + "đ",});
        }
        // reset table IMEI
        loadTableIMEI(new ArrayList<>());
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

    //hàm lấy IDHD từ bảng hóa đơn
    public int getIDHDFromTableHoaDon() {
        return Integer.parseInt(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
    }

    //Get textTrangThai IMEI
    public String getTextTrangThaiCuaIMEI(int trangThai) {
        if (trangThai == 0) {
            return "Đang bán";
        } else if (trangThai == 1) {
            return "Đang trong giỏ hàng";
        } else if (trangThai == 2) {
            return "Đã bán";

        } else if (trangThai == 3) {
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
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ResetTimKiemTheoSDTKH;
    private javax.swing.JButton btnLocTheoNgay;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnResetLocTheoNgay;
    private javax.swing.JButton btnTimKiemTheoSDTKH;
    private javax.swing.JComboBox<String> cboLocTrangThaiHoaDon;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblIMEI;
    private com.toedter.calendar.JDateChooser txtDateBatDau;
    private com.toedter.calendar.JDateChooser txtDateKetThuc;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
