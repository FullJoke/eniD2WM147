package fr.eni.eneDW2M147.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eniD2WM147.bo.Utilisateur;

public class EnchereDaoJdbcImpl implements EnchereDAO {

	private static final String SELECT_BY_EMAIL_MDP = "Select * from UTILISATEURS where (email =? and mot_de_passe =?) OR (pseudo=? and mot_de_passe =?)";
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,"
			+ "rue,code_postal,ville,credit,administrateur)VALUES(?,?,?,?,?,?,?,?,?,?)";

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
			String rue, String code_postal, String ville, int credit, boolean administrateur) throws BusinessException {

		PreparedStatement stmtp = null;
		Utilisateur utilisateurs = null;

		Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();

			stmtp = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			stmtp.setString(1, pseudo);
			stmtp.setString(2, nom);
			stmtp.setString(3, prenom);
			stmtp.setString(4, email);
			stmtp.setString(5, telephone);
			stmtp.setString(6, rue);
			stmtp.setString(7, code_postal);
			stmtp.setString(8, ville);
			stmtp.setInt(9, credit);
			stmtp.setBoolean(10, administrateur);

			stmtp.executeQuery();
			ResultSet rs = stmtp.getGeneratedKeys();

			if (rs.next()) {
				String pseudo1 = rs.getString("pseudo");
				String nom1 = rs.getString("nom");
				String prenom1 = rs.getString("prenom");
				String email1 = rs.getString("email");
				String telephone1 = rs.getString("telephone");
				String rue1 = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville1 = rs.getString("ville");
				int credit1 = rs.getInt("credit");
				boolean administrateur1 = rs.getBoolean("administrateur");
				utilisateurs = new Utilisateur(pseudo1, nom1, prenom1, email1, telephone1, rue1, codePostal, ville1,
						credit1, administrateur1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

		return utilisateurs;

	}



}
