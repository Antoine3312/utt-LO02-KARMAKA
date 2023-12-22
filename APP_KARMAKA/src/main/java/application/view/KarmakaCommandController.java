package application.view;

import model.EtatPartie;
import model.carte.Carte;
import model.carte.NomCouleur;
import model.carte.PileCartes;
import model.joueur.*;

import java.util.*;

public class KarmakaCommandController {
//    private static final int DELAYDETWEENCHARPROMPTinms = 30;
    private static final int DELAYDETWEENCHARPROMPTinms = 0;

    public void displayGameStart() {
        System.out.println("========================= KARMAKA 2023 =========================");
        this.display("Bienvenue sur le jeu de société Karmaka !");
    }

    public void beginDisplayOfTheGame(EtatPartie partie) {
        this.display("Début d'une partie ..." );
    }

    public int numberOfBot() {
        return this.askMultipleChoiceQuestion("Combien voulez-vous de BOT ?", List.of("Aucun", "1 ordinateur","2 ordinateurs")) - 1;
    }

    public String getPlayerName(int numJoueur) {
        return this.askQuestion(String.format("Quel est le nom du joueur %s",numJoueur), Optional.of(1), Optional.of(2));
    }

    public NomCouleur choisirCouleur(PileCartes cartes) {
        List<String> couleursDesCartes = new ArrayList<>(cartes.getCouleursInStack());

        int choixUtilisateur = this.askMultipleChoiceQuestion("Choisissez la couleur que vous estimez la plus rentable. ", couleursDesCartes);

        NomCouleur res = null;
        for(int i = 0; i<couleursDesCartes.size();i++){
            if (i+1 == choixUtilisateur){
                res = NomCouleur.valueOf(couleursDesCartes.get(i));
            }
        }
        return res;
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
        this.display(question);
        int numOfChoice = 0;
        for(String choice: choices){
            this.display((numOfChoice+1)+". "+choice);
            numOfChoice ++;
        }
        Scanner in = new Scanner(System.in);
        int userInput = 0;
        boolean inputIsValid = false;
        // Vérifie que la saisie est bien un entier
        while(!inputIsValid){
            try {
                this.display("Choisissez une proposition : ");
                userInput = in.nextInt();
                // Vérifie que la saisie est bien comprise entre 1 et le nombre de choix du menu
                while(userInput<1 || userInput>numOfChoice){
                    this.display(String.format("Votre choix doit être compris entre 1 et %s, Veuillez saisir à nouveaux :", numOfChoice));
                    userInput = in.nextInt();
                }
                inputIsValid = true;
            } catch (java.util.InputMismatchException e) {
                this.display("Saisie invalide. Veuillez entrer un entier valide.");
                in.nextLine();
            }
        }
        return userInput;
    }

    private String askQuestion(String question, Optional<Integer> maxLength, Optional<Integer> minLength){
        Scanner in = new Scanner(System.in);
        this.display(question);
        String userInput = in.next();
        if (maxLength.isPresent() && minLength.isPresent()){
            while (userInput.length()> maxLength.get() && userInput.length()<minLength.get()){
                this.display(String.format("Votre réponse doit contenir entre %s et %s caractères. Veuillez saisir à nouveaux", minLength.get(), maxLength.get()));
                userInput = in.next();
            }
        }
        return userInput;
    }

    public void afficherCartes(List<Carte> cartes){
        for(Carte c : cartes){
            this.displayInColor(c.toString(), c.getCouleur());
        }
    }

    private void display(String _s){
        printRowToScreen(_s);
        System.out.println();
    }

    public void displayInColor(String _s, NomCouleur couleur){
        switch (couleur){
            case BLEU -> System.out.print(ColoredText.BLEU.getValue());
            case ROUGE -> System.out.print(ColoredText.ROUGE.getValue());
            case VERTE -> System.out.print(ColoredText.VERT.getValue());
            default -> {}
        }
        if(couleur.equals(NomCouleur.MOSAIQUE)){
            this.diplayMosaique(_s);
        } else {
            printRowToScreen(_s);
        }
        System.out.println(ColoredText.RESET.getValue());
    }

    private void diplayMosaique(String ligne) {
        int i = 1;
        for(char c: ligne.toCharArray()){
            if(i%3 == 0){
                System.out.print(ColoredText.VERT.getValue());
                i = 1;
            } else if (i%2 == 0) {
                System.out.print(ColoredText.BLEU.getValue());
                i = 3;
            } else {
                System.out.print(ColoredText.ROUGE.getValue());
                i = 2;
            }
            System.out.print(c);
            wait(DELAYDETWEENCHARPROMPTinms);
        }
    }

    private void printRowToScreen(String ligne) {
        for(char c: ligne.toCharArray()){
            System.out.print(c);
            wait(DELAYDETWEENCHARPROMPTinms);
        }
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

    public boolean utiliserJetonKarmique(Joueur joueur) {
        int input = this.askMultipleChoiceQuestion(String.format("Voulez vous utiliser vos jetons karmique ? (Vous en avez %s )", joueur.getNbAnneauxKarmique()), Arrays.asList("Oui", "Non"));
        boolean choice = false;
        switch (input){
            case 1 -> choice=true;
            case 2 -> choice=false;
        }
        return choice;
    }

    public int combienDeJeton(Joueur joueur) {
        this.display(String.format("Vous avez %s jeton", joueur.getNbAnneauxKarmique()));
        String input = askQuestion("Combien de jeton voulez vous utiliser ?", Optional.empty(),Optional.empty());
        while (!input.matches("\\d+") || Integer.parseInt(input)>joueur.getNbAnneauxKarmique() || Integer.parseInt(input)<1){
            input = askQuestion("Saisi invalide, veuillez saisir à nouveau :", Optional.empty(),Optional.empty());
        }
        return Integer.parseInt(input);
    }

    public Carte afficherEtChoisirCarteMain(Joueur joueur) {
        this.display("Voici votre main :");
        this.afficherCartes(joueur.getMain());
        List<String> nomDesCartes = joueur.getMain().stream().map(Carte::getNom).toList();
        int input = this.askMultipleChoiceQuestion("Choisissez une carte à jouer :", nomDesCartes);
        return joueur.getMain().get(input-1);
    }

    public int choisirUtilisation(Carte carte) {
        List<String> typeUtilisationCarte = Arrays.asList("Pour son pouvoir", "Pour votre futur", "Pour ses points");
        return this.askMultipleChoiceQuestion("Comment voulez vous jouer cette carte ?", typeUtilisationCarte);
    }

    public boolean jouerUneCarteOuNon() {
        return this.askMultipleChoiceQuestion("Voulez vous jouez une carte ou passer votre tour ?", Arrays.asList("Jouer", "Passer")) == 1;
    }

    public Carte choisirUneCarte(List<Carte> cartes){
        return null;
    }


    public void displayErrorMessage(String s){
        this.displayInColor(s, NomCouleur.ROUGE);
    }

    public List<Carte> choisirDeuxCarte(List<Carte> cartes) {
        return null;
    }

    public void displayMessage(String message) {
        this.display(message);
    }

    public void afficherInfoJoueurDebutTour(Joueur joueur) {
        this.display(String.format("\nAu tour de %s de jouer. Voici un résumé de son avancé :", joueur.getNom()));
        this.display(String.format("    Echellon : %s", EtatPartie.getInstance().getEchelle().getEchellonOf(joueur).getNom()));
        this.display(String.format("    Nombre de jeton karmique : %s", joueur.getNbAnneauxKarmique()));
        this.display(String.format("    Nombre de Cartes dans la  Pile : %s", joueur.getPile().getCartes().size()));
        this.display(String.format("    Oeuvre exposées : %s", joueur.getOeuvre().getCartes().isEmpty() ? "Aucune" : joueur.getOeuvre().getCartes().peek()));
    }

    public void afficherInfoReicarnation(Joueur joueur) {
        this.display(String.format("%s n'a plus aucune carte dans sa main et dans sa pile, il va se réincarner ...", joueur.getNom()));
        if(!joueur.getOeuvre().getCartes().isEmpty()){
            this.display("Voici les oeuvres qu'il a joué durant cette vie :");
            this.afficherCartes(joueur.getOeuvre().getCartes());
        }
    }

    public void diplayTourInfo(EtatPartie partie) {
        this.displayMessage(String.format("\n================= Tour %s =================", partie.getNumTour()));
    }
}
