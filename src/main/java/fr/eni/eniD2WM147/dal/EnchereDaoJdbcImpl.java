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
	
	
	
	

	public Enchere insertBid(LocalDateTime dateEnchere, int montantEnchere) throws BusinessException {

		Enchere bid = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmtp = cnx.prepareStatement(INSERT_BID);
			stmtp.setTimestamp(3, java.sql.Timestamp.valueOf(dateEnchere));
			stmtp.setInt(2, montantEnchere);
			ResultSet rs = stmtp.executeQuery();

			if (rs.next()) {
				bid = new Enchere(LocalDateTime.of(rs.getDate("date_enchere").toLocalDate(),
						rs.getTime("date_enchere").toLocalTime()), rs.getInt("montant_enchere"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

		return bid;

	}

	public int bidArticle() throws BusinessException {
		Utilisateur user = new Utilisateur();
		Enchere prixEnchere = new Enchere();
		ArticleVendu art = new ArticleVendu();
	

		int creditEnchere = 0;

		if (user.getCredit() > prixEnchere.getMontantEnchere()
				&& prixEnchere.getMontantEnchere() > art.getPrixVente()) {
			creditEnchere = user.getCredit() - prixEnchere.getMontantEnchere();
		
		} 
		
		else if (user.getCredit() == prixEnchere.getMontantEnchere()
				|| user.getCredit() < prixEnchere.getMontantEnchere()) {

			BusinessException bException = new BusinessException();
			bException.addMessage("Crédit insuffisant");
			throw bException;
		}

		EnchereManager.getInstance().insertBid(prixEnchere.getDateEnchere(), prixEnchere.getMontantEnchere());

		return creditEnchere;

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
