
package br.com.autofuel.view;

import java.awt.Dimension;
import br.com.autofuel.ctr.FuncionarioCTR;
import br.com.autofuel.dto.FuncionarioDTO;
import java.awt.Dimension;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FuncionarioVIEW extends javax.swing.JInternalFrame {

    FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
    FuncionarioCTR funcionarioCTR = new FuncionarioCTR();
 
    int gravar_alterar;
    ResultSet rs;
    DefaultTableModel modeloTbl;
 
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2,
                         (d.height - this.getSize().height) / 2);
    }
 
    private void gravar() {
        try {
            funcionarioDTO.setNome_fun(txtNomeFun.getText());
            funcionarioDTO.setCpf_fun(txtCpfFun.getText());
            funcionarioDTO.setLogin_fun(txtLoginFun.getText());
            funcionarioDTO.setSenha_fun(String.valueOf(txtSenhaFun.getPassword()));
            funcionarioDTO.setTipo_fun(cmbTipoFun.getSelectedItem().toString());
            JOptionPane.showMessageDialog(null, funcionarioCTR.inserirFuncionario(funcionarioDTO));
        } catch (Exception e) {
            System.out.println("Erro gravar funcionário: " + e.getMessage());
        }
    }
 
    private void alterar() {
        try {
            funcionarioDTO.setNome_fun(txtNomeFun.getText());
            funcionarioDTO.setCpf_fun(txtCpfFun.getText());
            funcionarioDTO.setLogin_fun(txtLoginFun.getText());
            funcionarioDTO.setSenha_fun(String.valueOf(txtSenhaFun.getPassword()));
            funcionarioDTO.setTipo_fun(cmbTipoFun.getSelectedItem().toString());
            JOptionPane.showMessageDialog(null, funcionarioCTR.alterarFuncionario(funcionarioDTO));
        } catch (Exception e) {
            System.out.println("Erro alterar funcionário: " + e.getMessage());
        }
    }
 
    private void excluir() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir este funcionário?",
                "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, funcionarioCTR.excluirFuncionario(funcionarioDTO));
        }
    }
 
    private void preencheTabela(String nome) {
        try {
            modeloTbl.setNumRows(0);
            funcionarioDTO.setNome_fun(nome);
            rs = funcionarioCTR.consultarFuncionario(funcionarioDTO, 1);
            while (rs.next()) {
                modeloTbl.addRow(new Object[]{
                    rs.getString("id_fun"),
                    rs.getString("nome_fun")
                });
            }
        } catch (Exception e) {
            System.out.println("Erro preencheTabela funcionário: " + e.getMessage());
        } finally {
            funcionarioCTR.CloseDB();
        }
    }
 
    private void preencheCampos(int id) {
        try {
            funcionarioDTO.setId_fun(id);
            rs = funcionarioCTR.consultarFuncionario(funcionarioDTO, 2);
            if (rs.next()) {
                limpaCampos();
                txtNomeFun.setText(rs.getString("nome_fun"));
                txtCpfFun.setText(rs.getString("cpf_fun"));
                txtLoginFun.setText(rs.getString("login_fun"));
                txtSenhaFun.setText("");
                cmbTipoFun.setSelectedItem(rs.getString("tipo_fun"));
                gravar_alterar = 2;
                liberaCampos(true);
            }
        } catch (Exception e) {
            System.out.println("Erro preencheCampos funcionário: " + e.getMessage());
        } finally {
            funcionarioCTR.CloseDB();
        }
    }
 
    private void liberaCampos(boolean a) {
        txtNomeFun.setEnabled(a);
        txtCpfFun.setEnabled(a);
        txtLoginFun.setEnabled(a);
        txtSenhaFun.setEnabled(a);
        cmbTipoFun.setEnabled(a);
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
        txtNomeFun.setText("");
        txtCpfFun.setText("");
        txtLoginFun.setText("");
        txtSenhaFun.setText("");
        cmbTipoFun.setSelectedIndex(0);
    }
    
    public FuncionarioVIEW() {
    initComponents();

    setSize(720, 330);
    setMinimumSize(new Dimension(720, 330));

    liberaCampos(false);
    liberaBotoes(true, false, false, false, true);
    modeloTbl = (DefaultTableModel) tblFuncionario.getModel();
    txtNomeFun.setNextFocusableComponent(txtCpfFun);
    txtCpfFun.setNextFocusableComponent(txtLoginFun);
    txtLoginFun.setNextFocusableComponent(cmbTipoFun);
    cmbTipoFun.setNextFocusableComponent(txtSenhaFun);
    txtSenhaFun.setNextFocusableComponent(btnNovo);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNomeFun = new javax.swing.JTextField();
        txtLoginFun = new javax.swing.JTextField();
        txtSenhaFun = new javax.swing.JPasswordField();
        cmbTipoFun = new javax.swing.JComboBox<>();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtBuscarFun = new javax.swing.JTextField();
        btnBuscarFun = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionario = new javax.swing.JTable();
        btnSair = new javax.swing.JButton();
        txtCpfFun = new javax.swing.JFormattedTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro de Funcionário");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jLabel2.setText("Nome:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 20));

        jLabel3.setText("CPF:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel4.setText("Login:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel5.setText("Senha:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel6.setText("Tipo:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, 20));
        getContentPane().add(txtNomeFun, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 250, -1));
        getContentPane().add(txtLoginFun, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 120, -1));
        getContentPane().add(txtSenhaFun, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 120, -1));

        cmbTipoFun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "OPERADOR" }));
        cmbTipoFun.addActionListener(this::cmbTipoFunActionPerformed);
        getContentPane().add(cmbTipoFun, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 90, -1));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, -1, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, -1, -1));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Consultar");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        jLabel8.setText("Nome:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, -1, 20));
        getContentPane().add(txtBuscarFun, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 180, -1));

        btnBuscarFun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/pesquisar.png"))); // NOI18N
        btnBuscarFun.addActionListener(this::btnBuscarFunActionPerformed);
        btnBuscarFun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscarFunKeyPressed(evt);
            }
        });
        getContentPane().add(btnBuscarFun, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, 50, -1));

        tblFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        tblFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFuncionario);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 280, 100));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(this::btnSairActionPerformed);
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, -1, -1));

        try {
            txtCpfFun.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txtCpfFun, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 120, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoFunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoFunActionPerformed

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

    private void btnBuscarFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFunActionPerformed
        preencheTabela(txtBuscarFun.getText());
    }//GEN-LAST:event_btnBuscarFunActionPerformed

    private void tblFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionarioMouseClicked
        preencheCampos(Integer.parseInt(String.valueOf(
            tblFuncionario.getValueAt(tblFuncionario.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_tblFuncionarioMouseClicked

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnBuscarFunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarFunKeyPressed
        preencheTabela(txtBuscarFun.getText());
    }//GEN-LAST:event_btnBuscarFunKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarFun;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cmbTipoFun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFuncionario;
    private javax.swing.JTextField txtBuscarFun;
    private javax.swing.JFormattedTextField txtCpfFun;
    private javax.swing.JTextField txtLoginFun;
    private javax.swing.JTextField txtNomeFun;
    private javax.swing.JPasswordField txtSenhaFun;
    // End of variables declaration//GEN-END:variables
}
