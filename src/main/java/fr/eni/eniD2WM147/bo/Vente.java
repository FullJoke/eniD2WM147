package fr.eni.eniD2WM147.bo;

import java.time.LocalDateTime;

public class Vente {
	private String nom;
	private String description;
	private String nomCategorie;
	private int meilleurOffre;
	private int miseAPrix;
	private LocalDateTime dateFinEnchere;
	private Retrait retrait;
	Utilisateur vendeur;

	// Constructeur
	public Vente(String nom, String description, String nomCategorie, int meilleurOffre, int miseAPrix,
			LocalDateTime dateFinEnchere, Retrait retrait, Utilisateur vendeur) {
		super();
		this.setNom(nom);
		this.setDescription(description);
		this.setNomCategorie(nomCategorie);
		this.setMeilleurOffre(meilleurOffre);
		this.setMiseAPrix(miseAPrix);
		this.setDateFinEnchere(dateFinEnchere);
		this.setRetrait(retrait);
		this.vendeur = vendeur;
	}

	// Getters & Setters
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

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public int getMeilleurOffre() {
		return meilleurOffre;
	}

	public void setMeilleurOffre(int meilleurOffre) {
		this.meilleurOffre = meilleurOffre;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public LocalDateTime getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setDateFinEnchere(LocalDateTime dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

}
