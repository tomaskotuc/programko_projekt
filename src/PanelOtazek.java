import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelOtazek extends JPanel {
    public PanelOtazek() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void smazVsechnyOtazky() {
        removeAll();
        revalidate();
        repaint();
    }

    public void zobrazOtazky(List<Otazka> otazky) {
        smazVsechnyOtazky();

        if (otazky.isEmpty()) {
            JLabel labelZadne = new JLabel("Nebyly nalezeny žádné otázky.");
            labelZadne.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(labelZadne);
        } else {
            for (Otazka otazka : otazky) {
                add(vytvorKomponentuOtazky(otazka));
                add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        revalidate();
        repaint();
    }

    private JComponent vytvorKomponentuOtazky(Otazka otazka) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY),
                BorderFactory.createEmptyBorder(10, 5, 10, 5)
        ));

        // Text otázky
        JLabel labelOtazka = new JLabel(otazka.getText());
        labelOtazka.setFont(new Font(labelOtazka.getFont().getName(), Font.BOLD, 14));
        panel.add(labelOtazka);

        // Tlačítko pro odpovědi
        JButton tlacitkoOdpovedi = new JButton("Zobrazit odpovědi");
        tlacitkoOdpovedi.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Panel s odpověďmi (zpočátku skrytý)
        JPanel panelOdpovedi = vytvorPanelOdpovedi(otazka);

        tlacitkoOdpovedi.addActionListener(e -> {
            panelOdpovedi.setVisible(!panelOdpovedi.isVisible());
            tlacitkoOdpovedi.setText(panelOdpovedi.isVisible() ? "Skrýt odpovědi" : "Zobrazit odpovědi");
            panel.revalidate();
        });

        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(tlacitkoOdpovedi);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(panelOdpovedi);

        return panel;
    }

    private JPanel vytvorPanelOdpovedi(Otazka otazka) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setVisible(false);

        for (Odpoved odpoved : otazka.getOdpovedi()) {
            JLabel labelOdpoved = new JLabel("• " + odpoved.getText());
            if (odpoved.isSpravna()) {
                labelOdpoved.setForeground(Color.RED);
                labelOdpoved.setFont(new Font(labelOdpoved.getFont().getName(), Font.BOLD, 12));
            }
            labelOdpoved.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 2));
            panel.add(labelOdpoved);
        }

        return panel;
    }
}