package it.dstech.bibliotecawebapp.modelli;

public class Acquisto {

	private int idLibro;
	private String titolo;
	private int quantita;
	private double prezzoTotale;
	private Acquisto(int idLibro, String titolo, int quantita, double prezzoTotale) {
		super();
		this.idLibro = idLibro;
		this.titolo = titolo;
		this.quantita = quantita;
		this.prezzoTotale = prezzoTotale;
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
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public double getPrezzoTotale() {
		return prezzoTotale;
	}
	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	@Override
	public String toString() {
		return "Acquisto [idLibro=" + idLibro + ", titolo=" + titolo + ", quantita=" + quantita + ", prezzoTotale="
				+ prezzoTotale + "]";
	}
	
}
