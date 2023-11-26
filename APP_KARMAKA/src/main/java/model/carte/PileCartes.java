package model.carte;

import java.util.*;

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

    public void viderCartes() {
        this.cartes = new Stack<>();
    }

    public Set<String> getCouleursInStack() {
        Set<String> res = new HashSet<>();
        for(Carte carte : this.cartes){
            res.add(String.valueOf(carte.getCouleur()));
        }
        return res;
    }
}
