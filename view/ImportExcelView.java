/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.*;
import model.*;

/**
 *
 * @author PHONG
 */
public class ImportExcelView extends javax.swing.JFrame {

    /**
     * Creates new form ThemIMEIView
     */
    List<String> listIMEIExcelImport;
    List<String> listIMEILoiChuaKyTuKPhLaSo;
    List<String> listIMEILoiKhac15so;
    List<String> listIMEILoiTrungTrongListIMEIImport;
    List<String> listIMEILoiDaTonTaiIMEI;
    int IDSPCT = 0;
    IMEIService imeiService = new IMEIService();
    DefaultTableModel defaultTableModel;
    // Khai báo IDNV
    int IDNV = 0;

    public JButton getBtnExcel() {
        return btnExcel;
    }

    public JButton getBtnInsertIMEI() {
        return btnInsertIMEI;
    }

    public ImportExcelView() {
        initComponents();
        setLocationRelativeTo(null);
        defaultTableModel = (DefaultTableModel) tblIMEIImportExcel.getModel();
        btnInsertIMEI.setEnabled(false);
        btnInIMEILoi.setEnabled(false);
    }

    public void loadTable(List<String> listIMEI) {
        defaultTableModel.setRowCount(0);
        for (String string : listIMEI) {
            defaultTableModel.addRow(new Object[]{string});
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

        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblIMEIImportExcel = new javax.swing.JTable();
        btnExcel = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnInsertIMEI = new javax.swing.JButton();
        btnInIMEILoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("IMEI Excel"));

        tblIMEIImportExcel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "IMEI"
            }
        ));
        jScrollPane1.setViewportView(tblIMEIImportExcel);

        btnExcel.setText("Excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        btnInsertIMEI.setText("Insert");
        btnInsertIMEI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertIMEIActionPerformed(evt);
            }
        });

        btnInIMEILoi.setText("In IMEI Lỗi");
        btnInIMEILoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInIMEILoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(btnInIMEILoi)
                .addGap(18, 24, Short.MAX_VALUE)
                .addComponent(btnInsertIMEI))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnInIMEILoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInsertIMEI, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 104, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExcel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        // TODO add your handling code here:
        listIMEIExcelImport = new ArrayList<>();
        listIMEILoiChuaKyTuKPhLaSo = new ArrayList<>();
        listIMEILoiDaTonTaiIMEI = new ArrayList<>();
        listIMEILoiKhac15so = new ArrayList<>();
        listIMEILoiTrungTrongListIMEIImport = new ArrayList<>();
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTable = null;
        JFileChooser excelFileChooser = new JFileChooser("D:\\FPTPolytechnic\\DUAN1\\DA1\\src\\excel");
        //filter file
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("excel_file", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        //set title
        excelFileChooser.setDialogTitle("Chọn file excels");
        int excelChooser = excelFileChooser.showOpenDialog(null);
        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = excelFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTable = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTable.getSheetAt(0);// lấy tại trang sheet đầu tiên
                // lấy qua cột và dòng
                for (int row = 0; row < excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row); // lấy row
//                    for (int column = 0; column < excelRow.getLastCellNum(); column++) {
//                        XSSFCell exceCell = excelRow.getCell(column);
//                    }
                    XSSFCell excelIMEI = excelRow.getCell(0);//lấy ô
                    /// định thêm một cái check IMEI
                    if (excelIMEI != null) { // check có null không - không null mới chạy
                        if (excelIMEI.toString().trim().equals("") == false) { //check không trống mới chạy
                            try {
                                String IMEI = excelIMEI.toString().trim();
                                Integer i = checkIMEI(IMEI);
                                if (i == null) {
                                    listIMEIExcelImport.add(IMEI);
                                } else if (i == 0) {
                                    listIMEILoiChuaKyTuKPhLaSo.add("'"+IMEI);
                                } else if (i == 1) {
                                    listIMEILoiKhac15so.add("'"+IMEI);
                                } else if (i == 2) {
                                    listIMEILoiTrungTrongListIMEIImport.add("'"+IMEI);
                                } else if (i == 3) {
                                    listIMEILoiDaTonTaiIMEI.add("'"+IMEI);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                listIMEILoiKhac15so.add("'"+excelIMEI.toString());
                            }
                        }
                    }
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "File lỗi", "Thông báo", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelJTable != null) {
                        excelJTable.close();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    e.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(this,
                    "Có " + listIMEIExcelImport.size() + " IMEI hợp lệ" + "\n"
                    + "Và " + listIMEILoiChuaKyTuKPhLaSo.size() + " IMEI chứa ký tự không phải là số" + "\n"
                    + "Và " + listIMEILoiKhac15so.size() + " IMEI không đủ 15 số" + "\n"
                    + "Và " + listIMEILoiTrungTrongListIMEIImport.size() + " IMEI trùng trong file Excel" + "\n"
                    + "Và " + listIMEILoiDaTonTaiIMEI.size() + " IMEI đã tồn tại",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadTable(listIMEIExcelImport);
            if (!listIMEIExcelImport.isEmpty()) {
                btnInsertIMEI.setEnabled(true);
            } else {
                btnInsertIMEI.setEnabled(false);
            }
            if (listIMEILoiChuaKyTuKPhLaSo.isEmpty() && listIMEILoiKhac15so.isEmpty() && listIMEILoiTrungTrongListIMEIImport.isEmpty() && listIMEILoiDaTonTaiIMEI.isEmpty()) {
                btnInIMEILoi.setEnabled(false);
            } else {
                btnInIMEILoi.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnInsertIMEIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertIMEIActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnInsertIMEIActionPerformed
    public void thucHienBtnInsertIMEI() {
        System.out.println("ID của sản phẩm là:" + IDSPCT);
        if (!listIMEILoiChuaKyTuKPhLaSo.isEmpty() || !listIMEILoiKhac15so.isEmpty() || !listIMEILoiTrungTrongListIMEIImport.isEmpty() || !listIMEILoiDaTonTaiIMEI.isEmpty()) {
            Object[] options = {"Có in hộ tôi", "Không cần in"};
            int result = JOptionPane.showOptionDialog(this, "Nếu bạn insert sẽ k thể in các IMEI lỗi sau đó \n  Vậy bạn có muốn in các IMEI lỗi ra excel không", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (result == JOptionPane.YES_OPTION) {
                if (inIMEILoiRaFileExcel()) {
                    insertIMEI();
                }
            } else if (result == JOptionPane.NO_OPTION) {
                insertIMEI();
            }
        } else {
            insertIMEI();
        }

    }

    public void insertIMEI() {
        int dem = 0;
        System.out.println(IDNV);
        for (String string : listIMEIExcelImport) {
            if (imeiService.insertIMEI(string, IDSPCT, IDNV) > 0) {
                ++dem;
            }
        }
        if (dem > 0) {
            JOptionPane.showMessageDialog(this, "Đã thêm " + dem + " IMEI thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            listIMEIExcelImport = new ArrayList<>();
            loadTable(listIMEIExcelImport);
            btnInsertIMEI.setEnabled(false);
            btnInIMEILoi.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    private void btnInIMEILoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInIMEILoiActionPerformed
        inIMEILoiRaFileExcel();
    }//GEN-LAST:event_btnInIMEILoiActionPerformed
    public boolean inIMEILoiRaFileExcel() {
        try {
            // TODO add your handling code here:
            writeExcelIMEILoi(listIMEILoiChuaKyTuKPhLaSo, listIMEILoiKhac15so, listIMEILoiTrungTrongListIMEIImport, listIMEILoiDaTonTaiIMEI);
            JOptionPane.showMessageDialog(this, "In thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            listIMEILoiChuaKyTuKPhLaSo = new ArrayList<>();
            listIMEILoiKhac15so = new ArrayList<>();
            listIMEILoiTrungTrongListIMEIImport = new ArrayList<>();
            listIMEILoiDaTonTaiIMEI = new ArrayList<>();
            btnInIMEILoi.setEnabled(false);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "In ra file bị lỗi \n Bạn kiểm tra đã đóng file Excel chưa ", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public Integer checkIMEI(String IMEI) {
        for (char object : IMEI.toCharArray()) { // có ký tự k ph là số
            if (Character.isDigit(object) == false) {
                return 0;
            }
        }
        if (IMEI.length() != 15) {// k đủ 15 số
            return 1;
        }
        for (String string : listIMEIExcelImport) { // check đã trùng trong list
            if (string.equalsIgnoreCase(IMEI)) {
                return 2;
            }
        }
        for (IMEI object : imeiService.getAllIMEI()) { // trùng imei trong db
            if (object.getIMEI().equalsIgnoreCase(IMEI)) {
                return 3;
            }
        }
        return null;
    }

    public void writeExcelIMEILoi(List<String> listIMEILoiChuaKyTuKPhLaSo, List<String> listIMEILoiKhac15So, List<String> listIMEILoiTrungIMEITrongListIMEIImport, List<String> listIMEIDaTonTai) throws FileNotFoundException, IOException {
        FileOutputStream file = new FileOutputStream("D:\\FPTPolytechnic\\DUAN1\\DA1\\src\\excel\\wrireIMEILoi.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("IMEILoi");
        XSSFRow row;
        XSSFCell cell0;
//        for (int i = 0; i < listIMEILoiKhac15.size()+1; i++) {
//            row = workSheet.createRow((short)i);
//            if(i==0){
//                cell0= row.createCell((short)0);
//                cell0.setCellValue("Những IMEI lỗi không đủ 15 số, quá 15 số");
//            }else {
//                cell0= row.createCell((short)1);
//                cell0.setCellValue(listIMEILoiKhac15.get(i-1));
//            }
//        }
        int demDong = 0;
        if (!listIMEILoiChuaKyTuKPhLaSo.isEmpty()) {
            row = workSheet.createRow((short) demDong);
            cell0 = row.createCell((short) 0);
            cell0.setCellValue("IMEI CHỨA KÝ TỰ KHÔNG PHẢI LÀ SỐ");
            for (int i = 0; i < listIMEILoiChuaKyTuKPhLaSo.size(); i++) {
                demDong = ++demDong;
                row = workSheet.createRow((short) demDong);
                cell0 = row.createCell((short) 0);
                cell0.setCellValue(listIMEILoiChuaKyTuKPhLaSo.get(i));
            }
            demDong = ++demDong;
        }
        if (!listIMEILoiKhac15So.isEmpty()) {
            row = workSheet.createRow((short) demDong);
            cell0 = row.createCell((short) 0);
            cell0.setCellValue("IMEI CÓ ĐỘ DÀI KHÔNG HỢP LỆ");
            for (int i = 0; i < listIMEILoiKhac15So.size(); i++) {
                demDong = ++demDong;
                row = workSheet.createRow((short) demDong);
                cell0 = row.createCell((short) 0);
                cell0.setCellValue(listIMEILoiKhac15So.get(i));
            }
            demDong = ++demDong;
        }
        if (!listIMEILoiTrungIMEITrongListIMEIImport.isEmpty()) {
            demDong = ++demDong;
            row = workSheet.createRow((short) demDong);
            cell0 = row.createCell((short) 0);
            cell0.setCellValue("IMEI TRÙNG TRONG LIST EXCEL");
            for (int i = 0; i < listIMEILoiTrungIMEITrongListIMEIImport.size(); i++) {
                demDong = ++demDong;
                row = workSheet.createRow((short) demDong);
                cell0 = row.createCell((short) 0);
                cell0.setCellValue(listIMEILoiTrungIMEITrongListIMEIImport.get(i));
            }
            demDong = ++demDong;
        }
        if (!listIMEIDaTonTai.isEmpty()) {
            demDong = ++demDong;
            row = workSheet.createRow((short) demDong);

            cell0 = row.createCell((short) 0);
            cell0.setCellValue("IMEI ĐÃ TỒN TẠI");
            for (int i = 0; i < listIMEIDaTonTai.size(); i++) {
                demDong = ++demDong;
                row = workSheet.createRow((short) demDong);
                cell0 = row.createCell((short) 0);
                cell0.setCellValue(listIMEIDaTonTai.get(i));
            }
            demDong = ++demDong;
        }
        workbook.write(file);
        workbook.close();
        file.close();
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImportExcelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImportExcelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImportExcelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImportExcelView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImportExcelView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnInIMEILoi;
    private javax.swing.JButton btnInsertIMEI;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblIMEIImportExcel;
    // End of variables declaration//GEN-END:variables
}
