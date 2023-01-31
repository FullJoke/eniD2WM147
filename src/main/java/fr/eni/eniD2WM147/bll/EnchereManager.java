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

	public Utilisateur getUserByEmailAndPassword(String email, String mdp) throws BusinessException {
		BusinessException bE = new BusinessException();
		validerEmail(email, bE);
		validerMDP(mdp, bE);
		
		if(bE.getListeMessage().isEmpty()) {
			
		}
		return enchereDAO.getUserByEmailAndPassword(email, mdp);

	}
	
	private void validerEmail(String email, BusinessException businessException ) {
		if(email==null ) {
			businessException.addMessage("L'email n'est pas valide");
		}
	}
	
	private void validerMDP(String mdp, BusinessException businessException) {
		if(mdp==null) {
			businessException.addMessage("Le mot de passe n'est pas valide");
		}
	}

}
