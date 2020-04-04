package it.dstech.bibliotecawebapp.connessione;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import it.dstech.bibliotecawebapp.modelli.Libro;
import it.dstech.bibliotecawebapp.modelli.LibroVenduto;
import it.dstech.bibliotecawebapp.modelli.Scontrino;
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
		int idLibro = risultato.getInt("idLibro");
		String titolo = risultato.getString("titolo");
		String autore = risultato.getString("autore");
		double prezzo = risultato.getDouble("prezzo");
		int disponibilita = risultato.getInt("disponibilita");
		int quantita = risultato.getInt("quantita");
		Libro libro = new Libro(idLibro, titolo, autore, prezzo, disponibilita, quantita);
		listaLibri.add(libro);
	} return listaLibri;
	
}
public int creaScontrino(String username) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("insert into scontrino (idScontrino, username, data) values (?,?, ?);");
	java.util.Date data = new java.util.Date();
	   DateFormat formato = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
       int idScontrino = (int) (Math.random() * 1000 + Math.random() * 1000);
       state.setInt(1, idScontrino);
       state.setString(2, username);
       state.setString(3, formato.format(data));
       state.execute();
       return idScontrino;
} 
public boolean controlloQuantitaLibri (String titolo, int quantita, List<Libro> lista) throws SQLException {
	
	for (Libro libro : lista) {
		
	if (titolo.equalsIgnoreCase(libro.getTitolo()) && quantita > libro.getDisponibilita()) {
		return false;
	}
	} return true;
	
}
public void inserimentoTabellaAcquisto (String titolo, int quantita, String username, int idScontrino) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("insert into acquisto (titolo, quantita, username, idscontrino) values (?, ?, ?, ?);");
	state.setString(1, titolo);
	state.setInt(2, quantita);
	state.setString(3, username);
	state.setInt(4, idScontrino);
	state.execute();
} 
public void updateQuantitaLibri (String titolo, int quantita) throws SQLException {
	List<Libro> lista = stampaListaLibri();
	for (int i = 0; i < lista.size(); i++) {
		if (lista.get(i).getTitolo().equalsIgnoreCase(titolo)) {
			int nuovaQuantita = lista.get(i).getQuantita() - quantita;
					int nuovaDisponibilita = lista.get(i).getDisponibilita() - quantita;
					lista.get(i).setQuantita(nuovaQuantita);
					lista.get(i).setDisponibilita(nuovaDisponibilita);
					updateTabellaLibro(titolo, nuovaQuantita, nuovaDisponibilita);
					
		}
	}
} public void updateTabellaLibro (String titolo, int quantita, int disponibilita) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("update libro set quantita= ? and disponibilita=? where titolo = ?;");
	state.setInt(1, quantita);
	state.setInt(2, disponibilita);
	state.setString(3, titolo);
	state.execute();
}
public  double getPrezzo( int idScontrino) throws SQLException {
    double costo = 0;
    String query = "select acquisto.quantita,libro.prezzo from acquisto inner join libro on acquisto.titolo=libro.titolo where acquisto.idScontrino=?;";
    PreparedStatement statement = connessione.prepareStatement(query);
    statement.setInt(1, idScontrino);

    ResultSet risultato = statement.executeQuery();
    while (risultato.next()) {

        costo = costo + (risultato.getInt(1) * risultato.getDouble(2));

    }
    return costo;

}


public boolean totaleScontrino(int idScontrino, double spesa) throws SQLException {
	String query = "update scontrino set prezzoTotale=? where idScontrino=?;";
    PreparedStatement statement = connessione.prepareStatement(query);
    statement.setDouble(1, spesa);
    statement.setInt(2, idScontrino);
    statement.execute();
    return true;
	
}
	public  List<Scontrino> stampaScontrini(String username) throws SQLException {
		PreparedStatement statement = connessione.prepareStatement("select * from scontrino where username = ?;");
		statement.setString(1, username);

		ResultSet risultatoQuery = statement.executeQuery();
		List<Scontrino> elenco = new ArrayList<>();
		while (risultatoQuery.next()) {
			int idScontrino = risultatoQuery.getInt("idScontrino");
			String data = risultatoQuery.getString("data");
			double spesa = risultatoQuery.getDouble("prezzoTotale");

			Scontrino scontrino = new Scontrino(idScontrino, username, data, spesa);
			elenco.add(scontrino);

		}

		return elenco;

	}
	public  List<LibroVenduto> stampaProdottiScontrino(int idScontrino)
			throws SQLException {
		PreparedStatement statement = connessione
				.prepareStatement("select username, titolo, quantita from acquisto where idScontrino = ?;");
		statement.setInt(1, idScontrino);

		ResultSet risultatoQuery = statement.executeQuery();
		List<LibroVenduto> elenco = new ArrayList<>();
		while (risultatoQuery.next()) {
			String username = risultatoQuery.getString("username");
			String titolo = risultatoQuery.getString("titolo");
			int quantita = risultatoQuery.getInt("quantita");
			

			LibroVenduto p = new LibroVenduto(idScontrino, username, titolo, quantita);
			elenco.add(p);

		}

		return elenco;

	}
}
