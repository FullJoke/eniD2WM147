import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.bo.Vente;
import fr.eni.eniDW2M147.dal.ConnectionProvider;
import fr.eni.eniDW2M147.dal.VenteDAO;

public class venteDAOJdbcImpl implements VenteDAO {
	private final String SELECT_VENTE = "SELECT av.nom_article, av.description, c.libelle, av.date_fin_enchere, e.montant_enchere, e.no_utilisateur, av.prix_initial, r.rue, r.code_postal, r.ville, u.pseudo\r\n"
			+ "FROM ARTICLES_VENDUS av JOIN CATEGORIES c ON av.no_categorie = c.no_categorie\r\n"
			+ "						JOIN RETRAITS r ON av.no_article = r.no_article\r\n"
			+ "						JOIN UTILISATEURS u ON av.no_utilisateur = u.no_utilisateur\r\n"
			+ "						JOIN ENCHERES e ON av.no_article = e.no_article\r\n" + "WHERE av.no_article=?";

	@Override
	public Vente selectVente(int idArticle) {
		Vente v = null;
//
//		try (Connection cnx = ConnectionProvider.getConnection()) {
//			PreparedStatement pstmt = cnx.prepareStatement(SELECT_VENTE);
//			pstmt.setInt(1, idArticle);
//
//			ResultSet rs = pstmt.executeQuery();

//			if (rs.next()) {
//				v = new Vente(rs.getString("nom_article"),
//							  rs.getString("description"),
//							  rs.getString("libelle"),
//							  rs.getS,
//							  rs.getInt("montant_enchere"),
//							  u,
//							  null, null)
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
		return v;
	}

}
