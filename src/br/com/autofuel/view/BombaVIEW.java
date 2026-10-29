
package br.com.autofuel.view;

import br.com.autofuel.ctr.BombaCTR;
import br.com.autofuel.dto.BombaDTO;
import java.awt.Dimension;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BombaVIEW extends javax.swing.JInternalFrame {

    
    BombaDTO bombaDTO = new BombaDTO();
    BombaCTR bombaCTR = new BombaCTR();
 
    int gravar_alterar;
    ResultSet rs;
    DefaultTableModel modeloTbl;
 
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2,
                         (d.height - this.getSize().height) / 2);
    }
 
    // ----------------------------------------------------------
    //  CRUD
    // ----------------------------------------------------------
    private void gravar() {
    try {
        bombaDTO.setDescricao_bom(txtDescricaoBom.getText());
        bombaDTO.setTipo_comb_bom(cmbTipoCombBom.getSelectedItem().toString());
        bombaDTO.setPreco_litro_bom(Double.parseDouble(txtPrecolitroBom.getText().replace(".", "").replace(",", ".")));
        bombaDTO.setAtiva_bom(chkAtivaBom.isSelected());
        JOptionPane.showMessageDialog(null, bombaCTR.inserirBomba(bombaDTO));
    } catch (Exception e) {
        System.out.println("Erro gravar bomba: " + e.getMessage());
    }
}

    private void alterar() {
    try {
        bombaDTO.setDescricao_bom(txtDescricaoBom.getText());
        bombaDTO.setTipo_comb_bom(cmbTipoCombBom.getSelectedItem().toString());
        bombaDTO.setPreco_litro_bom(Double.parseDouble(txtPrecolitroBom.getText().replace(".", "").replace(",", ".")));
        bombaDTO.setAtiva_bom(chkAtivaBom.isSelected());
        JOptionPane.showMessageDialog(null, bombaCTR.alterarBomba(bombaDTO));
    } catch (Exception e) {
        System.out.println("Erro alterar bomba: " + e.getMessage());
    }
}
 
    private void excluir() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir esta bomba?",
                "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, bombaCTR.excluirBomba(bombaDTO));
        }
    }
    
    
    private void preencheTabela(String descricao) {
        try {
            modeloTbl.setNumRows(0);
            bombaDTO.setDescricao_bom(descricao);
            rs = bombaCTR.consultarBomba(bombaDTO, 1);
            while (rs.next()) {
                modeloTbl.addRow(new Object[]{
                    rs.getString("id_bom"),
                    rs.getString("descricao_bom"),
                    rs.getString("tipo_comb_bom"),
                    rs.getString("preco_litro_bom"),
                    rs.getBoolean("ativa_bom") ? "Sim" : "Não"
                });
            }
        } catch (Exception e) {
            System.out.println("Erro preencheTabela bomba: " + e.getMessage());
        } finally {
            bombaCTR.CloseDB();
        }
    }

    private void preencheCampos(int id) {
        try {
          bombaDTO.setId_bom(id);
          rs = bombaCTR.consultarBomba(bombaDTO, 2);
            if (rs.next()) {
                limpaCampos();
                txtDescricaoBom.setText(rs.getString("descricao_bom"));
                cmbTipoCombBom.setSelectedItem(rs.getString("tipo_comb_bom"));
                txtPrecolitroBom.setText(rs.getString("preco_litro_bom"));
                chkAtivaBom.setSelected(rs.getBoolean("ativa_bom"));
                gravar_alterar = 2;
                liberaCampos(true);
            }
        } catch (Exception e) {
            System.out.println("Erro preencheCampos bomba: " + e.getMessage());
        } finally {
            bombaCTR.CloseDB();
        }
    }
 
    // ----------------------------------------------------------
    //  HELPERS DE UI
    // ----------------------------------------------------------
    private void liberaCampos(boolean a) {
        txtDescricaoBom.setEnabled(a);
        cmbTipoCombBom.setEnabled(a);
        txtPrecolitroBom.setEnabled(a);
        chkAtivaBom.setEnabled(a);
    }
 
    private void liberaBotoes(boolean novo, boolean salvar, boolean cancelar,
                               boolean excluir, boolean sair) {
        btnNovo.setEnabled(novo);
        btnSalvar.setEnabled(salvar);
        btnCancelar.setEnabled(cancelar);
        btnExcluir.setEnabled(excluir);
        btnSair.setEnabled(sair);
    }
 
    private void limpaCampos() {
        txtDescricaoBom.setText("");
        cmbTipoCombBom.setSelectedIndex(0);
        txtPrecolitroBom.setText("");
        chkAtivaBom.setSelected(true);
    }
    
    
public BombaVIEW() {
    initComponents();

    setSize(740, 300);
    setMinimumSize(new java.awt.Dimension(740, 300));

    liberaCampos(false);
    liberaBotoes(true, false, false, false, true);
    modeloTbl = (DefaultTableModel) tblBomba.getModel();
    txtDescricaoBom.setNextFocusableComponent(cmbTipoCombBom);
    cmbTipoCombBom.setNextFocusableComponent(txtPrecolitroBom);
    txtPrecolitroBom.setNextFocusableComponent(chkAtivaBom);
    chkAtivaBom.setNextFocusableComponent(btnNovo);
    btnNovo.setNextFocusableComponent(btnSalvar);
    btnSalvar.setNextFocusableComponent(btnCancelar);
    btnCancelar.setNextFocusableComponent(btnExcluir);
    btnExcluir.setNextFocusableComponent(btnSair);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDescricaoBom = new javax.swing.JTextField();
        cmbTipoCombBom = new javax.swing.JComboBox<>();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtBuscarBom = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBomba = new javax.swing.JTable();
        btnBuscarBom = new javax.swing.JButton();
        chkAtivaBom = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        txtPrecolitroBom = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro de Bomba");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        jLabel2.setText("Descrição:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 20));

        jLabel6.setText("Combustível:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 20));
        getContentPane().add(txtDescricaoBom, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 260, -1));

        cmbTipoCombBom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ETANOL", "GASOLINA", "DIESEL", " " }));
        cmbTipoCombBom.addActionListener(this::cmbTipoCombBomActionPerformed);
        cmbTipoCombBom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTipoCombBomKeyPressed(evt);
            }
        });
        getContentPane().add(cmbTipoCombBom, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 80, -1));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 238, -1, 30));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 238, 100, 30));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 238, 110, 30));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 238, 100, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Consultar");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, -1, -1));

        jLabel8.setText("Nome:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, 20));
        getContentPane().add(txtBuscarBom, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 180, -1));

        tblBomba.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Descrição"
            }
        ));
        tblBomba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBombaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBomba);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 280, 110));

        btnBuscarBom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/pesquisar.png"))); // NOI18N
        btnBuscarBom.addActionListener(this::btnBuscarBomActionPerformed);
        btnBuscarBom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarBomKeyPressed(evt);
            }
        });
        getContentPane().add(btnBuscarBom, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 50, -1));

        chkAtivaBom.setText("Ativa");
        getContentPane().add(chkAtivaBom, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        jLabel3.setText("Preço/Litro:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(this::btnSairActionPerformed);
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 238, -1, 30));
        getContentPane().add(txtPrecolitroBom, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoCombBomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoCombBomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoCombBomActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limpaCampos();
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (gravar_alterar == 1) { gravar(); gravar_alterar = 0; }
        else if (gravar_alterar == 2) { alterar(); gravar_alterar = 0; }
        else { JOptionPane.showMessageDialog(null, "Erro no sistema!"); }
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modeloTbl.setNumRows(0);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modeloTbl.setNumRows(0);
        gravar_alterar = 0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modeloTbl.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnBuscarBomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarBomActionPerformed
        preencheTabela(txtBuscarBom.getText());
    }//GEN-LAST:event_btnBuscarBomActionPerformed

    private void tblBombaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBombaMouseClicked
        preencheCampos(Integer.parseInt(String.valueOf(
                tblBomba.getValueAt(tblBomba.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_tblBombaMouseClicked

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnBuscarBomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarBomKeyPressed
        preencheTabela(txtBuscarBom.getText());
    }//GEN-LAST:event_btnBuscarBomKeyPressed

    private void cmbTipoCombBomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoCombBomKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoCombBomKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarBom;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox chkAtivaBom;
    private javax.swing.JComboBox<String> cmbTipoCombBom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBomba;
    private javax.swing.JTextField txtBuscarBom;
    private javax.swing.JTextField txtDescricaoBom;
    private javax.swing.JTextField txtPrecolitroBom;
    // End of variables declaration//GEN-END:variables
}
