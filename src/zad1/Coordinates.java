package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Coordinates extends JFrame {

    private DrawingPanel drawingPanel;
    private JTextField textField;
    public Coordinates(){
        setTitle("Coordinates");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawingPanel = new DrawingPanel();
        drawingPanel.addMouseListener(new MyMouseListener());

        textField = new JTextField();
        textField.setEditable(false);
        textField.addActionListener(e -> textField.setText(""));

        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);
        add(drawingPanel);

        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    textField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            textField.setText("Clicked at: (" + x + ", " + y + ")");
        }
    }

    private class DrawingPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Coordinates app = new Coordinates();
            app.setVisible(true);
        });
    }
}