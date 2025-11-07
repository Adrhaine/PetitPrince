package jeu;

import java.util.Random;

/**
 * Implémentation du jeu de dé, hérite de JeuSimple.
 */
public class JeuDe extends JeuSimple {
	// Un objet pour générer des nombres aléatoires
    private Random generateur;

    /**
     * Constructeur du JeuDe
     */
    public JeuDe() {
        // On donne un nom au jeu
        this.nom = "Jeu de Dés";
        
        // On initialise le générateur de nombres aléatoires
        this.generateur = new Random();
    }

    /**
     * Simule un lancer de dé (entre 1 et 6)
     * @return Le résultat sous forme de String
     */
    @Override
    public String jouerUnTour() {
        // nextInt(6) donne un nombre entre 0 et 5
        // On ajoute 1 pour avoir un résultat entre 1 et 6
        int resultat = this.generateur.nextInt(6) + 1;
        
        // On convertit le nombre en String, comme demandé [cite: 117]
        return "" + resultat; // Astuce de conversion facile [cite: 118]
    }

    /**
     * Arbitre une partie. Le plus grand score gagne.
     * @param c1 Résultat (String) du joueur 1
     * @param c2 Résultat (String) du joueur 2
     * @return -1 si c1 gagne, +1 si c2 gagne, 0 si égalité
     */
    @Override
    public int arbitrer(String c1, String c2) {
        // On reconvertit les String en nombres (int)
        int score1 = Integer.parseInt(c1);
        int score2 = Integer.parseInt(c2);

        // On compare les scores [cite: 120]
        if (score1 > score2) {
            return -1; // j1 gagne (convention du TP) [cite: 121]
        } else if (score2 > score1) {
            return +1; // j2 gagne [cite: 121]
        } else {
            return 0; // Égalité [cite: 121]
        }
    }
}
