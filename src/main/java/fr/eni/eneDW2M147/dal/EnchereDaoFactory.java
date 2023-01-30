package fr.eni.eneDW2M147.dal;

public interface EnchereDaoFactory {

	public static EnchereDAO getEnchereDao() {

		return new EnchereDaoJdbcImpl();

	}

}
