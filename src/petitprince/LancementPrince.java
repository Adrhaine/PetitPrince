package petitprince;

/**
 * Cette classe contient juste un main pour lancer un (ou plusieurs) 
 * de nos Petits Princes.
 */
public class LancementPrince {
	public static void main(String[] args) {
		// On definit le nombre de princes à lancer
        int nombreDePrinces = 10; 

        System.out.println("--- Lancement de " + nombreDePrinces + " Petits Princes ---");

        // On utilise une boucle 'for' pour créer les 10 princes
        for (int i = 1; i <= nombreDePrinces; i++) {
            
            // On crée un nom unique pour chaque prince
            String nomDuPrince = "Prince " + i;
            
            // On crée l'instance du prince.
            // Son constructeur s'occupe de le connecter au serveur.
            PetitPrince prince = new PetitPrince(nomDuPrince);
            
            // On affiche un message de lancement
            System.out.println("Lancement de " + prince.getNom() + "...");
        }
        
        System.out.println("--- Tous les princes sont lancés ! ---");
    }
}
