package model.carte;

import application.control.Renderable;
import model.joueur.Joueur;

import java.util.List;
import java.util.Scanner;

/**
 * Carte "Destinee" qui hérite de la classe abstraite "Carte".
 * Représente une carte permettant au joueur appelant de regarder les 3 premières cartes de la Source,
 * de choisir jusqu'à 2 cartes à ajouter à sa Vie Future, et de replacer le reste dans l'ordre souhaité.
 */
public abstract class Destinee extends Carte {

    /**
     * Constructeur de la carte "Destinee".
     *
     * @param renderable L'objet permettant le rendu visuel.
     */
    public Destinee(Renderable renderable) {
        super(renderable);
        this.point = 2; // Définition du nombre de points attribués par cette carte
        this.couleur = NomCouleur.BLEU; // Définition de la couleur de la carte
    }

    /**
     * Méthode pour jouer le pouvoir de la carte "Destinee".
     * Le joueur appelant regarde les 3 premières cartes de la Source,
     * choisit jusqu'à 2 cartes à ajouter à sa Vie Future,
     * et replace le reste dans l'ordre souhaité.
     *
     * @param joueurAppelant Le joueur qui joue la carte.
     * @param joueurReceveur Non utilisé dans ce contexte.
     */
    @Override
    public void jouerPouvoir(Joueur joueurAppelant, Joueur joueurReceveur) {
        // Regardez les 3 premières cartes de la Source
        List<Carte> cartesSource = joueurAppelant.getPartie().getSource().getCartes();
        List<Carte> cartesVieFuture = joueurAppelant.getVieFutur().getCartes();

        // Affichez les 3 premières cartes de la Source
        List<Carte> cartesVisible = cartesSource.subList(0, Math.min(cartesSource.size(), 3));
        this.renderer.afficherCartes(cartesVisible);

        // Choisissez jusqu'à 2 cartes à ajouter à votre Vie Future
        choisirCartesDestinee(cartesVisible, cartesVieFuture, joueurAppelant);

        // Replacez le reste dans l'ordre souhaité
        replacezResteDansOrdre(cartesVisible, cartesSource, joueurAppelant);
    }

    /**
     * Méthode privée pour choisir jusqu'à 2 cartes à ajouter à la Vie Future.
     *
     * @param cartesVisible Les cartes visibles de la Source.
     * @param cartesVieFuture Les cartes de la Vie Future du joueur appelant.
     * @param joueurAppelant Le joueur appelant qui fait le choix.
     */
    private void choisirCartesDestinee(List<Carte> cartesVisible, List<Carte> cartesVieFuture, Joueur joueurAppelant) {
        // Création d'un scanner pour obtenir les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Affichage des instructions pour choisir les cartes
        System.out.println("Choisissez jusqu'à 2 cartes à ajouter à votre Vie Future (entrez les numéros correspondants, séparés par des espaces) :");

        // Affichage des cartes visibles avec leurs numéros
        for (int i = 0; i < cartesVisible.size(); i++) {
            System.out.println((i + 1) + ". " + cartesVisible.get(i).getNom());
        }

        // Lecture de l'entrée de l'utilisateur pour choisir les cartes
        String choixCartes = scanner.nextLine();
        // Séparation des numéros entrés par l'utilisateur
        String[] numCartes = choixCartes.split(" ");

        // Parcours des numéros pour ajouter les cartes correspondantes à la Vie Future
        for (String num : numCartes) {
            int index = Integer.parseInt(num) - 1;
            // Vérification si l'index est valide
            if (index >= 0 && index < cartesVisible.size()) {
                // Récupération de la carte choisie
                Carte carteChoisie = cartesVisible.get(index);
                // Ajout de la carte à la Vie Future
                cartesVieFuture.add(carteChoisie);
                // Suppression de la carte de la liste des cartes visibles
                cartesVisible.remove(carteChoisie);
            }
        }
    }

    /**
     * Méthode privée pour replacer le reste des cartes dans l'ordre souhaité.
     *
     * @param cartesVisible Les cartes visibles de la Source après la sélection.
     * @param cartesSource Les cartes de la Source.
     * @param joueurAppelant Le joueur appelant qui fait le placement.
     */
    private void replacezResteDansOrdre(List<Carte> cartesVisible, List<Carte> cartesSource, Joueur joueurAppelant) {
        // Logique pour replacer le reste des cartes dans l'ordre souhaité
        // (Cette logique peut être adaptée en fonction des règles spécifiques du jeu)

        // Efface la liste des cartes dans la Source
        cartesSource.clear();
        // Ajoute toutes les cartes visibles à la Source (dans l'ordre choisi)
        cartesSource.addAll(cartesVisible);
    }
}
