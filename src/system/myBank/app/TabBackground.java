package system.myBank.app;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TabBackground {

    private BufferedImage bg;

    public TabBackground() {
        try {
            bg = ImageIO.read(new File("background.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(TabBackground.class.getName()).log(Level.SEVERE, null, ex);
        }

        JPanel tabPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 300);
            }
        };
      /*  JPanel buttons = new JPanel(new GridLayout(4, 1, 15, 15));
        buttons.setOpaque(false);
        for (int i = 0; i < 4; i++) {
            buttons.add(new JButton("Button"));
        }
        tabPanel.add(buttons);*/
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.add("Panel with Bachground", tabPanel);

        JFrame frame = new JFrame("Tabbed Pane with Background");
        frame.setContentPane(tabPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(TabBackground.class.getName()).log(Level.SEVERE, null, ex);
                }
                new TabBackground();
            }
        });
    }
}