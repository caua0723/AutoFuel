package br.com.autofuel.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDAO {

    public static Connection con = null;

    public ConexaoDAO() {
    }

    public static void ConnectDB() {
        try {
            if (con != null && !con.isClosed()) {
                return;
            }

            Properties config = new Properties();
            File arquivoConfig = new File("config.properties");
            if (arquivoConfig.isFile()) {
                try (FileInputStream entrada = new FileInputStream(arquivoConfig)) {
                    config.load(entrada);
                }
            }

            String host = obterConfiguracao(config, "host", "AUTOFUEL_DB_HOST", "localhost");
            String porta = obterConfiguracao(config, "port", "AUTOFUEL_DB_PORT", "5432");
            String banco = obterConfiguracao(config, "database", "AUTOFUEL_DB_NAME", "autofuel");
            String usuario = obterConfiguracao(config, "user", "AUTOFUEL_DB_USER", "postgres");
            String senha = obterConfiguracao(config, "password", "AUTOFUEL_DB_PASSWORD", null);

            if (senha == null || senha.trim().isEmpty()) {
                throw new SQLException("Senha do PostgreSQL não configurada. Crie config.properties a partir de config.properties.example.");
            }

            String url = "jdbc:postgresql://" + host + ":" + porta + "/" + banco;
            con = DriverManager.getConnection(url, usuario, senha);
            con.setAutoCommit(false);
        } catch (Exception e) {
            con = null;
            System.out.println("Problema ao abrir a base de dados: " + e.getMessage());
        }
    }

    private static String obterConfiguracao(Properties config, String chave,
            String variavelAmbiente, String valorPadrao) {
        String valor = System.getProperty("autofuel.db." + chave);
        if (valor == null || valor.trim().isEmpty()) {
            valor = System.getenv(variavelAmbiente);
        }
        if (valor == null || valor.trim().isEmpty()) {
            valor = config.getProperty(chave);
        }
        if (valor == null || valor.trim().isEmpty()) {
            valor = valorPadrao;
        }
        return valor;
    }

    public static void CloseDB() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Problema ao fechar a base de dados: " + e.getMessage());
        } finally {
            con = null;
        }
    }

    public static Connection ConnectDBRels() {
        ConnectDB();
        return con;
    }
}
