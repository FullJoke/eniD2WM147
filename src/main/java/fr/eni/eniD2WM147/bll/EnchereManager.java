package fr.eni.eniD2WM147.bll;

import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eneDW2M147.dal.UtlisateurDAO;
import fr.eni.eneDW2M147.dal.EnchereDAOFactory;

import fr.eni.eniD2WM147.bo.Utilisateur;

public class EnchereManager {

	private UtlisateurDAO enchereDAO;

	public EnchereManager() {
		this.enchereDAO = EnchereDAOFactory.getEnchereDao();
	}

	public Utilisateur getUserByEmailAndPassword(String id, String mdp) throws BusinessException {
		BusinessException bE = new BusinessException();

		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		return enchereDAO.getUserByEmailAndPassword(id, mdp);

	}

	public Utilisateur insertUtilisateur(String pseudo, String nom, String prenom, String email, String tel, String rue,
			String codePostal, String ville, int i, boolean b, String mdp) throws BusinessException {

		BusinessException bE = new BusinessException();

		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		Utilisateur user = enchereDAO.insertUtilisateur(pseudo, nom, prenom, email, tel, rue, codePostal, ville, i, b, mdp);
		return user;
	}

}
