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

	public EnchereManager() {
		this.enchereDAO = EnchereDAOFactory.getEnchereDAO();
	}

	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}

//	public Enchere insertBid(LocalDateTime dateEnchere, int montantEnchere) throws BusinessException {
//
//		BusinessException bE = new BusinessException();
//
//		if (!bE.getListeMessage().isEmpty()) {
//			throw bE;
//		}
//
//	//	Enchere enchere = enchereDAO.insertBid(dateEnchere, montantEnchere);
//
//	//	return enchere;
//	}

	public Enchere bidArticle(LocalDateTime dateEnchere,int montantEnchere,Utilisateur utilisateur,ArticleVendu article) throws BusinessException {

		BusinessException bE = new BusinessException();

		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		Enchere enchere = enchereDAO.bidArticle(dateEnchere, montantEnchere, utilisateur, article);

		return enchere;

	}

}
