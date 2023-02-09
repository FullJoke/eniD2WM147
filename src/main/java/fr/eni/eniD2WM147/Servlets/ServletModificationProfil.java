package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eniD2WM147.bll.EnchereManager;
import fr.eni.eniD2WM147.bll.UtilisateurManager;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;

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
		System.out.println("doGet");
		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("Utilisateur");
		
		try {
			u = UtilisateurManager.getInstance().getUtilisateurById(u.getIdUtilisateur());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("Utilisateur", u);
		
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
		request.setCharacterEncoding("UTF-8");
		
		Utilisateur user = null;
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("mdp");
		String newMdp = request.getParameter("newMdp");
		String confirmation = request.getParameter("confirmation");

		BusinessException bE = new BusinessException();
		try {
			if (pseudo.isBlank()) {
				bE.addMessage("Le pseudo ne peut contenir que des chiffres et des lettres");
			}
			if (nom.isBlank()) {
				bE.addMessage("Le nom est obligatoire et ne peut contenir que des lettres.");
			}
			if (prenom.isBlank()) {
				bE.addMessage("Le prenom est obligatoire et ne peut contenir que des lettres.");
			}
			if (email.isBlank()) {
				bE.addMessage("Adresse mail non valide.");
			}

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
			if ((confirmation.isBlank() && !newMdp.isBlank()) || !confirmation.equals(newMdp)) {
				bE.addMessage("Veuillez confirmer votre mot de passe");
			}
			if (!bE.getListeMessage().isEmpty()) {
				throw bE;
			}
			user =UtilisateurManager.getInstance().updateUserProfil(pseudo, nom, prenom, email, tel, rue, codePostal, ville,newMdp.isBlank()?mdp:newMdp , ((Utilisateur)session.getAttribute("Utilisateur")).getCredit(), ((Utilisateur)session.getAttribute("Utilisateur")).getIdUtilisateur());
			session.setAttribute("Utilisateur", user);
			System.out.println("MODIFICATION - SUCCESS");
			response.sendRedirect(request.getContextPath()+"/accueil");
			
		} catch (
				
		BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeErreur", e.getListeMessage());
			doGet(request, response);
		}
	}

}
