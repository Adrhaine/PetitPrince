package main;

public class Run1Serveur {

	public static void main(String[] args) {
		try {
			String [] s = {};
			asteroide.serveur.Asteroide325Server.main(s);
		} catch (Exception e) {
			System.out.println("ERREUR SERVEUR : " + e);
		}
	}

}
