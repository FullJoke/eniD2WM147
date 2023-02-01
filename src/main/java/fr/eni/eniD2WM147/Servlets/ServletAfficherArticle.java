package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletAfficherArticle
 */
@WebServlet("/AfficherArticle")
public class ServletAfficherArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAfficherArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/AffichageArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		response.sendRedirect("/WEB-INF/JSP/Accueil.jsp");
		request.getParameter("annulerNewArt");
		response.sendRedirect("/WEB-INF/JSP/Accueil.jsp");
		
		
		
		
		
		doGet(request, response);
	}

}
