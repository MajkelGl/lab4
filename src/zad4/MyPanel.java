package zad4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class DrawingPanel extends JPanel implements KeyListener, MouseListener {
    private ArrayList<Rectangle> squares;
    private int selectedSquareIndex = -1;

    public DrawingPanel() {
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);

        squares = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            squares.add(new Rectangle(200 + i * (i%2) * 100 + i*10, i * 50 + (i%2) * 200, 50, 50));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Rectangle square : squares) {
            g.drawRect(square.x, square.y, square.width, square.height);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (selectedSquareIndex != -1) {
            int keyCode = e.getKeyCode();
            Rectangle selectedSquare = squares.get(selectedSquareIndex);

            switch (keyCode) {
                case KeyEvent.VK_UP:
                    if (selectedSquare.y - 10 >= 0)
                        selectedSquare.y -= 10;
                    break;
                case KeyEvent.VK_DOWN:
                    if (selectedSquare.y + 10 <= getHeight() - selectedSquare.height)
                        selectedSquare.y += 10;
                    break;
                case KeyEvent.VK_LEFT:
                    if (selectedSquare.x - 10 >= 0)
                        selectedSquare.x -= 10;
                    break;
                case KeyEvent.VK_RIGHT:
                    if (selectedSquare.x + 10 <= getWidth() - selectedSquare.width)
                        selectedSquare.x += 10;
                    break;
            }
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < squares.size(); i++) {
            Rectangle square = squares.get(i);
            if (square.contains(e.getPoint())) {
                selectedSquareIndex = i;
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}

public class MyPanel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Drawing Panel with Arrow Keys");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DrawingPanel drawingPanel = new DrawingPanel();
            frame.add(drawingPanel);

            frame.setSize(600, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}