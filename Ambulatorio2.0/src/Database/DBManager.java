package Database;

import java.sql.*;

public class DBManager {
    //Classe per la gestione della connessione al database

    public static String url = "jdbc:mysql://localhost:3000/";
    public static String dbName = "mydb";
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String userName = "root";
    public static String password = "admin";



    // Ottieni una connessione al database
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Connection connessione = null;
        Class.forName(driver); //com.mysql.cj.jdbc.Driver

        connessione = DriverManager.getConnection(url+dbName,userName,password);

        return connessione;
    }

    public static void closeConnection(Connection c) throws SQLException {
        c.close();
    }

    public static ResultSet selectQuery(String query) throws ClassNotFoundException, SQLException {

        Connection connessione = getConnection();

        Statement statement = connessione.createStatement();

        ResultSet result = statement.executeQuery(query);

        return result;
    }

    public static int updateQuery(String query) throws ClassNotFoundException, SQLException {

        Connection connessione = getConnection();
        Statement statement = connessione.createStatement();
        int ret = statement.executeUpdate(query);
        connessione.close();
        return ret;
    }

    public static Integer updateQueryReturnGeneratedKey(String query) throws ClassNotFoundException, SQLException {
        Integer ret = null;

        Connection connessione = getConnection();
        Statement statement = connessione.createStatement();
        statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            ret = rs.getInt(1);
        }

        connessione.close();

        return ret;
    }
}