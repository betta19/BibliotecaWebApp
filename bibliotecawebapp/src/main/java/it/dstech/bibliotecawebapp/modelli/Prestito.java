package it.dstech.bibliotecawebapp.modelli;

public class Prestito {
private int idLibro;
private String titolo;
private String dataAffitto;
private Utente username;
public Prestito(int idLibro, String titolo, String dataAffitto, Utente username) {
	super();
	this.idLibro = idLibro;
	this.titolo = titolo;
	this.dataAffitto = dataAffitto;
	this.username = username;
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
public String getDataAffitto() {
	return dataAffitto;
}
public void setDataAffitto(String dataAffitto) {
	this.dataAffitto = dataAffitto;
}
public Utente getUsername() {
	return username;
}
public void setUsername(Utente username) {
	this.username = username;
}
@Override
public String toString() {
	return "Prestito [idLibro=" + idLibro + ", titolo=" + titolo + ", dataAffitto=" + dataAffitto + ", username="
			+ username + "]";
}

}