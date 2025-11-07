package petitprince;

//Imports des packages fournis
import asteroide.Sujet;
import asteroide.console.ConsoleJavaBoy;

//Imports de vos classes de jeu
import jeu.JeuDe;
import jeu.JeuShifumi;
import jeu.JeuSimple;

//Imports Java nécessaires
import java.util.ArrayList;
import java.util.Random;

public class PetitPrince implements Sujet, Joueur {

// --- ATTRIBUTS (d'après le diagramme page 4) ---
    
// --- ATTRIBUTS (d'après le diagramme page 10) ---
    
    private String nom;
    private int argent;
    private ConsoleJavaBoy console;
    private int cpt; // Compteur de tours
    
    // Nouveaux attributs pour l'IA
    private ArrayList<Integer> sujetsConnus;
    private ArrayList<JeuSimple> mesJeux;
    private Random generateurAleatoire;

    
    // --- CONSTRUCTEUR ---
    
    public PetitPrince(String nom) {
        this.nom = nom;
        this.argent = 100; // On lui donne 100 pour commencer
        this.cpt = 0;
        
        // Connexion de la console
        this.console = new ConsoleJavaBoy(this);
        
        // Initialisation des nouveaux attributs
        this.sujetsConnus = new ArrayList<>();
        this.generateurAleatoire = new Random();
        
        // On crée la liste des jeux auxquels notre prince sait jouer
        this.mesJeux = new ArrayList<>();
        this.mesJeux.add(new JeuDe());
        this.mesJeux.add(new JeuShifumi());
    }

    
    // --- MÉTHODES DE L'INTERFACE Sujet ---

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public int getArgent() {
        return this.argent;
    }

    
    // --- MÉTHODES DE L'INTERFACE Joueur ---

    @Override
    public void gagner(int argentGagne) {
        this.argent += argentGagne;
        this.console.parler("Super ! J'ai gagné " + argentGagne + " ! J'ai maintenant " + this.argent);
    }

    @Override
    public void perdre(int argentPerdu) {
        this.argent -= argentPerdu;
        this.console.parler("Zut... j'ai perdu " + argentPerdu + "... Il me reste " + this.argent);
    }

    
    // --- MÉTHODES D'AIDE POUR L'IA (Page 10) ---

    /**
     * Calcule le max de deux entiers
     */
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    /**
     * Calcule la valeur absolue d'un entier
     */
    private int abs(int a) {
        return (a < 0) ? -a : a;
    }

    /**
     * Calcule la distance de Chebyshev entre notre position (centre 10,10)
     * et la coordonnée (l, c).
     */
    private int distanceChebyshev(int l, int c) {
        int centre = 10; // Le prince est toujours au centre (10,10) de sa vision
        int dL = abs(l - centre); // Distance en lignes
        int dC = abs(c - centre); // Distance en colonnes
        return max(dL, dC);
    }

    /**
     * Cherche le sujet le plus proche dans la vision
     * @param vision Le tableau 2D renvoyé par console.regarder()
     * @return un tableau [id, ligne, colonne] du sujet le plus proche, 
     * ou null si personne n'est vu.
     */
    private int[] chercherPlusProche(int[][] vision) {
    	int[] plusProche = null;
        int minDist = 1000; // Une distance maximale arbitraire
        int centre = 10; // Position du prince au centre de la vision 

        // On parcourt la grille de vision (20x20)
        for (int l = 0; l < vision.length; l++) {
            for (int c = 0; c < vision[l].length; c++) {
                
                // --- DÉBUT DE LA CORRECTION ---
                
                // 1. On ignore le centre (c'est nous-mêmes)
                if (l == centre && c == centre) {
                    continue; // On passe à la case suivante
                }
                
                int idSujet = vision[l][c];
                
                // 2. On vérifie si la case n'est pas vide (id > 0)
                //    ET si on n'a pas déjà joué avec (non-connu)
                if (idSujet > 0 && !this.sujetsConnus.contains(idSujet)) {
                    
                // --- FIN DE LA CORRECTION ---
                    
                    int dist = distanceChebyshev(l, c);
                    
                    if (dist < minDist) {
                        minDist = dist;
                        plusProche = new int[]{idSujet, l, c};
                    }
                }
            }
        }
        return plusProche;
    }

    
    // --- MÉTHODE PRINCIPALE (IA) ---

    /**
     * Méthode "cœur" du PetitPrince, appelée à chaque tour par le serveur.
     * Contient l'algorithme de décision.
     */
    @Override
    public void run() {
        this.cpt++;
        
        // 1. REGARDER
        // V = regarder
        int[][] vision = this.console.regarder();
        
        // 2. CHERCHER LE PLUS PROCHE
        // S = chercher le plus proche sujet dans V
        int[] S = chercherPlusProche(vision);
        
        // 3. AGIR
        if (S != null) {
            // S contient [id, ligne, colonne]
            int idSujet = S[0];
            int distance = distanceChebyshev(S[1], S[2]);

            // SI (distance (S)<=1) ALORS
            if (distance <= 1) {
                // --- JOUER ---
                // On choisit un jeu au hasard dans notre liste
                int indexJeu = this.generateurAleatoire.nextInt(this.mesJeux.size());
                JeuSimple jeuAProposer = this.mesJeux.get(indexJeu);
                
                this.console.parler("Salut " + idSujet + " ! Jouons à " + jeuAProposer.getNom() + " !");
                
                // On joue...
                this.console.jouer(jeuAProposer, idSujet);
                // ...et on ajoute le sujet à la liste des connus pour ne plus jouer avec
                this.sujetsConnus.add(idSujet);

            } else {
                // SINON se diriger vers S
                this.console.parler("Je me dirige vers " + idSujet + "...");
                this.console.seDirigerVers(idSujet);
            }
            
        } else {
            // SINON (S est null) ALORS se diriger au hasard
            this.console.parler("Je n'ai personne à qui parler, j'erre...");
            this.console.seDirigerVers(0); // 0 = errer
        }
    }
}
