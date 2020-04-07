package it.dstech.bibliotecawebapp.modelli;

public class LibroInPrestito {

	private String titolo;
	private String username;
	private int idTessera;
	private int quantita;
	private String dataAffitto;
	private String dataFine;
	
	
	public LibroInPrestito(String titolo, String username, int idTessera, int quantita, String dataAffitto,
			String dataFine) {
		super();
		this.titolo = titolo;
		this.username = username;
		this.idTessera = idTessera;
		this.quantita = quantita;
		this.dataAffitto = dataAffitto;
		this.dataFine = dataFine;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDataFine() {
		return dataFine;
	}
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	public String getDataAffitto() {
		return dataAffitto;
	}
	public void setDataAffitto(String dataAffitto) {
		this.dataAffitto = dataAffitto;
	}
	public String getUsername() { 
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getIdTessera() {
		return idTessera;
	}
	public void setIdTessera(int idTessera) {
		this.idTessera = idTessera;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	@Override
	public String toString() {
		return "LibroInPrestito [titolo=" + titolo + ", username=" + username + ", idTessera=" + idTessera
				+ ", quantita=" + quantita + ", dataAffitto=" + dataAffitto + ", dataFine=" + dataFine + "]";
	}
	
	
	}