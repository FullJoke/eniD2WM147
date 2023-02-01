package fr.eni.eneDW2M147.dal;

import java.util.List;

import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniD2WM147.bo.Utilisateur;

public interface EnchereDAO {

	public Utilisateur getUserByEmailAndPassword(String mail, String mdp) throws BusinessException;

	public Utilisateur insertUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, int credit, boolean administrateur, String mdp) throws BusinessException;

	public List<Categorie> selectCategorie() throws BusinessException;

}
