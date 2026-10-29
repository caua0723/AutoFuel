
package br.com.autofuel.ctr;

import java.sql.ResultSet;
import br.com.autofuel.dao.AbastecimentoDAO;
import br.com.autofuel.dao.ConexaoDAO;
import br.com.autofuel.dto.AbastecimentoDTO;
import br.com.autofuel.dto.BombaDTO;
import br.com.autofuel.dto.FuncionarioDTO;

public class AbastecimentoCTR {
    
    AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO();

    public AbastecimentoCTR() {
    }

    public String inserirAbastecimento(AbastecimentoDTO abastecimentoDTO,
                                       BombaDTO bombaDTO,
                                       FuncionarioDTO funcionarioDTO) {
        try {
            if (abastecimentoDAO.inserirAbastecimento(abastecimentoDTO, bombaDTO, funcionarioDTO)) {
                return "Abastecimento registrado com sucesso!";
            } else {
                return "Abastecimento NÃO registrado!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Abastecimento NÃO registrado!";
        }
    }

    public String alterarAbastecimento(AbastecimentoDTO abastecimentoDTO,
                                       BombaDTO bombaDTO,
                                       FuncionarioDTO funcionarioDTO) {
        try {
            if (abastecimentoDAO.alterarAbastecimento(abastecimentoDTO, bombaDTO, funcionarioDTO)) {
                return "Abastecimento alterado com sucesso!";
            } else {
                return "Abastecimento NÃO alterado!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Abastecimento NÃO alterado!";
        }
    }

    public String excluirAbastecimento(AbastecimentoDTO abastecimentoDTO) {
        try {
            if (abastecimentoDAO.excluirAbastecimento(abastecimentoDTO)) {
                return "Abastecimento excluído com sucesso!";
            } else {
                return "Abastecimento NÃO excluído!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Abastecimento NÃO excluído!";
        }
    }

    public ResultSet consultarAbastecimento(AbastecimentoDTO abastecimentoDTO, int opc) {
        return abastecimentoDAO.consultarAbastecimento(abastecimentoDTO, opc);
    }

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }    
}
