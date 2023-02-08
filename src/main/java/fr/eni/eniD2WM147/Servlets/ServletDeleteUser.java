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
 * Servlet implementation class ServletDeleteUser
 */
@WebServlet("/delete")
public class ServletDeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doPost-DELETE");
		request.getParameter("retourSuppression");
		//response.sendRedirect("/WEB-INF/JSP/Accueil.jsp");

		HttpSession session = request.getSession();

		Utilisateur user = null;
		user = (Utilisateur) session.getAttribute("Utilisateur");
		request.getParameter("retourSuppression");
		try {
			UtilisateurManager.getInstance().deleteAll(user.getIdUtilisateur());
			System.out.println(user.getIdUtilisateur());
		} catch (BusinessException e) {
			e.printStackTrace();

			request.setAttribute("listeErreur", e.getListeMessage());
		}
		session.setAttribute("Utilisateur", user);
		response.sendRedirect(request.getContextPath() + "/deconnexion");

	}

}
