package fr.eni.eneDW2M147.dal;


import fr.eni.eneDW2M147.businessException.BusinessException;



public interface EnchereDAO {

	

	public void getUserByEmailAndPassword(String mail,String mdp) throws BusinessException;
	
	
	

}
