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
import it.dstech.bibliotecawebapp.modelli.LibroInPrestito;
import it.dstech.bibliotecawebapp.modelli.LibroVenduto;

import it.dstech.bibliotecawebapp.modelli.Scontrino;
import it.dstech.bibliotecawebapp.modelli.Tessera;
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

public int checkQuantita(String titolo) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("select * from libro where titolo = ?;");
	state.setString(1, titolo);
	ResultSet risultato = state.executeQuery();
	while (risultato.next()) {
		return risultato.getInt("quantita");
	}

	return 0;
}

public int checkDisponibilita(String titolo) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("select * from libro where titolo = ?;");
	state.setString(1, titolo);
	ResultSet risultato = state.executeQuery();
	while (risultato.next()) {
		return risultato.getInt("disponibilita");
	}

	return 0;
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
} 
public void updateLibri(String titolo, int quantita, int qtV, int dsV) throws SQLException {
	String query3 = "update libro set quantita=?, disponibilita=? where titolo=?;";
	PreparedStatement statement3 = connessione.prepareStatement(query3);
	statement3.setInt(1, qtV + quantita);
	statement3.setInt(2, dsV + quantita);
	statement3.setString(3, titolo);
	statement3.execute();
}
public void updateTabellaLibro (String titolo, int quantita, int disponibilita) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("update libro set quantita= ?, disponibilita=? where titolo = ?;");
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
		PreparedStatement statement = connessione.prepareStatement("select * from scontrino where username = ? ORDER BY STR_TO_DATE(data,\"%d/%m/%Y\") desc;");
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
	public int creaTessera(String username) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("insert into tessera (idTessera, username, dataAffitto) values (?,?,?);");
	java.util.Date data = new java.util.Date();
	   DateFormat formato = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
       int idTessera = (int) (Math.random() * 2000 + Math.random() * 1000);
       state.setInt(1, idTessera);
       state.setString(2, username);
       state.setString(3, formato.format(data));
       state.execute();
       return idTessera;
}
	public void inserimentoTabellaAffitto(String titolo, String username, int idTessera, int quantita) throws SQLException {
		PreparedStatement state = connessione.prepareStatement("insert into prestito (titolo, username, idTessera, quantita, dataAffitto) values (?, ?, ?, ?, ?);");
		java.util.Date data = new java.util.Date();
		DateFormat formato = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
		state.setString(1, titolo);
		state.setString(2, username);
		state.setInt(3, idTessera);
		state.setInt(4, quantita);
		state.setString(5, formato.format(data));
		state.execute();
	}
	public void updateDisponibilitaLibri(String titolo, int quantita) throws SQLException {
		List<Libro> lista = stampaListaLibri();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getTitolo().equalsIgnoreCase(titolo)) {
						int nuovaDisponibilita = lista.get(i).getDisponibilita() - quantita;
				
						lista.get(i).setDisponibilita(nuovaDisponibilita);
						updateTabellaLibroSuDisp(titolo, nuovaDisponibilita);		
			} 

		
	} 
}  public void updateTabellaLibroSuDisp (String titolo, int disponibilita) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("update libro set  disponibilita=? where titolo = ?;");
	state.setInt(1, disponibilita);
	state.setString(2, titolo);
	state.execute();
}

public List<Tessera> stampaPrestiti(String username) throws SQLException {
	PreparedStatement statement = connessione.prepareStatement("select * from tessera where username = ? ORDER BY STR_TO_DATE(data,\"%d/%m/%Y\") desc;");
	statement.setString(1, username);

	ResultSet risultatoQuery = statement.executeQuery();
	List<Tessera> elenco = new ArrayList<>();
	while (risultatoQuery.next()) {
		
		int idTessera = risultatoQuery.getInt("idTessera");

		String dataAffitto = risultatoQuery.getString("dataAffitto");

		Tessera tessera = new Tessera(idTessera, username, dataAffitto);
		elenco.add(tessera);

	}

	return elenco;
}
public  List<LibroInPrestito> stampaLibriInPrestito(int idTessera)
		throws SQLException {
	PreparedStatement statement = connessione
			.prepareStatement("select username, titolo, quantita, dataAffitto, dataFine from prestito where idTessera = ?;");
	statement.setInt(1, idTessera);

	ResultSet risultatoQuery = statement.executeQuery();
	List<LibroInPrestito> elenco = new ArrayList<>();
	
	while (risultatoQuery.next()) {
		String username = risultatoQuery.getString("username");
		String titolo = risultatoQuery.getString("titolo");
		int quantita = risultatoQuery.getInt("quantita");
		String dataAffitto = risultatoQuery.getString("dataAffitto");
		String dataFine= risultatoQuery.getString("dataFine");
		

		LibroInPrestito l = new LibroInPrestito(titolo, username, idTessera, quantita, dataAffitto, dataFine);
		elenco.add(l);

	}

	return elenco;

}
public void insertLibro(Libro l) throws SQLException {
	PreparedStatement statement = connessione.prepareStatement(
			"insert into libro (titolo, autore, prezzo, disponibilita, quantita) values (?,?,?,?,?);");
	statement.setString(1, l.getTitolo());
	statement.setString(2, l.getAutore());
	statement.setDouble(3, l.getPrezzo());
	statement.setInt(4, l.getDisponibilita());
	statement.setInt(5, l.getQuantita());
	statement.execute();
}

public List<Libro> getLibri(Libro l) throws SQLException, ClassNotFoundException {
	insertLibro(l);
	List<Libro> elenco = stampaListaLibri();

	return elenco;

}
public List<LibroInPrestito> ordinaData() throws SQLException {
	PreparedStatement state = connessione
			.prepareStatement("select *  from prestito ORDER BY STR_TO_DATE(dataAffitto,\"%d/%m/%Y\") asc;");
	ResultSet risultato = state.executeQuery();
	List<LibroInPrestito> lista = new ArrayList<>();
	while (risultato.next()) {
		LibroInPrestito l  = new LibroInPrestito( risultato.getString("titolo"), risultato.getString("username"), risultato.getInt("idTessera"), risultato.getInt("quantita"),
				risultato.getString("dataAffitto"), risultato.getString("dataFine"));
		lista.add(l);
	}
	return lista;
}
public List<LibroVenduto> stampaLibriVenduti() throws SQLException {
	PreparedStatement state = connessione.prepareStatement("select * from acquisto;");
	ResultSet risultato = state.executeQuery();
	List<LibroVenduto> lista = new ArrayList<>();
	while (risultato.next()) {
		LibroVenduto a = new LibroVenduto(risultato.getInt("idScontrino"), risultato.getString("username"),
				risultato.getString("titolo"), risultato.getInt("quantita"));
		lista.add(a);
	}
	return lista;
}

public List<LibroInPrestito> stampaLibriPrestati() throws SQLException {
	PreparedStatement state = connessione.prepareStatement("select * from prestito;");
	ResultSet risultato = state.executeQuery();
	List<LibroInPrestito> lista = new ArrayList<>();
	while (risultato.next()) {
		LibroInPrestito l = new LibroInPrestito(risultato.getString("titolo"), risultato.getString("username"), risultato.getInt("idTessera"),
				risultato.getInt("quantita"), risultato.getString("dataAffitto"), risultato.getString("dataFine"));
		lista.add(l);
	}
	return lista;
} 
public void cancellaScontrinoVuoto(int idScontrino, String username) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("DELETE from scontrino where idScontrino = ? and username = ?;");
	state.setInt(1, idScontrino);
	state.setString(2, username);
	state.execute();
}
public void cancellaAcquistoVuoto(int idScontrino, String username) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("DELETE from acquisto where idScontrino = ? and username = ?;");
	state.setInt(1, idScontrino);
	state.setString(2, username);
	state.execute();
}

public void cancellaPrestitoVuoto(int idTessera, String username) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("DELETE from prestito where idTessera = ? and username = ?;");
	state.setInt(1, idTessera);
	state.setString(2, username);
	state.execute();
}
public void cancellaTesseraVuoto(int idTessera, String username) throws SQLException {
	PreparedStatement state = connessione.prepareStatement("DELETE from tessera where idTessera = ? and username = ?;");
	state.setInt(1, idTessera);
	state.setString(2, username);
	state.execute();
}

}
