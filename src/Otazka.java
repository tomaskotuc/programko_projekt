import java.util.List;

public class Otazka {
    private final String text;
    private final List<Odpoved> odpovedi;

    public Otazka(String text, List<Odpoved> odpovedi) {
        this.text = text;
        this.odpovedi = odpovedi;
    }

    public String getText() {
        return text;
    }

    public List<Odpoved> getOdpovedi() {
        return odpovedi;
    }
}