package application.control;

public class DeroulementJeu {
    private Renderable renderer;
    private DeroulementPartie dp;

    public DeroulementJeu(Renderable renderer) {
        this.renderer = renderer;
        this.dp = new DeroulementPartie(this.renderer);
    }

    public void beginGame(){
        this.renderer.displayGameStart();
        this.renderer.beginDisplayOfTheGame();
    }
}
