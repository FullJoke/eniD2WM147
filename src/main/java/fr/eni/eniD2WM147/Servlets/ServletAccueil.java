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
		List<ArticleVendu> articlesDoPost = (List<ArticleVendu>) request.getAttribute("articles");
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
			System.out.println("Liste des articles à afficher : "+articles);

			if (articlesDoPost != null) {
				articles = articlesDoPost;
			} else {
				articles = ArticleManager.getInstance().selectAllArticles();

			}
			System.out.println("Liste des articles à afficher : "+articles);

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
		request.setCharacterEncoding("UTF-8");


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
		
		String rechercheClavier = request.getParameter("rechercheClavier");
		System.out.println("recherche tappée par l'utilisateur : " + rechercheClavier);

		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("Utilisateur");
		int idSession;
		if (u == null) {
			idSession = 0;
		} else {
			idSession = u.getIdUtilisateur();
		}
		System.out.println("Id de la session : "+idSession);

		List<ArticleVendu> articles = new ArrayList<>();
		ArticleManager am = ArticleManager.getInstance();

		articles = am.listeArticleAccueil(rechercheClavier, catChoisie, filtreAchat, enchereOuv, mesEncheres, encheresRemportees,
				ventesEnCours, ventesNonDebutees, ventesTerminees, idSession);

		request.setAttribute("articles", articles);

		doGet(request, response);
	}

}
