package application.control;

import application.view.KarmakaCommandController;

public class KarmakaCommand
        implements Renderable
{

    private final KarmakaCommandController kcc = new KarmakaCommandController();

    @Override
    public void displayGameStart() {
        this.kcc.displayGameStart();
    }

    @Override
    public void beginDisplayOfTheGame() {
        this.kcc.beginDisplayOfTheGame();
    }
}
