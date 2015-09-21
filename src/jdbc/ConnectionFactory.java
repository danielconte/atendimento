package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection(){
		try{
			return DriverManager.getConnection(
				"jdbc:postgresql://179.188.16.34/w1feedback1","w1feedback1","xpto1234"
					);
			
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}
