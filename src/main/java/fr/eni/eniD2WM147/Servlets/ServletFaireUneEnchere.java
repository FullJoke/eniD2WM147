package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eniD2WM147.bll.EnchereManager;
import fr.eni.eniD2WM147.bo.Utilisateur;

/**
 * Servlet implementation class ServletFaireUneEnchere
 */
@WebServlet("/FaireUneEnchere")
public class ServletFaireUneEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("/nServlet FaireUneEnchere : doPost");
		EnchereManager em = EnchereManager.getInstance();

		HttpSession session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("Utilisateur");
		int idSession = u.getIdUtilisateur();
		System.out.println("idSession : " + idSession);

		String idArticleTemp = request.getParameter("idArticle");
		int idArticle = Integer.parseInt(idArticleTemp);
		System.out.println("idArticle : " + idArticle);

		String bestOfferTemp = request.getParameter("bestOffer");
		int bestOffer = Integer.parseInt(bestOfferTemp);
		System.out.println("Meilleure Offre : " + bestOffer);

		String myOfferTemp = request.getParameter("enchere");
		int myOffer = Integer.parseInt(myOfferTemp);
		System.out.println("Proposition d'enchère : " + myOffer);

		if (bestOffer == 0) {
			System.out.println("Vous êtes le premier à enchérir");
			em.enchereInsert(idSession, myOffer, idArticle);
		} else if (myOffer <= bestOffer) {
			System.out.println("Vous êtes trop radin !");
		} else {
			System.out.println("Vous l'emportez !");
			em.enchereUpdate(idSession, myOffer, idArticle);
		}
		
		request.setAttribute("idArticle", idArticle);
		response.sendRedirect(request.getContextPath()+"/accueil");
	}

}
