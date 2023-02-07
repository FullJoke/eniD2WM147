package fr.eni.eniD2WM147.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;

public class EnchereDaoJdbcImpl implements EnchereDAO {

	private static final String INSERT_BID = "INSERT INTO ENCHERES(no_utilisateur,no_article,date_enchere,montant_enchere)VALUES(?,?,?,?)";
	//private static final String INSERT_BID2 = "SELECT * FROM ENCHERES WHERE no_article =? ";
	public Enchere insertBid(LocalDateTime dateEnchere,int montantEnchere,Utilisateur utilisateur,ArticleVendu article) throws BusinessException {

		Enchere bid = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmtp = cnx.prepareStatement(INSERT_BID);
			stmtp.setTimestamp(1,java.sql.Timestamp.valueOf(dateEnchere));
			stmtp.setInt(2,montantEnchere);
			stmtp.setInt(3,utilisateur.getIdUtilisateur());
			stmtp.setInt(4,article.getIdArticle());
			ResultSet rs = stmtp.executeQuery();

			if (rs.next()) {
				//bid = new Enchere(LocalDateTime.of(rs.getDate("date_enchere").toLocalDate(),
					//	rs.getTime("date_enchere").toLocalTime()), rs.getInt("montant_enchere"),(Utilisateur)rs.getInt("no_utilisateur"),rs.getInt("no_article"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

		return bid;

	}

	public Enchere bidArticle(LocalDateTime dateEnchere,int montantEnchere,Utilisateur utilisateur,ArticleVendu article) throws BusinessException {
		ArticleVendu art = null;
		Enchere prixEnchere = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERT_BID);
			pstmt.setInt(1, utilisateur.getIdUtilisateur());
			pstmt.setInt(2, article.getIdArticle());
			pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(dateEnchere));
			pstmt.setInt(4, montantEnchere);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery( );
			while (rs.next()) {
				
				if(montantEnchere > rs.getInt("montant_enchere")) {
					
					
					
					System.out.println("ooops");
				}
				
				

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prixEnchere;

	}

}

