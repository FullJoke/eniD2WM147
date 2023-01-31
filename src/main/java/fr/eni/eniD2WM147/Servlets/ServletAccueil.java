package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniD2WM147.bll.EnchereManager;
import fr.eni.eniD2WM147.bo.Categorie;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Accueil - doGet");
		EnchereManager em = new EnchereManager();
		String categorieChoisie = request.getParameter("categorie");
		List<Categorie> articles = em.selectAllArticles();
		request.setAttribute("nom", categorieChoisie);

		if (categorieChoisie == null) {
			categorieChoisie = "toutes";
		}

		if (categorieChoisie.equals("toutes")) {
			for (Categorie c : articles) {
				System.out.println(c.getLibelle());
			}
		} else {
			System.out.println(categorieChoisie);

		}
		request.setAttribute("articles", articles);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Accueil - doPost");

	}

}
