package fr.eni.eneDW2M147.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.eneDW2M147.businessException.BusinessException;
import fr.eni.eniD2WM147.bo.Utilisateur;

public class EnchereDaoJdbcImpl implements EnchereDAO {

	private static final String SELECT_BY_EMAIL_MDP=  "Select * from UTILISATEURS where email =? and mot_de_passe =?";
	

	public Utilisateur getUserByEmailAndPassword(String mail, String mdp) throws BusinessException {
		PreparedStatement pstmt = null;
		Utilisateur utilisateur = null;

		try (Connection cnx = ConnectionProvider.getConnection();) {
			pstmt = cnx.prepareStatement(SELECT_BY_EMAIL_MDP);
			pstmt.setString(1, mail);
			pstmt.setString(2, mdp);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("no_utilisateur");
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
				utilisateur = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit,
						administrateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;

		}

		return utilisateur;

}
}

