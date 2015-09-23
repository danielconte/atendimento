package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import model.Senha;

public class SenhaDao {
	private Connection connection;
	public void adiciona(Senha senha){
		String sql = " INSERT INTO SENHA "+
				"(TIPO,NUMERO,STATUS) VALUES (?,?,?)";
		try {
			this.connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, senha.getTipo());
			stmt.setLong(2, senha.getNumero());
			stmt.setString(3, senha.getStatus());
			stmt.executeQuery();
			stmt.close();
			connection.close();
		} catch (SQLException e ){
			throw new RuntimeException(e);
		} 
	}
	
	public void atualiza(Senha senha){
		String sql = "UPDATE SENHA SET STATUS=? WHERE CODSENHA=? ";
		try {
			this.connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,senha.getStatus());
			stmt.setLong(2,senha.getId());
			stmt.execute();
			stmt.close();			
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	
	public Senha getChamada(){
		try {
			this.connection = new ConnectionFactory().getConnection();
			String sql = "SELECT  * FROM SENHA " +
				"WHERE STATUS = 'S' " +
				"ORDER BY TIPO DESC, NUMERO ASC " +
				"LIMIT 1";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Senha senha = new Senha();
			if (rs.next()){				
				senha.setId(rs.getLong("CODSENHA"));
				senha.setNumero(rs.getLong("NUMERO"));
				senha.setStatus(rs.getString("STATUS"));
				senha.setTipo(rs.getString("TIPO"));
			} else {
				senha.setNumero((long) 0);
				senha.setTipo("-");

			}
			rs.close();
			stmt.close();
			connection.close();
			return senha;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public Senha getUltima(String tipo){
		try {
			this.connection = new ConnectionFactory().getConnection();
			String sql = "SELECT  * FROM SENHA " +
				"WHERE STATUS = 'S' AND TIPO = ? " +
				"ORDER BY NUMERO ASC " +
				"LIMIT 1";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1,tipo);
			ResultSet rs = stmt.executeQuery();
			Senha senha = new Senha();
			if (rs.next()){				
				senha.setId(rs.getLong("CODSENHA"));
				senha.setNumero(rs.getLong("NUMERO"));
				senha.setStatus(rs.getString("STATUS"));
				senha.setTipo(rs.getString("TIPO"));
			} else {

			}
			rs.close();
			stmt.close();
			connection.close();
			return senha;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Senha> getLista(){
		try {
			this.connection = new ConnectionFactory().getConnection();
			List<Senha> senhas = new ArrayList<Senha>();
			String sql = "SELECT * FROM SENHA " +
				"WHERE STATUS = 'S' " +
				"ORDER BY NUMERO ASC ";
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()){	
				Senha senha = new Senha();
				senha.setId(rs.getLong("CODSENHA"));
				senha.setNumero(rs.getLong("NUMERO"));
				senha.setStatus(rs.getString("STATUS"));
				senha.setTipo(rs.getString("TIPO"));
				senhas.add(senha);
			} 
			
			rs.close();
			stmt.close();
			connection.close();
			return senhas;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

}
