package it.dstech.bibliotecawebapp.modelli;

public class Scontrino {
private int idScontrino;
private String username;
private String data;
private double prezzoTotale;
public Scontrino(int idScontrino, String nome, String data, double prezzoTotale) {
	super();
	this.idScontrino = idScontrino;
	this.username = nome;
	this.data = data;
	this.prezzoTotale = prezzoTotale;
}
public int getIdScontrino() {
	return idScontrino;
}
public void setIdScontrino(int idScontrino) {
	this.idScontrino = idScontrino;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
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
