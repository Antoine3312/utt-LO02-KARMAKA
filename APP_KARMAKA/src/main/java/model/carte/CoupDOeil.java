// Importation des classes nécessaires
import application.control.Renderable;
import model.joueur.Joueur;
import model.joueur.Ordinateur;

// Carte "CoupDOeil" qui hérite de la classe abstraite "Carte"
public abstract class CoupDOeil extends Carte {

    // Constructeur de la carte "CoupDOeil"
    public CoupDOeil(Renderable renderable) {
        super(renderable);
        this.point = 1; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    // CODER : COPIER POUVOIR (Cette partie est laissée à implémenter)

    // Méthode privée pour défausser automatiquement une carte (utilisée par l'ordinateur)
    private void defausserAutomatiquement(Joueur joueur) {
        // Logique pour que l'ordinateur choisisse et défausse automatiquement une carte de sa main
        // Vous pouvez adapter cette logique en fonction des règles spécifiques du jeu
        if (!joueur.getMain().getCartes().isEmpty()) {
            // Choix aléatoire de la première carte de la main
            Carte carteADefausser = joueur.getMain().getCartes().get(0);
            // Retirer la carte de la main du joueur
            joueur.getMain().getCartes().remove(carteADefausser);
            // Ajouter la carte à la défausse du joueur
            joueur.getDefausse().getCartes().add(carteADefausser);

            // Copier le pouvoir de cette carte
            Carte copieDeni = new CoupDOeil(this.renderer);
            copieDeni.jouerPouvoir(joueur, joueur);
        }
    }
}
