package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import controller.mainMVC;
import model.LIVRE;
import java.awt.List;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View_Retour {
	private JFrame frame;
	private JTextField textField_isbn;
	private JTextField textField_adherent; // Nouveau champ pour le numéro d'adhérent

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public View_Retour() throws SQLException {
		mainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Titre de la fenêtre
		frame.setTitle("Retour de livre");
		
		// Liste des livres empruntés
		List list = new List();
		list.setBounds(28, 27, 430, 180);
		frame.getContentPane().add(list);
		
		for (LIVRE l : mainMVC.getM().getListlivre())
		{
			// Afficher uniquement les livres empruntés
			if (l.getEmprunteur() != null)
			{
				String auteur;
				if (l.getAuteur()==null)
				{
					auteur="inconnu";
				}
				else
				{
					auteur=l.getAuteur().getNom();
				}
				list.add("ISBN :"+l.getISBN()+" titre : "+l.getTitre()+" de : "+ auteur+ " (emprunté par : "+l.getEmprunteur().getNom()+" - N°"+l.getEmprunteur().getNum()+")");
			}
		}
		
		// Champ ISBN
		JLabel lblIsbn = new JLabel("ISBN du livre :");
		lblIsbn.setBounds(28, 230, 120, 20);
		frame.getContentPane().add(lblIsbn);
		
		textField_isbn = new JTextField();
		textField_isbn.setBounds(150, 230, 150, 20);
		frame.getContentPane().add(textField_isbn);
		textField_isbn.setColumns(10);
		
		// NOUVEAU : Champ Numéro adhérent
		JLabel lblAdherent = new JLabel("Votre N° Adhérent :");
		lblAdherent.setBounds(28, 260, 120, 20);
		frame.getContentPane().add(lblAdherent);
		
		textField_adherent = new JTextField();
		textField_adherent.setBounds(150, 260, 150, 20);
		frame.getContentPane().add(textField_adherent);
		textField_adherent.setColumns(10);
		
		// Bouton Rendre
		JButton btnRendre = new JButton("RENDRE");
		btnRendre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String isbn = textField_isbn.getText().trim();
					String numAdherent = textField_adherent.getText().trim();
					
					// Vérifier que les champs ne sont pas vides
					if (isbn.isEmpty() || numAdherent.isEmpty()) {
						System.out.println("Veuillez remplir tous les champs");
						return;
					}
					
					// Vérifier que le livre existe
					LIVRE livre = mainMVC.getM().findLivre(isbn);
					if (livre == null) {
						System.out.println("Ce livre n'existe pas");
						return;
					}
					
					// Vérifier que le livre est emprunté
					if (livre.getEmprunteur() == null) {
						System.out.println("Ce livre n'est pas emprunté");
						return;
					}
					
					// IMPORTANT : Vérifier que l'adhérent qui rend est bien celui qui a emprunté
					if (!livre.getEmprunteur().getNum().equals(numAdherent)) {
						System.out.println("Vous ne pouvez pas rendre un livre que vous n'avez pas emprunté !");
						System.out.println("Ce livre est emprunté par : " + livre.getEmprunteur().getNom() + " (N°" + livre.getEmprunteur().getNum() + ")");
						return;
					}
					
					// Effectuer le retour
					mainMVC.getM().rendreLivre(isbn);
					System.out.println("Retour effectué avec succès par l'adhérent N°" + numAdherent);
					
					// Fermer la fenêtre
					frame.dispose();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRendre.setBounds(150, 300, 150, 30);
		frame.getContentPane().add(btnRendre);
	}
}