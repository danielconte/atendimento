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
	public SenhaDao(){
		this.connection = new ConnectionFactory().getConnection();
	}
	public void adiciona(Senha senha){
		String sql = " INSERT INTO SENHA "+
				"(TIPO,NUMERO,STATUS) VALUES (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, senha.getTipo());
			stmt.setLong(2, senha.getNumero());
			stmt.setString(3, senha.getStatus());
			stmt.executeQuery();
			stmt.close();
		} catch (SQLException e ){
			throw new RuntimeException(e);
		}
	}
	
	public void atualiza(Senha senha){
		String sql = "UPDATE SENHA SET STATUS=? WHERE CODSENHA=? ";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1,senha.getStatus());
			stmt.setLong(2,senha.getId());
			stmt.execute();
			stmt.close();			
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Senha> getLista(){
		try {
			List<Senha> senhas = new ArrayList<Senha>();
			String sql = "SELECT  * FROM SENHA " +
				"WHERE STATUS = 'S' " +
				"ORDER BY TIPO DESC, NUMERO ASC " +
				"LIMIT 1";
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
			return senhas;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}

}
