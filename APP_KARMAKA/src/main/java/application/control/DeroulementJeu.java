package application.control;

public class DeroulementJeu {
    private Renderable renderer;

    public DeroulementJeu(Renderable renderer) {
        this.renderer = renderer;
    }

    public void beginGame(){
        this.renderer.displayGameStart();
    }
}
