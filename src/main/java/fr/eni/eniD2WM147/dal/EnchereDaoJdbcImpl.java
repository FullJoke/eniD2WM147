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


	private static final String INSERT_BID = "INSERT INTO ENCHERES(date_enchere,montant_enchere)VALUES(?,?)";
	private static final String UPDATE_ENCHERE ="UPDATE ENCHERES "
			+ "SET no_utilisateur=?, date_enchere=GETDATE(), montant_enchere=? "
			+ "WHERE no_article = ?";
	private static final String INSERT_ENCHERE ="INSERT INTO ENCHERES VALUES (?, ?, GETDATE(), ?);";
	
	
	
	


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



	@Override
	public void enchereUpdate(int idSession, int myOffer, int idArticle) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
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
		
		try(Connection cnx = ConnectionProvider.getConnection()){
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
