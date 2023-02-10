package fr.eni.eniD2WM147.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnchereDaoJdbcImpl implements EnchereDAO {

	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES "
			+ "SET no_utilisateur=?, date_enchere=GETDATE(), montant_enchere=? " + "WHERE no_article = ?";
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES (?, ?, GETDATE(), ?);";
	private static final String UPDATE_CREDITS = "UPDATE UTILISATEURS SET credit=? WHERE no_utilisateur=?";

	@Override
	public void enchereUpdate(int idSession, int myOffer, int idArticle, int newCredits) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			try {
				cnx.setAutoCommit(false);

				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
				pstmt.setInt(1, idSession);
				pstmt.setInt(2, myOffer);
				pstmt.setInt(3, idArticle);

				pstmt.executeUpdate();
				pstmt.close();

				editCredit(idSession, newCredits);

				pstmt.close();
				cnx.commit();

			} catch (SQLException e) {
				e.printStackTrace();
				cnx.rollback();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enchereInsert(int idSession, int myOffer, int idArticle, int newCredits) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			try {
				cnx.setAutoCommit(false);
				
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE);
				pstmt.setInt(1, idSession);
				pstmt.setInt(2, idArticle);
				pstmt.setInt(3, myOffer);

				pstmt.executeUpdate();
				pstmt.close();
				
				editCredit(idSession, newCredits);
				
				pstmt.close();
				cnx.commit();
				
			} catch (SQLException e) {
				e.printStackTrace();
				cnx.rollback();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editCredit(int idSession, int newCredits) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CREDITS);
			pstmt.setInt(1, newCredits);
			pstmt.setInt(2, idSession);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
