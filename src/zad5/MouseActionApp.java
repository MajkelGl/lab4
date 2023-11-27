package zad5;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MouseAction extends JPanel {
    private boolean drawSquare = false;
    private int squareSize = 50; // Rozmiar kwadratu

    public MouseAction() {
        JFrame frame = new JFrame("Mouse Action");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(517, 560);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JTextField textField = new JTextField();
        textField.setEditable(false);

        frame.add(textField, BorderLayout.NORTH);
        frame.add(this);

        addMouseWheelListener(e -> {
            int scrollAmount = e.getWheelRotation();
            if (scrollAmount > 0) {
                if(squareSize - 5 >= -500)
                {
                    squareSize -= 5;
                    textField.setText("Zakręcono scrollem myszki w dół");
                }
            } else {
                if(squareSize + 5 <=500) {
                    squareSize += 5;
                    textField.setText("Zakręcono scrollem myszki do góry");
                }
            }
            repaint();
        });

//        addMouseWheelListener(e -> {
//            textField.setText("Zakręcono scrollem myszki");
//            int scrollAmount = e.getWheelRotation();
//            if (scrollAmount > 0) {
//                if(squareSize - 5 >= -500)
//                    squareSize -= 5;
//            } else {
//                if(squareSize + 5 <=500)
//                    squareSize += 5;
//            }
//        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                textField.setText("Naciśnięto przycisk myszki");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                textField.setText("Zwolniono przycisk myszki");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                textField.setText("Myszka weszła na obszar panelu");
                drawSquare = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                textField.setText("Myszka opuściła obszar");
                drawSquare = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (drawSquare) {
            int centerX = getWidth() / 2 - squareSize / 2;
            int centerY = getHeight() / 2 - squareSize / 2;
            g.drawRect(centerX, centerY, squareSize, squareSize);
        }
    }
}

public class MouseActionApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MouseAction app = new MouseAction();
            app.setVisible(true);
        });
    }
}