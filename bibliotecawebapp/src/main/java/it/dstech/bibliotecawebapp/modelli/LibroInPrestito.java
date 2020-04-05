package it.dstech.bibliotecawebapp.modelli;

public class LibroInPrestito {

	private String titolo;
	private String username;
	private int idTessera;
	private int quantita;
	private String dataAffitto;
	
	
	public LibroInPrestito(String titolo, String username, int idTessera, int quantita, String dataAffitto) {
		super();
		this.titolo = titolo;
		this.username = username;
		this.idTessera = idTessera;
		this.quantita = quantita;
		this.dataAffitto = dataAffitto;
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
				+ ", quantita=" + quantita + ", dataAffitto=" + dataAffitto + "]";
	}
	
	}