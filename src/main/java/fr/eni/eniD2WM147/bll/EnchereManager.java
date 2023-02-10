package fr.eni.eniD2WM147.bll;

import fr.eni.eniD2WM147.dal.EnchereDAO;
import fr.eni.eniD2WM147.dal.EnchereDAOFactory;
public class EnchereManager {

	private EnchereDAO enchereDAO;
	private static EnchereManager instance;

	private EnchereManager() {
		this.enchereDAO = EnchereDAOFactory.getEnchereDAO();
	}

	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}


	public void enchereUpdate(int idSession, int myOffer, int idArticle, int newCredits) {
		System.out.println("BLL - enchereUpdate");
		enchereDAO.enchereUpdate(idSession, myOffer, idArticle, newCredits);
	}

	public void enchereInsert(int idSession, int myOffer, int idArticle, int newCredits) {
		System.out.println("BLL - enchereInsert");
		enchereDAO.enchereInsert(idSession, myOffer, idArticle, newCredits);
	}

}

