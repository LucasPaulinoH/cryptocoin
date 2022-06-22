package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.ConversaoVO;

public class ConversaoDAO<VO extends ConversaoVO> extends BaseDAO<VO> {

  public void inserir(VO vo) {
    String sql = "INSERT INTO conversao(valor1,valor2,cliente,moeda,tag) values (?,?,?,?,?)";
    PreparedStatement ptst;
    try {
      ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ptst.setDouble(1, vo.getValor1());
      ptst.setDouble(2, vo.getValor2());
      ptst.setString(3, vo.getCliente());
      ptst.setString(4, vo.getMoeda());
      ptst.setString(5, vo.getTag());

      int affectedRows = ptst.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("A inserção falhou. Nenhuma linha foi alterada.");
      }
      ResultSet generatedKeys = ptst.getGeneratedKeys();
      if (generatedKeys.next()) {
        vo.setId(generatedKeys.getLong(1));
      } else {
        throw new SQLException("A inserção falhou. Nenhum Id foi retornado.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void remover(VO vo) {
    String sql = "DELETE * FROM Conversao WHERE id = ?";
    PreparedStatement ptst;
    try {
      ptst = getConnection().prepareStatement(sql);
      ptst.setLong(1, vo.getId());
      ptst.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public ResultSet buscarPorCliente(VO vo) {
    String sql = "SELECT * FROM Conversao WHERE client = ?";
    PreparedStatement ptst;
    ResultSet rs = null;
    try {
      ptst = getConnection().prepareStatement(sql);
      ptst.setString(1, vo.getCliente());
      rs = ptst.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rs;
  }

  public ResultSet buscarTudo() {
    String sql = "SELECT * FROM Conversao";
    Statement st;
    ResultSet rs = null;
    try {
      st = getConnection().createStatement();
      rs = st.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rs;
  }

  public ResultSet buscarPorTag(VO vo) {
    String sql = "SELECT * FROM Conversao WHERE tag = ?";
    PreparedStatement ptst;
    ResultSet rs = null;
    try {
      ptst = getConnection().prepareStatement(sql);
      ptst.setString(1, vo.getTag());
      rs = ptst.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rs;
  }

  public ResultSet buscarPorId(VO vo) {
    String sql = "SELECT * FROM Conversao WHERE id = ?";
    PreparedStatement ptst;
    ResultSet rs = null;
    try {
      ptst = getConnection().prepareStatement(sql);
      ptst.setLong(1, vo.getId());
      rs = ptst.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rs;
  }

  public void editar(VO vo) throws SQLException {
    conn = getConnection();
    String sql = "UPDATE Conversao SET valor1 = '" + vo.getValor1() + "' WHERE id = '" + vo.getId() + "'";

    try {
      Statement ptst = conn.createStatement();

      ptst.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}