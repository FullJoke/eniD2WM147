package fr.eni.eneDW2M147.dal;

import fr.eni.eneDW2M147.businessException.BusinessException;

import fr.eni.eniD2WM147.bo.Utilisateur;

public interface UtilisateurDAO {

	public Utilisateur getUserByEmailAndPassword(String mail, String mdp) throws BusinessException;

	public Utilisateur insertUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,String rue, String code_postal, String ville, int credit, boolean administrateur, String mdp) throws BusinessException;
	
	public void deleteUser(int idUtilisateur);


}
