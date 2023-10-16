package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static Connection conn = null;

   /**
    * Método para conectar
    * @return 
    */
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl"); //Ele é usado para recuperar o valor associado a uma chave específica nesse caso, o link da dburl, em um objeto Properties.
                conn = DriverManager.getConnection(url, props);
                
                System.out.println("Conectado com sucesso");
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }

        return conn;
    }

    /**
     * Método para fechar conexão
     */
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    /**
     * método para carregar a propriedades salvas no arquivo db.propeties
     *
     * @return
     */
    private static Properties loadProperties() {

        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs); // esse comando faz a leitura apontado pelo FileInputStream e guarda os dados no objeto props.
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

}
