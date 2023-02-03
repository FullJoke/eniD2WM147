package fr.eni.eniD2WM147.bll;

import fr.eni.eniDW2M147.dal.EnchereDAO;
import fr.eni.eniDW2M147.dal.EnchereDAOFactory;

public class EnchereManager {

	private EnchereDAO enchereDAO;

	public EnchereManager() {
		this.enchereDAO = EnchereDAOFactory.getEnchereDAO();

	}

}
