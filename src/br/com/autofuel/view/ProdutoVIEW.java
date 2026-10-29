
package br.com.autofuel.view;

import java.awt.Dimension;
import br.com.autofuel.ctr.ProdutoCTR;
import br.com.autofuel.dto.ProdutoDTO;
import java.awt.Dimension;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProdutoVIEW extends javax.swing.JInternalFrame {

   ProdutoDTO produtoDTO = new ProdutoDTO();
    ProdutoCTR produtoCTR = new ProdutoCTR();
 
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
            produtoDTO.setNome_prod(txtNomeProd.getText());
            produtoDTO.setPreco_prod(Double.parseDouble(txtPrecoProd.getText().replace(",", ".")));
            produtoDTO.setEstoque_prod(Integer.parseInt(txtEstoqueProd.getText()));
            JOptionPane.showMessageDialog(null, produtoCTR.inserirProduto(produtoDTO));
        } catch (Exception e) {
            System.out.println("Erro gravar produto: " + e.getMessage());
        }
    }
 
    private void alterar() {
        try {
            produtoDTO.setNome_prod(txtNomeProd.getText());
            produtoDTO.setPreco_prod(Double.parseDouble(txtPrecoProd.getText().replace(",", ".")));
            produtoDTO.setEstoque_prod(Integer.parseInt(txtEstoqueProd.getText()));
            JOptionPane.showMessageDialog(null, produtoCTR.alterarProduto(produtoDTO));
        } catch (Exception e) {
            System.out.println("Erro alterar produto: " + e.getMessage());
        }
    }
 
    private void excluir() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir este produto?",
                "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, produtoCTR.excluirProduto(produtoDTO));
        }
    }
    
    private void preencheTabela(String nome) {
        try {
            modeloTbl.setNumRows(0);
            produtoDTO.setNome_prod(nome);
            rs = produtoCTR.consultarProduto(produtoDTO, 1);
            while (rs.next()) {
                modeloTbl.addRow(new Object[]{
                    rs.getString("id_prod"),
                    rs.getString("nome_prod")
                });
            }
        } catch (Exception e) {
            System.out.println("Erro preencheTabela produto: " + e.getMessage());
        } finally {
            produtoCTR.CloseDB();
        }
    }
 
    private void preencheCampos(int id) {
        try {
            produtoDTO.setId_prod(id);
            rs = produtoCTR.consultarProduto(produtoDTO, 2);
            if (rs.next()) {
                limpaCampos();
                txtNomeProd.setText(rs.getString("nome_prod"));
                txtPrecoProd.setText(rs.getString("preco_prod"));
                txtEstoqueProd.setText(rs.getString("estoque_prod"));
                gravar_alterar = 2;
                liberaCampos(true);
            }
        } catch (Exception e) {
            System.out.println("Erro preencheCampos produto: " + e.getMessage());
        } finally {
            produtoCTR.CloseDB();
        }
    }
 
    private void liberaCampos(boolean a) {
        txtNomeProd.setEnabled(a);
        txtPrecoProd.setEnabled(a);
        txtEstoqueProd.setEnabled(a);
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
        txtNomeProd.setText("");
        txtPrecoProd.setText("");
        txtEstoqueProd.setText("");
    }
 
    
public ProdutoVIEW() {
    initComponents();
    javax.swing.SwingUtilities.updateComponentTreeUI(this);

    setSize(760, 300);
    setMinimumSize(new java.awt.Dimension(760, 300));

    liberaCampos(false);
    liberaBotoes(true, false, false, false, true);
    modeloTbl = (DefaultTableModel) tblProduto.getModel();
    txtNomeProd.setNextFocusableComponent(txtEstoqueProd);
    txtEstoqueProd.setNextFocusableComponent(txtPrecoProd);
    txtPrecoProd.setNextFocusableComponent(btnNovo);
    btnNovo.setNextFocusableComponent(btnSalvar);
    btnSalvar.setNextFocusableComponent(btnCancelar);
    btnCancelar.setNextFocusableComponent(btnExcluir);
    btnExcluir.setNextFocusableComponent(btnSair);
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNomeProd = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtBuscarProd = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduto = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtPrecoProd = new javax.swing.JFormattedTextField();
        btnSair = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtEstoqueProd = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Cadastro");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        jLabel2.setText("Nome:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, 20));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, 20));
        getContentPane().add(txtNomeProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 290, -1));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, -1, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, -1));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Consultar Produtos");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));

        jLabel8.setText("Produto:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 50, 20));
        getContentPane().add(txtBuscarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 160, -1));

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisar.addActionListener(this::btnPesquisarActionPerformed);
        btnPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPesquisarKeyPressed(evt);
            }
        });
        getContentPane().add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 50, -1));

        tblProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Produto"
            }
        ));
        tblProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduto);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 270, 100));

        jLabel10.setText("Preço de Venda:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 90, 20));
        getContentPane().add(txtPrecoProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 70, -1));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(this::btnSairActionPerformed);
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Produtos da Convêniencia");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));
        getContentPane().add(txtEstoqueProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 90, -1));

        jLabel5.setText("Estoque:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        preencheTabela(txtBuscarProd.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void tblProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutoMouseClicked
        preencheCampos(Integer.parseInt(String.valueOf(
            tblProduto.getValueAt(tblProduto.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_tblProdutoMouseClicked

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPesquisarKeyPressed
        preencheTabela(txtBuscarProd.getText());
    }//GEN-LAST:event_btnPesquisarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProduto;
    private javax.swing.JTextField txtBuscarProd;
    private javax.swing.JFormattedTextField txtEstoqueProd;
    private javax.swing.JTextField txtNomeProd;
    private javax.swing.JFormattedTextField txtPrecoProd;
    // End of variables declaration//GEN-END:variables
}
