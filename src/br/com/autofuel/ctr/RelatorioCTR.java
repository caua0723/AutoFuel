
package br.com.autofuel.ctr;

import java.sql.Connection;
import java.io.InputStream;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import br.com.autofuel.dao.ConexaoDAO;
import static br.com.autofuel.dao.ConexaoDAO.CloseDB;

public class RelatorioCTR {
    
    public RelatorioCTR() {
    }

    public JFrame abrirRelatorio(String relatorio, String titulo, Map parametros) {
        try {
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("br/com/autofuel/rels/" + relatorio);

            JasperPrint print = JasperFillManager.fillReport(
                    inputStream, parametros, getConexao());

            CloseDB();

            JRViewer viewer = new JRViewer(print);

            JFrame frameRelatorio = new JFrame(titulo);
            frameRelatorio.add(viewer);
            frameRelatorio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frameRelatorio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            return frameRelatorio;

        } catch (Exception e) {
            System.out.println("Erro ao abrir relatório: " + e.getMessage());
            return null;
        }
    }

    public Connection getConexao() {
        return ConexaoDAO.ConnectDBRels();
    }

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }    
}
