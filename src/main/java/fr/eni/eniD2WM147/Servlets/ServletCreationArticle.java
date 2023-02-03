package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniD2WM147.bll.EnchereManager;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniDW2M147.businessException.BusinessException;

/**
 * Servlet implementation class ServletAfficherArticle
 */
@WebServlet("/CreationArticle")
public class ServletCreationArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Creation Vente - doGet");
		EnchereManager em = new EnchereManager();
		
		List<Categorie> categories = new ArrayList<>();
		try {
			categories = em.selectAllCat();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("categories", categories);

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
