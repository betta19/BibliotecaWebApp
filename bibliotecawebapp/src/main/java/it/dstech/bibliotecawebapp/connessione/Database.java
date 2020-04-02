package it.dstech.bibliotecawebapp.connessione;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	
	
private Connection connessione;

public Database() throws IOException, ClassNotFoundException, SQLException {
	Properties prop = new Properties();
	prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("file.properties"));
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		String password = prop.getProperty("db.password");
		String username = prop.getProperty("db.username");
		String url = "jdbc:mysql://"+prop.getProperty("db.url")+"?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false";

	
	this.connessione =  DriverManager.getConnection(url, username, password);

} public void close () throws SQLException {
	this.connessione.close();
}
 			
public void inserimentoUtente(String username, String password) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("insert into utente (username, password) values (?,?);");
	state.setString(1, username);
	state.setString(2, password);
	state.execute();
}

public boolean checkAccessoUtente(String username, String password) throws SQLException {
	PreparedStatement s = connessione.prepareStatement("select * from utente where username = ? and password = ? ;");
	s.setString(1, username);
	s.setString(2, password);
	ResultSet risultato = s.executeQuery();
	while (risultato.next()) {

		return true;
	}

	return false;
}
}
