package fr.eni.eniD2WM147.bll;


import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eneDW2M147.dal.EnchereDAO;
import fr.eni.eniD2WM147.bo.Utilisateur;


public class EnchereManager {

	private EnchereDAO enchereDAO;
	
	public Utilisateur getUserByEmailAndPassword(String email,String mdp) throws BusinessException {
		try {
			return enchereDAO.getUserByEmailAndPassword(email, mdp);
		}catch (BusinessException e ) {
			throw new BusinessException();
		}
		
		
	}

}
