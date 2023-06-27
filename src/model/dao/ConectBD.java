package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectBD {
    private Connection conexao;

    public ConectBD() {
        String url = "jdbc:mariadb://localhost:3306/Conexao_Java";
        String user = "root";
        String pwd = "root";

        try {
            conexao = DriverManager.getConnection(url, user, pwd);
            System.out.println("Conexao realizada.");
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * metodo que retorna a conexao com o database
     * 
     * @return um obj do tipo connection
     */
    public Connection getConnection() {
        return conexao;
    }
}