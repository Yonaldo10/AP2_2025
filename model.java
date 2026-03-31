package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class model {

    private ArrayList<ADHERENT> listadherent;
    private ArrayList<AUTEUR> listauteur;
    private ArrayList<LIVRE> listlivre;

    private Connection conn;

    public ArrayList<ADHERENT> getListadherent() {
        return listadherent;
    }

    public void setListadherent(ArrayList<ADHERENT> listadherent) {
        this.listadherent = listadherent;
    }

    public ArrayList<AUTEUR> getListauteur() {
        return listauteur;
    }

    public void setListauteur(ArrayList<AUTEUR> listauteur) {
        this.listauteur = listauteur;
    }

    public ArrayList<LIVRE> getListlivre() {
        return listlivre;
    }

    public void setListlivre(ArrayList<LIVRE> listlivre) {
        this.listlivre = listlivre;
    }

    // Charger toutes les données
    public void getall() throws SQLException {
        listlivre.clear();
        listauteur.clear();
        listadherent.clear();

        Statement stmt = conn.createStatement();

        // AUTEURS
        ResultSet resultat = stmt.executeQuery("SELECT num,nom,prenom,date_naissance,description FROM AUTEUR");

        while (resultat.next()) {
            AUTEUR a = new AUTEUR(
                    resultat.getString(1),
                    resultat.getString(2),
                    resultat.getString(3),
                    resultat.getString(4),
                    resultat.getString(5)
            );
            listauteur.add(a);
        }

        // ADHERENTS
        resultat = stmt.executeQuery("SELECT * FROM ADHERENT");

        while (resultat.next()) {
            ADHERENT ad = new ADHERENT(
                    resultat.getString(1),
                    resultat.getString(2),
                    resultat.getString(3),
                    resultat.getString(4),
                    new ArrayList<LIVRE>()
            );
            listadherent.add(ad);
        }

        // LIVRES
        resultat = stmt.executeQuery("SELECT * FROM LIVRE");

        while (resultat.next()) {

            AUTEUR a = null;
            ADHERENT ad = null;

            // Recherche auteur
            String numauteur = resultat.getString("auteur");
            if (numauteur != null) {
                a = findAuteur(numauteur);
            }

            // Recherche adherent
            String numadherent = resultat.getString("adherent");
            if (numadherent != null) {
                ad = findAdherent(numadherent);
            }

            // Création livre
            LIVRE l = new LIVRE(
                    resultat.getString("ISBN"),
                    resultat.getString("titre"),
                    resultat.getFloat("prix"),
                    a,
                    ad
            );
            listlivre.add(l);

            // Ajouter le livre à l’adhérent
            if (numadherent != null) {
                ad.getListLivre().add(l);
            }
        }
    }

    public AUTEUR findAuteur(String num) {
        for (int i = 0; i < listauteur.size(); i++) {
            if (num.equals(listauteur.get(i).getNum())) {
                return listauteur.get(i);
            }
        }
        return null;
    }

    public ADHERENT findAdherent(String num) {
        for (int i = 0; i < listadherent.size(); i++) {
            if (num.equals(listadherent.get(i).getNum())) {
                return listadherent.get(i);
            }
        }
        return null;
    }

    public LIVRE findLivre(String num) {
        for (int i = 0; i < listlivre.size(); i++) {
            if (num.equals(listlivre.get(i).getISBN())) {
                return listlivre.get(i);
            }
        }
        return null;
    }

    // Emprunter un livre
    public void emprunterLivre(String isbn, String numAdherent) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "UPDATE LIVRE SET adherent = '" + numAdherent + "' WHERE ISBN = '" + isbn + "'";
        stmt.executeUpdate(sql);

        getall();
    }

    // Rendre un livre
    public void rendreLivre(String isbn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "UPDATE LIVRE SET adherent = NULL WHERE ISBN = '" + isbn + "'";
        stmt.executeUpdate(sql);

        getall();
    }

    // Mettre à jour email adhérent
    public void updateAdherentEmail(String numAdherent, String newEmail) throws SQLException {

        String sql = "UPDATE ADHERENT SET Email = ? WHERE Num = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newEmail);
            pstmt.setString(2, numAdherent);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {

                for (ADHERENT adherent : listadherent) {
                    if (adherent.getNum().equals(numAdherent)) {
                        adherent.setEmail(newEmail);
                        break;
                    }
                }
            }
        }
    }

    // CONSTRUCTEUR : CONNEXION MYSQL
    public model() throws ClassNotFoundException, SQLException {

        listlivre = new ArrayList<LIVRE>();
        listadherent = new ArrayList<ADHERENT>();
        listauteur = new ArrayList<AUTEUR>();

        String BDD = "ap2_biblio";
        String url = "jdbc:mysql://localhost:3306/" + BDD
                + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

        String user = "root";
        String mdp = "";

        // DRIVER CORRECT
        Class.forName("com.mysql.cj.jdbc.Driver");

        conn = DriverManager.getConnection(url, user, mdp);
        System.out.println("connexion OK");
    }
}
