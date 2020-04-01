package it.dstech.bibliotecawebapp.modelli;

public class Scontrino {
private int idScontrino;
private Utente username;
private String data;
private double prezzoTotale;
public Scontrino(int idScontrino, Utente username, String data, double prezzoTotale) {
	super();
	this.idScontrino = idScontrino;
	this.username = username;
	this.data = data;
	this.prezzoTotale = prezzoTotale;
}
public int getIdScontrino() {
	return idScontrino;
}
public void setIdScontrino(int idScontrino) {
	this.idScontrino = idScontrino;
}
public Utente getUsername() {
	return username;
}
public void setUsername(Utente username) {
	this.username = username;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public double getPrezzoTotale() {
	return prezzoTotale;
}
public void setPrezzoTotale(double prezzoTotale) {
	this.prezzoTotale = prezzoTotale;
}
@Override
public String toString() {
	return "Scontrino [idScontrino=" + idScontrino + ", username=" + username + ", data=" + data + ", prezzoTotale="
			+ prezzoTotale + "]";
}

}
