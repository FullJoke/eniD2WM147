package fr.eni.eniD2WM147.dal;

public class EnchereDAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {

		return new UtilisateurDaoJdbcImpl();

	}

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}

	public static EnchereDAO getEnchereDAO() {

		return new EnchereDaoJdbcImpl();
	}
}
