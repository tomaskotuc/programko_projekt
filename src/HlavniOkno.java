import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HlavniOkno extends JFrame {
    private final PanelOtazek panelOtazek;
    private final PanelURL panelURL;

    public HlavniOkno() {
        setTitle("IT Exam Answers Scraper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Vytvoření komponent
        panelURL = new PanelURL();
        panelOtazek = new PanelOtazek();

        // Hlavní panel
        JPanel hlavniPanel = new JPanel(new BorderLayout(10, 10));
        hlavniPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        hlavniPanel.add(panelURL, BorderLayout.NORTH);
        hlavniPanel.add(new JScrollPane(panelOtazek), BorderLayout.CENTER);

        // Propojení komponent pomocí rozhraní PosluchacURL
        panelURL.setPosluchacURL(url -> {
            panelOtazek.smazVsechnyOtazky();
            new StahovacOtazek(url, panelOtazek).execute();
        });

        add(hlavniPanel);
    }

    // Hlavní metoda – vstupní bod aplikace
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HlavniOkno().setVisible(true);
        });
    }
}
