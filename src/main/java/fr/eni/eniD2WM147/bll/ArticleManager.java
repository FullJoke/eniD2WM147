package fr.eni.eniD2WM147.bll;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniD2WM147.businessException.BusinessException;
import fr.eni.eniD2WM147.dal.ArticleDAO;
import fr.eni.eniD2WM147.dal.EnchereDAOFactory;


public class ArticleManager {
	private ArticleDAO articleDAO;

	public ArticleManager() {
		this.articleDAO = EnchereDAOFactory.getArticleDAO();
	}

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
		return articleDAO.selectArticleById(idArticle);
	}

	public List<Categorie> selectAllCat() throws BusinessException {
		BusinessException bE = new BusinessException();
		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		return articleDAO.selectAllCategories();
	}

	public ArticleVendu insert(ArticleVendu article) throws BusinessException {
		BusinessException bE = new BusinessException();
		validerDebutEnchere(article.getDebutEnchere(), bE);
		validerFinEnchere(article.getDebutEnchere(), article.getFinEnchere(), bE);
		validerDescription(article.getDescription(), bE);
		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		return articleDAO.insertArticle(article);

	}

	private void validerDebutEnchere(LocalDateTime debutEnchere, BusinessException businessException) {
		if (debutEnchere == null || debutEnchere.isAfter(LocalDateTime.now())) {
			businessException.addMessage("La date est obligatoire et ne peut pas être dans le futur");
		}
	}

	private void validerFinEnchere(LocalDateTime debutEnchere, LocalDateTime finEnchere,
			BusinessException businessException) {
		if (finEnchere == null || finEnchere.isBefore(debutEnchere)) {
			businessException.addMessage("La date est obligatoire et ne peut pas être avant le début de l'enchère");
		}
	}

	private void validerDescription(String description, BusinessException businessException) {
		if (description == null || description.length() > 300) {
			businessException.addMessage("La description est obligatoire et ne peut pas dépasser 300 caractères");
		}
	}

	public Categorie selectCatByIdArt(int idArticle) {
		return articleDAO.selectCatByIdArt(idArticle);
	}

}
