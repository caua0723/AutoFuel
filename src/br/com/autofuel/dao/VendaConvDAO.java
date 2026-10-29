
package br.com.autofuel.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import br.com.autofuel.dto.FuncionarioDTO;
import br.com.autofuel.dto.VendaConvDTO;

public class VendaConvDAO {
    
        public VendaConvDAO() {
    }

    SimpleDateFormat dataFmt = new SimpleDateFormat("dd/MM/yyyy");

    private ResultSet rs    = null;
    private Statement stmt  = null;
    private Statement stmt1 = null;

    public boolean inserirVendaConv(VendaConvDTO vendaConvDTO,
                                    FuncionarioDTO funcionarioDTO,
                                    JTable itens) {
        try {
            ConexaoDAO.ConnectDB();
            stmt  = ConexaoDAO.con.createStatement();
            stmt1 = ConexaoDAO.con.createStatement();
            Statement stmt2 = ConexaoDAO.con.createStatement();        

            String cmd1 = "INSERT INTO venda_conv (id_fun, val_total_vend, dat_vend) VALUES ("
                    + funcionarioDTO.getId_fun()                                                    + ", "
                    + vendaConvDTO.getVal_total_vend()                                             + ", "
                    + "to_date('" + dataFmt.format(vendaConvDTO.getDat_vend()) + "', 'DD/MM/YYYY'))";

            stmt.execute(cmd1, Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            rs.next();
            int idVendGerado = rs.getInt("id_vend");

            for (int i = 0; i < itens.getRowCount(); i++) {
                int idProd = Integer.parseInt(itens.getValueAt(i, 0).toString());
                int qtdVendida = Integer.parseInt(itens.getValueAt(i, 3).toString());

                ResultSet rsEstoque = stmt2.executeQuery(
                "SELECT estoque_prod FROM produto WHERE id_prod = " + idProd);
                    rsEstoque.next();
                int estoqueAtual = rsEstoque.getInt("estoque_prod");

                if (qtdVendida > estoqueAtual) {
                    throw new Exception("Estoque insuficiente para o produto: "
                        + itens.getValueAt(i, 1));
                }   

                String cmd2 = "INSERT INTO item_venda (id_vend, id_prod, qtd_item, val_unit_item) VALUES ("
                        + idVendGerado          + ", "
                        + idProd                + ", "
                        + qtdVendida            + ", "
                        + itens.getValueAt(i, 2) + ")";
                stmt1.execute(cmd2);

                stmt1.execute("UPDATE produto SET estoque_prod = estoque_prod - " 
                    + qtdVendida + " WHERE id_prod = " + idProd);
            }

            ConexaoDAO.con.commit();
            stmt.close();
            stmt1.close();
            rs.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }

    public ResultSet consultarVendaConv(VendaConvDTO vendaConvDTO, int opc) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";

            switch (opc) {
                case 1:
                    comando = "SELECT v.id_vend, to_char(v.dat_vend, 'DD/MM/YYYY') AS dat_vend, "
                            + "v.val_total_vend, f.nome_fun "
                            + "FROM venda_conv v "
                            + "JOIN funcionario f ON v.id_fun = f.id_fun "
                            + "WHERE to_char(v.dat_vend, 'DD/MM/YYYY') LIKE '"
                            + vendaConvDTO.getDat_vend() + "%' "
                            + "ORDER BY v.dat_vend DESC";
                    break;
                case 2:
                    comando = "SELECT v.id_vend, v.id_fun, v.val_total_vend, "
                            + "to_char(v.dat_vend, 'DD/MM/YYYY') AS dat_vend, f.nome_fun "
                            + "FROM venda_conv v "
                            + "JOIN funcionario f ON v.id_fun = f.id_fun "
                            + "WHERE v.id_vend = " + vendaConvDTO.getId_vend();
                    break;
                case 3:
                    // Itens de uma venda específica (para exibir no JTable de detalhe)
                    comando = "SELECT p.nome_prod, iv.qtd_item, iv.val_unit_item, "
                            + "(iv.qtd_item * iv.val_unit_item) AS subtotal "
                            + "FROM item_venda iv "
                            + "JOIN produto p ON iv.id_prod = p.id_prod "
                            + "WHERE iv.id_vend = " + vendaConvDTO.getId_vend();
                    break;
            }

            rs = stmt.executeQuery(comando);
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
    
}
