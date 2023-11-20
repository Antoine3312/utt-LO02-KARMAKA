package model.carte;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PileCartes {
    private Stack<Carte> cartes;

    public PileCartes() {
        this.cartes = new Stack<>();
    }

    public PileCartes(Stack<Carte> listCartes) {
        this.cartes = listCartes;
    }

    public void addCartes(List<Carte> cartes){
        cartes.forEach(this.cartes::push);
        Collections.shuffle(this.cartes);
    }

    public Stack<Carte> getCartes() {
        return cartes;
    }
}
