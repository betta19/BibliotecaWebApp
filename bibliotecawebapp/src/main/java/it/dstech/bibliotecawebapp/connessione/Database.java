package it.dstech.bibliotecawebapp.connessione;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import it.dstech.bibliotecawebapp.modelli.Libro;
import it.dstech.bibliotecawebapp.modelli.Utente;

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
/*
public boolean checkAccessoUtente(String username, String password) throws SQLException {
	PreparedStatement s = connessione.prepareStatement("select * from utente where username = ? and password = ? ;");
	s.setString(1, username);
	s.setString(2, password);
	ResultSet risultato = s.executeQuery();
	while (risultato.next()) {

		return true;
	}

	return false;
}*/

public boolean checkRegistraUtente(String username) throws SQLException {
	PreparedStatement s = connessione.prepareStatement("select * from utente where username = ?;");
	s.setString(1, username);
	ResultSet risultato = s.executeQuery();
	while (risultato.next()) {

		return true;
	}

	return false;
}

public Utente getUtente(String username, String password) throws SQLException {
	PreparedStatement statement = connessione
			.prepareStatement("select * from utente where username = ? and password = ?");
	statement.setString(1, username);
	statement.setString(2, password);
	ResultSet executeQuery = statement.executeQuery();
	while (executeQuery.next()) {
//		String u = executeQuery.getString(1);
		String u = executeQuery.getString("username");
		String p = executeQuery.getString("password");
		boolean ac = executeQuery.getBoolean("active");
		
		return new Utente(u, p, ac);

	}
	return null;
}

public void validaUtente(String username) throws SQLException {
	PreparedStatement prepareStatement = this.connessione.prepareStatement("UPDATE utente SET active = ? WHERE (username = ?);");
	prepareStatement.setBoolean(1, true);
	prepareStatement.setString(2, username);
	prepareStatement.execute();
	

	
	
}


public void inserimentoLibro(String titolo, String autore, double prezzo, int disponibilita, int quantita) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("insert into libro (titolo, autore, prezzo, disponibilita, quantita) values (?,?, ?, ?, ?);");
	state.setString(1, titolo);
	state.setString(2, autore);
	state.setDouble(3, prezzo);
	state.setInt(4, disponibilita);
	state.setInt(5, quantita);
	state.execute();
} 
public List<Libro> stampaListaLibri () throws SQLException {
	PreparedStatement state = connessione.prepareStatement("select * from libro;");
	ResultSet risultato = state.executeQuery();
	List<Libro> listaLibri = new ArrayList<>();
	while (risultato.next()) {
		String titolo = risultato.getString("titolo");
		String autore = risultato.getString("autore");
		double prezzo = risultato.getDouble("prezzo");
		int disponibilita = risultato.getInt("disponibilita");
		int quantita = risultato.getInt("quantita");
		Libro libro = new Libro(titolo, autore, prezzo, disponibilita, quantita);
		listaLibri.add(libro);
	} return listaLibri;
}
}
