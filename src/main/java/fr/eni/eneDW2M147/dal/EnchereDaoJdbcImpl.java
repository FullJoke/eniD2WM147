package fr.eni.eneDW2M147.dal;

public class EnchereDaoJdbcImpl implements EnchereDAO {
	private static final String SELECT_BY_EMAIL_MDP="Select * from UTILISATEURS where email =? and mot_de_passe =?";
	
	public void VeriUtilisateur() {

	}

}
