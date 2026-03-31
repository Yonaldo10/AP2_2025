package model;

public class LIVRE {
	private String ISBN;
	private String titre;
	private float prix;
	private AUTEUR Auteur;
	private ADHERENT Emprunteur;

	public LIVRE(String iSBN, String titre, float prix, AUTEUR auteur, ADHERENT emprunteur) {
		super();
		ISBN = iSBN;
		this.titre = titre;
		this.prix = prix;
		Auteur = auteur;
		Emprunteur = emprunteur;
	}
	public ADHERENT getEmprunteur() {
		return Emprunteur;
	}
	public void setEmprunteur(ADHERENT emprunteur) {
		Emprunteur = emprunteur;
	}

	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public AUTEUR getAuteur() {
		return Auteur;
	}
	public void setAuteur(AUTEUR auteur) {
		Auteur = auteur;
	}

	public void AFFICHER()
	{
		System.out.print("ISBN : "+ ISBN + " - titre " + titre + " - prix : "+ prix );
		if (Auteur!=null)
			System.out.print("Auteur : "+Auteur.getNom());
		else
			System.out.print(" - Auteur : Inconnu");

		if (Emprunteur!=null)
			System.out.print("Emprunteur : "+Emprunteur.getNom());
		else
			System.out.print(" - Livre Disponible");
		
		System.out.println();

	}


}
