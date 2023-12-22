package model.echelle;

import model.joueur.Joueur;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class EchelleKarmique implements Serializable {
    private final Echellon bousier = new Echellon(4, NomPalier.BOUSIER);
    private final Echellon serpent = new Echellon(5, NomPalier.SERPENT);
    private final Echellon loup = new Echellon(6, NomPalier.LOUP);
    private final Echellon singe = new Echellon(7, NomPalier.SINGE);

    public Echellon getBousier() {
        return bousier;
    }

    public Echellon getSerpent() {
        return serpent;
    }

    public Echellon getLoup() {
        return loup;
    }

    public Echellon getSinge() {
        return singe;
    }

    public Echellon getEchellonOf(Joueur joueur) {
        List<Echellon> echellons = Arrays.asList(this.serpent, this.bousier, this.singe, this.loup);
        for(Echellon echellon : echellons){
            if(echellon.getJoueurs().contains(joueur)){
                return echellon;
            }
        }
        return null;
    }

    public NomPalier monterCategorie(Joueur joueur) {
        NomPalier current = null;
        switch (this.getEchellonOf(joueur).getNom()){
            case BOUSIER :
                this.bousier.getJoueurs().remove(joueur);
                this.serpent.getJoueurs().add(joueur);
                current = NomPalier.SERPENT;
                break;
            case SERPENT:
                this.serpent.getJoueurs().remove(joueur);
                this.loup.getJoueurs().add(joueur);
                current = NomPalier.LOUP;
                break;
            case LOUP:
                this.loup.getJoueurs().remove(joueur);
                this.singe.getJoueurs().add(joueur);
                current = NomPalier.SINGE;
                break;
            case SINGE:
                current = NomPalier.SINGE;
                break;
        }
        return current;
    }
}
