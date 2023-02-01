package fr.eni.eneDW2M147.dal;

public class EnchereDAOFactory {

	public static UtlisateurDAO getEnchereDao() {

		return new UtilisateurDaoJdbcImpl();

	}
}
