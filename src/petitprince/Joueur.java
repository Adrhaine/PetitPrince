package petitprince;

/**
 * Interface pour tout sujet capable de jouer et de
 * gagner ou perdre de l'argent.
 */
public interface Joueur {
	/**
     * Méthode appelée lorsque le joueur gagne.
     * @param argent la somme gagnée
     */
    void gagner(int argent);
    
    /**
     * Méthode appelée lorsque le joueur perd.
     * @param argent la somme perdue
     */
    void perdre(int argent);
}
