
package br.com.autofuel.ctr;

import java.sql.ResultSet;
import br.com.autofuel.dao.ConexaoDAO;
import br.com.autofuel.dao.ProdutoDAO;
import br.com.autofuel.dto.ProdutoDTO;

public class ProdutoCTR {
    
    ProdutoDAO produtoDAO = new ProdutoDAO();

    public ProdutoCTR() {
    }

    public String inserirProduto(ProdutoDTO produtoDTO) {
        try {
            if (produtoDAO.inserirProduto(produtoDTO)) {
                return "Produto cadastrado com sucesso!";
            } else {
                return "Produto NÃO cadastrado!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Produto NÃO cadastrado!";
        }
    }

    public String alterarProduto(ProdutoDTO produtoDTO) {
        try {
            if (produtoDAO.alterarProduto(produtoDTO)) {
                return "Produto alterado com sucesso!";
            } else {
                return "Produto NÃO alterado!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Produto NÃO alterado!";
        }
    }

    public String excluirProduto(ProdutoDTO produtoDTO) {
        try {
            if (produtoDAO.excluirProduto(produtoDTO)) {
                return "Produto excluído com sucesso!";
            } else {
                return "Produto NÃO excluído!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Produto NÃO excluído!";
        }
    }

    public ResultSet consultarProduto(ProdutoDTO produtoDTO, int opc) {
        return produtoDAO.consultarProduto(produtoDTO, opc);
    }

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }   
}
