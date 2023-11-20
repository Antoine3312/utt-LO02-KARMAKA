package application.view;

import model.EtatPartie;
import model.joueur.*;

import java.util.*;

public class KarmakaCommandController {
//    private static final int DELAYDETWEENCHARPROMPTinms = 30;
    private static final int DELAYDETWEENCHARPROMPTinms = 0;

    public void displayGameStart() {
        System.out.println("========================= KARMAKA 2023 =========================");
        this.print("Bienvenue sur le jeu de société Karmaka !");
    }

    public void beginDisplayOfTheGame(EtatPartie partie) {
        this.print("Début d'une partie ..." );
    }

    public int numberOfBot() {
        return this.askMultipleChoiceQuestion("Combien voulez-vous de BOT ?", List.of("1 ordinateur","2 ordinateurs"));
    }

    public String getPlayerName(int numJoueur) {
        return this.askQuestion(String.format("Quel est le nom du joueur %s",numJoueur), Optional.of(1), Optional.of(2));
    }

    public void loadSave() {
        System.out.println("Chargement d'une sauvegarde ...");
    }

    public StyleJeuStrategy getBotDifficulty(String botName) {
        int userInput = this.askMultipleChoiceQuestion(String.format("Choisir le niveau de %s :", botName), List.of("Débutant", "Intermédiaire","Expert"));
        StyleJeuStrategy botDifficulty = null;
        switch (userInput){
            case 1:
                botDifficulty = new Debutant();
                break;
            case 2:
                botDifficulty = new Intermediaire();
                break;
            case 3:
                botDifficulty = new Expert();
                break;
            default:
                break;
        }
        return botDifficulty;
    }

    private int askMultipleChoiceQuestion(String question, List<String> choices){
        this.print(question);
        int numOfChoice = 0;
        for(String choice: choices){
            this.print((numOfChoice+1)+". "+choice);
            numOfChoice ++;
        }
        Scanner in = new Scanner(System.in);
        int userInput = 0;
        boolean inputIsValid = false;
        // Vérifie que la saisie est bien un entier
        while(!inputIsValid){
            try {
                this.print("Choisissez une proposition : ");
                userInput = in.nextInt();
                // Vérifie que la saisie est bien comprise entre 1 et le nombre de choix du menu
                while(userInput<1 || userInput>numOfChoice){
                    this.print(String.format("Votre choix doit être compris entre 1 et %s, Veuillez saisir à nouveaux :", numOfChoice));
                    userInput = in.nextInt();
                }
                inputIsValid = true;
            } catch (java.util.InputMismatchException e) {
                this.print("Saisie invalide. Veuillez entrer un entier valide.");
                in.nextLine();
            }
        }
        return userInput;
    }

    private String askQuestion(String question, Optional<Integer> maxLength, Optional<Integer> minLength){
        Scanner in = new Scanner(System.in);
        this.print(question);
        String userInput = in.next();
        if (maxLength.isPresent() && minLength.isPresent()){
            while (userInput.length()> maxLength.get() && userInput.length()<minLength.get()){
                this.print(String.format("Votre réponse doit contenir entre %s et %s caractères. Veuillez saisir à nouveaux", minLength.get(), maxLength.get()));
                userInput = in.next();
            }
        }
        return userInput;
    }

    private void print(String _s){
        for(char c: _s.toCharArray()){
            System.out.print(c);
            wait(DELAYDETWEENCHARPROMPTinms);
        }
        System.out.println();
    }

    private void wait(int ms) {
        try{
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void showPlayer(List<Joueur> joueurs) {
        System.out.println("Voici les deux joueurs de la partie :");
        System.out.println(String.format("Joueur 1 : %s", joueurs.get(0)));
        System.out.println(String.format("Joueur 2 : %s", joueurs.get(1)));
    }
}
