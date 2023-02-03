package fr.eni.eniDW2M147.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import fr.eni.eniD2WM147.bo.Enchere;
import fr.eni.eniDW2M147.businessException.BusinessException;

public class EnchereDaoJdbcImpl implements EnchereDAO{
	
	private static final String INSERT_BID = "INSERT INTO ENCHERES(date_enchere,montant_enchere)VALUES(?,?)";

	
	
	public Enchere insertBid(LocalDateTime dateEnchere, int montantEnchere) throws BusinessException {

		
		Enchere bid = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmtp = cnx.prepareStatement(INSERT_BID);
			stmtp.setTimestamp(3, java.sql.Timestamp.valueOf(dateEnchere));
			stmtp.setInt(2, montantEnchere);
			ResultSet rs = stmtp.executeQuery();

			if (rs.next()) {
			 bid = new Enchere( LocalDateTime.of(rs.getDate("date_enchere").toLocalDate(),
                    rs.getTime("date_enchere").toLocalTime()),rs.getInt("montant_enchere"));
				}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException bException = new BusinessException();
			bException.addMessage("une erreur est survenue");
			throw bException;
		}

		return bid;
		
		
		
	}
}