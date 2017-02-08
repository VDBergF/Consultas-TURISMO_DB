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

    public ArrayList<String> cidadeComMaisRecursos() {
        Statement stmt = null;
        ArrayList<String> cidades = new ArrayList<>();
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("error ao pegar statement");
            e.printStackTrace();
        }
        String sql = "SELECT NOME AS n FROM CIDADE WHERE CODIGO = (SELECT c_codigo_i AS c FROM (SELECT c_codigo_i, count_i + count_m + count_cc AS soma_atr FROM\n" +
                "  (SELECT CIDADE.CODIGO as c_codigo_i, COUNT(IGREJA.CODIGO) AS count_i\n" +
                "   FROM CIDADE, IGREJA\n" +
                "   WHERE (CIDADE.CODIGO = IGREJA.CODIGO_CIDADE)\n" +
                "   GROUP BY CIDADE.CODIGO\n" +
                "  ) I,\n" +
                "\n" +
                "  (SELECT  CIDADE.CODIGO as c_codigo_m,  COUNT(MUSEU.CODIGO) AS count_m FROM CIDADE, MUSEU\n" +
                "WHERE CIDADE.CODIGO = MUSEU.CODIGO_CIDADE\n" +
                "GROUP BY CIDADE.CODIGO) M,\n" +
                "\n" +
                "  (SELECT\n" +
                "  CIDADE.CODIGO as c_codigo_cc,\n" +
                "  COUNT(CASA_SHOW.CODIGO) AS count_cc\n" +
                "FROM CIDADE, CASA_SHOW\n" +
                "WHERE CIDADE.CODIGO = CASA_SHOW.CODIGO_CIDADE\n" +
                "GROUP BY CIDADE.CODIGO) CC WHERE CC.c_codigo_cc = I.c_codigo_i AND I.c_codigo_i = M.c_codigo_m),\n" +
                "\n" +
                " (SELECT MAX(soma_atr) AS qnt_max FROM (SELECT c_codigo_i, count_i + count_m + count_cc AS soma_atr FROM\n" +
                "  (SELECT CIDADE.CODIGO as c_codigo_i, COUNT(IGREJA.CODIGO) AS count_i\n" +
                "   FROM CIDADE, IGREJA\n" +
                "   WHERE (CIDADE.CODIGO = IGREJA.CODIGO_CIDADE)\n" +
                "   GROUP BY CIDADE.CODIGO\n" +
                "  ) I,\n" +
                "\n" +
                "  (SELECT  CIDADE.CODIGO as c_codigo_m,  COUNT(MUSEU.CODIGO) AS count_m FROM CIDADE, MUSEU\n" +
                "WHERE CIDADE.CODIGO = MUSEU.CODIGO_CIDADE\n" +
                "GROUP BY CIDADE.CODIGO) M,\n" +
                "\n" +
                "  (SELECT\n" +
                "  CIDADE.CODIGO as c_codigo_cc,\n" +
                "  COUNT(CASA_SHOW.CODIGO) AS count_cc\n" +
                "FROM CIDADE, CASA_SHOW\n" +
                "WHERE CIDADE.CODIGO = CASA_SHOW.CODIGO_CIDADE\n" +
                "GROUP BY CIDADE.CODIGO) CC WHERE CC.c_codigo_cc = I.c_codigo_i AND I.c_codigo_i = M.c_codigo_m)) WHERE soma_atr = qnt_max)";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nome = rs.getString("n");
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

    public String precoMedioQuartoHoteis(String cidade) {
        Statement stmt = null;
        String precoMedio = "";

        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("error ao pegar statement");
            e.printStackTrace();
        }
        String sql = "SELECT AVG(PRECO) AS preco_medio FROM (\n" +
                "  SELECT * FROM QUARTO WHERE TIPO LIKE '%Luxury%' AND QUARTO.CODIGO_HOTEL IN (SELECT CODIGO FROM HOTEL " +
                "WHERE CODIGO_CIDADE = (SELECT CODIGO FROM CIDADE WHERE NOME = '" + cidade + "'))\n" +
                "    MINUS (\n" +
                "      SELECT *\n" +
                "      FROM QUARTO\n" +
                "      WHERE TIPO LIKE '%Super%Luxury%'\n" +
                "    )\n" +
                ") GROUP BY TIPO";

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                precoMedio = rs.getString("preco_medio");
                System.out.println(precoMedio);
            }

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("error ao pegar resultSet");
            e.printStackTrace();
        }

        return precoMedio;
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
