package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eniD2WM147.bll.EnchereManager;
import fr.eni.eniD2WM147.bo.Utilisateur;

/**
 * Servlet implementation class ServletModificationProfil
 */
@WebServlet("/ModificationProfil")
public class ServletModificationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("doGet");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		HttpSession session = request.getSession();

		EnchereManager em = new EnchereManager();
		Utilisateur u = null;
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("mdp");
		String confirmation = request.getParameter("confirmation");

		BusinessException bE = new BusinessException();
		try {
			if (pseudo.isBlank() || !pseudo.chars().allMatch(Character::isLetterOrDigit)) {
				bE.addMessage("Le pseudo ne peut contenir que des chiffres et des lettres");
			}
			if (nom.isBlank() || !nom.chars().allMatch(Character::isLetter)) {
				bE.addMessage("Le nom est obligatoire et ne peut contenir que des lettres.");
			}
			if (prenom.isBlank() || !prenom.chars().allMatch(Character::isLetter)) {
				bE.addMessage("Le prenom est obligatoire et ne peut contenir que des lettres.");
			}
			if (email.isBlank()) {
				bE.addMessage("Adresse mail non valide.");
			}
			// || !email.contains(MOTIF)
			// mettre limitation en chiffre et 10
			if (tel.isBlank() || !tel.chars().allMatch(Character::isDigit)) {
				bE.addMessage("Le numéro de téléphone doit contenir 10 chiffres.");
			}

			if (rue.isBlank()) {
				bE.addMessage("La rue est obligatoire.");
			}
			if (codePostal.isBlank()) {
				bE.addMessage("Le code postal est obligatoire.");
			}
			if (ville.isBlank()) {
				bE.addMessage("La ville est obligatoire.");
			}
			if (mdp.isBlank()) {
				bE.addMessage("Le mot de passe est obligatoire.");
			}
			if (confirmation.isBlank() & confirmation.equals(mdp)) {
				bE.addMessage("Veuillez confirmer votre mot de passe");
			}
			if (!bE.getListeMessage().isEmpty()) {
				throw bE;
			}
			Utilisateur ut = em.insertUtilisateur(pseudo, nom, prenom, email, tel, rue, codePostal, ville, 100, false, mdp);
			session.setAttribute("Utilisateur", ut);
			System.out.println("MODIFICATION - SUCCESS");
			response.sendRedirect(request.getContextPath()+"/Profil");
		} catch (
				
		BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeErreur", e.getListeMessage());
			doGet(request, response);
		}
	}

}
