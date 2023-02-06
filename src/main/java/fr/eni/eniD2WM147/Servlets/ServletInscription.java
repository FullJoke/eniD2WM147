package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import fr.eni.eniD2WM147.bll.UtilisateurManager;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/inscription")
public class ServletInscription extends HttpServlet {
	// private static final String MOTIF =
	// "^[a-zA-Z0-9_-]+@[a-zA-Z0-9-]{2,}[.][a-zA-Z]{2,3}$";
	// private static final String
	// MOTIF="(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))";
	// private static String regex
	// ="(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))";
	// private static Pattern pattern =Pattern.compile(regex);
	// private static Matcher matcher;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// une fois connecté redirigé vers l'ecran d'accueil en mode connecté // annuler
		// n'enregistre pas l'utilisateur et envoie directement sur la page // d'accueil
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		UtilisateurManager um = new UtilisateurManager();
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
		String confirmation = request.getParameter("confirmation");

		System.out.println(pseudo + nom + prenom + email + tel + rue + codePostal + ville + mdp + confirmation);

		try {
			BusinessException bE = new BusinessException();
			if (pseudo.isBlank() ) {
				bE.addMessage("Le pseudo est obligatoire");
			}
			if (nom.isBlank()) {
				bE.addMessage("Le nom est obligatoire.");
			}
			if (prenom.isBlank() ) {
				bE.addMessage("Le prenom est obligatoire et ne peut contenir que des lettres.");
			}
			if (email.isBlank()) {
				bE.addMessage("Adresse mail non valide.");
			}
			// || !email.contains(MOTIF)
			// mettre limitation en chiffre et 10
			if (tel.isBlank()) {
				bE.addMessage("Le numéro de téléphone est obligatoire.");
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
			if (confirmation.isBlank()) {
				bE.addMessage("Veuillez confirmer votre mot de passe");
			}
			if (!bE.getListeMessage().isEmpty()) {
				throw bE;
			}


			user = um.insertUtilisateur(pseudo, nom, prenom, email, tel, rue, codePostal, ville, 100, false, mdp);
			session.setAttribute("Utilisateur", user);


			System.out.println("INSCRIPTION - SUCCESS");
			response.sendRedirect(request.getContextPath() + "/accueil");

		} catch (

		BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeErreur", e.getListeMessage());
			doGet(request, response);
		}
	}

}
