package model.carte;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PileCartes {
    private Stack<Carte> pile;

    public PileCartes() {
        this.pile = new Stack<>();
    }

    public PileCartes(Stack<Carte> listCartes) {
        this.pile = listCartes;
    }

    public void addCartes(List<Carte> cartes){
        cartes.stream().forEach(carte -> {
            this.pile.push(carte);
        });
        Collections.shuffle(this.pile);
    }
}
