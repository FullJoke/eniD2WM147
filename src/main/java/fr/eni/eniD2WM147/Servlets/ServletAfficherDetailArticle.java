package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniD2WM147.bll.ArticleManager;
import fr.eni.eniD2WM147.bll.UtilisateurManager;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;

/**
 * Servlet implementation class ServletAfficherDetailArticle
 */
@WebServlet("/AfficherDetailArticle")
public class ServletAfficherDetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("AfficherArticle - doGet");
		String idArtTemp = request.getParameter("Article");

		int idArt = Integer.parseInt(idArtTemp);
		System.out.println("Servlet - idArticle selectionné : " + idArt);

		try {

			ArticleVendu av = ArticleManager.getInstance().selectArticleById(idArt);
			System.out.println(av);

			Enchere e = ArticleManager.getInstance().selectEnchereByIdArticle(idArt);
			if (e == null) {
				e = new Enchere(0, null);
			}
			System.out.println(e);
			
			av.setEnchere(e);

			request.setAttribute("ArticleAAfficher", av);

//			ArticleVendu av = ArticleManager.getInstance().selectArticleById(idArt);
//			System.out.println("SERVLET - Attribut Article : " + av);
//			request.setAttribute("detailArticle", av);
//
//			Categorie c = ArticleManager.getInstance().selectCatByIdArt(av.getIdArticle());
//			System.out.println("SERVLET - Attribut Categorie : " + c);
//			request.setAttribute("articleCategorie", c);
//
//			Utilisateur u = UtilisateurManager.getInstance().getUtilisateurByEnchere(idArt);
//			System.out.println("SERVLET - Attribut Utilisateur : " + u.getPseudo());
//			request.setAttribute("enchereUtilisateur", u);

		} catch (BusinessException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/AfficherDetailArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AfficherArticle - doPost");

		doGet(request, response);
	}

}
