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

import fr.eni.eniD2WM147.bll.ArticleManager;
import fr.eni.eniD2WM147.bll.EnchereManager;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniD2WM147.businessException.BusinessException;

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
		System.out.println("ServletAccueil - doGet");
		ArticleManager am = new ArticleManager();
		List<ArticleVendu> articles = new ArrayList<>();
		List<Categorie> categories = new ArrayList<>();
		int catChoisie;

		try {
			categories = am.selectAllCat();
			request.setAttribute("categories", categories);

			String catChoisieTemp = request.getParameter("categorieChoisie");
			if (catChoisieTemp == null) {
				catChoisieTemp = "0";
			}
			catChoisie = Integer.parseInt(catChoisieTemp);

			if (catChoisie == 0) {
				articles = am.selectAllArticles();
			} else {
				articles = am.selectArticlesByCat(catChoisie);
			}

			for (ArticleVendu a : articles) {
				System.out.println(a.getIdArticle());

			}
				request.setAttribute("articles", articles);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
