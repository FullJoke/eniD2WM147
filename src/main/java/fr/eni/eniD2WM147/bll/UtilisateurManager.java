package fr.eni.eniD2WM147.bll;
import java.util.regex.Pattern;
import fr.eni.eniD2WM147.bo.Utilisateur;
import fr.eni.eniD2WM147.businessException.BusinessException;
import fr.eni.eniD2WM147.dal.EnchereDAOFactory;
import fr.eni.eniD2WM147.dal.UtilisateurDAO;
public class UtilisateurManager {
    private static final String regexVerifNom = "\\D\\s\\w";
    private static final String regexVerifPrenom = "\\D\\S";
    private static final String regexVerifMail = "(\\w^\\S)[@](\\D^\\s\\w)[.](\\D){2,3}";
    private static final String regexVerifTel = "(^0[0-9]){9}";
    private static final String regexVerifCP = "\\d";
    private static final String regexVerifVille = "\\w\\s";
    private static final String regexVerifRue = "\\w\\s^\\s";
    private static final String regewVerifMdp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8}$";
    private UtilisateurDAO utilisateurDAO;
    private static UtilisateurManager instance;
    private UtilisateurManager() {
        this.utilisateurDAO = EnchereDAOFactory.getUtilisateurDAO();
    }
    public static UtilisateurManager getInstance() {
        if (instance == null) {
            instance = new UtilisateurManager();
        }
        return instance;
    }
    public Utilisateur getUserByEmailAndPassword(String id, String mdp) throws BusinessException {
        BusinessException bE = new BusinessException();
        if (!bE.getListeMessage().isEmpty()) {
            throw bE;
        }
        return utilisateurDAO.getUserByEmailAndPassword(id, mdp);
    }
    public Utilisateur insertUtilisateur(String pseudo, String nom, String prenom, String email, String tel, String rue,
            String codePostal, String ville, int i, boolean b, String mdp)
            throws BusinessException {
        BusinessException bE = new BusinessException();
        validerUtilisateur(pseudo, nom, prenom, email, tel, rue, ville, codePostal, mdp, bE);
        if (!bE.getListeMessage().isEmpty()) {
            throw bE;
        }
        Utilisateur user = utilisateurDAO.insertUtilisateur(pseudo, nom, prenom, email, tel, rue, codePostal, ville, i,
                b, mdp);
        return user;
    }
    public void validerUtilisateur(String pseudo, String nom, String prenom, String mail, String telephone, String rue,
            String ville, String codePostal, String mdp, BusinessException businessException) {
        if (pseudo == null || !pseudo.chars().allMatch(Character::isLetterOrDigit)) {
            businessException.addMessage(
                    "Le pseudo est obligatoire et le pseudo ne peut contenir que des chiffres et des lettres");
        }
        if (pseudo.length() > 30 || pseudo.length() < 3) {
            businessException.addMessage("Le pseudo doit etre compris entre 3 et 30 caractères");
        }
        if (nom == null || Pattern.matches(regexVerifNom, nom)) {
            businessException.addMessage("Le nom est obligatoire et ne peut contenir que des lettres.");
        }
        if (nom.length() > 30 || nom.length() < 2) {
            businessException.addMessage("Le nom doit etre compris entre 2 et 30 caractères");
        }
        if (prenom == null || Pattern.matches(regexVerifPrenom, prenom)) {
            businessException.addMessage("Le prénom est obligatoire et ne peut contenir que des lettres.");
        }
        if (prenom.length() > 30 || prenom.length() < 2) {
            businessException.addMessage("Le nom doit etre compris entre 2 et 30 caractères");
        }
        if (mail == null || Pattern.matches(regexVerifMail, mail)) {
            businessException.addMessage("Le mail est obligatoire");
        }
        if (mail.length() > 20) {
            businessException.addMessage("Le mail ne doit pas dépasser 20 caractères");
        }
        if (telephone == null || Pattern.matches(regexVerifTel, telephone)) {
            businessException.addMessage("Le telephone est obligatoire et doit contenir 10 chiffres");
        }
        if (rue == null || Pattern.matches(regexVerifRue, rue)) {
            businessException.addMessage("La rue est obligatoire.");
        }
        if (rue.length() > 30) {
            businessException.addMessage("Le nom de la rue ne doit pas dépasser 30 caractères");
        }
        if ((ville == null) || Pattern.matches(regexVerifVille, ville)) {
            businessException.addMessage("La ville est obligatoire.");
        }
        if (ville.length() > 30) {
            businessException.addMessage("Le nom de la ville ne doit pas dépasser 30 caractères");
        }
        if (codePostal == null || Pattern.matches(regexVerifCP, codePostal)) {
            businessException.addMessage("Le code postal est obligatoire.");
        }
        if (codePostal.length() != 5) {
            businessException.addMessage("Le code postal ne doit pas dépasser 10 caractères");
        }
        if (mdp == null || Pattern.matches(regewVerifMdp, mdp)) {
            businessException.addMessage("Le mot de passe est obligatoire.");
        }
        if (mdp.length() != 8) {
            businessException.addMessage("Le mot de passe doit contenir 8 caractères");
        }
    }
    public Utilisateur updateUserProfil(String pseudo, String nom, String prenom, String email, String tel, String rue,
            String codePostal, String ville, String mdp, int credit, int idUtilisateur) throws BusinessException {
        BusinessException bE = new BusinessException();
        if (!bE.getListeMessage().isEmpty()) {
            throw bE;
        }
        validerUtilisateur(pseudo, nom, prenom, email, tel, rue, ville, codePostal, mdp, bE);
        Utilisateur user = utilisateurDAO.updateUserProfil(pseudo, nom, prenom, email, tel, rue, codePostal, ville, mdp,
                credit, idUtilisateur);
        return user;
    }
    public void deleteAll(int idUtilisateur) throws BusinessException {
        BusinessException bE = new BusinessException();
        if (!bE.getListeMessage().isEmpty()) {
            throw bE;
        }
        utilisateurDAO.deleteAll(idUtilisateur);
    }
    public Utilisateur getUtilisateurByEnchere(int idArticle) throws BusinessException {
        System.out.println("BLL - idArticle : " + idArticle);
        return utilisateurDAO.selectByEnchere(idArticle);
    }
    public Utilisateur getUtilisateurById(int idUtilisateur) throws BusinessException {
        System.out.println("BLL - id de l'utilisateur recherché : " + idUtilisateur);
        return utilisateurDAO.selectUserById(idUtilisateur);
    }
}