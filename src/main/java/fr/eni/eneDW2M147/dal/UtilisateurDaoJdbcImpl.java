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
import fr.eni.eniD2WM147.bo.Utilisateur;

public class UtilisateurDaoJdbcImpl implements UtilisateurDAO {

	private static final String SELECT_BY_EMAIL_MDP = "Select * from UTILISATEURS where (email =? and mot_de_passe =?) OR (pseudo=? and mot_de_passe =?)";
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,"
			+ "rue,code_postal,ville,credit, mot_de_passe, administrateur)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_ALL_ARTICLE = "SELECT c.libelle, art.nom_article , art.description, art.date_debut_enchere, art.date_fin_enchere, art.prix_initial, art.prix_vente, art.no_utilisateur, art.etat_vente, art.image\r\n"
			+ "FROM CATEGORIES c INNER JOIN ARTICLES_VENDUS as art ON c.no_categorie = art.no_categorie";

	public Utilisateur getUserByEmailAndPassword(String id, String mdp) throws BusinessException {

		PreparedStatement pstmt = null;
		Utilisateur utilisateur = null;

		try (Connection cnx = ConnectionProvider.getConnection();) {
			pstmt = cnx.prepareStatement(SELECT_BY_EMAIL_MDP);
			pstmt.setString(1, id);
			pstmt.setString(2, mdp);
			pstmt.setString(3, id);
			pstmt.setString(4, mdp);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int idUtilisateur = rs.getInt("no_utilisateur");
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				int credit = rs.getInt("credit");
				boolean administrateur = rs.getBoolean("administrateur");
				utilisateur = new Utilisateur(idUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal,
						ville, credit, administrateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;

		}

		return utilisateur;

	}

	public Utilisateur insertUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, int credit, boolean administrateur, String mdp)
			throws BusinessException {

		Utilisateur utilisateur = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmtp = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			stmtp.setString(1, pseudo);
			stmtp.setString(2, nom);
			stmtp.setString(3, prenom);
			stmtp.setString(4, email);
			stmtp.setString(5, telephone);
			stmtp.setString(6, rue);
			stmtp.setString(7, code_postal);
			stmtp.setString(8, ville);
			stmtp.setInt(9, credit);
			stmtp.setString(10, mdp);
			stmtp.setBoolean(11, administrateur);

			stmtp.executeUpdate();
			ResultSet rs = stmtp.getGeneratedKeys();

			if (rs.next()) {
				int idUtilisateur = rs.getInt(1);

				utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit,
						administrateur);
				utilisateur.setIdUtilisateur(idUtilisateur);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

		return utilisateur;

	}

	public List<Categorie> selectCategorie() throws BusinessException {

		List<Categorie> listecat = new ArrayList<Categorie>();
		List<ArticleVendu> art = new ArrayList<ArticleVendu>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLE);
			ResultSet rs = pstmt.executeQuery();
			Categorie catcourante = new Categorie();

			while (rs.next()) {
				if (rs.getInt("no_categorie") != catcourante.getNumCategorie()) {

					catcourante = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
					listecat.add(catcourante);
				}
				ArticleVendu arti = new ArticleVendu(rs.getString("nom_article"), rs.getString("description"),
						LocalDateTime.of(rs.getDate("date_debut_enchere").toLocalDate(),
								rs.getTime("date_debut_enchere").toLocalTime()),
						LocalDateTime.of(rs.getDate("date_fin_enchere").toLocalDate(),
								rs.getTime("date_debut_enchere").toLocalTime()),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"), rs.getString("etat_vente"), rs.getString("image"));

				catcourante.getArticles().add(arti);

			}
		} catch (SQLException e) {

			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.addMessage("erreur");
			throw be;
		}

		return listecat;

	}

}
