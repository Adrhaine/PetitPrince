package jeu;

import petitprince.Joueur;

public class TestArbitre {
	/**
     * Méthode statique qui simule une partie entre deux joueurs.
     * * @param j1   Le premier joueur
     * @param j2   Le deuxième joueur
     * @param jeu  Le jeu auquel ils jouent
     */
    public static void arbitrer(Joueur j1, Joueur j2, JeuSimple jeu) {
        // 1. On fait jouer chaque joueur
        String coup1 = jeu.jouerUnTour();
        String coup2 = jeu.jouerUnTour();

        // 2. On affiche les coups
        System.out.println("J1 joue: " + coup1);
        System.out.println("J2 joue: " + coup2);

        // 3. On arbitre la partie
        int resultat = jeu.arbitrer(coup1, coup2);

        // 4. On affiche le résultat
        if (resultat == -1) {
            System.out.println("Gagnant: J1");
            // Plus tard, on appellera j1.gagner() et j2.perdre()
        } else if (resultat == +1) {
            System.out.println("Gagnant: J2");
            // Plus tard, on appellera j2.gagner() et j1.perdre()
        } else {
            System.out.println("Egalite");
        }
    }

    /**
     * Main pour tester nos jeux
     */
    public static void main(String[] args) {
        
        // On crée nos deux jeux
        JeuSimple jeuDe = new JeuDe();
        JeuSimple jeuShifumi = new JeuShifumi();

        // --- Test du Jeu de Dés ---
        System.out.println("--- Jouons maintenant au jeu " + jeuDe.getNom() + " ---");
        // Le TP demande de mettre null pour les joueurs pour l'instant [cite: 148]
        arbitrer(null, null, jeuDe); 

        System.out.println(); // Saute une ligne

        // --- Test du Jeu Shifumi ---
        System.out.println("--- Jouons maintenant au jeu " + jeuShifumi.getNom() + " ---");
        arbitrer(null, null, jeuShifumi);
    }
}
