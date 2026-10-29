
package br.com.autofuel.dao;

import java.sql.*;
import br.com.autofuel.dto.ProdutoDTO;

public class ProdutoDAO {
    
        public ProdutoDAO() {
    }

    private ResultSet rs   = null;
    private Statement stmt = null;

    public boolean inserirProduto(ProdutoDTO produtoDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "INSERT INTO produto (nome_prod, preco_prod, estoque_prod) VALUES ("
                    + "'" + produtoDTO.getNome_prod().toUpperCase() + "', "
                    + produtoDTO.getPreco_prod()                    + ", "
                    + produtoDTO.getEstoque_prod()                  + ")";

            stmt.execute(comando);
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }

    public boolean alterarProduto(ProdutoDTO produtoDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "UPDATE produto SET "
                    + "nome_prod    = '" + produtoDTO.getNome_prod().toUpperCase() + "', "
                    + "preco_prod   = "  + produtoDTO.getPreco_prod()              + ", "
                    + "estoque_prod = "  + produtoDTO.getEstoque_prod()            + " "
                    + "WHERE id_prod = " + produtoDTO.getId_prod();

            System.out.println("SQL: " + comando);
            stmt.execute(comando);
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO alterar produto: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }

    public boolean excluirProduto(ProdutoDTO produtoDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "DELETE FROM produto WHERE id_prod = " + produtoDTO.getId_prod();

            stmt.execute(comando);
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }


    public ResultSet consultarProduto(ProdutoDTO produtoDTO, int opc) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";

            switch (opc) {
                case 1:
                    comando = "SELECT p.id_prod, p.nome_prod, p.preco_prod, p.estoque_prod "
                            + "FROM produto p "
                            + "WHERE p.nome_prod ILIKE '" + produtoDTO.getNome_prod() + "%' "
                            + "ORDER BY p.nome_prod";
                    break;
                case 2:
                    comando = "SELECT p.id_prod, p.nome_prod, p.preco_prod, p.estoque_prod "
                            + "FROM produto p "
                            + "WHERE p.id_prod = " + produtoDTO.getId_prod();
                    break;
                case 3:
                    comando = "SELECT p.id_prod, p.nome_prod, p.preco_prod, p.estoque_prod "
                            + "FROM produto p "
                            + "WHERE p.estoque_prod > 0 "
                            + "ORDER BY p.nome_prod";
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
