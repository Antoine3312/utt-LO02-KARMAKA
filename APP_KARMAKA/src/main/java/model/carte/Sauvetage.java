package model.carte;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Sauvetage extends Carte{

    public Sauvetage(){
        this.point = 2;
        this.couleur = NomCouleur.VERTE;
    }
    // Ajoutez à votre Main l'une des 3 dernières cartes de la Fosse, au choix du joueur.
    public void pouvoir(List<Carte> mainJoueur, List<Carte> fosse, Random scanner) {
     */
        //Calculez la taille de la liste fosse
        int fosseSize = fosse.size();
        // verifie que la fosse n'est pas vide
        if (fosseSize > 0){
            /*Calcule l'indice de départ pour la boucle, en prenant le maximum entre zéro
             et la différence entre la taille de la pile et 3.
              Cela garantit que l'indice de départ ne sera pas négatif.*/
            int startIndex = Math.max(0, fosseSize - 3);
           /* Calcule l'indice de fin pour la boucle, qui est simplement la taille de la pile moins 1,
            représentant ainsi le dernier élément de la pile. */
            int endIndex = fosseSize - 1;
            //
            scanner = new scanner(System.in);
           /* Calcule l'indice de fin pour la boucle, qui est la taille de la pile moins 1,
            représentant ainsi le dernier élément de la pile.*/
            for (int i = endIndex; i >= startIndex; i--) {
                /*  À chaque itération de la boucle, cette ligne affiche l'indice de la carte dans la pile ( i)
                 suivi de la représentation sous forme de chaîne de caractères de la carte à cet indice dans la liste fosse.*/
                Scanner scanner;


                System.out.println(i +": " + fosse.get(i).toString());
                int choixJoueur = scanner.nextInt();
                Carte carteRecuperee = fosse.remove(choixJoueur);
                mainJoueur.add(carteRecuperee);
            }
            }



    }

    @Override
    public void jouerPouvoir() {
        System.out.println("Joue pouvoir carte "+this.nom);
    }
}
