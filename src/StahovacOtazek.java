import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StahovacOtazek extends SwingWorker<List<Otazka>, Void> {
    private final String url;
    private final PanelOtazek panelOtazek;

    public StahovacOtazek(String url, PanelOtazek panelOtazek) {
        this.url = url;
        this.panelOtazek = panelOtazek;
    }

    @Override
    protected List<Otazka> doInBackground() throws Exception {
        List<Otazka> otazky = new ArrayList<>();

        Document doc = Jsoup.connect(url).get();

        // Vyber všechny <strong> uvnitř <p>, jejichž text začíná číslem a tečkou (např. "9.")
        Elements questionElements = doc.select("p > strong:matches(^\\d+\\.\\s.*)");

        for (Element strongElement : questionElements) {
            String questionText = strongElement.text();
            List<Odpoved> odpovedi = new ArrayList<>();

            Element questionParagraph = strongElement.parent();

            // Najdi následující <ul> nebo <ol> mezi sourozenci
            Element sibling = questionParagraph.nextElementSibling();
            while (sibling != null && !("ul".equals(sibling.tagName()) || "ol".equals(sibling.tagName()))) {
                sibling = sibling.nextElementSibling();
            }
            Element answersList = sibling;

            if (answersList != null) {
                Elements answerItems = answersList.select("li");

                for (Element answerItem : answerItems) {
                    String answerText = answerItem.text();

                    boolean isCorrect = false;
                    Elements spans = answerItem.select("span[style]");
                    for (Element span : spans) {
                        String style = span.attr("style").replaceAll("\\s", "").toLowerCase();
                        if (style.contains("color:#ff0000") || style.contains("color:red")) {
                            isCorrect = true;
                            break;
                        }
                    }

                    odpovedi.add(new Odpoved(answerText, isCorrect));
                }
            }

            otazky.add(new Otazka(questionText, odpovedi));
        }


        return otazky;
    }

    @Override
    protected void done() {
        try {
            panelOtazek.zobrazOtazky(get());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panelOtazek,
                    "Chyba při načítání otázek. Zkontrolujte zadanou URL.",
                    "Chyba",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
