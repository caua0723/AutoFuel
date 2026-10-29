
package br.com.autofuel.ctr;

import java.sql.ResultSet;
import br.com.autofuel.dao.ConexaoDAO;
import br.com.autofuel.dao.FuncionarioDAO;
import br.com.autofuel.dto.FuncionarioDTO;

public class FuncionarioCTR {
    
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public FuncionarioCTR() {
    }

    public String inserirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDAO.inserirFuncionario(funcionarioDTO)) {
                return "Funcionário cadastrado com sucesso!";
            } else {
                return "Funcionário NÃO cadastrado!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionário NÃO cadastrado!";
        }
    }

    public String alterarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDAO.alterarFuncionario(funcionarioDTO)) {
                return "Funcionário alterado com sucesso!";
            } else {
                return "Funcionário NÃO alterado!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionário NÃO alterado!";
        }
    }

    public String excluirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDAO.excluirFuncionario(funcionarioDTO)) {
                return "Funcionário excluído com sucesso!";
            } else {
                return "Funcionário NÃO excluído!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionário NÃO excluído!";
        }
    }

    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opc) {
        return funcionarioDAO.consultarFuncionario(funcionarioDTO, opc);
    }

    public String logarFuncionario(FuncionarioDTO funcionarioDTO) {
        return funcionarioDAO.logarFuncionario(funcionarioDTO);
    }

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }  
}
