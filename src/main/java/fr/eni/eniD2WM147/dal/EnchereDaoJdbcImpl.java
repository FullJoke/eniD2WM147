package fr.eni.eniD2WM147.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import fr.eni.eniD2WM147.bll.EnchereManager;
import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;

public class EnchereDaoJdbcImpl implements EnchereDAO {

	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES "
			+ "SET no_utilisateur=?, date_enchere=GETDATE(), montant_enchere=? " + "WHERE no_article = ?";
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES VALUES (?, ?, GETDATE(), ?);";

	@Override
	public void enchereUpdate(int idSession, int myOffer, int idArticle) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
			pstmt.setInt(1, idSession);
			pstmt.setInt(2, myOffer);
			pstmt.setInt(3, idArticle);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enchereInsert(int idSession, int myOffer, int idArticle) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE);
			pstmt.setInt(1, idSession);
			pstmt.setInt(2, idArticle);
			pstmt.setInt(3, myOffer);

			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
