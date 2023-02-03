package fr.eni.eniD2WM147.bll;

import java.util.List;

import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniDW2M147.businessException.BusinessException;
import fr.eni.eniDW2M147.dal.ArticleDAO;
import fr.eni.eniDW2M147.dal.EnchereDAOFactory;
import fr.eni.eniDW2M147.dal.UtilisateurDAO;

public class EnchereManager {

	private UtilisateurDAO utilisateurDAO;

	private ArticleDAO articleDAO;

	public EnchereManager() {
		this.utilisateurDAO = EnchereDAOFactory.getEnchereDao();

		this.articleDAO = EnchereDAOFactory.getArticleDAO();

	}

	// Utilisateurs
	public Utilisateur getUserByEmailAndPassword(String id, String mdp) throws BusinessException {
		BusinessException bE = new BusinessException();

		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		return utilisateurDAO.getUserByEmailAndPassword(id, mdp);

	}

	public Utilisateur insertUtilisateur(String pseudo, String nom, String prenom, String email, String tel, String rue,
			String codePostal, String ville, int i, boolean b, String mdp) throws BusinessException {

		BusinessException bE = new BusinessException();

		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}

		Utilisateur user = utilisateurDAO.insertUtilisateur(pseudo, nom, prenom, email, tel, rue, codePostal, ville, i,
				b, mdp);

		return user;
	}

	public Utilisateur updateUserProfil(String pseudo, String nom, String prenom, String email, String tel, String rue,
			String codePostal, String ville, String mdp, int credit, int idUtilisateur) throws BusinessException {
		BusinessException bE = new BusinessException();
		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}

		Utilisateur user = utilisateurDAO.updateUserProfil(pseudo, nom, prenom, email, tel, rue, codePostal, ville, mdp,
				credit, idUtilisateur);

		return user;

	}

	public void deleteAll(int idUtilisateur) throws BusinessException {
		BusinessException bE = new BusinessException();
		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		utilisateurDAO.deleteAll(idUtilisateur);
	}
	
	public Utilisateur getUtilisateurByEnchere(int idArticle) throws BusinessException {
		System.out.println("BLL - idArticle : " + idArticle);
		return utilisateurDAO.selectByEnchere(idArticle);
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	// Articles
	public List<ArticleVendu> selectAllArticles() throws BusinessException {
		BusinessException bE = new BusinessException();
		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}

		return articleDAO.selectAllArticles();
	}

	public List<ArticleVendu> selectArticlesByCat(int noCategorie) throws BusinessException {
		BusinessException bE = new BusinessException();
		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		return articleDAO.selectArticlesByCat(noCategorie);
	}
	
	public ArticleVendu selectArticleById(int idArticle) throws BusinessException {
		System.out.println("BLL - idArticle selectionné : " + idArticle);
		return articleDAO.selectArticleById(idArticle);
	}

	public List<Categorie> selectAllCat() throws BusinessException {
		BusinessException bE = new BusinessException();
		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		return articleDAO.selectAllCategories();
	}
	
	public Categorie selectCatByIdArt(int idArticle) {
		return articleDAO.selectCatByIdArt(idArticle);
	}

}
