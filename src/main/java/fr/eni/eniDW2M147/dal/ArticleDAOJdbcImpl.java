package fr.eni.eniDW2M147.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;

import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniDW2M147.businessException.BusinessException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES_VENDUS av "
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur";
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES";
	private static final String SELECT_ART_BY_CAT = "SELECT * FROM ARTICLES_VENDUS av "
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur WHERE no_categorie=?";
	private static final String INSERT_NEW_ART = "INSERT INTO ARTICLES_VENDUS (nom_article,description,"
			+ "date_debut_enchere,date_fin_enchere,prix_initial,prix_vente,no_utilisateur,no_categorie,etat_vente,image)"
			+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ART_BY_ID = "SELECT * FROM ARTICLES_VENDUS av"
			+ " INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur WHERE no_article=?";
	private static final String INSERT_ENCHERES="";
	

	public List<ArticleVendu> selectAllArticles() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Utilisateur u = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"));

				ArticleVendu arti = new ArticleVendu(rs.getInt("no_Article"), rs.getString("nom_article"),
						rs.getString("description"),
						LocalDateTime.of(rs.getDate("date_debut_enchere").toLocalDate(),
								rs.getTime("date_debut_enchere").toLocalTime()),
						LocalDateTime.of(rs.getDate("date_fin_enchere").toLocalDate(),
								rs.getTime("date_fin_enchere").toLocalTime()),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("etat_vente"),
						rs.getString("image"), u);
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
				Utilisateur u = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"));

				ArticleVendu arti = new ArticleVendu(rs.getInt("no_Article"), rs.getString("nom_article"),
						rs.getString("description"),
						LocalDateTime.of(rs.getDate("date_debut_enchere").toLocalDate(),
								rs.getTime("date_debut_enchere").toLocalTime()),
						LocalDateTime.of(rs.getDate("date_fin_enchere").toLocalDate(),
								rs.getTime("date_fin_enchere").toLocalTime()),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("etat_vente"),
						rs.getString("image"), u);
				articles.add(arti);

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return articles;
	}

	public List<Categorie> selectAllCategories() throws BusinessException {
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
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

		return categories;
	}

	public ArticleVendu insertArticle(ArticleVendu article) throws BusinessException {
		Connection cnx;
		ArticleVendu art=null;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_NEW_ART, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1,article.getNom());
			pstmt.setString(2,article.getDescription());
			pstmt.setTimestamp(3,java.sql.Timestamp.valueOf(article.getDebutEnchere()));
			pstmt.setTimestamp(4,java.sql.Timestamp.valueOf(article.getFinEnchere()));
			pstmt.setInt(5,article.getPrixInitial());
			pstmt.setInt(6,article.getPrixVente());
			pstmt.setString(7,String.valueOf(article.getUtilisateur().getIdUtilisateur()));
			pstmt.setString(8,String.valueOf(article.getCategorie().getNumCategorie()));
			pstmt.setString(9,String.valueOf(article.getEtatVente()));
			pstmt.setString(10,article.getImage());

			pstmt.executeUpdate();
			
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				int idArticle = rs.getInt(article.getIdArticle());
				art = new ArticleVendu(article);
				art.setIdArticle(idArticle);
				}
			System.out.println(article);
			//faire une boucle for each avec un INSERT Enchere pour créer les encheres avec l'article. 

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}
		return article;
		
	}

	public ArticleVendu selectArticleById(int idArticle) throws BusinessException {
		ArticleVendu article = null;
		PreparedStatement pstmt;
		Utilisateur u;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			pstmt = cnx.prepareStatement(SELECT_ART_BY_ID);
			pstmt.setInt(1, idArticle);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				u = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"));
				System.out.println(u.getPseudo());
				ArticleVendu arti = new ArticleVendu(rs.getInt("no_Article"), rs.getString("nom_article"),
						rs.getString("description"),
						LocalDateTime.of(rs.getDate("date_debut_enchere").toLocalDate(),
								rs.getTime("date_debut_enchere").toLocalTime()),
						LocalDateTime.of(rs.getDate("date_fin_enchere").toLocalDate(),
								rs.getTime("date_fin_enchere").toLocalTime()),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("etat_vente"),
						rs.getString("image"), u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

		return null;

	}



}
