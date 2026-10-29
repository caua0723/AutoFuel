package br.com.autofuel.view;

import br.com.autofuel.ctr.RelatorioCTR;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JOptionPane;
import br.com.autofuel.dto.FuncionarioDTO;

public class PrincipalVIEW extends javax.swing.JFrame {

    Map parametros = new HashMap();
    FuncionarioDTO funcionarioLogado;
 
    
public PrincipalVIEW(FuncionarioDTO funcionarioDTO) {
    initComponents();

    getContentPane().removeAll();
    getContentPane().setLayout(new java.awt.BorderLayout());
    getContentPane().add(desktopPane, java.awt.BorderLayout.CENTER);

    this.funcionarioLogado = funcionarioDTO;
    this.setLocationRelativeTo(null);

    if (funcionarioDTO.getTipo_fun().equalsIgnoreCase("COMUM")) {
        itemMenuFuncionario.setVisible(false);
    }

    setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon imageicon = new ImageIcon(getClass().getResource("imagens/tela_inicial.png"));
        Image image = imageicon.getImage();
        desktopPane = new javax.swing.JDesktopPane() {
            public void paintComponent(Graphics graphics) {
                graphics.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        menuBar = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        itemMenuBomba = new javax.swing.JMenuItem();
        itemMenuProduto = new javax.swing.JMenuItem();
        itemMenuFuncionario = new javax.swing.JMenuItem();
        menuVenda = new javax.swing.JMenu();
        itemMenuVenda = new javax.swing.JMenuItem();
        itemMenuAbastecimento = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();
        itemMenuRelAbastecimento = new javax.swing.JMenuItem();
        itemMenuRelProdutos = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuCadastro.setMnemonic('f');
        menuCadastro.setText("Cadastro");

        itemMenuBomba.setMnemonic('o');
        itemMenuBomba.setText("Bomba");
        itemMenuBomba.addActionListener(this::itemMenuBombaActionPerformed);
        menuCadastro.add(itemMenuBomba);

        itemMenuProduto.setMnemonic('s');
        itemMenuProduto.setText("Produto");
        itemMenuProduto.addActionListener(this::itemMenuProdutoActionPerformed);
        menuCadastro.add(itemMenuProduto);

        itemMenuFuncionario.setText("Funcionário");
        itemMenuFuncionario.addActionListener(this::itemMenuFuncionarioActionPerformed);
        menuCadastro.add(itemMenuFuncionario);

        menuBar.add(menuCadastro);

        menuVenda.setMnemonic('e');
        menuVenda.setText("Movimentações");

        itemMenuVenda.setMnemonic('t');
        itemMenuVenda.setText("Venda Convêniencia");
        itemMenuVenda.addActionListener(this::itemMenuVendaActionPerformed);
        menuVenda.add(itemMenuVenda);

        itemMenuAbastecimento.setText("Abastecimento");
        itemMenuAbastecimento.addActionListener(this::itemMenuAbastecimentoActionPerformed);
        menuVenda.add(itemMenuAbastecimento);

        menuBar.add(menuVenda);

        menuRelatorios.setText("Relatórios");

        itemMenuRelAbastecimento.setText("Abastecimento");
        itemMenuRelAbastecimento.addActionListener(this::itemMenuRelAbastecimentoActionPerformed);
        menuRelatorios.add(itemMenuRelAbastecimento);

        itemMenuRelProdutos.setText("Produtos");
        itemMenuRelProdutos.addActionListener(this::itemMenuRelProdutosActionPerformed);
        menuRelatorios.add(itemMenuRelProdutos);

        menuBar.add(menuRelatorios);

        menuSair.setMnemonic('h');
        menuSair.setText("Sair");
        menuSair.addActionListener(this::menuSairActionPerformed);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1421, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
        sair();        // TODO add your handling code here:
    }//GEN-LAST:event_menuSairActionPerformed

    private void itemMenuBombaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuBombaActionPerformed
        abreBombaVIEW();        // TODO add your handling code here:
    }//GEN-LAST:event_itemMenuBombaActionPerformed

    private void itemMenuProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuProdutoActionPerformed
        abreProdutoVIEW();        // TODO add your handling code here:
    }//GEN-LAST:event_itemMenuProdutoActionPerformed

    private void itemMenuVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuVendaActionPerformed
        abreVendaConvVIEW();        // TODO add your handling code here:
    }//GEN-LAST:event_itemMenuVendaActionPerformed

    private void itemMenuFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuFuncionarioActionPerformed
        abreFuncionarioVIEW();
    }//GEN-LAST:event_itemMenuFuncionarioActionPerformed

    private void itemMenuRelAbastecimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuRelAbastecimentoActionPerformed
        RelatorioCTR relatorioCTR = new RelatorioCTR();
        relatorioCTR.abrirRelatorio("Rel_abastecimento.jasper", "Relatório de Abastecimentos", parametros).setVisible(true);
    }//GEN-LAST:event_itemMenuRelAbastecimentoActionPerformed

    private void itemMenuRelProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuRelProdutosActionPerformed
        RelatorioCTR relatorioCTR = new RelatorioCTR();
        relatorioCTR.abrirRelatorio("Rel_conveniencia.jasper", "Relatório de Produtos - Conveniência", parametros).setVisible(true);
    }//GEN-LAST:event_itemMenuRelProdutosActionPerformed

    private void itemMenuAbastecimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuAbastecimentoActionPerformed
        abreAbastecimentoVIEW();
    }//GEN-LAST:event_itemMenuAbastecimentoActionPerformed

    /**
     * @param args the command line arguments
     */
    private void sair() {
        Object[] options = {"Sair", "Cancelar"};
        if (JOptionPane.showOptionDialog(null, "Deseja sair do Sistema? ", "Informação",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]) == 0) {
            System.exit(0);
        }
    }
 
    private void abreBombaVIEW() {
        BombaVIEW bombaVIEW = new BombaVIEW();
        this.desktopPane.add(bombaVIEW);
        bombaVIEW.setVisible(true);
        bombaVIEW.setPosicao();
    }
 
    private void abreProdutoVIEW() {
        ProdutoVIEW produtoVIEW = new ProdutoVIEW();
        this.desktopPane.add(produtoVIEW);
        produtoVIEW.setVisible(true);
        produtoVIEW.setPosicao();
    }
 
    private void abreVendaConvVIEW() {
        VendaConvVIEW vendaConvVIEW = new VendaConvVIEW();
        this.desktopPane.add(vendaConvVIEW);
        vendaConvVIEW.setFuncionarioLogado(funcionarioLogado);
        vendaConvVIEW.setVisible(true);
        vendaConvVIEW.setPosicao();
    }
 
    private void abreAbastecimentoVIEW() {
        AbastecimentoVIEW abastecimentoVIEW = new AbastecimentoVIEW();
        this.desktopPane.add(abastecimentoVIEW);
        abastecimentoVIEW.setFuncionarioLogado(funcionarioLogado);
        abastecimentoVIEW.setVisible(true);
        abastecimentoVIEW.setPosicao();
    }
 
    private void abreFuncionarioVIEW() {
        FuncionarioVIEW funcionarioVIEW = new FuncionarioVIEW();
        this.desktopPane.add(funcionarioVIEW);
        funcionarioVIEW.setVisible(true);
        funcionarioVIEW.setPosicao();
    }
     
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PrincipalVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PrincipalVIEW().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem itemMenuAbastecimento;
    private javax.swing.JMenuItem itemMenuBomba;
    private javax.swing.JMenuItem itemMenuFuncionario;
    private javax.swing.JMenuItem itemMenuProduto;
    private javax.swing.JMenuItem itemMenuRelAbastecimento;
    private javax.swing.JMenuItem itemMenuRelProdutos;
    private javax.swing.JMenuItem itemMenuVenda;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuRelatorios;
    private javax.swing.JMenu menuSair;
    private javax.swing.JMenu menuVenda;
    // End of variables declaration//GEN-END:variables

}
