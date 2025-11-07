package petitprince;

import asteroide.Sujet;
import asteroide.console.ConsoleJavaBoyOLD;

public class PetitPrince0 implements Sujet {

// --- ATTRIBUTS (d'après le diagramme page 4) ---
    
    private String nom;
    private int argent;
    private ConsoleJavaBoyOLD console; // La console pour interagir [cite: 41]
    private int cpt;                // Le compteur pour les tours de jeu [cite: 42]

    // --- CONSTRUCTEUR ---
    
    public PetitPrince0(String nom) {
        this.nom = nom;
        this.argent = 0; // On peut supposer qu'il commence sans argent
        this.cpt = 0;
        
        // C'est ici qu'on branche la console ! [cite: 41, 224]
        // Le constructeur de la console prend le sujet (this) en paramètre.
        this.console = new ConsoleJavaBoyOLD(this);
        
        // La ligne ci-dessus va sûrement échouer avant que le serveur soit lancé
        // C'est normal, comme indiqué dans le PDF [cite: 46]
    }

    // --- MÉTHODES DE L'INTERFACE Sujet ---

    /**
     * Méthode appelée automatiquement par le serveur à chaque tour de jeu [cite: 39]
     */
    @Override
    public void run() {
        // 1. Incrémenter le compteur et parler
        this.cpt++;
        this.console.parler("Tour de jeu N°" + this.cpt + " ! Je parle !");

        // 2. Se déplacer aléatoirement 
        // seDirigerVers(0) signifie "errer aléatoirement"
        this.console.seDirigerVers(0); 
    }

    @Override
    public String getNom() {
        return this.nom; // Doit retourner le nom du sujet 
    }

    @Override
    public int getArgent() {
        return this.argent; // Doit retourner l'argent du sujet
    }
}
