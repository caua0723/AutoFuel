
package br.com.autofuel.ctr;

import java.sql.ResultSet;
import javax.swing.JTable;
import br.com.autofuel.dao.ConexaoDAO;
import br.com.autofuel.dao.VendaConvDAO;
import br.com.autofuel.dto.FuncionarioDTO;
import br.com.autofuel.dto.VendaConvDTO;

public class VendaConvCTR {
    
    VendaConvDAO vendaConvDAO = new VendaConvDAO();

    public VendaConvCTR() {
    }

   public String inserirVendaConv(VendaConvDTO vendaConvDTO,
                               FuncionarioDTO funcionarioDTO,
                               JTable itens) {
        try {
            if (vendaConvDAO.inserirVendaConv(vendaConvDTO, funcionarioDTO, itens)) {
                return "Venda registrada com sucesso!";
            } else {
                return "Venda NÃO registrada!";
            }
        } catch (Exception e) {
            return e.getMessage(); // ← repassa a mensagem real do DAO
        }
    }

    public ResultSet consultarVendaConv(VendaConvDTO vendaConvDTO, int opc) {
        return vendaConvDAO.consultarVendaConv(vendaConvDTO, opc);
    }

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }

    
}
