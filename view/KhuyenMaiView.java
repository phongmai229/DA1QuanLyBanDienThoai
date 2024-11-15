/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

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
public class KhuyenMaiView extends javax.swing.JFrame {

    /**
     * Creates new form KhuyenMaiView
     */
    DefaultTableModel defaultTableModel;
    DefaultTableModel defaultTableModelKhuyenMaiChiTiet;
    DefaultTableModel defaultTableModelDieuKienSanPham;
    KhuyenMaiService khuyenMaiService = new KhuyenMaiService();
    KhuyenMaiChiTietService khuyenMaiChiTietService = new KhuyenMaiChiTietService();
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
    SimpleDateFormat sdfNgayThangNam = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdfNamThangNgay = new SimpleDateFormat("yyyy-MM-dd");
    // Khai báo IDNV
    int IDNV = 0;

    public KhuyenMaiView() {
        initComponents();
        setLocationRelativeTo(null);
        // Lấy model bảng
        defaultTableModel = (DefaultTableModel) tblKhuyenMai.getModel();
        defaultTableModelKhuyenMaiChiTiet = (DefaultTableModel) tblKhuyenMaiChiTiet.getModel();
        defaultTableModelDieuKienSanPham = (DefaultTableModel) tblDieuKienSanPham.getModel();
        // load tất cả
        loadTable(khuyenMaiService.getAllKhuyenMaiTuMoiXuongCu());
        // disable một số nút
        jTabbedPane1.setEnabledAt(1, false);
        btnCapNhat.setEnabled(false);
        btnXemKhuyenMaiChiTiet.setEnabled(false);
        
        loadTableDieuKienSanPham(spctService.getAllSPCTDangHoatDongVaTuMoiXuongCu());
    }

    public void loadTable(List<KhuyenMai> list) {
        try {
            defaultTableModel.setRowCount(0);
            for (KhuyenMai khuyenMai : list) {
                defaultTableModel.addRow(new Object[]{
                    khuyenMai.getIDKM(),
                    khuyenMai.getTenKM(),
                    sdfNgayThangNam.format(sdfNamThangNgay.parse(khuyenMai.getNgayBatDau())) + " đến hết ngày " + sdfNgayThangNam.format(sdfNamThangNgay.parse(khuyenMai.getNgayKetThuc())),
                    khuyenMai.getGhiChu(),
                    getTextTrangThai(khuyenMai.getTrangThai())
                });
            }
        } catch (Exception e) {
        }
    }

    public void loadTableKhuyenMaiChiTiet(List<KhuyenMaiChiTiet> list) {
        try {
            defaultTableModelKhuyenMaiChiTiet.setRowCount(0);
            for (KhuyenMaiChiTiet khuyenMaiChiTiet : list) {
                defaultTableModelKhuyenMaiChiTiet.addRow(new Object[]{
                    khuyenMaiChiTiet.getMa_Voucher(),
                    getTextLoaiKhuyenMai(khuyenMaiChiTiet.getLoaiKhuyenMai()),
                    getTextIntFromVND(khuyenMaiChiTiet.getDieuKienGia()),
                    getTextGia(khuyenMaiChiTiet.getGiaKhuyenMai()) + getTextDonViKhuyenMai(khuyenMaiChiTiet.getDonViKhuyenMai()),
                    getTextIntFromVND(khuyenMaiChiTiet.getGiamToiDa()),
                    khuyenMaiChiTiet.getDieuKienSoLuong() + "sp",
                    khuyenMaiChiTiet.getSoLuong() + "cái"
                });
            }
        } catch (Exception e) {
        }
    }

    public String getTextGia(int gia) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(gia);
        return formattedNumber.replace(",", ".");
    }

    public String getTextIntFromVND(Integer gia) {
        if (gia != null) {
            return getTextGia(gia) + "đ";
        }
        return null;
    }

    public String getTextLoaiKhuyenMai(int loaiKhuyenMai) {
        if (loaiKhuyenMai == 0) {
            return "Voucher cho hóa đơn";
        } else if (loaiKhuyenMai == 1) {
            return "Voucher cho sản phẩm";
        } else {
            return "Không biết";
        }
    }

    public Integer getIntDonViKhuyenMai() {
        if (rdoVND.isSelected()) {
            return 0;
        } else if (rdoPhanTram.isSelected()) {
            return 1;
        }
        return null;
    }

    public String getTextDonViKhuyenMai(int donVi) {
        if (donVi == 0) {
            return "VND";
        } else if (donVi == 1) {
            return "%";
        } else {
            return "Không biết";
        }
    }

    public String getTextTrangThai(int trangThai) {
        if (trangThai == 0) {
            return "Đang diễn ra";
        } else if (trangThai == 1) {
            return "Sắp diễn ra";
        } else if (trangThai == 2) {
            return "Đã kết thúc";
        } else {
            return "Không biết";
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenKhuyenMai = new javax.swing.JTextField();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        btnXemKhuyenMaiChiTiet = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cboLoc = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtMaVoucher = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDieuKienGia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rdoVND = new javax.swing.JRadioButton();
        rdoPhanTram = new javax.swing.JRadioButton();
        txtGiaKhuyenMai = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGiamToiDa = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDieuKienSoLuong = new javax.swing.JTextField();
        btnCapNhatKhuyenMaiChiTiet = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        btnThemKhuyenMaiChiTiet = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhuyenMaiChiTiet = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        cboLoaiKhuyenMai = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        btnResetTableDieuKienSanPham = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDieuKienSanPham = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1106, 704));

        jLabel1.setText("Tên khuyến mãi:");

        jLabel2.setText("Từ ngày:");

        jLabel3.setText("Đến ngày:");

        jLabel4.setText("Ghi chú");

        txtNgayKetThuc.setDateFormatString("dd-MM-yyyy");

        txtNgayBatDau.setDateFormatString("dd-MM-yyyy");
        txtNgayBatDau.setMaxSelectableDate(new java.util.Date(253370743301000L));

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khuyến mãi"));
        jPanel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên khuyến mãi", "Thời gian", "Ghi chú", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMai.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblKhuyenMai.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhuyenMai);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );

        btnXemKhuyenMaiChiTiet.setText("Chi Tiết Khuyến Mãi");
        btnXemKhuyenMaiChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemKhuyenMaiChiTietActionPerformed(evt);
            }
        });

        jLabel20.setText("Lọc");

        cboLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang diễn ra", "Sắp diễn ra", "Đã kết thúc" }));
        cboLoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocItemStateChanged(evt);
            }
        });
        cboLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 662, Short.MAX_VALUE)
                .addComponent(btnXemKhuyenMaiChiTiet)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemKhuyenMaiChiTiet)
                    .addComponent(jLabel20)
                    .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCapNhat.setText("CẬP NHẬT");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(btnThem))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCapNhat)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
                .addContainerGap(812, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnCapNhat))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Khuyến mãi", jPanel1);

        jLabel5.setText("Mã Voucher");

        jLabel7.setText("Điều kiện giá");

        jLabel8.setText("Giá khuyến mãi");

        jLabel9.setText("Đơn vị khuyến mãi");

        buttonGroup2.add(rdoVND);
        rdoVND.setText("VND");
        rdoVND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoVNDActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoPhanTram);
        rdoPhanTram.setText("%");
        rdoPhanTram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoPhanTramActionPerformed(evt);
            }
        });

        jLabel10.setText("Giảm tối đa");

        jLabel11.setText("Điều kiên số lượng sản phẩm");

        btnCapNhatKhuyenMaiChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCapNhatKhuyenMaiChiTiet.setText("CẬP NHẬT");
        btnCapNhatKhuyenMaiChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatKhuyenMaiChiTietActionPerformed(evt);
            }
        });

        jLabel12.setText("Số lượng");

        btnThemKhuyenMaiChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemKhuyenMaiChiTiet.setText("THÊM");
        btnThemKhuyenMaiChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhuyenMaiChiTietActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khuyến mãi"));

        tblKhuyenMaiChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Voucher", "Loại khuyến mãi", "Điều kiện giá", "Giảm", "Giảm tối đa", "Điều kiện số lượng", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMaiChiTiet.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblKhuyenMaiChiTiet.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblKhuyenMaiChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiChiTietMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKhuyenMaiChiTiet);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jLabel13.setText("Loại khuyến mãi");

        cboLoaiKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Voucher cho hóa đơn", "Voucher cho sản phẩm" }));
        cboLoaiKhuyenMai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLoaiKhuyenMaiItemStateChanged(evt);
            }
        });

        btnResetTableDieuKienSanPham.setText("Reset");
        btnResetTableDieuKienSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTableDieuKienSanPhamActionPerformed(evt);
            }
        });

        tblDieuKienSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tên sản phẩm", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDieuKienSanPham.setOpaque(false);
        jScrollPane3.setViewportView(tblDieuKienSanPham);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btnResetTableDieuKienSanPham)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btnResetTableDieuKienSanPham)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSoLuong))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtGiamToiDa))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDieuKienSoLuong))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGiaKhuyenMai)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(rdoVND, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                        .addComponent(rdoPhanTram, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtMaVoucher)
                                    .addComponent(txtDieuKienGia)
                                    .addComponent(cboLoaiKhuyenMai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(86, 86, 86)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnThemKhuyenMaiChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCapNhatKhuyenMaiChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 180, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cboLoaiKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDieuKienGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtGiaKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addComponent(jLabel9))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoVND)
                                .addComponent(rdoPhanTram)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtGiamToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtDieuKienSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCapNhatKhuyenMaiChiTiet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThemKhuyenMaiChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Khuyến mãi chi tiết", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        // TODO add your handling code here:
        int viTri = tblKhuyenMai.getSelectedRow();
        if (viTri >= 0) {
            btnCapNhat.setEnabled(true);
            btnXemKhuyenMaiChiTiet.setEnabled(true);
        } else {
            btnCapNhat.setEnabled(false);
            btnXemKhuyenMaiChiTiet.setEnabled(false);
        }
        if (khuyenMaiService.getKhuyenMaiTheoIDKM(getIDKMFromRowSelectedTable()).getTrangThai() == 2) {
            btnCapNhat.setEnabled(false);
            btnThemKhuyenMaiChiTiet.setEnabled(false);
            btnCapNhatKhuyenMaiChiTiet.setEnabled(false);
        } else {
            btnCapNhat.setEnabled(true);
            btnThemKhuyenMaiChiTiet.setEnabled(true);
            btnCapNhatKhuyenMaiChiTiet.setEnabled(true);
        }
        setForm(viTri);
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void btnXemKhuyenMaiChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemKhuyenMaiChiTietActionPerformed
        // TODO add your handling code here:
        int viTriRow = tblKhuyenMai.getSelectedRow();
        if (viTriRow == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khuyến mãi", "Chú ý", JOptionPane.WARNING_MESSAGE);
        } else {
            jTabbedPane1.setSelectedIndex(1);
            xoaFormKhuyenMaiChiTiet();
            loadTableKhuyenMaiChiTiet(khuyenMaiChiTietService.getAllKhuyenMaiChiTietTheoIDKM(getIDKMFromRowSelectedTable()));
        }
    }//GEN-LAST:event_btnXemKhuyenMaiChiTietActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        if (tblKhuyenMai.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng khuyến mãi trên table", "Chú ý", JOptionPane.WARNING_MESSAGE);
        } else {
            if (checkTT()) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn cập nhật khuyến mãi?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.OK_OPTION) {
                    if (khuyenMaiService.updateKhuyenMai(getForm(), getIDKMFromRowSelectedTable()) > 0) {
                        JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thành công");
                        getLoadTableTheoIndexCbo();
                        xoaForm();
                    } else {
                        JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thất bại");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed
    public int getIDKMFromRowSelectedTable() {
        return Integer.parseInt(tblKhuyenMai.getValueAt(tblKhuyenMai.getSelectedRow(), 0).toString());
    }
    private void tblKhuyenMaiChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiChiTietMouseClicked
        // TODO add your handling code here:
        int viTri = tblKhuyenMaiChiTiet.getSelectedRow();
        KhuyenMaiChiTiet khuyenMaiChiTiet = khuyenMaiChiTietService.getKhuyenMaiChiTietTheoMaVoucher(getMaVoucherFromSelectedTable());
        txtMaVoucher.setText(tblKhuyenMaiChiTiet.getValueAt(viTri, 0).toString());
        cboLoaiKhuyenMai.setSelectedIndex(khuyenMaiChiTiet.getLoaiKhuyenMai());
        if (khuyenMaiChiTiet.getDieuKienGia() == null) {
            txtDieuKienGia.setText(null);
        } else {
            txtDieuKienGia.setText(String.valueOf(khuyenMaiChiTiet.getDieuKienGia()));
        }
        txtGiaKhuyenMai.setText(String.valueOf(khuyenMaiChiTiet.getGiaKhuyenMai()));
        setRdoByDonViKhuyenMaiInt(khuyenMaiChiTiet.getDonViKhuyenMai());
        if (khuyenMaiChiTiet.getGiamToiDa() == null) {
            txtGiamToiDa.setText(null);
        } else {
            txtGiamToiDa.setText(String.valueOf(khuyenMaiChiTiet.getGiamToiDa()));
        }
        txtDieuKienSoLuong.setText(String.valueOf(khuyenMaiChiTiet.getDieuKienSoLuong()));
        txtSoLuong.setText(String.valueOf(khuyenMaiChiTiet.getSoLuong()));
        if (khuyenMaiChiTiet.getLoaiKhuyenMai() == 1) {
            loadTableDieuKienSanPhamTheoDieuKienSPCuaKhuyenMai(getListDieuKienSanPham(khuyenMaiChiTiet.getDieuKienSP()));
        }
    }//GEN-LAST:event_tblKhuyenMaiChiTietMouseClicked

    public void setRdoByDonViKhuyenMaiInt(int donViKhuyenMai) {
        if (donViKhuyenMai == 0) {
            rdoVND.setSelected(true);
        } else if (donViKhuyenMai == 1) {
            rdoPhanTram.setSelected(true);
        }
    }

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (checkTT()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thêm khuyến mãi?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.OK_OPTION) {
                if (khuyenMaiService.insertKhuyenMai(getForm()) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công");// hiện thông báo
                    getLoadTableTheoIndexCbo();// load lại
                    xoaForm();// xóa form
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thất bại thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void cboLocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocItemStateChanged
        // TODO add your handling code here:
        xoaFormKhuyenMaiChiTiet();
        btnCapNhat.setEnabled(false);
        getLoadTableTheoIndexCbo();
    }//GEN-LAST:event_cboLocItemStateChanged

    private void btnThemKhuyenMaiChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhuyenMaiChiTietActionPerformed
        // TODO add your handling code here:
        if (checkTTKhuyenMaiChiTiet() && checkMaVoucher()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn thêm khuyến mãi?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.OK_OPTION) {
                if (khuyenMaiChiTietService.insertKhuyenMaiChiTiet(getFormKhuyenMaiChiTiet()) > 0) {
                    JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công");// hiện thông báo
                    loadTableKhuyenMaiChiTiet(khuyenMaiChiTietService.getAllKhuyenMaiChiTietTheoIDKM(getIDKMFromRowSelectedTable()));
                    xoaFormKhuyenMaiChiTiet();// xóa form
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thất bại");
                }
            }
        }
    }//GEN-LAST:event_btnThemKhuyenMaiChiTietActionPerformed
    public void xoaFormKhuyenMaiChiTiet() {
        txtMaVoucher.setText("");
        cboLoaiKhuyenMai.setSelectedIndex(-1);
        txtDieuKienGia.setText("");
        txtGiaKhuyenMai.setText("");
        buttonGroup2.clearSelection();
        txtGiamToiDa.setText("");
        txtDieuKienSoLuong.setText("");
        txtSoLuong.setText("");
    }

    public void loadTableDieuKienSanPham(List<SPCT> list) {
        defaultTableModelDieuKienSanPham.setRowCount(0);
        for (SPCT spct : list) {
            SanPham sanPham = sanPhamService.getSanPhamFromIDSP(spct.getID_San_Pha());
            Dong dong = dongService.getById(sanPham.getID_Dong());
            MauSac mauSac = mauSacService.findByID(spct.getID_Mau());
            Ram ram = ramService.findByID(spct.getID_Ram());
            Rom rom = romService.findByID(spct.getID_Rom());
            Chip chip = chipService.timkiemTheoID(sanPham.getID_Chip());
            ManHinh manHinh = manHinhService.getManHinhTheoIDManHinh(sanPham.getID_Man_Hinh());
            ThongSoManHinh thongSoManHinh = thongSoManHinhService.getThongSoManHinhTheoIDThongSo(manHinh.getIDThongSoManHinh());
            MODELNUMBER modelnumber = modelnumberService.getById(spct.getID_Model_Number());
            String tenFullSanPham = "IPhone " + sanPham.getThe_He() + " " + getTextTenDong(dong.getTen()) + " " + getTextPhienBan(spct.getPhien_Ban()) + " " + modelnumber.getKihieu() + " "
                    + mauSac.getTenMau() + " "
                    + getTextTinhTrangFromInt(spct.getTinh_Trang()) + " "
                    + getTextRamFromDungLuong(ram.getDungLuong()) + " "
                    + getTextRomFromDungLuong(rom.getDungLuong());
            defaultTableModelDieuKienSanPham.addRow(new Object[]{
                spct.getID_SPCT(),
                tenFullSanPham,
                false
            });
        }
    }

    public List<SPCT> getListDieuKienSanPham(String text) {
        List<SPCT> listSPCT = new ArrayList<>();
        String[] a = text.split(",");
        for (int i = 0; i < a.length; i++) {
            SPCT spct = spctService.getSPCTTheoIDSPCT(Integer.parseInt(a[i]));
            if (spct != null) {
                listSPCT.add(spct);
            }
        }
        return listSPCT;
    }

    public void loadTableDieuKienSanPhamTheoDieuKienSPCuaKhuyenMai(List<SPCT> list) {
        defaultTableModelDieuKienSanPham.setRowCount(0);
        for (SPCT spct : list) {
            SanPham sanPham = sanPhamService.getSanPhamFromIDSP(spct.getID_San_Pha());
            Dong dong = dongService.getById(sanPham.getID_Dong());
            MauSac mauSac = mauSacService.findByID(spct.getID_Mau());
            Ram ram = ramService.findByID(spct.getID_Ram());
            Rom rom = romService.findByID(spct.getID_Rom());
            Chip chip = chipService.timkiemTheoID(sanPham.getID_Chip());
            ManHinh manHinh = manHinhService.getManHinhTheoIDManHinh(sanPham.getID_Man_Hinh());
            ThongSoManHinh thongSoManHinh = thongSoManHinhService.getThongSoManHinhTheoIDThongSo(manHinh.getIDThongSoManHinh());
            MODELNUMBER modelnumber = modelnumberService.getById(spct.getID_Model_Number());
            String tenFullSanPham = "IPhone " + sanPham.getThe_He() + " " + getTextTenDong(dong.getTen()) + " " + getTextPhienBan(spct.getPhien_Ban()) + " " + modelnumber.getKihieu() + " "
                    + mauSac.getTenMau() + " "
                    + getTextTinhTrangFromInt(spct.getTinh_Trang()) + " "
                    + getTextRamFromDungLuong(ram.getDungLuong()) + " "
                    + getTextRomFromDungLuong(rom.getDungLuong());
            defaultTableModelDieuKienSanPham.addRow(new Object[]{
                spct.getID_SPCT(),
                tenFullSanPham,
                true
            });
        }
    }

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
    private void cboLoaiKhuyenMaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLoaiKhuyenMaiItemStateChanged
        // TODO add your handling code here:
        if (cboLoaiKhuyenMai.getSelectedIndex() != 1) {
            jPanel8.setVisible(false);

        } else {
            jPanel8.setVisible(true);
            loadTableDieuKienSanPham(spctService.getAllSPCTDangHoatDongVaTuMoiXuongCu());
        }
        if (cboLoaiKhuyenMai.getSelectedIndex() == 0) {
            txtDieuKienGia.setEnabled(true);

        } else {
            txtDieuKienGia.setEnabled(false);
        }
        txtDieuKienGia.setText(null);
    }//GEN-LAST:event_cboLoaiKhuyenMaiItemStateChanged

    private void btnResetTableDieuKienSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTableDieuKienSanPhamActionPerformed
        // TODO add your handling code here:
        loadTableDieuKienSanPham(spctService.getAllSPCTDangHoatDongVaTuMoiXuongCu());
    }//GEN-LAST:event_btnResetTableDieuKienSanPhamActionPerformed

    private void btnCapNhatKhuyenMaiChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatKhuyenMaiChiTietActionPerformed
        // TODO add your handling code here:
        if (tblKhuyenMaiChiTiet.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng khuyến mãi trên table", "Chú ý", JOptionPane.WARNING_MESSAGE);
        } else {
            if (checkTTKhuyenMaiChiTiet() && checkMaVoucherCapNhat()) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn cập nhật khuyến mãi?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.OK_OPTION) {
                    if (khuyenMaiChiTietService.updateKhuyenMaiChiTiet(getFormKhuyenMaiChiTiet(), getMaVoucherFromSelectedTable()) > 0) {
                        JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thành công");
                        loadTableKhuyenMaiChiTiet(khuyenMaiChiTietService.getAllKhuyenMaiChiTietTheoIDKM(getIDKMFromRowSelectedTable()));
                        xoaFormKhuyenMaiChiTiet();
                    } else {
                        JOptionPane.showMessageDialog(this, "Cập nhật khuyến mãi thất bại");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnCapNhatKhuyenMaiChiTietActionPerformed

    private void cboLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocActionPerformed

    private void rdoVNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoVNDActionPerformed
        // TODO add your handling code here:
        txtGiamToiDa.setEnabled(false);
        txtGiamToiDa.setText("");
    }//GEN-LAST:event_rdoVNDActionPerformed

    private void rdoPhanTramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoPhanTramActionPerformed
        // TODO add your handling code here:
        txtGiamToiDa.setEnabled(true);
        txtGiamToiDa.setText("");
    }//GEN-LAST:event_rdoPhanTramActionPerformed
    public KhuyenMaiChiTiet getFormKhuyenMaiChiTiet() {
        return new KhuyenMaiChiTiet(0, getIDKMFromRowSelectedTable(), getTextDieuKienSP(),
                Integer.parseInt(txtSoLuong.getText().trim()), getIntegerNeuNullFromText(txtDieuKienGia.getText().trim()),
                Integer.parseInt(txtGiaKhuyenMai.getText().trim()), getIntDonViKhuyenMai(),
                getIntegerNeuNullFromText(txtGiamToiDa.getText().trim()),
                Integer.parseInt(txtDieuKienSoLuong.getText().trim()),
                0, LocalDate.now().toString(), LocalDate.now().toString(),
                txtMaVoucher.getText().trim(), cboLoaiKhuyenMai.getSelectedIndex());
    }

    public String getMaVoucherFromSelectedTable() {
        return tblKhuyenMaiChiTiet.getValueAt(tblKhuyenMaiChiTiet.getSelectedRow(), 0).toString();
    }

    public Integer getIntegerNeuNullFromText(String text) {
        if (text.equals("")) {
            return null;
        } else {
            return Integer.parseInt(text);
        }
    }

    public String getTextDieuKienSP() {
        String text = "";
        if (jPanel8.isVisible()) {
            for (int i = 0; i < tblDieuKienSanPham.getRowCount(); i++) {
                if (Boolean.TRUE.equals(tblDieuKienSanPham.getValueAt(i, 2))) {
                    if (text.equals("")) {
                        text += tblDieuKienSanPham.getValueAt(i, 0).toString();
                    } else {
                        text += "," + tblDieuKienSanPham.getValueAt(i, 0).toString();
                    }
                }
            }
        }
        return text;
    }

    public boolean checkTTKhuyenMaiChiTiet() {
        // Kiểm tra txt mã voucher
        if (txtMaVoucher.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã voucher", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        //Kiểm tra loaoi voucher
        if (cboLoaiKhuyenMai.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn loại khuyến mãi của voucher", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // kiểm tra nếu loại voucher theo hóa đơn thì phải nhập điều kiện giá
        if (cboLoaiKhuyenMai.getSelectedIndex() == 0) {
            if (txtDieuKienGia.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập điều kiện giá", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            try {
                if (Integer.parseInt(txtDieuKienGia.getText().trim()) < 0) {
                    JOptionPane.showMessageDialog(this, "Điều kiện giá không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Điều kiện giá không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        // Kiểm tra nhập giá khuyến mãi
        if (txtGiaKhuyenMai.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập giá khuyến mãi", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            if (Integer.parseInt(txtGiaKhuyenMai.getText().trim()) <= 0) {
                JOptionPane.showMessageDialog(this, "Giá khuyến mãi không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá khuyến mãi không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // KIểm tra chọn đơn vị khuyến mãi ch
        if (getIntDonViKhuyenMai() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đơn vị khuyến mãi", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // Kiểm tra đơn vị khuyến mãi với giá khuyến mãi
        if (getIntDonViKhuyenMai() == 0 && Integer.parseInt(txtGiaKhuyenMai.getText().trim()) < 1000) {
            JOptionPane.showMessageDialog(this, "Giảm gì giảm chưa đến 1000VND vậy", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (getIntDonViKhuyenMai() == 1 && Integer.parseInt(txtGiaKhuyenMai.getText().trim()) > 100) {
            JOptionPane.showMessageDialog(this, "Giá khuyến mãi không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // Kiểm tra giảm tối đa
        if (txtGiamToiDa.getText().trim().equals("") == false) {
            try {
                if (Integer.parseInt(txtGiamToiDa.getText().trim()) <= 0) {
                    JOptionPane.showMessageDialog(this, "Số tiền giảm tối đa không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số tiền giảm tối đa không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        // KIểm tra nhập điều kiện số lượng
        if (txtDieuKienSoLuong.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập điều kiện số lượng", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            if (Integer.parseInt(txtDieuKienSoLuong.getText().trim()) <= 0) {
                JOptionPane.showMessageDialog(this, "Điều kiện số lượng không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Điều kiện số lượng không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // Kiểm tra nhập số lượng voucher
        if (txtSoLuong.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số lượng voucher", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try {
            if (Integer.parseInt(txtSoLuong.getText().trim()) < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        // Kiểm tra chọn sản phẩm khuyến mãi
        if (cboLoaiKhuyenMai.getSelectedIndex() == 1) {
            int dem = 0;
            for (int i = 0; i < tblDieuKienSanPham.getRowCount(); i++) {
                if (Boolean.TRUE.equals(tblDieuKienSanPham.getValueAt(i, 2))) {
                    ++dem;
                    break;
                }
            }
            if (dem == 0) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm được khuyến mãi", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public boolean checkMaVoucher() {
        if (khuyenMaiChiTietService.getKhuyenMaiChiTietTheoMaVoucher(txtMaVoucher.getText().trim()) != null) {
            JOptionPane.showMessageDialog(this, "Mã voucher đã tồn tại", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean checkMaVoucherCapNhat() {
        if (txtMaVoucher.getText().trim().equalsIgnoreCase(tblKhuyenMaiChiTiet.getValueAt(tblKhuyenMaiChiTiet.getSelectedRow(), 0).toString()) == false) {
            if (khuyenMaiChiTietService.getKhuyenMaiChiTietTheoMaVoucher(txtMaVoucher.getText().trim()) != null) {
                JOptionPane.showMessageDialog(this, "Mã voucher đã tồn tại", "Chú ý", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        if (cboLoaiKhuyenMai.getSelectedIndex() == 1) {
            return true;
        }
        return true;
    }

    public void setForm(int viTri) {
        try {
            KhuyenMai khuyenMai = khuyenMaiService.timKiemKhuyenMaiTheoIDKhuyenMai(getIDKMFromRowSelectedTable());
            txtTenKhuyenMai.setText(khuyenMai.getTenKM());
            Date ngayBatDauChuyenDoi = sdfNamThangNgay.parse(khuyenMai.getNgayBatDau());
            Date ngayKetThucChuyenDoi = sdfNamThangNgay.parse(khuyenMai.getNgayKetThuc());
            txtNgayBatDau.setDate(sdfNgayThangNam.parse(sdfNgayThangNam.format(ngayBatDauChuyenDoi)));
            txtNgayKetThuc.setDate(sdfNgayThangNam.parse(sdfNgayThangNam.format(ngayKetThucChuyenDoi)));
            txtGhiChu.setText(khuyenMai.getGhiChu());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLoadTableTheoIndexCbo() {
        if (cboLoc.getSelectedIndex() == 0) {
            loadTable(khuyenMaiService.getAllKhuyenMaiTuMoiXuongCu());
        }
        if (cboLoc.getSelectedIndex() == 1) {
            loadTable(khuyenMaiService.getAllKhuyenMaiDangDienRa());
        }
        if (cboLoc.getSelectedIndex() == 2) {
            loadTable(khuyenMaiService.getAllKhuyenMaiSapDienRa());
        }
        if (cboLoc.getSelectedIndex() == 3) {
            loadTable(khuyenMaiService.getAllKhuyenMaiDaKetThuc());
        }
    }

    public void xoaForm() {
        txtTenKhuyenMai.setText("");
        txtNgayBatDau.setDate(null);
        txtNgayKetThuc.setDate(null);
        txtGhiChu.setText("");
        btnCapNhat.setEnabled(false);
        btnXemKhuyenMaiChiTiet.setEnabled(false);
    }

    public KhuyenMai getForm() {
        return new KhuyenMai(0,
                txtTenKhuyenMai.getText().trim(),
                sdfNamThangNgay.format(txtNgayBatDau.getDate()),
                sdfNamThangNgay.format(txtNgayKetThuc.getDate()),
                LocalDate.now().toString(),
                IDNV,
                txtGhiChu.getText().trim(),
                LocalDate.now().toString(),
                0);
    }

    public boolean checkTT() {
        if (txtTenKhuyenMai.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên khuyến mãi", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtNgayBatDau.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập thời gian bắt đầu của khuyến mãi", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtNgayKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập thời gian kết thúc khuyến mãi", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtNgayKetThuc.getDate().compareTo(txtNgayBatDau.getDate()) < 0) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc đang nhỏ hơn ngày bắt đầu", "Chú ý", JOptionPane.WARNING_MESSAGE);
            return false;
        }
         try {
            sdfNamThangNgay.format(txtNgayBatDau.getDate());
            sdfNamThangNgay.format(txtNgayKetThuc.getDate());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ \n Vui lòng chỉ chọn lịch k nhập tay", "Chú ý", JOptionPane.WARNING_MESSAGE);
            txtNgayBatDau.setDate(null);
            txtNgayKetThuc.setDate(null);
            return false;
        }
        return true;
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
            java.util.logging.Logger.getLogger(KhuyenMaiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhuyenMaiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhuyenMaiView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCapNhatKhuyenMaiChiTiet;
    private javax.swing.JButton btnResetTableDieuKienSanPham;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemKhuyenMaiChiTiet;
    private javax.swing.JButton btnXemKhuyenMaiChiTiet;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboLoaiKhuyenMai;
    private javax.swing.JComboBox<String> cboLoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoPhanTram;
    private javax.swing.JRadioButton rdoVND;
    private javax.swing.JTable tblDieuKienSanPham;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTable tblKhuyenMaiChiTiet;
    private javax.swing.JTextField txtDieuKienGia;
    private javax.swing.JTextField txtDieuKienSoLuong;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaKhuyenMai;
    private javax.swing.JTextField txtGiamToiDa;
    private javax.swing.JTextField txtMaVoucher;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKhuyenMai;
    // End of variables declaration//GEN-END:variables
}
