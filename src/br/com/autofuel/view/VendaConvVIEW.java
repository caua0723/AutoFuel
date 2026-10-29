
package br.com.autofuel.view;

import java.awt.Dimension;
import br.com.autofuel.ctr.ProdutoCTR;
import br.com.autofuel.ctr.VendaConvCTR;
import br.com.autofuel.dto.FuncionarioDTO;
import br.com.autofuel.dto.ProdutoDTO;
import br.com.autofuel.dto.VendaConvDTO;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VendaConvVIEW extends javax.swing.JInternalFrame {

    VendaConvDTO vendaConvDTO = new VendaConvDTO();
    VendaConvCTR vendaConvCTR = new VendaConvCTR();
    ProdutoDTO   produtoDTO   = new ProdutoDTO();
    ProdutoCTR   produtoCTR   = new ProdutoCTR();
    FuncionarioDTO funcionarioLogado;
 
    ResultSet rs;
    DefaultTableModel modeloTblProdutos;
    DefaultTableModel modeloTblItens;
 
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2,
                         (d.height - this.getSize().height) / 2);
    }
 
    public void setFuncionarioLogado(FuncionarioDTO fun) {
        this.funcionarioLogado = fun;
    }
 
    private void preencheTabelaP(String nome) {
        try {
            modeloTblProdutos.setNumRows(0);
            produtoDTO.setNome_prod(nome);
            rs = produtoCTR.consultarProduto(produtoDTO, 1);
            while (rs.next()) {
                modeloTblProdutos.addRow(new Object[]{
                    rs.getString("id_prod"),
                    rs.getString("nome_prod"),
                    rs.getString("preco_prod"),
                    rs.getString("estoque_prod")
                });
            }
        } catch (Exception e) {
            System.out.println("Erro preencheTabelaP: " + e.getMessage());
        } finally {
            produtoCTR.CloseDB();
        }
    }
 
    private void adicionaProdutoSelecionado(int id, String nome, double preco) {
        try {
            int qtd = Integer.parseInt(txtQtd.getText());
            modeloTblItens.addRow(new Object[]{id, nome, preco, qtd});
            calculaTotalVenda();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Informe a quantidade antes de adicionar.");
        }
    }
 
    private void removeProdutoSelecionado(int row) {
        if (row >= 0) {
            modeloTblItens.removeRow(row);
            calculaTotalVenda();
        }
    }
 
    private void calculaTotalVenda() {
        double total = 0;
        for (int i = 0; i < modeloTblItens.getRowCount(); i++) {
            double preco = Double.parseDouble(String.valueOf(modeloTblItens.getValueAt(i, 2)));
            int qtd = Integer.parseInt(String.valueOf(modeloTblItens.getValueAt(i, 3)));
            total += preco * qtd;
        }
        txtValTotalVend.setText(String.format("%.2f", total));
    }
 
    private boolean verificaPreenchimento() {
        if (modeloTblItens.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Adicione ao menos um produto à venda.");
            return false;
        }
        return true;
    }
    
    
    
    private void gravar() {
        try {
            vendaConvDTO.setVal_total_vend(Double.parseDouble(txtValTotalVend.getText().replace(",", ".")));
            vendaConvDTO.setDat_vend(new Date());
            JOptionPane.showMessageDialog(null,
                    vendaConvCTR.inserirVendaConv(vendaConvDTO, funcionarioLogado, tblItensVend));
        } catch (Exception e) {
            System.out.println("Erro gravar venda: " + e.getMessage());
        }
    }
 
    private void limpaCampos() {
        txtBuscarProd.setText("");
        txtQtd.setText("");
        txtValTotalVend.setText("0.00");
        modeloTblProdutos.setNumRows(0);
        modeloTblItens.setNumRows(0);
    }
 
    private void liberaCampos(boolean a) {
        txtBuscarProd.setEnabled(a);
        txtQtd.setEnabled(a);
        btnPesquisarProd.setEnabled(a);
        btnAdicionarItem.setEnabled(a);
        btnRemoverItem.setEnabled(a);
    }
 
    private void liberaBotoes(boolean novo, boolean salvar, boolean cancelar, boolean sair) {
        btnNovo.setEnabled(novo);
        btnSalvar.setEnabled(salvar);
        btnCancelar.setEnabled(cancelar);
        btnSair.setEnabled(sair);
    }
 
    public VendaConvVIEW() {
    initComponents();

    setSize(560, 500);
    setMinimumSize(new Dimension(560, 500));

    modeloTblProdutos = (DefaultTableModel) tblProdutos.getModel();
    modeloTblItens = (DefaultTableModel) tblItensVend.getModel();
    liberaCampos(false);
    liberaBotoes(true, false, false, true);
    txtBuscarProd.setNextFocusableComponent(btnPesquisarProd);
    btnPesquisarProd.setNextFocusableComponent(tblProdutos);
    tblProdutos.setNextFocusableComponent(txtQtd);
    txtQtd.setNextFocusableComponent(btnAdicionarItem);
    btnAdicionarItem.setNextFocusableComponent(tblItensVend);
    tblItensVend.setNextFocusableComponent(btnRemoverItem);
    btnRemoverItem.setNextFocusableComponent(btnNovo);
    btnNovo.setNextFocusableComponent(btnSalvar);
    btnSalvar.setNextFocusableComponent(btnCancelar);
    btnCancelar.setNextFocusableComponent(btnSair);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txtBuscarProd = new javax.swing.JTextField();
        btnPesquisarProd = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        btnRemoverItem = new javax.swing.JButton();
        btnAdicionarItem = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblItensVend = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtQtd = new javax.swing.JTextField();
        txtValTotalVend = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Nome:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, 30));
        getContentPane().add(txtBuscarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 230, 30));

        btnPesquisarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisarProd.addActionListener(this::btnPesquisarProdActionPerformed);
        btnPesquisarProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPesquisarProdKeyPressed(evt);
            }
        });
        getContentPane().add(btnPesquisarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 40, 28));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Venda de Produtos");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Produto", "Preço", "Estoque"
            }
        ));
        jScrollPane2.setViewportView(tblProdutos);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 450, 80));

        btnRemoverItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/prod_rem.png"))); // NOI18N
        btnRemoverItem.addActionListener(this::btnRemoverItemActionPerformed);
        getContentPane().add(btnRemoverItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, -1, 30));

        btnAdicionarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/prod_add.png"))); // NOI18N
        btnAdicionarItem.addActionListener(this::btnAdicionarItemActionPerformed);
        getContentPane().add(btnAdicionarItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 30, 30));

        tblItensVend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Produto", "Valor", "QTD"
            }
        ));
        tblItensVend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblItensVendKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblItensVend);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 450, 110));

        jLabel1.setText("QTD:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, -1, 30));
        getContentPane().add(txtQtd, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 40, 30));

        txtValTotalVend.setEditable(false);
        txtValTotalVend.addActionListener(this::txtValTotalVendActionPerformed);
        getContentPane().add(txtValTotalVend, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 50, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Total: R$");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, -1, 30));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, -1));

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, -1, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, -1, -1));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/autofuel/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(this::btnSairActionPerformed);
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarProdActionPerformed
        preencheTabelaP(txtBuscarProd.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarProdActionPerformed

    private void btnRemoverItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverItemActionPerformed
        removeProdutoSelecionado(tblItensVend.getSelectedRow());        // TODO add your handling code here:
    }//GEN-LAST:event_btnRemoverItemActionPerformed

    private void btnAdicionarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarItemActionPerformed
        int row = tblProdutos.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(null, "Selecione um produto."); return; }
        adicionaProdutoSelecionado(
            Integer.parseInt(String.valueOf(tblProdutos.getValueAt(row, 0))),
            String.valueOf(tblProdutos.getValueAt(row, 1)),
            Double.parseDouble(String.valueOf(tblProdutos.getValueAt(row, 2)))
        );
    }//GEN-LAST:event_btnAdicionarItemActionPerformed

    private void tblItensVendKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblItensVendKeyReleased
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
            calculaTotalVenda();
        }
    }//GEN-LAST:event_tblItensVendKeyReleased

    private void txtValTotalVendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValTotalVendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValTotalVendActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limpaCampos();
        liberaCampos(true);
        liberaBotoes(false, true, true, true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (verificaPreenchimento()) {
            gravar();
            limpaCampos();
            liberaCampos(false);
            liberaBotoes(true, false, false, true);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        this.dispose();        
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnPesquisarProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPesquisarProdKeyPressed
        preencheTabelaP(txtBuscarProd.getText());        
    }//GEN-LAST:event_btnPesquisarProdKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarItem;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarProd;
    private javax.swing.JButton btnRemoverItem;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblItensVend;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtBuscarProd;
    private javax.swing.JTextField txtQtd;
    private javax.swing.JTextField txtValTotalVend;
    // End of variables declaration//GEN-END:variables
}
