package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import controller.mainMVC;
import model.ADHERENT;
import model.LIVRE;
import java.awt.List;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class View_InfoAdherent {
	private JFrame frame;
	private JTextField textField_num;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblEmail;
	private List list;
	private ADHERENT currentAdherent;

	public View_InfoAdherent() throws SQLException {
		mainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Champ Numéro adhérent
		JLabel lblNum = new JLabel("N° Adhérent :");
		lblNum.setBounds(28, 30, 120, 20);
		frame.getContentPane().add(lblNum);
		
		textField_num = new JTextField();
		textField_num.setBounds(150, 30, 150, 20);
		frame.getContentPane().add(textField_num);
		textField_num.setColumns(10);
		
		// Bouton Rechercher
		JButton btnRechercher = new JButton("OK");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = textField_num.getText();
				
				if (num.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Veuillez entrer un numéro d'adhérent");
					return;
				}
				
				currentAdherent = mainMVC.getM().findAdherent(num);
				
				if (currentAdherent == null) {
					lblNom.setText("Nom : Non trouvé");
					lblPrenom.setText("Prénom : ");
					lblEmail.setText("Email : ");
					list.removeAll();
					JOptionPane.showMessageDialog(frame, "Adhérent non trouvé");
					return;
				}
				
				// Afficher les infos
				lblNom.setText("Nom : " + currentAdherent.getNom());
				lblPrenom.setText("Prénom : " + currentAdherent.getPrenom());
				lblEmail.setText("Email : " + currentAdherent.getEmail());
				
				// Afficher les livres empruntés
				list.removeAll();
				for (LIVRE l : currentAdherent.getListLivre()) {
					String auteur = (l.getAuteur() == null) ? "inconnu" : l.getAuteur().getNom();
					list.add("ISBN :" + l.getISBN() + " - " + l.getTitre() + " de : " + auteur);
				}
				
				if (currentAdherent.getListLivre().isEmpty()) {
					list.add("Aucun livre emprunté");
				}
			}
		});
		btnRechercher.setBounds(310, 28, 130, 25);
		frame.getContentPane().add(btnRechercher);
		
		// Labels d'information
		lblNom = new JLabel("Nom : ");
		lblNom.setBounds(28, 80, 400, 20);
		frame.getContentPane().add(lblNom);
		
		lblPrenom = new JLabel("Prénom : ");
		lblPrenom.setBounds(28, 110, 400, 20);
		frame.getContentPane().add(lblPrenom);
		
		lblEmail = new JLabel("Email : ");
		lblEmail.setBounds(28, 140, 350, 20);
		frame.getContentPane().add(lblEmail);
		
		// Bouton MAJ EMAIL
		JButton btnMaj = new JButton("MAJ");
		btnMaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentAdherent == null) {
					JOptionPane.showMessageDialog(frame, "Veuillez d'abord rechercher un adhérent");
					return;
				}
				
				// Demander le nouvel email
				String newEmail = JOptionPane.showInputDialog(frame, "Nouvel email :");
				
				if (newEmail == null || newEmail.trim().isEmpty()) {
					return;
				}
				
				newEmail = newEmail.trim();
				
				if (!newEmail.contains("@")) {
					JOptionPane.showMessageDialog(frame, "Email invalide");
					return;
				}
				
				try {
					// Mettre à jour l'email
					mainMVC.getM().updateAdherentEmail(currentAdherent.getNum(), newEmail);
					
					// Rafraîchir l'affichage
					lblEmail.setText("Email : " + newEmail);
					
					JOptionPane.showMessageDialog(frame, "Email mis à jour");
					
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(frame, "Erreur : " + ex.getMessage());
				}
			}
		});
		btnMaj.setBounds(390, 138, 70, 25);
		frame.getContentPane().add(btnMaj);
		
		// Liste des livres empruntés
		JLabel lblLivres = new JLabel("Livres empruntés :");
		lblLivres.setBounds(28, 180, 200, 20);
		frame.getContentPane().add(lblLivres);
		
		list = new List();
		list.setBounds(28, 210, 430, 180);
		frame.getContentPane().add(list);
	}
}