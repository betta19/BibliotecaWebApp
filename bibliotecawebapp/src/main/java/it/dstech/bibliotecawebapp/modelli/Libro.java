package it.dstech.bibliotecawebapp.modelli;

public class Libro {
	
private int idLibro;
private String titolo;
private String autore;
private double prezzo;
private int disponibilita;
private int quantita;

public Libro(int idLibro, String titolo, String autore, double prezzo, int disponibilita, int quantita) {
	super();
	this.idLibro = idLibro;
	this.titolo = titolo;
	this.autore = autore;
	this.prezzo = prezzo;
	this.disponibilita = disponibilita;
	this.quantita = quantita;
}
public int getIdLibro() {
	return idLibro;
}
public void setIdLibro(int idLibro) {
	this.idLibro = idLibro;
}
public String getTitolo() {
	return titolo;
}
public void setTitolo(String titolo) {
	this.titolo = titolo;
}
public String getAutore() {
	return autore;
}
public void setAutore(String autore) {
	this.autore = autore;
}
public double getPrezzo() {
	return prezzo;
}
public void setPrezzo(double prezzo) {
	this.prezzo = prezzo;
}
public int getDisponibilita() {
	return disponibilita;
}
public void setDisponibilita(int disponibilita) {
	this.disponibilita = disponibilita;
}
public int getQuantita() {
	return quantita;
}
public void setQuantita(int quantita) {
	this.quantita = quantita;
}
@Override
public String toString() {
	return "Libro [idLibro=" + idLibro + ", titolo=" + titolo + ", autore=" + autore + ", prezzo=" + prezzo
			+ ", disponibilita=" + disponibilita + ", quantita=" + quantita + "]";
}

}
