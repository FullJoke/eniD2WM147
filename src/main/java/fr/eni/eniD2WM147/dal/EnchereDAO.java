package fr.eni.eniD2WM147.dal;


public interface EnchereDAO {


	public void enchereUpdate(int idSession, int myOffer, int idArticle);

	public void enchereInsert(int idSession, int myOffer, int idArticle);

}
