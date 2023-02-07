package fr.eni.eniD2WM147.dal;

import java.time.LocalDateTime;

import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;

public interface EnchereDAO {
	
	
	public Enchere insertBid(LocalDateTime dateEnchere,int montantEnchere,Utilisateur utilisateur,ArticleVendu article) throws BusinessException;
	public void bidArticle(int montantEnchere,int idUtilisateur,LocalDateTime now,int idArticle) throws BusinessException;

}
