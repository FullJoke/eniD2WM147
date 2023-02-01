package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletAfficherArticle
 */
@WebServlet("/AfficherArticle")
public class ServletCreationArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/CreationArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		
		request.getParameter("article");
		request.getParameter("story");
		request.getParameter("listcate");
		request.getParameter("miseAprix");
		request.getParameter("debutEnchere");
		request.getParameter("finEnchere");
		request.getParameter("rue");
		request.getParameter("codePostal");
		request.getParameter("ville");

		request.getParameter("saveNewArt");
		request.getParameter("annulerNewArt");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

}
