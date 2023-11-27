package zad3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class DrawingPanel extends JPanel {
    private ArrayList<Rectangle> rectangles;
    boolean chosenrectangle = false;
    int i;


    public DrawingPanel() {
        rectangles = new ArrayList<>();

        rectangles.add(new Rectangle(50, 50, 50, 50));
        rectangles.add(new Rectangle(250, 200, 50, 50));

        addMouseListener(new MouseAdapter() {
            private long lastClickTime;

            @Override
            public void mouseClicked(MouseEvent e) {
                long currentTime = System.currentTimeMillis();

                if (currentTime - lastClickTime < 2500) {
                    if(!chosenrectangle)
                    {
                        i = -1;
                        for (Rectangle rectangle : rectangles) {
                            i++;
                            if((rectangle.x - rectangle.width <= e.getX() && rectangle.x + rectangle.width >= e.getX()) &&
                                    (rectangle.y - rectangle.height <= e.getY() && rectangle.y + rectangle.height >= e.getY()))
                            {
                                chosenrectangle = true;
                                break;
                            }
                        }
                    }
                    else
                    {
                        moveRectangle(e.getX(), e.getY(), i);
                        chosenrectangle = false;
                    }
                }

                lastClickTime = currentTime;
            }
        });
    }

    private void moveRectangle(int x, int y, int i) {
        if (!rectangles.isEmpty()) {
            Rectangle moveRec = rectangles.get(i);
            moveRec.setLocation(x - moveRec.width / 2, y - moveRec.height / 2);
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Rectangle rectangle : rectangles) {
            g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }
}

public class DrawingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Drawing App");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DrawingPanel drawingPanel = new DrawingPanel();
            frame.add(drawingPanel);

            frame.setVisible(true);
        });
    }
}
