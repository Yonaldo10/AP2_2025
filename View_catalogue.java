package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.mainMVC;
import model.LIVRE;

import java.awt.List;
import java.sql.SQLException;

public class View_catalogue {

	private JFrame frame;

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public View_catalogue() throws SQLException {
		mainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		List list = new List();
		list.setBounds(28, 27, 360, 209);
		frame.getContentPane().add(list);
		for (LIVRE l : mainMVC.getM().getListlivre())
		{
			String dispo;
			if (l.getEmprunteur() == null)
			{
				dispo="disponible";
			}
			else
			{
				dispo="emprunté";
			}
			String auteur;
			if (l.getAuteur()==null)
			{
				auteur="inconnu";
			}
			else
			{
				auteur=l.getAuteur().getNom();
			}
			list.add("ISBN :"+l.getISBN()+" titre : "+l.getTitre()+" de : "+ auteur+ "("+dispo+")");
		
		}
	}
}
