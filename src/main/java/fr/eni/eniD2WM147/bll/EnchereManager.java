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

		if (id.equals(null)) {
			bE.addMessage("L'email ou le pseudo n'est pas valide");
		}
		if (mdp.equals(null)) {
			bE.addMessage("Le mot de passe n'est pas valide");
		}

		
		if(bE.getListeMessage().isEmpty()) {
			
		}
		return enchereDAO.getUserByEmailAndPassword(id, mdp);

	}
	
	private void validerID(String id, BusinessException businessException ) {
		if(id==null ) {
			businessException.addMessage("L'email n'est pas valide");
		}
	}
	
	private void validerMDP(String mdp, BusinessException businessException) {
		if(mdp==null) {
			businessException.addMessage("Le mot de passe n'est pas valide");
		}
	}

}
