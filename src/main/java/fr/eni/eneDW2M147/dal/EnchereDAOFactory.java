package fr.eni.eneDW2M147.dal;

public class EnchereDAOFactory {

	public static UtilisateurDAO getEnchereDao() {

		return new UtilisateurDaoJdbcImpl();

	}

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
}
