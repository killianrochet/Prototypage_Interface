package Model;

public class Utilisateur {
	public String Nom ;
	public String Prenom;
	public String Date_naissance;
	public String email ;
	public String NumeroTel ;
	public String Adresse ;
	
	public Utilisateur(String Nom,String Prenom, String Date_naissance, String email, String NumeroTel , String Adresse)
	{
		this.Nom = Nom;
		this.Prenom = Prenom;
		this.Date_naissance = Date_naissance ;
		this.email = email ;
		this.NumeroTel = NumeroTel ;
		this.Adresse = Adresse ;
	}

	public String getNumeroTel() {
		return NumeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		NumeroTel = numeroTel;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getDate_naissance() {
		return Date_naissance;
	}

	public void setDate_naissance(String date_naissance) {
		Date_naissance = date_naissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
}
