package fr.eni.eniD2WM147.Servlets;

import java.io.IOException;
import java.time.LocalDateTime;
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
import fr.eni.eniD2WM147.bll.EnchereManager;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;



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

		ArticleManager am = ArticleManager.getInstance();

		List<Categorie> categories = new ArrayList<>();
		try {
			categories = am.selectAllCat();
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
		HttpSession session = request.getSession();
		try {

			ArticleVendu article = null;

			String art = request.getParameter("article");
			String description = request.getParameter("story");
			String image = request.getParameter("photoArticle");
			String categorie = request.getParameter("listcate");
			String prix = request.getParameter("miseAprix");
			String debutVente = request.getParameter("debutEnchere");
			String finVente = request.getParameter("finEnchere");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");

			LocalDateTime dateDebut = null;
			LocalDateTime dateFin = null;
			int numCat = Integer.parseInt(categorie);

			dateDebut = LocalDateTime.parse(debutVente);
			dateFin = LocalDateTime.parse(finVente);

			int prixEntier = Integer.parseInt(prix);
			Utilisateur vendeur = (Utilisateur) session.getAttribute("Utilisateur");
			Categorie cat = new Categorie(numCat);
			// voir pour le lieu de retrait
			// voir pour cat et parse pour localdate
			// Ajouter article
			article = new ArticleVendu(art, description, dateDebut, dateFin, prixEntier, 0, image, "CR", vendeur, null, cat, null);
			request.getParameter("saveNewArt");

			article = ArticleManager.getInstance().insert(article);
			request.getParameter("annulerNewArt");

			BusinessException bE = new BusinessException();
			if (art.isBlank()) {
				bE.addMessage("L'article doit avoir un nom");
			}
			if (description.isBlank()) {
				bE.addMessage("L'article doit avoir une description ");
			}
			if (debutVente.isBlank()) {
				bE.addMessage("");
			}
			RequestDispatcher rd = request.getRequestDispatcher("/accueil");
			rd.forward(request, response);

		} catch (BusinessException e) {
			e.printStackTrace();

			// response.sendRedirect(request.getContextPath()+"/WEB-INF/JSP/CreationArticle.jsp");
			doGet(request, response);
		}

	}

}
