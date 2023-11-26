package application.view;

public enum ColoredText {
    RESET("\u001B[0m"),
    ROUGE("\u001B[31m"),
    VERT("\u001B[32m"),
    BLEU("\u001B[34m");

    private final String value;

    ColoredText(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
