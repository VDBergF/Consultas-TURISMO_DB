package Consultas;

import java.sql.*;

/**
 * Created by berg on 03/02/17.
 */
public class Consulta {
    private Connection connection;

    public Consulta(Connection connection) {
        this.connection = connection;
    }

    public void hoteisComRestaurante() {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("error ao pegar statement");
            e.printStackTrace();
        }
        String sql = "SELECT NOME FROM HOTEL WHERE CODIGO_RESTAURANTE IS NOT NULL";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nome = rs.getString("nome");

                System.out.println(nome);
            }

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("error ao pegar resultSet");
            e.printStackTrace();
        }


    }
}
