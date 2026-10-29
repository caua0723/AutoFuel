
package br.com.autofuel.dao;

import java.sql.*;
import br.com.autofuel.dto.FuncionarioDTO;

public class FuncionarioDAO {
    
    public FuncionarioDAO() {
        
    }
 
    private ResultSet rs   = null;
    private Statement stmt = null;
 
    public boolean inserirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
 
            String comando = "INSERT INTO funcionario (nome_fun, cpf_fun, login_fun, senha_fun, tipo_fun) "
                    + "VALUES ("
                    + "'" + funcionarioDTO.getNome_fun().toUpperCase() + "', "
                    + "'" + funcionarioDTO.getCpf_fun()               + "', "
                    + "'" + funcionarioDTO.getLogin_fun()             + "', "
                    + "crypt('" + funcionarioDTO.getSenha_fun() + "', gen_salt('bf', 8)), "
                    + "'" + funcionarioDTO.getTipo_fun().toUpperCase() + "')";
 
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
 
    public boolean alterarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
 
            String comando = "UPDATE funcionario SET "
                    + "nome_fun  = '" + funcionarioDTO.getNome_fun().toUpperCase() + "', "
                    + "cpf_fun   = '" + funcionarioDTO.getCpf_fun()               + "', "
                    + "login_fun = '" + funcionarioDTO.getLogin_fun()             + "', ";
 
            if (funcionarioDTO.getSenha_fun() != null && !funcionarioDTO.getSenha_fun().isEmpty()) {
                comando += "senha_fun = crypt('" + funcionarioDTO.getSenha_fun() + "', gen_salt('bf', 8)), ";
            }
 
            comando += "tipo_fun = '" + funcionarioDTO.getTipo_fun().toUpperCase() + "' "
                    + "WHERE id_fun = " + funcionarioDTO.getId_fun();
 
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
 
    public boolean excluirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
 
            String comando = "DELETE FROM funcionario WHERE id_fun = " + funcionarioDTO.getId_fun();
 
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
 
    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opc) {
        try {
            ConexaoDAO.ConnectDB();
            stmt = ConexaoDAO.con.createStatement();
            String comando = "";
 
            switch (opc) {
                case 1:
                    comando = "SELECT f.id_fun, f.nome_fun, f.cpf_fun, f.login_fun, f.tipo_fun "
                            + "FROM funcionario f "
                            + "WHERE f.nome_fun ILIKE '" + funcionarioDTO.getNome_fun() + "%' "
                            + "ORDER BY f.nome_fun";
                    break;
                case 2:
                    comando = "SELECT f.id_fun, f.nome_fun, f.cpf_fun, f.login_fun, f.tipo_fun "
                            + "FROM funcionario f "
                            + "WHERE f.id_fun = " + funcionarioDTO.getId_fun();
                    break;
                case 3:
                    comando = "SELECT f.id_fun, f.nome_fun FROM funcionario f ORDER BY f.nome_fun";
                    break;
            }
 
            rs = stmt.executeQuery(comando);
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
 
    public String logarFuncionario(FuncionarioDTO funcionarioDTO) {
    try {
        ConexaoDAO.ConnectDB();
        stmt = ConexaoDAO.con.createStatement();

        String comando = "SELECT f.id_fun, f.tipo_fun FROM funcionario f "
                + "WHERE f.login_fun = '" + funcionarioDTO.getLogin_fun() + "' "
                + "AND f.senha_fun = crypt('" + funcionarioDTO.getSenha_fun() + "', senha_fun)";

        rs = stmt.executeQuery(comando);
        if (rs.next()) {
            funcionarioDTO.setId_fun(rs.getInt("id_fun")); // ← preenche o id
            return rs.getString("tipo_fun");
        } else {
            return "";
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return "";
    } finally {
        ConexaoDAO.CloseDB();
    }
}   
}
