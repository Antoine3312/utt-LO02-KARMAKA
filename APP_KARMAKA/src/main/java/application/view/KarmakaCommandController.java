package application.view;

import application.control.Renderable;

public class KarmakaCommandController {

    public void displayGameStart() {
        System.out.println("========================= KARMAKA 2023 =========================");
        this.print("Bienvenu sur le jeu de société Karmaka !");
    }

    public void beginDisplayOfTheGame() {
        this.print("Début d'une partie ...");
    }

    private void print(String _s){
        for(char c: _s.toCharArray()){
            System.out.print(c);
            wait(20);
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
}
