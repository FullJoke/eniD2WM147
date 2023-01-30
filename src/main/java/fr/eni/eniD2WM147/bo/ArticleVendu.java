package fr.eni.eniD2WM147.bo;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleVendu {
	private int idArticle;
	private String nom;
	private String description;
	LocalDateTime debutEnchere;
	LocalDateTime finEnchere;
	private int prixInitial;
	private int prixVente;
	private int numCategorie;
	private String image;
	private List<Enchere> listeEnchere;
	private Categorie categorie;
	private Utilisateur utilisateur;
	private Retrait retrait;

	// etat vente est un char en SQL, utilisation du parse ?
	private String etatVente;
	
	
	public ArticleVendu(int idArticle, String nom, String description, LocalDateTime debutEnchere,
			LocalDateTime finEnchere, int prixInitial, int prixVente, int numCategorie, String etatVente,
			String image) {
		super();
		this.idArticle = idArticle;
		this.nom = nom;
		this.description = description;
		this.debutEnchere = debutEnchere;
		this.finEnchere = finEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.numCategorie = numCategorie;
		this.etatVente = etatVente;
		this.image = image;
	}

	public ArticleVendu(String nom, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere,
			int prixInitial, int prixVente, int numCategorie, String etatVente, String image) {
		super();
		this.nom = nom;
		this.description = description;
		this.debutEnchere = debutEnchere;
		this.finEnchere = finEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.numCategorie = numCategorie;
		this.etatVente = etatVente;
		this.image = image;
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDebutEnchere() {
		return debutEnchere;
	}

	public void setDebutEnchere(LocalDateTime debutEnchere) {
		this.debutEnchere = debutEnchere;
	}

	public LocalDateTime getFinEnchere() {
		return finEnchere;
	}

	public void setFinEnchere(LocalDateTime finEnchere) {
		this.finEnchere = finEnchere;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public int getNumCategorie() {
		return numCategorie;
	}

	public void setNumCategorie(int numCategorie) {
		this.numCategorie = numCategorie;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Enchere> getListeEnchere() {
		return listeEnchere;
	}

	public void setListeEnchere(List<Enchere> listeEnchere) {
		this.listeEnchere = listeEnchere;
	}
	

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	@Override
	public String toString() {
		return "ArticleVendu [idArticle=" + idArticle + ", nom=" + nom + ", description=" + description
				+ ", debutEnchere=" + debutEnchere + ", finEnchere=" + finEnchere + ", prixInitial=" + prixInitial
				+ ", prixVente=" + prixVente + ", numCategorie=" + numCategorie + ", etatVente=" + etatVente
				+ ", image=" + image + "]";
	}

}
