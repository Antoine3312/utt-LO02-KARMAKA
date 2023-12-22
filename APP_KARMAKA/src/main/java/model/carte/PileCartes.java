package model.carte;

import java.io.Serializable;
import java.util.*;

public class PileCartes implements Serializable {

    private static final long serialVersionUID = 2711998155099132322L;
    private Stack<Carte> cartes;

    public PileCartes() {
        this.cartes = new Stack<>();
    }

    public PileCartes(Stack<Carte> listCartes) {
        this.cartes = listCartes;
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
