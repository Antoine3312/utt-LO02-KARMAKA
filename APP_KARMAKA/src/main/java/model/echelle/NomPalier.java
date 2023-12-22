package model.echelle;

import java.io.Serializable;

/**
 * L'énumération NomPalier représente les différents paliers de l'échelle karmique du jeu.
 * Chaque palier a une valeur associée, qui est utilisée pour identifier l'échelon auquel un joueur appartient.
 * L'interface Serializable est implémentée pour permettre la sérialisation.
 */
public enum NomPalier implements Serializable {

    // Les différents paliers de l'échelle karmique
    BOUSIER, SERPENT, LOUP, SINGE
}

