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

public class View_Emprunt {
	private JFrame frame;
	private JTextField textField_isbn;
	private JTextField textField_adherent;

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public View_Emprunt() throws SQLException {
		mainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Liste des livres disponibles
		List list = new List();
		list.setBounds(28, 27, 430, 180);
		frame.getContentPane().add(list);
		
		for (LIVRE l : mainMVC.getM().getListlivre())
		{
			// Afficher uniquement les livres disponibles
			if (l.getEmprunteur() == null)
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
				list.add("ISBN :"+l.getISBN()+" titre : "+l.getTitre()+" de : "+ auteur);
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
		
		// Champ Numéro adhérent
		JLabel lblAdherent = new JLabel("N° Adhérent :");
		lblAdherent.setBounds(28, 260, 120, 20);
		frame.getContentPane().add(lblAdherent);
		
		textField_adherent = new JTextField();
		textField_adherent.setBounds(150, 260, 150, 20);
		frame.getContentPane().add(textField_adherent);
		textField_adherent.setColumns(10);
		
		// Bouton Emprunter
		JButton btnEmprunter = new JButton("EMPRUNTER");
		btnEmprunter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String isbn = textField_isbn.getText();
					String numAdherent = textField_adherent.getText();
					
					// Vérifier que les champs ne sont pas vides
					if (isbn.isEmpty() || numAdherent.isEmpty()) {
						System.out.println("Veuillez remplir tous les champs");
						return;
					}
					
					// Vérifier que le livre existe et est disponible
					LIVRE livre = mainMVC.getM().findLivre(isbn);
					if (livre == null) {
						System.out.println("Ce livre n'existe pas");
						return;
					}
					if (livre.getEmprunteur() != null) {
						System.out.println("Ce livre est déjà emprunté");
						return;
					}
					
					// Vérifier que l'adhérent existe
					if (mainMVC.getM().findAdherent(numAdherent) == null) {
						System.out.println("Cet adhérent n'existe pas");
						return;
					}
					
					// Effectuer l'emprunt
					mainMVC.getM().emprunterLivre(isbn, numAdherent);
					System.out.println("Emprunt effectué avec succès");
					
					// Fermer la fenêtre et rafraîchir
					frame.dispose();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEmprunter.setBounds(150, 300, 150, 30);
		frame.getContentPane().add(btnEmprunter);
	}
}