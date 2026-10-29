
package br.com.autofuel.dao;

import java.sql.*;
import br.com.autofuel.dto.BombaDTO;

public class BombaDAO {
    
        public BombaDAO() {
    }

    private ResultSet rs   = null;
    private Statement stmt = null;

    // -------------------------------------------------------
    //  INSERIR
    // -------------------------------------------------------
    public boolean inserirBomba(BombaDTO bombaDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "INSERT INTO bomba (descricao_bom, tipo_comb_bom, preco_litro_bom, ativa_bom) VALUES ("
                + "'" + bombaDTO.getDescricao_bom().toUpperCase() + "', "
                + "'" + bombaDTO.getTipo_comb_bom().toUpperCase() + "', "
                +        bombaDTO.getPreco_litro_bom()             + ", "
                +        bombaDTO.isAtiva_bom()                    + ")";

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

    // -------------------------------------------------------
    //  ALTERAR
    // -------------------------------------------------------
    public boolean alterarBomba(BombaDTO bombaDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "UPDATE bomba SET "
                    + "descricao_bom   = '" + bombaDTO.getDescricao_bom().toUpperCase() + "', "
                    + "tipo_comb_bom   = '" + bombaDTO.getTipo_comb_bom().toUpperCase() + "', "
                    + "preco_litro_bom = "  + bombaDTO.getPreco_litro_bom()             + ", "
                    + "ativa_bom       = "  + bombaDTO.isAtiva_bom()                    + " "
                    + "WHERE id_bom    = "  + bombaDTO.getId_bom();

            System.out.println("SQL: " + comando);
            stmt.execute(comando);
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO alterar bomba: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }

    // -------------------------------------------------------
    //  EXCLUIR
    // -------------------------------------------------------
    public boolean excluirBomba(BombaDTO bombaDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "DELETE FROM bomba WHERE id_bom = " + bombaDTO.getId_bom();

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

    // -------------------------------------------------------
    //  CONSULTAR
    //  opc 1 → por descrição (tabela de busca)
    //  opc 2 → por id        (carregar campos do formulário)
    //  opc 3 → todas ativas  (preencher ComboBox)
    // -------------------------------------------------------
    public ResultSet consultarBomba(BombaDTO bombaDTO, int opc) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";

            switch (opc) {
                case 1:
                    comando = "SELECT b.id_bom, b.descricao_bom, b.tipo_comb_bom, b.preco_litro_bom, b.ativa_bom "
                            + "FROM bomba b "
                            + "WHERE b.descricao_bom ILIKE '" + bombaDTO.getDescricao_bom() + "%' "
                            + "ORDER BY b.descricao_bom";
                    break;
                case 2:
                    comando = "SELECT b.id_bom, b.descricao_bom, b.tipo_comb_bom, b.preco_litro_bom, b.ativa_bom "
                            + "FROM bomba b "
                            + "WHERE b.id_bom = " + bombaDTO.getId_bom();
                    break;
                case 3:
                    comando = "SELECT b.id_bom, b.descricao_bom, b.tipo_comb_bom, b.preco_litro_bom "
                            + "FROM bomba b "
                            + "WHERE b.ativa_bom = true "
                            + "ORDER BY b.descricao_bom";
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
