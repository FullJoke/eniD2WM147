
package fr.eni.eniD2WM147.dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Categorie;
import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniD2WM147.bo.Retrait;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String SELECT_ALL_ARTICLES = "SELECT  * FROM ARTICLES_VENDUS av "
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur "
			+ "LEFT JOIN ENCHERES e ON av.no_article=e.no_article WHERE av.etat_vente='EC'";
	private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES";

	private static final String SELECT_ART_BY_ID = "SELECT av.no_article, av.nom_article, av.description, av.date_debut_enchere, av.date_fin_enchere, av.prix_initial,\r\n"
			+ "	   av.prix_vente, av.image, av.etat_vente,\r\n"
			+ "	   u.no_utilisateur as id_vendeur, u.pseudo as pseudo_vendeur,\r\n"
			+ "	   r.rue, r.code_postal, r.ville,\r\n" + "	   c.no_categorie, c.libelle\r\n"
			+ "FROM ARTICLES_VENDUS av INNER JOIN UTILISATEURS u ON av.no_utilisateur=u.no_utilisateur\r\n"
			+ "							  JOIN RETRAITS r ON av.no_article=r.no_article\r\n"
			+ "							  JOIN CATEGORIES c ON av.no_categorie=c.no_categorie\r\n"
			+ "WHERE av.no_article=?";
	private static final String SELECT_CAT = "SELECT * FROM CATEGORIES c JOIN ARTICLES_VENDUS av ON "
			+ "c.no_categorie = av.no_categorie WHERE no_article=?";
	private static final String SELECT_ENCHERE_BY_IDARTICLE = "SELECT e.montant_enchere, u.no_utilisateur, "
			+ "u.pseudo FROM ENCHERES e JOIN UTILISATEURS u ON e.no_utilisateur=u.no_utilisateur "
			+ "WHERE no_article=?";
	public static final String SELECT_ALL = "SELECT av.no_article AS noArticle, "
			+ "av.nom_article as nomArticle, av.description, av.date_debut_enchere, av.date_fin_enchere, "
			+ "av.prix_initial, av.prix_vente, av.etat_vente, av.image, u.no_utilisateur AS noVendeur, u.pseudo AS pseudoVendeur, "
			+ "e.montant_enchere " + "FROM ARTICLES_VENDUS av "
			+ "INNER JOIN UTILISATEURS u ON av.no_utilisateur=u.no_utilisateur "
			+ "LEFT JOIN ENCHERES e ON av.no_article=e.no_article";

	public static final String CATEGORIE = "av.no_categorie=?";
	public static final String ACHATS = "av.no_utilisateur<>?";
	public static final String ENCHERES_OUVERTES = "av.etat_vente='EC'";
	public static final String MES_ENCHERES = "e.no_utilisateur=?";
	public static final String MES_ENCHERES_REMPORTEES = MES_ENCHERES + " AND av.etat_vente='VD'";
	public static final String VENTES = "av.no_utilisateur= ?";
	public static final String MES_VENTES_EN_COURS = "av.etat_vente='EC'";
	public static final String MES_VENTES_NON_DEBUTEES = "av.etat_vente='CR'";
	public static final String MES_VENTES_TERMINEES = "av.etat_vente='VD'";

	private static final String INSERT_NEW_ART = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente, image) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES (?,?,?,?)";

	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";

	public List<ArticleVendu> selectAllArticles() throws BusinessException {
		List<ArticleVendu> articles = new ArrayList<>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			System.out.println(SELECT_ALL_ARTICLES);
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Utilisateur u = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"));

				Enchere enchere = new Enchere(rs.getInt("montant_enchere"), null);

				ArticleVendu arti = new ArticleVendu(rs.getInt("no_Article"), rs.getString("nom_article"),
						rs.getString("description"),
						LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),
								rs.getTime("date_debut_enchere").toLocalTime()),
						LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),
								rs.getTime("date_fin_enchere").toLocalTime()),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("etat_vente"),
						rs.getString("image"), u, null, null, enchere);
				articles.add(arti);

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return articles;

	}

	public List<Categorie> selectAllCategories() throws BusinessException {
		List<Categorie> categories = new ArrayList<>();
		System.out.println(SELECT_ALL_CATEGORIES);

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

		System.out.println(INSERT_NEW_ART);

		try (Connection cnx = ConnectionProvider.getConnection();) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT_NEW_ART, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, article.getNom());
			pstmt.setString(2, article.getDescription());
			pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(article.getDebutEnchere()));
			pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(article.getFinEnchere()));
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setString(7, String.valueOf(article.getUtilisateur().getIdUtilisateur()));
			pstmt.setString(8, String.valueOf(article.getCategorie().getNumCategorie()));
			System.out.println(article.getCategorie().getNumCategorie());
			pstmt.setString(9, String.valueOf(article.getEtatVente()));
			pstmt.setString(10, article.getImage());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {

				int idArticle = rs.getInt(1);
				article.setIdArticle(idArticle);
				//article.setImage(rs.getString("image"));
				

			}
			System.out.println(article);

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

		return article;

	}

	public Retrait insertRetrait(Retrait retrait) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection();) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT);

			System.out.println(retrait);
			System.out.println(retrait.getArticle());

			pstmt.setInt(1, retrait.getArticle().getIdArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}
		return retrait;

	}

	public ArticleVendu selectArticleById(int idArticle) throws BusinessException {
		System.out.println("DAL - idArticle selectionné : " + idArticle);

		ArticleVendu art = null;
		PreparedStatement pstmt;
		Utilisateur u;
		Categorie c;
		Retrait r;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			System.out.println(SELECT_ART_BY_ID);
			pstmt = cnx.prepareStatement(SELECT_ART_BY_ID);
			pstmt.setInt(1, idArticle);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				c = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				System.out.println(c.getLibelle());

				u = new Utilisateur(rs.getInt("id_vendeur"), rs.getString("pseudo_vendeur"));
				System.out.println("Pseudo du vender : " + u.getPseudo());

				r = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
				System.out.println("Ville du retrait : " + r.getVille());

				art = new ArticleVendu(rs.getInt("no_Article"), rs.getString("nom_article"),
						rs.getString("description"),
						LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),
								rs.getTime("date_debut_enchere").toLocalTime()),
						LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),
								rs.getTime("date_fin_enchere").toLocalTime()),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("image"),
						rs.getString("etat_vente"), u, r, c, null);

				System.out.println("Nom de l'article : " + art.getNom());

				// recuperer aussi la categorie en base de donnée

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

		return art;

	}

	@Override
	public Categorie selectCatByIdArt(int idArticle) {
		Categorie cat = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			System.out.println(SELECT_CAT);
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CAT);
			pstmt.setInt(1, idArticle);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				cat = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DAL - Categorie Article : " + cat.getLibelle());
		return cat;
	}

	@Override
	public Enchere selectEnchereByIdArticle(int idArt) {
		Enchere e = null;
		Utilisateur u = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			System.out.println(SELECT_ENCHERE_BY_IDARTICLE);
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERE_BY_IDARTICLE);
			pstmt.setInt(1, idArt);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				u = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"));

				e = new Enchere(rs.getInt("montant_enchere"), u);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return e;
	}

	@Override
	public List<ArticleVendu> listeArticleAccueil(String rechercheClavier, int catChoisie, String filtreAchat,
			String enchereOuv, String mesEncheres, String encheresRemportees, String ventesEnCours,
			String ventesNonDebutees, String ventesTerminees, int idSession) {
		List<ArticleVendu> articles = new ArrayList<>();
		List<String> parametres = new ArrayList<>();
		int compteur = 0;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			StringBuilder preparedStatement = new StringBuilder();
			preparedStatement.append(SELECT_ALL);

			if (!rechercheClavier.isBlank()) {
				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? " AND " : " WHERE ");
				preparedStatement.append("nom_article like '%" + rechercheClavier + "%'");

			}
			if (catChoisie != 0) {
				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? " AND " : " WHERE ");
				preparedStatement.append(CATEGORIE);
				parametres.add(String.valueOf(catChoisie));
				compteur++;
			}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (filtreAchat != null && filtreAchat.equalsIgnoreCase("Achats")) {
				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? " AND " : " WHERE ");
				preparedStatement.append(ACHATS);
				parametres.add(String.valueOf(idSession));
				compteur++;
			}
			if (enchereOuv != null) {
				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? " AND " : " WHERE ");
				preparedStatement.append(preparedStatement.toString().contains(ACHATS)?"( ":"");
				preparedStatement.append(ENCHERES_OUVERTES);
			}
			if (mesEncheres != null) {
				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? 
				(preparedStatement.toString().contains(ENCHERES_OUVERTES)?" OR ":" AND "):
				" WHERE ");
				preparedStatement.append(preparedStatement.toString().contains(ACHATS)?"( ":"");
				preparedStatement.append(MES_ENCHERES);
				parametres.add(String.valueOf(idSession));
				compteur++;
			}
			if (encheresRemportees != null) {
				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? 
				(preparedStatement.toString().contains(ENCHERES_OUVERTES)||
				 preparedStatement.toString().contains(MES_ENCHERES)?" OR ":" AND ")
				: " WHERE ");
				preparedStatement.append(preparedStatement.toString().contains(ACHATS)?"( ":"");
				preparedStatement.append(MES_ENCHERES_REMPORTEES);
			}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if (filtreAchat != null && filtreAchat.equalsIgnoreCase("Ventes")) {
				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? " AND " : " WHERE ");
				preparedStatement.append(VENTES);
				parametres.add(String.valueOf(idSession));
				compteur++;
			}
			if (ventesEnCours != null) {
				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? " AND "	
				: " WHERE ");
				preparedStatement.append(preparedStatement.toString().contains(VENTES)?"( ":"");
				preparedStatement.append(MES_VENTES_EN_COURS);
			}
			if (ventesNonDebutees != null) {
				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? 
				(preparedStatement.toString().contains(MES_VENTES_EN_COURS)?" OR ":" AND ")
				:" WHERE ");
				preparedStatement.append(preparedStatement.toString()
						.contains(MES_VENTES_EN_COURS)?"":"( ");
				preparedStatement.append(MES_VENTES_NON_DEBUTEES);
			}
			if (ventesTerminees != null) {
				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? 
				((preparedStatement.toString().contains(MES_VENTES_EN_COURS)||
				  preparedStatement.toString().contains(MES_VENTES_NON_DEBUTEES))?" OR ":" AND ")
				: " WHERE ");
				preparedStatement.append(preparedStatement.toString()
						.contains(MES_VENTES_NON_DEBUTEES)?"":"( ");
				preparedStatement.append(MES_VENTES_TERMINEES);
			}
			
			preparedStatement.append(preparedStatement.toString().contains("( ")?" ) ":"");
			
			if(!preparedStatement.toString().contains(MES_VENTES_NON_DEBUTEES) &&
			   !preparedStatement.toString().contains(MES_VENTES_TERMINEES) &&
			   !preparedStatement.toString().contains(MES_ENCHERES_REMPORTEES)) {
				
				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? 
						" AND " : " WHERE ");
				preparedStatement.append(ENCHERES_OUVERTES);
			}
			
			System.out.println("Requete finale : " + preparedStatement.toString());
			System.out.println("Nombre de ? : " + compteur);
			System.out.println("Liste des Set du PreparedStatement : " + parametres);


			if (!preparedStatement.toString().contains(MES_VENTES_NON_DEBUTEES)
					&& !preparedStatement.toString().contains(MES_ENCHERES_REMPORTEES)) {

				preparedStatement.append(preparedStatement.toString().contains(" WHERE ") ? " AND " : " WHERE ");
				preparedStatement.append(ENCHERES_OUVERTES);
			}

			PreparedStatement pstmt = cnx.prepareStatement(preparedStatement.toString());
			for (String param : parametres) {
				try {
					int i = Integer.parseInt(param);
					pstmt.setInt(parametres.indexOf(param) + 1, i);
				} catch (NumberFormatException n) {
					pstmt.setString(parametres.indexOf(param) + 1, param);
				}
			}

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Utilisateur u = new Utilisateur(rs.getInt("noVendeur"), rs.getString("pseudoVendeur"));

				Enchere enchere = new Enchere(rs.getInt("montant_enchere"), null);

				ArticleVendu av = new ArticleVendu(rs.getInt("noArticle"), rs.getString("nomArticle"),
						rs.getString("description"),
						LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()),
								rs.getTime("date_debut_enchere").toLocalTime()),
						LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()),
								rs.getTime("date_fin_enchere").toLocalTime()),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("image"),
						rs.getString("etat_vente"), u, null, null, enchere);
				System.out.println(av);
				articles.add(av);
			}

			System.out.println("DAL - Liste d'Articles : " + articles);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return articles;
	}

	public void deleteArticle(int idArticle) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);

			pstmt.setInt(1, idArticle);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue lors de la suppression");
			throw bException;
		}

	}

}

