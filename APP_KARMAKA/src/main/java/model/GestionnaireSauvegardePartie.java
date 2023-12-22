package model;

import java.io.*;

/**
 * La classe GestionnaireSauvegardePartie gère la sauvegarde et le chargement des parties du jeu.
 * Elle implémente Serializable pour permettre la sérialisation.
 */
public class GestionnaireSauvegardePartie implements Serializable {

    /**
     * Méthode pour sauvegarder une partie dans un fichier.
     *
     * @param fileName Le nom du fichier où sauvegarder la partie.
     * @param partie   L'état de la partie à sauvegarder.
     */
    public void sauvegarderPartie(String fileName, EtatPartie partie) {
        // Crée un objet File pour représenter le fichier de sauvegarde
        File f = new File("parties/" + fileName + ".txt");
        try (
                // Utilise un flux de sortie pour écrire dans le fichier
                FileOutputStream fos = new FileOutputStream(f);
                // Utilise un objet ObjectOutputStream pour sérialiser l'objet EtatPartie dans le flux de sortie
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            // Écrit l'objet EtatPartie dans le fichier
            oos.writeObject(partie);
            // Flush assure que toutes les données sont écrites dans le flux de sortie
            oos.flush();
        } catch (IOException e) {
            // En cas d'erreur, lance une exception RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode pour charger une partie depuis un fichier.
     *
     * @param fileName Le nom du fichier où charger la partie.
     * @return L'état de la partie chargée.
     */
    public EtatPartie chargerPartie(String fileName) {
        // Initialise l'objet EtatPartie à null
        EtatPartie result = null;
        try (
                // Utilise un flux d'entrée pour lire à partir du fichier
                FileInputStream fis = new FileInputStream("parties/" + fileName);
                // Utilise un objet ObjectInputStream pour désérialiser l'objet EtatPartie à partir du flux d'entrée
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            // Lit l'objet EtatPartie à partir du fichier
            result = (EtatPartie) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // En cas d'erreur (par exemple, fichier non trouvé ou classe non trouvée), lance une exception RuntimeException
            throw new RuntimeException(e);
        }
        // Retourne l'objet EtatPartie résultant de la désérialisation
        return result;
    }
}
