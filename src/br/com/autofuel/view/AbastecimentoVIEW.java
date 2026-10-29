
package br.com.autofuel.view;

import br.com.autofuel.ctr.AbastecimentoCTR;
import br.com.autofuel.ctr.BombaCTR;
import br.com.autofuel.dto.AbastecimentoDTO;
import br.com.autofuel.dto.BombaDTO;
import br.com.autofuel.dto.FuncionarioDTO;
import br.com.autofuel.ctr.FuncionarioCTR;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AbastecimentoVIEW extends javax.swing.JInternalFrame {

    AbastecimentoDTO abastecimentoDTO = new AbastecimentoDTO();
    AbastecimentoCTR abastecimentoCTR = new AbastecimentoCTR();
    BombaDTO         bombaDTO         = new BombaDTO();
    BombaCTR         bombaCTR         = new BombaCTR();
    FuncionarioDTO   funcionarioLogado;
 
    int gravar_alterar;
    ResultSet rs;
    DefaultTableModel modeloTbl;
    SimpleDateFormat dataFmt = new SimpleDateFormat("dd/MM/yyyy");
 
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2,
                         (d.height - this.getSize().height) / 2);
    }
 
    // Recebe o funcionário logado vindo da PrincipalVIEW
    public void setFuncionarioLogado(FuncionarioDTO fun) {
        this.funcionarioLogado = fun;
    }
 
    private void carregarComboFuncionario() {
        try {
            cmbFunAbast.removeAllItems();
            FuncionarioCTR funcionarioCTR = new FuncionarioCTR();
            rs = funcionarioCTR.consultarFuncionario(new FuncionarioDTO(), 3);
            while (rs.next()) {
                cmbFunAbast.addItem(rs.getString("id_fun") + "|" + rs.getString("nome_fun"));
            }
        } catch (Exception e) {
            System.out.println("Erro carregarComboFuncionario: " + e.getMessage());
        }
    }
    
    private void carregarComboBomba() {
        try {
            cmbBombaAbast.removeAllItems();
            rs = bombaCTR.consultarBomba(new BombaDTO(), 3);
            while (rs.next()) {
                cmbBombaAbast.addItem(rs.getString("id_bom") + "|"
                    + rs.getString("descricao_bom") + "|"
                    + rs.getString("preco_litro_bom"));
            }
        } catch (Exception e) {
            System.out.println("Erro carregarComboBomba: " + e.getMessage());
        } finally {
            bombaCTR.CloseDB();
        }
    }

private void atualizaPreco() {
    try {
        if (cmbBombaAbast.getSelectedItem() != null) {
            String[] parts = cmbBombaAbast.getSelectedItem().toString().split("\\|");
            txtValLitroAbast.setText(parts[2]);
            calcularTotal();
        }
    } catch (Exception e) {
        System.out.println("Erro atualizaPreco: " + e.getMessage());
    }
}

private void calcularTotal() {
    try {
        double litros   = Double.parseDouble(txtLitrosAbast.getText().replace(",", "."));
        double valLitro = Double.parseDouble(txtValLitroAbast.getText().replace(",", "."));
        txtValTotalAbast.setText(String.format("%.2f", litros * valLitro)); // txtCpfFun reutilizado como val_total
    } catch (Exception e) {
        txtValTotalAbast.setText("0.00");
    }
}

    private void gravar() {
        try {
            String[] partsBomba = cmbBombaAbast.getSelectedItem().toString().split("\\|");
            bombaDTO.setId_bom(Integer.parseInt(partsBomba[0]));

            String[] partsFun = cmbFunAbast.getSelectedItem().toString().split("\\|");
            abastecimentoDTO.setId_fun(Integer.parseInt(partsFun[0]));

            abastecimentoDTO.setPlaca_abast(txtPlacaAbast.getText());
            abastecimentoDTO.setLitros_abast(Double.parseDouble(txtLitrosAbast.getText().replace(",", ".")));
            abastecimentoDTO.setVal_litro_abast(Double.parseDouble(txtValLitroAbast.getText().replace(",", ".")));
            abastecimentoDTO.setVal_total_abast(Double.parseDouble(txtValTotalAbast.getText().replace(",", ".")));
            abastecimentoDTO.setDat_abast(new Date());

            JOptionPane.showMessageDialog(null,
                abastecimentoCTR.inserirAbastecimento(abastecimentoDTO, bombaDTO, funcionarioLogado)); // ← AQUI
        } catch (Exception e) {
            System.out.println("Erro gravar abastecimento: " + e.getMessage());
        }
    }

    private void alterar() {
        try {
            String[] partsBomba = cmbBombaAbast.getSelectedItem().toString().split("\\|");
            bombaDTO.setId_bom(Integer.parseInt(partsBomba[0]));

            String[] partsFun = cmbFunAbast.getSelectedItem().toString().split("\\|");
            FuncionarioDTO funSelecionado = new FuncionarioDTO();
            funSelecionado.setId_fun(Integer.parseInt(partsFun[0]));

            abastecimentoDTO.setPlaca_abast(txtPlacaAbast.getText());
            abastecimentoDTO.setLitros_abast(Double.parseDouble(txtLitrosAbast.getText().replace(",", ".")));
            abastecimentoDTO.setVal_litro_abast(Double.parseDouble(txtValLitroAbast.getText().replace(",", ".")));
            abastecimentoDTO.setVal_total_abast(Double.parseDouble(txtValTotalAbast.getText().replace(",", ".")));
            abastecimentoDTO.setDat_abast(new Date());

            JOptionPane.showMessageDialog(null,
                abastecimentoCTR.alterarAbastecimento(abastecimentoDTO, bombaDTO, funSelecionado));
        } catch (Exception e) {
            System.out.println("Erro alterar abastecimento: " + e.getMessage());
        }
    }
 
    private void excluir() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir este abastecimento?",
                "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,
                    abastecimentoCTR.excluirAbastecimento(abastecimentoDTO));
        }
    }
 
    private void preencheTabela(String placa) {
        try {
            modeloTbl.setNumRows(0);
            abastecimentoDTO.setPlaca_abast(placa);
            rs = abastecimentoCTR.consultarAbastecimento(abastecimentoDTO, 1);
            while (rs.next()) {
                modeloTbl.addRow(new Object[]{
                    rs.getString("id_abast"),
                    rs.getString("placa_abast"),
                    rs.getString("descricao_bom"),
                    rs.getString("tipo_comb_bom"),
                    rs.getString("litros_abast"),
                    rs.getString("val_total_abast"),
                    rs.getString("dat_abast")
                });
            }
        } catch (Exception e) {
            System.out.println("Erro preencheTabela abastecimento: " + e.getMessage());
        } finally {
            abastecimentoCTR.CloseDB();
        }
    }

private void preencheCampos(int id) {
    try {
        abastecimentoDTO.setId_abast(id);
        rs = abastecimentoCTR.consultarAbastecimento(abastecimentoDTO, 2);
        if (rs.next()) {
            limpaCampos();
            txtPlacaAbast.setText(rs.getString("placa_abast"));
            txtLitrosAbast.setText(rs.getString("litros_abast"));
            txtValLitroAbast.setText(rs.getString("val_litro_abast"));
            txtValTotalAbast.setText(rs.getString("val_total_abast"));

            int idBom = rs.getInt("id_bom");
            for (int i = 0; i < cmbBombaAbast.getItemCount(); i++) {
                if (cmbBombaAbast.getItemAt(i).toString().startsWith(idBom + "|")) {
                    cmbBombaAbast.setSelectedIndex(i);
                    break;
                }
            }

            int idFun = rs.getInt("id_fun");
            for (int i = 0; i < cmbFunAbast.getItemCount(); i++) {
                if (cmbFunAbast.getItemAt(i).toString().startsWith(idFun + "|")) {
                    cmbFunAbast.setSelectedIndex(i);
                    break;
                }
            }

            gravar_alterar = 2;
            liberaCampos(true);
        }
    } catch (Exception e) {
        System.out.println("Erro preencheCampos abastecimento: " + e.getMessage());
    } finally {
        abastecimentoCTR.CloseDB();
    }
}

    private void liberaCampos(boolean a) {
        txtPlacaAbast.setEnabled(a);
        cmbBombaAbast.setEnabled(a);
        cmbFunAbast.setEnabled(a);
        txtLitrosAbast.setEnabled(a);
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
        txtPlacaAbast.setText("");
        txtLitrosAbast.setText("");
        txtValLitroAbast.setText("");
        txtValTotalAbast.setText("0.00");
        if (cmbBombaAbast.getItemCount() > 0) cmbBombaAbast.setSelectedIndex(0);
        if (cmbFunAbast.getItemCount() > 0) cmbFunAbast.setSelectedIndex(0);
    }
    
    
public AbastecimentoVIEW() {
    initComponents();

    setSize(720, 330);
    setMinimumSize(new java.awt.Dimension(720, 330));

    txtValLitroAbast.setEditable(false);
    txtValTotalAbast.setEditable(false);
    carregarComboBomba();
    carregarComboFuncionario();
    liberaCampos(false);
    liberaBotoes(true, false, false, false, true);
    modeloTbl = (DefaultTableModel) tblAbastecimento.getModel();
    txtPlacaAbast.setNextFocusableComponent(txtLitrosAbast);
    txtLitrosAbast.setNextFocusableComponent(cmbBombaAbast);
    cmbBombaAbast.setNextFocusableComponent(cmbFunAbast);
    cmbFunAbast.setNextFocusableComponent(btnNovo);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPlacaAbast = new javax.swing.JTextField();
        txtValTotalAbast = new javax.swing.JTextField();
        cmbFunAbast = new javax.swing.JComboBox<>();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtBuscarAbast = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAbastecimento = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        cmbBombaAbast = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtValLitroAbast = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtLitrosAbast = new javax.swing.JTextField();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro de Abastecimento");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Consultar");
        jPanel3.add(jLabel7);

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Placa do Carro:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, 20));

        jLabel3.setText("Valor Total:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, 20));

        jLabel6.setText("Funcionário:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, 20));
        getContentPane().add(txtPlacaAbast, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 120, -1));
        getContentPane().add(txtValTotalAbast, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 90, -1));

        cmbFunAbast.addActionListener(this::cmbFunAbastActionPerformed);
        getContentPane().add(cmbFunAbast, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 80, -1));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 260, -1, -1));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, -1));

        jLabel8.setText("Placa:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 40, 20));
        getContentPane().add(txtBuscarAbast, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 102, 130, 20));

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisar.addActionListener(this::btnPesquisarActionPerformed);
        btnPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPesquisarKeyPressed(evt);
            }
        });
        getContentPane().add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 100, 40, 30));

        tblAbastecimento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Placa"
            }
        ));
        tblAbastecimento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAbastecimentoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAbastecimento);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 230, 80));

        jLabel9.setText("Bomba:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, -1, 20));

        cmbBombaAbast.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cmbBombaAbast.addActionListener(this::cmbBombaAbastActionPerformed);
        getContentPane().add(cmbBombaAbast, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 80, -1));

        jLabel10.setText("Litros Abastecidos:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 100, 20));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(this::btnSairActionPerformed);
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, -1, -1));

        jLabel12.setText("Valor/Litro:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, 20));
        getContentPane().add(txtValLitroAbast, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, -1, -1));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Cadastro de Abastecimento");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Consultar");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));
        getContentPane().add(txtLitrosAbast, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 110, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbFunAbastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFunAbastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFunAbastActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limpaCampos();
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        atualizaPreco();
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

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        preencheTabela(txtBuscarAbast.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void tblAbastecimentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAbastecimentoMouseClicked
        preencheCampos(Integer.parseInt(String.valueOf(
                tblAbastecimento.getValueAt(tblAbastecimento.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_tblAbastecimentoMouseClicked

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void cmbBombaAbastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBombaAbastActionPerformed
        atualizaPreco();
    }//GEN-LAST:event_cmbBombaAbastActionPerformed

    private void btnPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPesquisarKeyPressed
        preencheTabela(txtBuscarAbast.getText());
    }//GEN-LAST:event_btnPesquisarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbBombaAbast;
    private javax.swing.JComboBox<String> cmbFunAbast;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAbastecimento;
    private javax.swing.JTextField txtBuscarAbast;
    private javax.swing.JTextField txtLitrosAbast;
    private javax.swing.JTextField txtPlacaAbast;
    private javax.swing.JTextField txtValLitroAbast;
    private javax.swing.JTextField txtValTotalAbast;
    // End of variables declaration//GEN-END:variables
}
