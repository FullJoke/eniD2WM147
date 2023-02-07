package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eniD2WM147.bll.EnchereManager;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;

/**
 * Servlet implementation class ServletFaireUneEnchere
 */
@WebServlet("/FaireUneEnchere")
public class ServletFaireUneEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			System.out.println("ServletfaireEnchere - doPost");

			int enchere = Integer.parseInt(request.getParameter("enchere"));
			System.out.println(enchere);
			
			HttpSession session = request.getSession();
			Utilisateur u = (Utilisateur) session.getAttribute("Utilisateur");
			int idsession = u.getIdUtilisateur();
			System.out.println(idsession);
			
			LocalDateTime now = LocalDateTime.now();
			System.out.println(now);
			
	
			int idArticle = Integer.parseInt(request.getParameter("idArticle"));
			System.out.println(idArticle);
			
			
			EnchereManager.getInstance().bidArticle(enchere,u.getIdUtilisateur(),now,idArticle);
			
			
			//request.getParameter("retourEnchere");
			

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/AfficherDetailArticle");

	}

}
