package fr.eni.eneDW2M147.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES_VENDUS";
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES";
	private static final String SELECT_ART_BY_CAT = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie=?";
	private static final String INSERT_NEW_ART = "INSERT INTO ARTICLE(nom,description,debutEnchere,"
			+ "finEnchere,prixInitial,prixVente,etatVente,image)VALUES(?,?,?,?,?,?,?,?)";

	public List<ArticleVendu> selectAllArticles() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ArticleVendu arti = new ArticleVendu(rs.getString("nom_article"), rs.getString("description"),
						LocalDateTime.of(rs.getDate("date_debut_enchere").toLocalDate(),
								rs.getTime("date_debut_enchere").toLocalTime()),
						LocalDateTime.of(rs.getDate("date_fin_enchere").toLocalDate(),
								rs.getTime("date_debut_enchere").toLocalTime()),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"), rs.getString("etat_vente"), rs.getString("image"));
				System.out.println(arti);
				articles.add(arti);

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return articles;

	}

	@Override
	public List<ArticleVendu> selectArticlesByCat(int noCategorie) throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ART_BY_CAT);
			pstmt.setInt(1, noCategorie);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ArticleVendu arti = new ArticleVendu(rs.getString("nom_article"), rs.getString("description"),
						LocalDateTime.of(rs.getDate("date_debut_enchere").toLocalDate(),
								rs.getTime("date_debut_enchere").toLocalTime()),
						LocalDateTime.of(rs.getDate("date_fin_enchere").toLocalDate(),
								rs.getTime("date_debut_enchere").toLocalTime()),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"), rs.getString("etat_vente"), rs.getString("image"));
				System.out.println(arti);
				articles.add(arti);

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return articles;
	}

	public List<Categorie> selectAllCategories() {
		List<Categorie> categories = new ArrayList<>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_CATEGORIES);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Categorie cat = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				System.out.println(cat);
				categories.add(cat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	public void insertArticle(String nom, String description, LocalDateTime debutEnchere,
			LocalDateTime finEnchere, int prixInitial, int prixVente, String etatVente, String image) throws BusinessException {
		ArticleVendu arti;
		Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_NEW_ART);

			pstmt.setString(1, nom);
			pstmt.setString(2, description);
			pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(debutEnchere));
			pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(finEnchere));
			pstmt.setInt(5, prixInitial);
			pstmt.setInt(6, prixVente);
			pstmt.setString(7, String.valueOf(etatVente));
			pstmt.setString(8, image);
			
		    pstmt.executeUpdate();

//			if (rs.next()) {
//
//				String nom1 = rs.getString("nom");
//				String description1 = rs.getString("description");
//				LocalDateTime debutEnchere1 = LocalDateTime.of((rs.getDate("debutEnchere")
//						.toLocalDate()), rs.getTime("debutEnchere").toLocalTime());
//				LocalDateTime finEnchere1 = LocalDateTime.of((rs.getDate("finEnchere")
//						.toLocalDate()), rs.getTime("finEnchere").toLocalTime());
//				String prixInitial1 = rs.getString("prixInitial");
//				String prixVente1 = rs.getString("prixVente");
//				String etatVente1 = rs.getString("etatVente");
//				String image1 = rs.getString("nom");
//				}
//			 arti = new ArticleVendu(nom,description, debutEnchere,
//					finEnchere, prixInitial,prixVente,etatVente, image);

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

	}
	

}
