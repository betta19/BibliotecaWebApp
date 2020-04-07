package it.dstech.bibliotecawebapp.modelli;

public class Utente {

	private String username;
	private String password;
	private boolean active;
	private String image;
	
	

	
	public Utente(String username, String password, boolean active, String image) {
		super();
		this.username = username;
		this.password = password;
		this.active = active;
		this.image = image;
	}





	public Utente(String username, String image) {
		super();
		this.username = username;
		this.image = image;
	}





	public Utente(String username, String password, String image) {
		super();
		this.username = username;
		this.password = password;
		this.image = image;
	}





	public Utente(String username, String password, boolean active) {
		super();
		this.username = username;
		this.password = password;
		this.active = active;
	}
	
	
	
	
	
	public Utente(String username) {
		super();
		this.username = username;
	}





	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

	public String getImage() {
		return image;
	}





	public void setImage(String image) {
		this.image = image;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + ", password=" + password + ", active=" + active + "]";
	}


}
