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

	private static final String INSERT_BID = "INSERT INTO ENCHERES(no_utilisateur,no_article,date_enchere,montant_enchere)VALUES(?,?,?,?)";
	private static final String BID_ARTICLE = "UPDATE ENCHERES SET no_utilisateur=?,date_enchere=GETDATE(),montant_enchere=? WHERE no_article=?";

	public Enchere insertBid(LocalDateTime dateEnchere, int montantEnchere, Utilisateur utilisateur,
			ArticleVendu article) throws BusinessException {

		ArticleVendu art;
		Utilisateur user;
		Enchere bid = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmtp = cnx.prepareStatement(INSERT_BID);
			stmtp.setInt(3, utilisateur.getIdUtilisateur());
			stmtp.setInt(4, article.getIdArticle());
			stmtp.setTimestamp(1, java.sql.Timestamp.valueOf(dateEnchere));
			stmtp.setInt(2, montantEnchere);
			stmtp.executeUpdate();
			ResultSet rs = stmtp.getResultSet();

			if (rs.next()) {

				LocalDateTime dateEn = LocalDateTime.of(rs.getDate("date_enchere").toLocalDate(),
						rs.getTime("date_enchere").toLocalTime());
				int montantEnch = rs.getInt("montant_enchere");
				int noUtilisateur = rs.getInt("no_utilisateur");
				int noArtcli = rs.getInt("no_article");

				bid = new Enchere(dateEnchere, montantEnchere, utilisateur, article);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

		return bid;

	}

	public void bidArticle(int montantEnchere, int idUtilisateur, LocalDateTime now, int idArticle)
			throws BusinessException {

		Enchere prixEnchere = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(BID_ARTICLE);

			
			pstmt.setInt(1, idUtilisateur);
			pstmt.setInt(2, montantEnchere);
			pstmt.setInt(3, idArticle);

			pstmt.executeUpdate();

//			if (rs.next()) {
//
//				int montantEnch = rs.getInt("montant_enchere");
//
//				if (prixEnchere.getMontantEnchere() > montantEnch) {
//
//					System.out.println("ooops");
//
//					prixEnchere = EnchereManager.getInstance().insertBid(prixEnchere.getDateEnchere(),
//							prixEnchere.getMontantEnchere(), prixEnchere.getUtilisateur(), prixEnchere.getArticle());
//
//				}
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		
//
//		return prixEnchere;
//
//	}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
