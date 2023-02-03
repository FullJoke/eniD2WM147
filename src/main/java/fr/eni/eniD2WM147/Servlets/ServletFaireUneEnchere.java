package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniD2WM147.bll.EnchereManager;
import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniDW2M147.dal.EnchereDAO;

/**
 * Servlet implementation class ServletFaireUneEnchere
 */
@WebServlet("/FaireUneEnchere")
public class ServletFaireUneEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doPost");
		
		EnchereManager enchere = new EnchereManager();
		
		request.getParameter("encherir");
	
		
	
		
		
	
		
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Accueil.jsp");
		rd.forward(request, response);
	}

}
