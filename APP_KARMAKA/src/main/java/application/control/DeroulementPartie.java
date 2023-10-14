package application.control;

import model.EtatPartie;

public class DeroulementPartie {

    private Renderable renderer;
    private EtatPartie partie;

    public DeroulementPartie(Renderable renderer) {
        this.renderer = renderer;
    }

    public void beginDisplayOfTheGame(){
        this.renderer.beginDisplayOfTheGame();
    }
}
