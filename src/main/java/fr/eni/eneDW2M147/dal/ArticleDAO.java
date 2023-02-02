package fr.eni.eneDW2M147.dal;

import java.time.LocalDateTime;
import java.util.List;

import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;

public interface ArticleDAO {

	public List<ArticleVendu> selectAllArticles() throws BusinessException;
	
	public List<ArticleVendu> selectArticlesByCat(int noCategorie) throws BusinessException;
	
	public List<Categorie> selectAllCategories() throws BusinessException;
	
	public void insertArticle(String nom, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere,
			int prixInitial, int prixVente, String etatVente, String image) throws BusinessException;
 
	public int selectArticleById(int idArticle) throws BusinessException;
	
	
}
