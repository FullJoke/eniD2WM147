package fr.eni.eniD2WM147.bll;

import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eneDW2M147.dal.EnchereDAO;
import fr.eni.eneDW2M147.dal.EnchereDAOFactory;

import fr.eni.eniD2WM147.bo.Utilisateur;

public class EnchereManager {

	private EnchereDAO enchereDAO;

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

	public Utilisateur insertUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, int credit, boolean administrateur) throws BusinessException {
		BusinessException bE = new BusinessException();

		if (bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit,
				administrateur);
		this.enchereDAO.insertUtilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit,
				administrateur);
		return user;

	}

}
