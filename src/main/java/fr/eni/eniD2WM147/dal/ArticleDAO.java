package fr.eni.eniD2WM147.dal;

import java.util.List;

import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniD2WM147.bo.Retrait;
import fr.eni.eniD2WM147.businessException.BusinessException;

public interface ArticleDAO {

	public List<ArticleVendu> selectAllArticles() throws BusinessException;

	public List<Categorie> selectAllCategories() throws BusinessException;

	public ArticleVendu selectArticleById(int idArticle) throws BusinessException;

	public Categorie selectCatByIdArt(int idArticle);

	public Retrait insertRetrait(Retrait retrait) throws BusinessException;
	
	public Enchere selectEnchereByIdArticle(int idArt);

	public ArticleVendu insertArticle(ArticleVendu article) throws BusinessException;

	public List<ArticleVendu> listeArticleAccueil(int catChoisie, String filtreAchat, String enchereOuv,
			String mesEncheres, String encheresRemportees, String ventesEnCours, String ventesNonDebutees,
			String ventesTerminees, int idSession);


}
