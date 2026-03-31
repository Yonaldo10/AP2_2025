package controller;
import java.sql.SQLException;
import model.model;
import view.View_Accueil;
import java.awt.EventQueue;

public class mainMVC {
	private static model m;
	
	public static model getM() {
		return m;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("début de mon programme - ");
					m = new model();
					View_Accueil va = new View_Accueil();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}