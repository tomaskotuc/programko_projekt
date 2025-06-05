import javax.swing.*;

public class ITExamScraperApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Použijte systémový vzhled
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Chyba při nastavování vzhledu");
            }

            new HlavniOkno().setVisible(true);
        });
    }
}