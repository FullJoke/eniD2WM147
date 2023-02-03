package fr.eni.eniDW2M147.dal;


import java.util.List;

import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniDW2M147.businessException.BusinessException;

public interface ArticleDAO {

	public List<ArticleVendu> selectAllArticles() throws BusinessException;

	public List<ArticleVendu> selectArticlesByCat(int noCategorie) throws BusinessException;

	public List<Categorie> selectAllCategories() throws BusinessException;

	public ArticleVendu insertArticle(ArticleVendu article) throws BusinessException;

	public ArticleVendu selectArticleById(int idArticle) throws BusinessException;

}
