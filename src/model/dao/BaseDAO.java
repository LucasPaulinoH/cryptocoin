package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAO<VO> implements BaseInterDAO<VO> {
    protected static Connection conn = null;
    private static final String url = "jdbc:postgresql://localhost:5432/CrypToCoin";
    private static final String user = "postgres";
    private static final String senha = "1107";

    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, senha);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return conn;
        } else
            return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void inserir(VO entity) throws SQLException;
    public abstract void remover(VO entity) throws SQLException;
    public abstract void editar(VO entity) throws SQLException;
    public abstract ResultSet buscarPorId(VO entity) throws SQLException;
    public abstract ResultSet buscarTudo() throws SQLException;
}
