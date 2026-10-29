
package br.com.autofuel.ctr;

import java.sql.ResultSet;
import br.com.autofuel.dao.BombaDAO;
import br.com.autofuel.dao.ConexaoDAO;
import br.com.autofuel.dto.BombaDTO;

public class BombaCTR {
    
    BombaDAO bombaDAO = new BombaDAO();
 
    public BombaCTR() {
    }
 
    public String inserirBomba(BombaDTO bombaDTO) {
        try {
            if (bombaDAO.inserirBomba(bombaDTO)) {
                return "Bomba cadastrada com sucesso!";
            } else {
                return "Bomba NÃO cadastrada!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Bomba NÃO cadastrada!";
        }
    }
 
    public String alterarBomba(BombaDTO bombaDTO) {
        try {
            if (bombaDAO.alterarBomba(bombaDTO)) {
                return "Bomba alterada com sucesso!";
            } else {
                return "Bomba NÃO alterada!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Bomba NÃO alterada!";
        }
    }
 
    public String excluirBomba(BombaDTO bombaDTO) {
        try {
            if (bombaDAO.excluirBomba(bombaDTO)) {
                return "Bomba excluída com sucesso!";
            } else {
                return "Bomba NÃO excluída!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Bomba NÃO excluída!";
        }
    }
 
    public ResultSet consultarBomba(BombaDTO bombaDTO, int opc) {
        return bombaDAO.consultarBomba(bombaDTO, opc);
    }
 
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
}
