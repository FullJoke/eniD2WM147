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
import javax.servlet.http.HttpSession;

import fr.eni.eniD2WM147.bll.ArticleManager;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniD2WM147.bo.Utilisateur;
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
		List<ArticleVendu> as = (List<ArticleVendu>) request.getAttribute("articles");
		List<ArticleVendu> articles = new ArrayList<>();
		List<Categorie> categories = new ArrayList<>();
		int catChoisie;

		try {
			categories = ArticleManager.getInstance().selectAllCat();
			request.setAttribute("categories", categories);

			String catChoisieTemp = request.getParameter("categorieChoisie");
			if (catChoisieTemp == null) {
				catChoisieTemp = "0";
			}
			catChoisie = Integer.parseInt(catChoisieTemp);
			System.out.println(articles);

			if (as != null) {
				articles = as;
			} else {
				articles = ArticleManager.getInstance().selectAllArticles();

			}

//			if (catChoisie == 0) {
//				articles = ArticleManager.getInstance().selectAllArticles();
//
//				for (ArticleVendu av : articles) {
//					Enchere e = ArticleManager.getInstance().selectEnchereByIdArticle(av.getIdArticle());
//
//					if (e == null) {
//						e = new Enchere(0, null);
//					}
//					av.setEnchere(e);
//				}
//
//			} else {
//				articles = ArticleManager.getInstance().selectArticlesByCat(catChoisie);
//			}
//
//			for (ArticleVendu a : articles) {
//				System.out.println(a.getIdArticle());
//
//			}
			request.setAttribute("articles", articles);
		} catch (BusinessException ex) {
			ex.printStackTrace();
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
		System.out.println("ServletAccueil - doPost");

		String catChoisieTemp = request.getParameter("categorieChoisie");
		int catChoisie = Integer.parseInt(catChoisieTemp);
		System.out.println("Catégorie choisie : " + catChoisie);

		String filtreAchat = request.getParameter("RButton");
		System.out.println("Filtre selectionné : " + filtreAchat);

		String enchereOuv = request.getParameter("enchereOuvCheck");
		System.out.println("Filtre Enchere Ouverte : " + enchereOuv);

		String mesEncheres = request.getParameter("mesEncheresCheck");
		System.out.println("Filtre Mes Encheres : " + mesEncheres);

		String encheresRemportees = request.getParameter("enchereRemporteesCheck");
		System.out.println("Filtre Mes Encheres Remportées : " + encheresRemportees);

		String ventesEnCours = request.getParameter("ventesEnCoursCheck");
		System.out.println("Filtre Ventes En Cours : " + ventesEnCours);

		String ventesNonDebutees = request.getParameter("ventesNonDébutéesCheck");
		System.out.println("Filtre Ventes Non Débutées : " + ventesNonDebutees);

		String ventesTerminees = request.getParameter("ventesTermineesCheck");
		System.out.println("Filtre Ventes Terminées : " + ventesTerminees);

		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("Utilisateur");
		System.out.println("Id de l'utilisateur connecté : " + u.getIdUtilisateur());
		int idSession = u.getIdUtilisateur();

		List<ArticleVendu> articles = new ArrayList<>();
		ArticleManager am = ArticleManager.getInstance();

		articles = am.listeArticleAccueil(catChoisie, filtreAchat, enchereOuv, mesEncheres, encheresRemportees,
				ventesEnCours, ventesNonDebutees, ventesTerminees, idSession);

		request.setAttribute("articles", articles);

		doGet(request, response);
	}

}
