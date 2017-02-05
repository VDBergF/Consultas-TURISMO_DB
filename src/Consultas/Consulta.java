package Consultas;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by berg on 03/02/17.
 */
public class Consulta {
    private Connection connection;

    public Consulta(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<String> hoteisComRestaurante(String cidade) {
        Statement stmt = null;
        ArrayList<String> hoteis = new ArrayList<>();
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("error ao pegar statement");
            e.printStackTrace();
        }
        String sql = "SELECT NOME FROM HOTEL WHERE CODIGO_RESTAURANTE IS NOT NULL AND CODIGO_CIDADE = (SELECT CODIGO FROM CIDADE " +
                "WHERE NOME = '" + cidade + "')";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nome = rs.getString("nome");
                hoteis.add(nome);

                System.out.println(nome);
            }

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("error ao pegar resultSet");
            e.printStackTrace();
        }

        return hoteis;
    }

    public ArrayList<String> cidadePopu(String populacao) {
        Statement stmt = null;
        ArrayList<String> cidades = new ArrayList<>();
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("error ao pegar statement");
            e.printStackTrace();
        }
        String sql = "SELECT NOME FROM CIDADE WHERE POPULACAO < " + Integer.valueOf(populacao);

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nome = rs.getString("nome");
                cidades.add(nome);

                System.out.println(nome);
            }

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("error ao pegar resultSet");
            e.printStackTrace();
        }

        return cidades;
    }

    public ArrayList<String> csSwComRestaurante(String cidade) {
        Statement stmt = null;
        ArrayList<String> cshow = new ArrayList<>();
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("error ao pegar statement");
            e.printStackTrace();
        }
        String sql = "SELECT NOME FROM CASA_SHOW WHERE CODIGO_RESTAURANTE IS NOT NULL AND CODIGO_CIDADE = (SELECT CODIGO FROM CIDADE " +
                "WHERE NOME = '" + cidade + "')";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nome = rs.getString("nome");
                cshow.add(nome);

                System.out.println(nome);
            }

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("error ao pegar resultSet");
            e.printStackTrace();
        }

        return cshow;
    }

    public String espRestaurante(String restaurante) {
        Statement stmt = null;
        String especialidade = "";

        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("error ao pegar statement");
            e.printStackTrace();
        }
        String sql = "SELECT ESPECIALIDADE FROM RESTAURANTE WHERE NOME = '" + restaurante + "'";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                especialidade = rs.getString("especialidade");
                System.out.println(especialidade);
            }

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("error ao pegar resultSet");
            e.printStackTrace();
        }

        return especialidade;
    }

    public String fundadorMuseu(String museu) {
        Statement stmt = null;
        String nome = "";

        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("error ao pegar statement");
            e.printStackTrace();
        }
        String sql = "SELECT NOME FROM FUNDADOR JOIN FUNDA ON (FUNDADOR.CODIGO = FUNDA.CODIGO_FUNDADOR) " +
                "WHERE FUNDA.CODIGO_MUSEU = (SELECT CODIGO FROM MUSEU WHERE NOME = '" + museu + "')";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                nome = rs.getString("nome");
                System.out.println(nome);
            }

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("error ao pegar resultSet");
            e.printStackTrace();
        }

        return nome;
    }
}
