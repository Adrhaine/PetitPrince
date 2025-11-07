package jeu;

import java.util.Random;

/**
 * Implémentation du jeu Shifumi (Pierre-Feuille-Ciseaux), 
 * hérite de JeuSimple.
 */
public class JeuShifumi extends JeuSimple {
	// --- ÉNUMÉRATION INTERNE ---
    // (Comme dans le diagramme de classe)
    
    /**
     * Énumération des 3 coups possibles au Shifumi
     */
    public enum Shifumi {
        PIERRE,
        FEUILLE,
        CISEAUX;
        // L'ordre ici est important pour l'arbitrage si on utilise compareTo()
        // mais nous allons gérer la logique manuellement pour plus de clarté.
    }

    // --- ATTRIBUTS ---
    
    private Random generateur;
    private Shifumi[] coupsPossibles; // Pour tirer un coup au hasard

    // --- CONSTRUCTEUR ---
    
    public JeuShifumi() {
        this.nom = "Shifumi";
        this.generateur = new Random();
        
        // On stocke les coups possibles dans un tableau pour y accéder 
        // facilement avec un index aléatoire
        this.coupsPossibles = Shifumi.values(); 
    }

    /**
     * Joue un coup aléatoire (Pierre, Feuille ou Ciseaux)
     * @return Le coup sous forme de String
     */
    @Override
    public String jouerUnTour() {
        // On tire un index aléatoire (0, 1, ou 2)
        int index = this.generateur.nextInt(this.coupsPossibles.length);
        
        // On récupère le coup correspondant
        Shifumi coupJoue = this.coupsPossibles[index];
        
        // On le retourne sous forme de String
        // (ex: Shifumi.PIERRE devient "PIERRE")
        return coupJoue.toString();
    }

    /**
     * Arbitre une partie de Shifumi.
     * @param c1 Coup (String) du joueur 1
     * @param c2 Coup (String) du joueur 2
     * @return -1 si c1 gagne, +1 si c2 gagne, 0 si égalité
     */
    @Override
    public int arbitrer(String c1, String c2) {
        
        // On reconvertit les String en énumération Shifumi
        Shifumi coup1 = Shifumi.valueOf(c1);
        Shifumi coup2 = Shifumi.valueOf(c2);

        // Cas 1 : Égalité
        if (coup1 == coup2) {
            return 0;
        }

        // Cas 2 : J1 gagne
        // (Pierre bat Ciseaux) OU (Feuille bat Pierre) OU (Ciseaux bat Feuille)
        if ((coup1 == Shifumi.PIERRE && coup2 == Shifumi.CISEAUX) ||
            (coup1 == Shifumi.FEUILLE && coup2 == Shifumi.PIERRE) ||
            (coup1 == Shifumi.CISEAUX && coup2 == Shifumi.FEUILLE)) 
        {
            return -1; // j1 gagne
        }
        
        // Cas 3 : J2 gagne (tous les autres cas)
        return +1; // j2 gagne
    }
}
