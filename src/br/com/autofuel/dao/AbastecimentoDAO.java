
package br.com.autofuel.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import br.com.autofuel.dto.AbastecimentoDTO;
import br.com.autofuel.dto.BombaDTO;
import br.com.autofuel.dto.FuncionarioDTO;

public class AbastecimentoDAO {
       public AbastecimentoDAO() {
    }

    SimpleDateFormat dataFmt = new SimpleDateFormat("dd/MM/yyyy");

    private ResultSet rs   = null;
    private Statement stmt = null;

    public boolean inserirAbastecimento(AbastecimentoDTO abastecimentoDTO,
                                        BombaDTO bombaDTO,
                                        FuncionarioDTO funcionarioDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "INSERT INTO abastecimento "
                    + "(id_bom, id_fun, placa_abast, litros_abast, val_litro_abast, val_total_abast, dat_abast) "
                    + "VALUES ("
                    + bombaDTO.getId_bom()                                                            + ", "
                    + funcionarioDTO.getId_fun()                                                       + ", "
                    + "'" + abastecimentoDTO.getPlaca_abast().toUpperCase()                    + "', "
                    + abastecimentoDTO.getLitros_abast()                                              + ", "
                    + abastecimentoDTO.getVal_litro_abast()                                           + ", "
                    + abastecimentoDTO.getVal_total_abast()                                           + ", "
                    + "to_date('" + dataFmt.format(abastecimentoDTO.getDat_abast()) + "', 'DD/MM/YYYY'))";

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

    public boolean alterarAbastecimento(AbastecimentoDTO abastecimentoDTO,
                                        BombaDTO bombaDTO,
                                        FuncionarioDTO funcionarioDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "UPDATE abastecimento SET "
                    + "id_bom          = " + bombaDTO.getId_bom()                                          + ", "
                    + "id_fun          = " + funcionarioDTO.getId_fun()                                     + ", "
                    + "placa_abast     = '" + abastecimentoDTO.getPlaca_abast().toUpperCase()       + "', "
                    + "litros_abast    = " + abastecimentoDTO.getLitros_abast()                            + ", "
                    + "val_litro_abast = " + abastecimentoDTO.getVal_litro_abast()                         + ", "
                    + "val_total_abast = " + abastecimentoDTO.getVal_total_abast()                         + ", "
                    + "dat_abast       = to_date('" + dataFmt.format(abastecimentoDTO.getDat_abast()) + "', 'DD/MM/YYYY') "
                    + "WHERE id_abast  = " + abastecimentoDTO.getId_abast();

            System.out.println("SQL: " + comando);
            stmt.execute(comando);
            ConexaoDAO.con.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO alterar abastecimento: " + e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }

    // -------------------------------------------------------
    //  EXCLUIR
    // -------------------------------------------------------
    public boolean excluirAbastecimento(AbastecimentoDTO abastecimentoDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();

            String comando = "DELETE FROM abastecimento WHERE id_abast = " + abastecimentoDTO.getId_abast();

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
    //  opc 1 → por placa       (tabela de busca)
    //  opc 2 → por id_abast    (carregar campos do formulário)
    //  opc 3 → todos           (relatório geral)
    // -------------------------------------------------------
    public ResultSet consultarAbastecimento(AbastecimentoDTO abastecimentoDTO, int opc) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";

            switch (opc) {
                case 1:
                    comando = "SELECT a.id_abast, a.placa_abast, b.descricao_bom, "
                            + "b.tipo_comb_bom, a.litros_abast, a.val_total_abast, "
                            + "to_char(a.dat_abast, 'DD/MM/YYYY') AS dat_abast "
                            + "FROM abastecimento a "
                            + "JOIN bomba b ON a.id_bom = b.id_bom "
                            + "WHERE a.placa_abast ILIKE '" + abastecimentoDTO.getPlaca_abast() + "%' "
                            + "ORDER BY a.dat_abast DESC";
                    break;
                case 2:
                    comando = "SELECT a.id_abast, a.id_bom, a.id_fun, a.placa_abast, "
                            + "a.litros_abast, a.val_litro_abast, a.val_total_abast, "
                            + "to_char(a.dat_abast, 'DD/MM/YYYY') AS dat_abast, "
                            + "b.descricao_bom, f.nome_fun "
                            + "FROM abastecimento a "
                            + "JOIN bomba b ON a.id_bom = b.id_bom "
                            + "JOIN funcionario f ON a.id_fun = f.id_fun "
                            + "WHERE a.id_abast = " + abastecimentoDTO.getId_abast();
                    break;
                case 3:
                    comando = "SELECT a.id_abast, a.placa_abast, b.descricao_bom, "
                            + "b.tipo_comb_bom, a.litros_abast, a.val_litro_abast, "
                            + "a.val_total_abast, to_char(a.dat_abast, 'DD/MM/YYYY') AS dat_abast, "
                            + "f.nome_fun "
                            + "FROM abastecimento a "
                            + "JOIN bomba b ON a.id_bom = b.id_bom "
                            + "JOIN funcionario f ON a.id_fun = f.id_fun "
                            + "ORDER BY a.dat_abast DESC";
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
