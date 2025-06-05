public class Odpoved {
    private final String text;
    private final boolean spravna;

    public Odpoved(String text, boolean spravna) {
        this.text = text;
        this.spravna = spravna;
    }

    public String getText() {
        return text;
    }

    public boolean isSpravna() {
        return spravna;
    }
}