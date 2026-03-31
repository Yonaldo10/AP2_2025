package view;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class View_Accueil {
	private JFrame frame;
	
	/**
	 * Create the application.
	 */
	public View_Accueil() {
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("BORNE BIBLIOTHÈQUE");
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Bouton CATALOGUE
		JButton btnCatalogue = new JButton("CATALOGUE");
		btnCatalogue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_catalogue vc = new View_catalogue();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCatalogue.setBounds(123, 30, 171, 40);
		frame.getContentPane().add(btnCatalogue);
		
		// Bouton EMPRUNTER
		JButton btnEmprunter = new JButton("EMPRUNTER UN LIVRE");
		btnEmprunter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Emprunt ve = new View_Emprunt();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEmprunter.setBounds(123, 90, 171, 40);
		frame.getContentPane().add(btnEmprunter);
		
		// Bouton RENDRE
		JButton btnRendre = new JButton("RENDRE UN LIVRE");
		btnRendre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Retour vr = new View_Retour();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRendre.setBounds(123, 150, 171, 40);
		frame.getContentPane().add(btnRendre);
		
		// Bouton INFO ADHÉRENT
		JButton btnInfo = new JButton("MON COMPTE");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_InfoAdherent via = new View_InfoAdherent();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnInfo.setBounds(123, 210, 171, 40);
		frame.getContentPane().add(btnInfo);
	}
}