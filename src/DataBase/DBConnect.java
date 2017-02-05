package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by berg on 03/02/17.
 */
public class DBConnect {
    private String opcao;
    private Connection conOracle;
    private Connection conPostgres;

    public DBConnect(String opcao) {
        this.opcao = opcao;
        initDb();
    }

    private void initDb() {
        if (this.opcao.equals("op1"))
            connectOracle();
        else
            connectPostgres();
    }

    public Connection getConnection(String opcao) {
        if (opcao.equals("op1")) return getConOracle();
        return getConPostgres();
    }

    private Connection getConOracle() {
        return conOracle;
    }

    private Connection getConPostgres() {
        return conPostgres;
    }

    public boolean connectOracle() {
        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return false;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        try {
            conOracle = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "TURISMO_DB", "041097");
        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return false;

        }

        if (conOracle != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make conOracle!");
        }
        return true;
    }

    public boolean connectPostgres() {
        System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
            return false;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        try {
            conPostgres = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TURISMO_DB", "postgres", "041097");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return false;

        }

        if (conPostgres != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make conOracle!");
        }
        return true;
    }
}
