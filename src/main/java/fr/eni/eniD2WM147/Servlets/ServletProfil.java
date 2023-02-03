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
import fr.eni.eniDW2M147.businessException.BusinessException;

/**
 * Servlet implementation class ServletProfil
 */
@WebServlet("/Profil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet Profil - doGet");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Profil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet Profil - doPost");
		
		String idUtilisateurTemp = request.getParameter("vendeur");
		int idUtilisateur = Integer.parseInt(idUtilisateurTemp);
		System.out.println("id de l'utilisateur à rechercher : " + idUtilisateur);
		
		UtilisateurManager um = new UtilisateurManager();
		try {
			Utilisateur u = um.getUtilisateurById(idUtilisateur);
			System.out.println(u.getNom());
			
			request.setAttribute("vendeur", u);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ProfilVendeur.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
