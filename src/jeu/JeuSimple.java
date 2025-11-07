package jeu;

public abstract class JeuSimple {
	// Champ nom, comme demandé
    protected String nom;

    /**
     * Méthode pour obtenir le nom du jeu.
     * @return le nom du jeu
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Méthode abstraite pour jouer un tour.
     * Elle devra être implémentée par chaque jeu concret.
     * @return Le coup joué (sous forme de String)
     */
    public abstract String jouerUnTour();

    /**
     * Méthode abstraite pour arbitrer une partie.
     * Elle devra être implémentée par chaque jeu concret.
     * @param c1 Le coup (String) du joueur 1
     * @param c2 Le coup (String) du joueur 2
     * @return -1 si c1 gagne, +1 si c2 gagne, 0 si égalité
     */
    public abstract int arbitrer(String c1, String c2);
}
