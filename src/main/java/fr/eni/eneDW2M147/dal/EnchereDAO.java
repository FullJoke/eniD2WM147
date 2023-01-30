package fr.eni.eneDW2M147.dal;


import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eniD2WM147.bo.Utilisateur;



public interface EnchereDAO {

	

	public Utilisateur getUserByEmailAndPassword(String mail,String mdp) throws BusinessException;
	
	
	

}
