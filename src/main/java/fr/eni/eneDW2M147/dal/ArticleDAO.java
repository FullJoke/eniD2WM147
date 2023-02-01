package fr.eni.eneDW2M147.dal;

import java.util.List;

import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;

public interface ArticleDAO {

	public List<ArticleVendu> selectAllArticles() throws BusinessException;
}
