package fr.eni.eniDW2M147.dal;

import java.time.LocalDateTime;

import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniDW2M147.businessException.BusinessException;

public interface EnchereDAO {
	
	
	public Enchere insertBid(LocalDateTime dateEnchere, int montantEnchere) throws BusinessException;

}
