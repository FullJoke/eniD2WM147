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

	private static final String SELECT_ALL_ARTICLE = "SELECT c.libelle, art.nom_article , art.description, art.date_debut_enchere, art.date_fin_enchere, art.prix_initial, art.prix_vente, art.no_utilisateur, art.etat_vente, art.image\r\n"
			+ "FROM CATEGORIES c INNER JOIN ARTICLES_VENDUS as art ON c.no_categorie = art.no_categorie";

	public List<Categorie> selectAllArticles() throws BusinessException {
		List<Categorie> categories = new ArrayList<>();
		List<ArticleVendu> articles = new ArrayList<ArticleVendu>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLE);
			ResultSet rs = pstmt.executeQuery();
			Categorie catCourante = new Categorie();

			while (rs.next()) {
				if (rs.getInt("no_categorie") != catCourante.getNumCategorie()) {
					catCourante = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
					categories.add(catCourante);
				}
				ArticleVendu arti = new ArticleVendu(rs.getString("nom_article"), rs.getString("description"),
						LocalDateTime.of(rs.getDate("date_debut_enchere").toLocalDate(),
								rs.getTime("date_debut_enchere").toLocalTime()),
						LocalDateTime.of(rs.getDate("date_fin_enchere").toLocalDate(),
								rs.getTime("date_debut_enchere").toLocalTime()),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"), rs.getString("etat_vente"), rs.getString("image"));

				catCourante.getArticles().add(arti);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return categories;

	}

}
