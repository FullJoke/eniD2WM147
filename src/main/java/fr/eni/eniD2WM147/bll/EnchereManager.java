package fr.eni.eniD2WM147.bll;

import java.time.LocalDateTime;

import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;
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

	public Enchere insertBid(LocalDateTime dateEnchere,int montantEnchere,Utilisateur utilisateur,ArticleVendu article) throws BusinessException {

		BusinessException bE = new BusinessException();

		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}

		Enchere enchereInsert = enchereDAO.insertBid(dateEnchere, montantEnchere,utilisateur,article);

		return enchereInsert;
	}

	public void bidArticle(int montantEnchere,int idUtilisateur,LocalDateTime now,int idArticle) throws BusinessException {

		BusinessException bE = new BusinessException();
		System.out.println("BLL insertion enchere");

		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		 enchereDAO.bidArticle(montantEnchere,idUtilisateur,now,idArticle);

		

	}

}
