package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseInterDAO<VO> {
	public void inserir(VO entity) throws SQLException;
	public void remover(VO entity) throws SQLException;
	public void editar(VO entity) throws SQLException;
	public ResultSet buscarPorId(VO entity) throws SQLException;
	public ResultSet buscarTudo() throws SQLException;
}
