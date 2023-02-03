package fr.eni.eniD2WM147.bll;

import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniDW2M147.businessException.BusinessException;
import fr.eni.eniDW2M147.dal.EnchereDAOFactory;
import fr.eni.eniDW2M147.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = EnchereDAOFactory.getUtilisateurDAO();

	}

	public Utilisateur getUserByEmailAndPassword(String id, String mdp) throws BusinessException {
		BusinessException bE = new BusinessException();

		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		return utilisateurDAO.getUserByEmailAndPassword(id, mdp);

	}

	public Utilisateur insertUtilisateur(String pseudo, String nom, String prenom, String email, String tel, String rue,
			String codePostal, String ville, int i, boolean b, String mdp) throws BusinessException {

		BusinessException bE = new BusinessException();

		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}

		Utilisateur user = utilisateurDAO.insertUtilisateur(pseudo, nom, prenom, email, tel, rue, codePostal, ville, i,
				b, mdp);

		return user;
	}

	public void validerPseudo(String pseudo, BusinessException businessException) {
		if (pseudo == null || !pseudo.chars().allMatch(Character::isLetterOrDigit)) {
			businessException.addMessage(
					"Le pseudo est obligatoire et le pseudo ne peut contenir que des chiffres et des lettres");
		}
	}

	public void validerNom(String nom, BusinessException businessException) {
		if (nom == null || !nom.chars().allMatch(Character::isLetter)) {
			businessException.addMessage("Le nom est obligatoire et ne peut contenir que des lettres.");
		}
	}

	public void validerPrenom(String prenom, BusinessException businessException) {

	}

	public void validerMail(String mail, BusinessException businessException) {

	}

	public void validerTelephone(String telephone, BusinessException businessException) {

	}

	public void validerRue(String rue, BusinessException businessException) {

	}

	public void validerVille(String ville, BusinessException businessException) {

	}

	public void validerCP(String codePostal, BusinessException businessException) {

	}

	public void validerMdp(String mdp, BusinessException businessException) {

	}

	public void validerConfirmation(String confirmation, BusinessException businessException) {

	}

	public Utilisateur updateUserProfil(String pseudo, String nom, String prenom, String email, String tel, String rue,
			String codePostal, String ville, String mdp, int credit, int idUtilisateur) throws BusinessException {
		BusinessException bE = new BusinessException();
		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}

		Utilisateur user = utilisateurDAO.updateUserProfil(pseudo, nom, prenom, email, tel, rue, codePostal, ville, mdp,
				credit, idUtilisateur);

		return user;

	}

	public void deleteAll(int idUtilisateur) throws BusinessException {
		BusinessException bE = new BusinessException();
		if (!bE.getListeMessage().isEmpty()) {
			throw bE;
		}
		utilisateurDAO.deleteAll(idUtilisateur);

	}
}
