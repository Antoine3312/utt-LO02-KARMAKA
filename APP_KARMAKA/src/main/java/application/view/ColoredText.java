package application.view;


/**
 * L'énumération ColoredText définit des codes de couleur ANSI pour le texte afin d'ajouter de la couleur
 * dans les sorties console. Chaque valeur de l'énumération représente un code de couleur.
 * Les valeurs disponibles sont :
 * - RESET : Réinitialise la couleur du texte.
 * - ROUGE : Donne une couleur rouge au texte.
 * - VERT : Donne une couleur verte au texte.
 * - BLEU : Donne une couleur bleue au texte.
 *
 * Les codes de couleur sont utilisés en tant que séquences d'échappement ANSI pour modifier la couleur du texte
 * affiché dans la console.
 *
 * Exemple d'utilisation :
 * ```
 * System.out.println(ColoredText.ROUGE.getValue() + "Texte en rouge" + ColoredText.RESET.getValue());
 * ```
 * Cela affiche le texte "Texte en rouge" en rouge dans la console.
 *
 * @see <a href="https://en.wikipedia.org/wiki/ANSI_escape_code">ANSI Escape Code</a> pour plus d'informations sur les codes de couleur ANSI.
 */
public enum ColoredText {
    RESET("\u001B[0m"),
    ROUGE("\u001B[31m"),
    VERT("\u001B[32m"),
    BLEU("\u001B[34m");

    private final String value;

    /**
     * Constructeur de l'énumération ColoredText.
     *
     * @param value La séquence d'échappement ANSI associée à la couleur.
     */
    ColoredText(String value){
        this.value = value;
    }

    /**
     * Récupère la séquence d'échappement ANSI associée à la couleur.
     *
     * @return La séquence d'échappement ANSI.
     */
    public String getValue() {
        return value;
    }
}
