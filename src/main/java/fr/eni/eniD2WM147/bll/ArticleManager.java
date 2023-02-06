package fr.eni.eniD2WM147.bll;

import java.time.LocalDateTime;
import java.util.List;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniD2WM147.businessException.BusinessException;
import fr.eni.eniD2WM147.dal.ArticleDAO;
import fr.eni.eniD2WM147.dal.EnchereDAOFactory;

public class ArticleManager {
	private ArticleDAO articleDAO;
	private static ArticleManager instance;

	private ArticleManager() {
		this.articleDAO = EnchereDAOFactory.getArticleDAO();
	}

	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
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
		validerInsert(article.getDescription(), article.getDebutEnchere(), article.getFinEnchere(),
				article.getPrixInitial(), bE);

		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		return articleDAO.insertArticle(article);
	}

	private void validerInsert(String description, LocalDateTime debutEnchere, LocalDateTime finEnchere, int prix,
			BusinessException businessException) {
		int jour = LocalDateTime.now().getDayOfMonth();
		int mois = LocalDateTime.now().getMonthValue();
		if (debutEnchere == null || debutEnchere.getDayOfMonth() < jour || debutEnchere.getMonthValue() < mois) {
			businessException.addMessage("La date est obligatoire et ne peut pas être dans le passé");
		}

		if (finEnchere == null || finEnchere.getDayOfMonth() < jour || finEnchere.getMonthValue() < mois) {
			businessException.addMessage("La date est obligatoire et ne peut pas être avant le début de l'enchère");
		}

		if (description == null || description.length() > 300) {
			businessException.addMessage("La description est obligatoire et ne peut pas dépasser 300 caractères");
		}
		if (prix < 0) {
			businessException.addMessage("Le prix est obligatoire et doit être positif");
		}
	}

	public Categorie selectCatByIdArt(int idArticle) {
		return articleDAO.selectCatByIdArt(idArticle);
	}

	public Enchere selectEnchereByIdArticle(int idArt) {
		return articleDAO.selectEnchereByIdArticle(idArt);
	}
}