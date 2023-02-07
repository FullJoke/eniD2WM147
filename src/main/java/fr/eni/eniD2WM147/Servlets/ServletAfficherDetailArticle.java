package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniD2WM147.bll.ArticleManager;
import fr.eni.eniD2WM147.bll.EnchereManager;
import fr.eni.eniD2WM147.bo.ArticleVendu;
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
			//	e = new Enchere(0, null);
			}
			System.out.println(e);

			av.setEnchere(e);

			request.setAttribute("ArticleAAfficher", av);

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
		String idArtTemp = request.getParameter("Article");

		int idArt = Integer.parseInt(idArtTemp);
	
		
	
		System.out.println("AfficherArticle - doPost");
		int enchere = Integer.parseInt(request.getParameter("enchere"));
		request.setAttribute("enchere",enchere );
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("Utilisateur");
		ArticleVendu arti = null;
		for(ArticleVendu art : utilisateur.getListeArticles()) {
			if (art.getIdArticle() == idArt) {
				arti = art;
			}
		}
		
		System.out.println(enchere);
		try {
			Enchere enc = EnchereManager.getInstance().bidArticle(LocalDateTime.now(), enchere, utilisateur, arti);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/AfficherDetailArticle.jsp");
			rd.forward(request, response);
		
	}
}
	


