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
	private String image;
	
	private String etatVente;
	private List<Enchere> listeEnchere;
	private Utilisateur utilisateur;
	private Retrait retrait;
	private Categorie categorie;

	// Constructeurs
	public ArticleVendu(int idArticle, String nom, String description, LocalDateTime debutEnchere,
			LocalDateTime finEnchere, int prixInitial, int prixVente, String etatVente, String image,
			Utilisateur utilisateur) {
		this.idArticle = idArticle;
		this.nom = nom;
		this.description = description;
		this.debutEnchere = debutEnchere;
		this.finEnchere = finEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.image = image;
		this.utilisateur = utilisateur;
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
		this.etatVente = etatVente;
		this.image = image;
	}



	public ArticleVendu() {
		
	}

	public ArticleVendu(String nom, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere,
			int prixInitial, String image, Utilisateur utilisateur, Categorie categorie) {
		super();
		this.nom = nom;
		this.description = description;
		this.debutEnchere = debutEnchere;
		this.finEnchere = finEnchere;
		this.prixInitial = prixInitial;
		this.image = image;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
	}

	public ArticleVendu(String art, String description2, LocalDateTime dateDebut, LocalDateTime dateFin, String prix,
			Utilisateur numUtilisateur, String listeCat, String string, Object object) {
		// TODO Auto-generated constructor stub
	}

	// Getters & Setters
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
	

	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	// Méthodes
	@Override
	public String toString() {
		return "ArticleVendu [idArticle=" + idArticle + ", nom=" + nom + ", description=" + description
				+ ", debutEnchere=" + debutEnchere + ", finEnchere=" + finEnchere + ", prixInitial=" + prixInitial
				+ ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", image=" + image + "]";
	}

}
