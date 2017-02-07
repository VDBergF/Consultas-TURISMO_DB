package Consultas;

import Modelos.Igreja;

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

    public ArrayList<Igreja> dataConstIgreja(String str, boolean buscaNome) {
        Statement stmt = null;
        ArrayList<Igreja> nomeOuCodigo = new ArrayList<>();
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("error ao pegar statement");
            e.printStackTrace();
        }
        String sql = "";
        if (buscaNome) sql = "SELECT * FROM IGREJA WHERE NOME = '" + str + "'";
        else sql = "SELECT DATA_CONSTRUCAO FROM IGREJA WHERE CODIGO = " + str;

        ResultSet rs = null;
        String nome = "";
        int codigo;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (buscaNome) {
                    codigo = rs.getInt("codigo");
                    nome = rs.getString("nome");
                    nomeOuCodigo.add(new Igreja(codigo, nome, null));
                } else {
                    nome = rs.getString("data_construcao");
                    nomeOuCodigo.add(new Igreja(-1, null, nome));
                }
            }

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("error ao pegar resultSet");
            e.printStackTrace();
        }

        return nomeOuCodigo;
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

    public String cidadePontoTuris() {
        Statement stmt = null;
        String nome = "";

        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("error ao pegar statement");
            e.printStackTrace();
        }
        String sql = "SELECT ESPECIALIDADE FROM RESTAURANTE WHERE NOME = '" + nome + "'";

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

    public ArrayList<String> buscaFundadorMuseu(String museu) {
        Statement stmt = null;
        ArrayList<String> nomes = new ArrayList<>();

        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("error ao pegar statement");
            e.printStackTrace();
        }
        String sql = "SELECT FUNDADOR.NOME FROM FUNDA INNER JOIN FUNDADOR ON FUNDA.CODIGO_FUNDADOR = FUNDADOR.CODIGO\n" +
                "INNER JOIN MUSEU ON MUSEU.CODIGO = FUNDA.CODIGO_MUSEU WHERE MUSEU.NOME = '" + museu + "'";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nome = rs.getString("nome");
                nomes.add(nome);
                System.out.println(nome);
            }

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("error ao pegar resultSet");
            e.printStackTrace();
        }

        return nomes;
    }
}
