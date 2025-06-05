import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelURL extends JPanel {
    private final JTextField poleURL;
    private final JButton tlacitkoNacti;
    private final JButton tlacitkoVymazat;   // nové tlačítko
    private PosluchacURL posluchac;

    public PanelURL() {
        super(new BorderLayout(10, 10));

        poleURL = new JTextField(30);
        poleURL.setToolTipText("Zadejte URL z itexamanswers.net");

        tlacitkoNacti = new JButton("Načíst otázky");
        tlacitkoNacti.addActionListener(this::zpracujNacteni);

        tlacitkoVymazat = new JButton("Vyčistit URL");      // vytvoření tlačítka
        tlacitkoVymazat.addActionListener(this::zpracujVymazani);

        // Panel pro tlačítka (aby byla vedle sebe)
        JPanel panelTlacitek = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        panelTlacitek.add(tlacitkoNacti);
        panelTlacitek.add(tlacitkoVymazat);

        add(new JLabel("URL:"), BorderLayout.WEST);
        add(poleURL, BorderLayout.CENTER);
        add(panelTlacitek, BorderLayout.EAST);
    }

    private void zpracujNacteni(ActionEvent e) {
        String url = poleURL.getText().trim();
        if (!url.isEmpty() && posluchac != null) {
            posluchac.naZadaniURL(url);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Zadejte prosím URL",
                    "Chyba",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void zpracujVymazani(ActionEvent e) {
        poleURL.setText("");
    }

    public void setPosluchacURL(PosluchacURL posluchac) {
        this.posluchac = posluchac;
    }

    public interface PosluchacURL {
        void naZadaniURL(String url);
    }
}
