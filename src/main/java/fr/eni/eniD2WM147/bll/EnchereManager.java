package fr.eni.eniD2WM147.bll;

import java.util.List;

import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eneDW2M147.dal.ArticleDAO;
import fr.eni.eneDW2M147.dal.EnchereDAOFactory;
import fr.eni.eneDW2M147.dal.UtilisateurDAO;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniD2WM147.bo.Utilisateur;

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

	public void deleteUser(int idUtilisateur) throws BusinessException {
		utilisateurDAO.deleteUser(idUtilisateur);
	}

	// Articles
	public List<ArticleVendu> selectAllArticles() throws BusinessException {
		BusinessException bE = new BusinessException();
		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}

		return articleDAO.selectAllArticles();
	}

 	public List<ArticleVendu> selectArticlesByCat(int noCategorie) throws BusinessException {
		return articleDAO.selectArticlesByCat(noCategorie);
	}
 	
 	public List<Categorie> selectAllCat() throws BusinessException{
 		BusinessException bE = new BusinessException();
		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		return articleDAO.selectAllCategories();
 	}

}
