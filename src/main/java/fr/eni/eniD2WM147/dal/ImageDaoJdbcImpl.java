package fr.eni.eniD2WM147.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.eniD2WM147.bo.ArticleVendu;
import fr.eni.eniD2WM147.bo.Image;

public class ImageDaoJdbcImpl {

	private final static String SELECTBYID_QUERY = "SELECT image,no_article FROM ARTICLES_VENDUS Where no_article = ?;";
	private final static String INSERT_QUERY = "INSERT INTO ARTICLES_VENDUS(image)VALUES (?);";

	public Image SelectImageById(int id) {

		Image image = null;

		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECTBYID_QUERY);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				image = new Image(rs.getString("image"), rs.getInt("no_article"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;

	}
}

