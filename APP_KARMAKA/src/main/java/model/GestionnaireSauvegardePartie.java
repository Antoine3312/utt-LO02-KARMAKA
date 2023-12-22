package model;

import java.io.*;

public class GestionnaireSauvegardePartie implements Serializable{

    public void sauvegarderPartie(String fileName, EtatPartie partie){
        File f = new File("parties/"+fileName+".txt");
        try (FileOutputStream fos = new FileOutputStream(f);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(partie);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public EtatPartie chargerPartie(String fileName){
        EtatPartie result = null;
        try (FileInputStream fis = new FileInputStream("parties/"+fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (EtatPartie) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
