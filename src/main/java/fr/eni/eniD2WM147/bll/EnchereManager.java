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

		validerID(id, bE);
		validerMDP(mdp, bE);
		
		if(!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		
		return enchereDAO.getUserByEmailAndPassword(id, mdp);

	}

	private void validerID(String id, BusinessException businessException) throws BusinessException {
		enchereDAO.selectAllUtilisateurByPseudoEmail();
		if (enchereDAO.selectAllUtilisateurByPseudoEmail().isEmpty() || enchereDAO.selectAllUtilisateurByPseudoEmail().contains(id)) {
			businessException.addMessage("L'identifiant n'est pas valide");
		}

	}

	private void validerMDP(String mdp, BusinessException businessException) throws BusinessException {
		enchereDAO.selectAllUtilisateurByPseudoEmail();
		if (enchereDAO.selectAllUtilisateurByPseudoEmail().isEmpty() || enchereDAO.selectAllUtilisateurByPseudoEmail().contains(mdp)) {
			businessException.addMessage("Le mot de passe n'est pas valide");
		}
	}

}
