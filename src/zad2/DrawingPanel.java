package zad2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {

    private List<Point> points;
    private boolean drawing;

    public DrawingPanel() {
        points = new ArrayList<>();
        drawing = false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.clear();
                points.add(new Point(e.getX(), e.getY()));
                drawing = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drawing = false;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drawing) {
                    points.add(new Point(e.getX(), e.getY()));
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!points.isEmpty()) {
            g.setColor(Color.BLACK);
            for (int i = 0; i < points.size() - 1; i++) {
                Point start = points.get(i);
                Point end = points.get(i + 1);
                g.drawLine(start.x, start.y, end.x, end.y);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Drawing Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            DrawingPanel drawingPanel = new DrawingPanel();
            frame.add(drawingPanel);

            frame.setVisible(true);
        });
    }
}