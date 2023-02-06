package fr.eni.eniD2WM147.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;

public class UtilisateurDaoJdbcImpl implements UtilisateurDAO {

	private static final String SELECT_BY_EMAIL_MDP = "Select * from UTILISATEURS where (email =? and mot_de_passe =?) OR (pseudo=? and mot_de_passe =?)";
	private static final String SELECT_BY_ENCHERE = "SELECT * FROM ENCHERES e JOIN UTILISATEURS u "
			+ "ON e.no_utilisateur=u.no_utilisateur WHERE no_article = ?";

	private static final String INSERT_USER = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,credit, mot_de_passe, administrateur)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?,ville=?, mot_de_passe=?,credit=? WHERE no_utilisateur = ?;";

	private static final String DELETE_ENCHERES = "DELETE e FROM ENCHERES e JOIN ARTICLES_VENDUS av ON e.no_article=av.no_article WHERE av.no_utilisateur=?";
	private static final String DELETE_ARTICLE_VENDUS = "DELETE av FROM ARTICLES_VENDUS av Inner join UTILISATEURS u ON av.no_utilisateur=u.no_utilisateur WHERE av.no_utilisateur=?";
	private static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";

	private static final String SELECT_USER_BY_ID = "SELECT * FROM UTILISATEURS  WHERE no_utilisateur =?";

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

	@Override
	public Utilisateur updateUserProfil(String pseudo, String nom, String prenom, String email, String tel, String rue,
			String codePostal, String ville, String mdp, int credit, int idUtilisateur) throws BusinessException {
		Utilisateur user = new Utilisateur();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, nom);
			pstmt.setString(3, prenom);
			pstmt.setString(4, email);
			pstmt.setString(5, tel);
			pstmt.setString(6, rue);
			pstmt.setString(7, codePostal);
			pstmt.setString(8, ville);
			pstmt.setString(9, mdp);
			pstmt.setInt(10, credit);
			pstmt.setInt(11, idUtilisateur);
			// pas bon car id à 0
			System.out.println("id utilisateur" + idUtilisateur);
			pstmt.executeUpdate();

			user.setPseudo(pseudo);
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setEmail(email);
			user.setTelephone(tel);
			user.setRue(rue);
			user.setCodePostal(codePostal);
			user.setVille(ville);
			user.setMdp(mdp);
			user.setCredit(credit);
			user.setIdUtilisateur(idUtilisateur);

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

		return user;

	}

	public void deleteAll(int idUtilisateur) throws BusinessException {
		try {
			deleteUser(idUtilisateur);
			deleteEnchere(idUtilisateur);
			deleteArticle(idUtilisateur);

		} catch (BusinessException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}
	}

	public void deleteUser(int idUtilisateur) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
			pstmt.setInt(1, idUtilisateur);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}
	}

	// public void deleteRetrait(int numArticle) throws BusinessException {
	//
	// }
	public void deleteEnchere(int idUtilisateur) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ENCHERES);
			pstmt.setInt(1, idUtilisateur);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}
	}

	public void deleteArticle(int idUtilisateur) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE_VENDUS);
			pstmt.setInt(1, idUtilisateur);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}
	}

	public Utilisateur selectUserById(int idUtilisateur) throws BusinessException {
		System.out.println("id de l'utilisateur recherché : " + idUtilisateur);
		Utilisateur user = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_ID);
			pstmt.setInt(1, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				int credit = rs.getInt("credit");
				Boolean administrateur = rs.getBoolean("administrateur");

				user = new Utilisateur(idUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit,
						administrateur);
				System.out.println("nom du vendeur : " + user.getNom());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue lors de la recherche de l'utilisateur en base de donnnée");
			throw bException;
		}

		return user;

	}

	public Utilisateur selectByEnchere(int idArticle) throws BusinessException {
		Utilisateur u = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ENCHERE);
			pstmt.setInt(1, idArticle);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				u = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue lors de la recherche de l'utilisateur en base de donnnée");
			throw bException;
		}
		System.out.println("DAL - Utilisateur : " + u.getPseudo());
		return u;
	}

}
