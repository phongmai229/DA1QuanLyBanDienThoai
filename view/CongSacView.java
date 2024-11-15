/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.CongSac;
import service.CongSacService;

/**
 *
 * @author Kin
 */
public class CongSacView extends javax.swing.JFrame {

    DefaultTableModel model;
    CongSacService sv;

    public CongSacView() {
        initComponents();
        model = (DefaultTableModel) tblCongSac.getModel();
        sv = new CongSacService();
        fillTable(sv.sapXepHoatDong());
        btSua.setEnabled(false);
        btXoa.setEnabled(false);
        btKhoiPhuc.setEnabled(false);

    }

    public void fillTable(List<CongSac> ListTruyenVao) {
        model.setRowCount(0);
        for (CongSac cs : ListTruyenVao) {

            model.addRow(new Object[]{
                cs.getID_Cong_Sac(), cs.getTen_Cong_Sac(), cs.getCong_Nghe() + "WH", cs.getTrang_Thai()

            });
        }

    }

    public CongSac getSacForm() {
        if (txtTen.getText().trim().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Khong the de trong ten cong sac");
            return null;
        } else if (txtTen.getText().trim().trim().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Khong the de trong ten cong nghe");
            return null;
        } else if (txtCongNghe.getText().trim().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Khong the de trong ten cong nghe");
            return null;
        } else if (txtCongNghe.getText().trim().trim().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Khong the de trong ten cong nghe");
            return null;
        }
        else if (!txtCongNghe.getText().trim().trim().trim().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(rootPane, "Ten cong nghe khong hop le");
            return null;

        } else {
            return new CongSac(txtTen.getText().trim().trim(), Integer.valueOf(txtCongNghe.getText().trim().trim().trim()), 0, LocalDate.now().toString());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        btThem2 = new javax.swing.JButton();
        btSua = new javax.swing.JButton();
        btKhoiPhuc = new javax.swing.JButton();
        btXoa = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCongNghe = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        btRefresh = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCongSac = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbbLoc = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btThem2.setText("Thêm");
        btThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThem2ActionPerformed(evt);
            }
        });

        btSua.setText("Sửa");
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        btKhoiPhuc.setText("Khôi phục");
        btKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKhoiPhucActionPerformed(evt);
            }
        });

        btXoa.setText("Xóa");
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        jLabel3.setText("Ten_Cong_Sac");

        jLabel4.setText("Cong_Nghe");

        btRefresh.setText("Refresh");
        btRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(362, Short.MAX_VALUE)
                .addComponent(btKhoiPhuc)
                .addGap(34, 34, 34)
                .addComponent(btRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCongNghe, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btThem2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCongNghe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(54, 54, 54)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btThem2)
                    .addComponent(btSua)
                    .addComponent(btXoa)
                    .addComponent(btKhoiPhuc)
                    .addComponent(btRefresh))
                .addGap(17, 17, 17))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Danh sách cổng sạc");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblCongSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Cong_Sac", "Ten_Cong_Sac", "Cong_Nghe", "Trang_Thai"
            }
        ));
        tblCongSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCongSacMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCongSac);

        jLabel2.setText("Lọc trạng thái");

        cbbLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoat Dong", "Khong hoat dong" }));
        cbbLoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbbLocMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbbLocMousePressed(evt);
            }
        });
        cbbLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addComponent(cbbLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThem2ActionPerformed
        if (getSacForm() != null) {
            if (sv.them(getSacForm()) == 1) {
                JOptionPane.showMessageDialog(rootPane, "Them thanh cong");
                fillTable(sv.sapXepHoatDong());
            } else {

                JOptionPane.showMessageDialog(rootPane, "Them that bai");
            }
        }
    }//GEN-LAST:event_btThem2ActionPerformed

    private void btKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKhoiPhucActionPerformed
        int i = tblCongSac.getSelectedRow();
        int chon = JOptionPane.showConfirmDialog(rootPane, "Xac nhan cap nhat trang thai ?", "Update", JOptionPane.YES_OPTION);
        if (chon == JOptionPane.YES_OPTION) {

            if (i != -1) {
                if (sv.khoiPhuc((int) tblCongSac.getValueAt(i, 0)) == 1) {
                    JOptionPane.showMessageDialog(rootPane, "Cap nhat thanh cong");
                    fillTable(sv.sapXepKhongHoatDong()
                    );
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Cap nhat that bai");

                }

            }
        }
    }//GEN-LAST:event_btKhoiPhucActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        int i = tblCongSac.getSelectedRow();
        int chon = JOptionPane.showConfirmDialog(rootPane, "Xac nhan cap nhat trang thai ?", "Update", JOptionPane.YES_OPTION);
        if (chon == JOptionPane.YES_OPTION) {

            if (i != -1) {
                if (sv.xoa((int) tblCongSac.getValueAt(i, 0)) == 1) {
                    JOptionPane.showMessageDialog(rootPane, "Cap nhat thanh cong");
                    fillTable(sv.sapXepHoatDong());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Cap nhat that bai");

                }

            }
        }
    }//GEN-LAST:event_btXoaActionPerformed
    public void OnClick(int viTri) {
        txtTen.setText(tblCongSac.getValueAt(viTri, 1).toString());
        txtCongNghe.setText(tblCongSac.getValueAt(viTri, 2).toString().replace("WH", ""));

    }
    private void btRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshActionPerformed
        txtCongNghe.setText("");
        txtTen.setText("");
    }//GEN-LAST:event_btRefreshActionPerformed

    private void tblCongSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCongSacMouseClicked
        int pos = tblCongSac.getSelectedRow();
        if (tblCongSac.getValueAt(pos, 3).toString().equals("Hoat dong")) {
            btSua.setEnabled(true);
            btXoa.setEnabled(true);
            btKhoiPhuc.setEnabled(false);

        }
        if (tblCongSac.getValueAt(pos, 3).toString().equals("Khong hoat dong")) {

            btSua.setEnabled(false);
            btXoa.setEnabled(false);
            btKhoiPhuc.setEnabled(true);
        }
        OnClick(pos);

    }//GEN-LAST:event_tblCongSacMouseClicked

    private void cbbLocMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbLocMouseEntered

    }//GEN-LAST:event_cbbLocMouseEntered

    private void cbbLocMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbLocMousePressed

    }//GEN-LAST:event_cbbLocMousePressed

    private void cbbLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocActionPerformed

        if (cbbLoc.getSelectedIndex() == 0) {
            fillTable(sv.sapXepHoatDong());
        }

        if (cbbLoc.getSelectedIndex() == 1) {
            fillTable(sv.sapXepKhongHoatDong());
        }

    }//GEN-LAST:event_cbbLocActionPerformed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        int i = tblCongSac.getSelectedRow();
        int chon = JOptionPane.showConfirmDialog(rootPane, "Xac nhan sua ?", "Update", JOptionPane.YES_OPTION);
        if (chon == JOptionPane.YES_OPTION) {

            if (i != -1) {
                if (sv.sua(getSacForm(), (int) tblCongSac.getValueAt(i, 0)) == 1) {
                    JOptionPane.showMessageDialog(rootPane, "Sua thanh cong");
                    fillTable(sv.sapXepHoatDong());
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Sua that bai");

                }

            }
        }
    }//GEN-LAST:event_btSuaActionPerformed

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
            java.util.logging.Logger.getLogger(CongSacView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CongSacView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CongSacView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CongSacView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CongSacView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btKhoiPhuc;
    private javax.swing.JButton btRefresh;
    private javax.swing.JButton btSua;
    private javax.swing.JButton btThem2;
    private javax.swing.JButton btXoa;
    private javax.swing.JComboBox<String> cbbLoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCongSac;
    private javax.swing.JTextField txtCongNghe;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
