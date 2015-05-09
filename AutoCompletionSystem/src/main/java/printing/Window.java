package printing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin on 5/9/2015 .
 */
public class Window extends JFrame {

    DrawingJPanel panel;

    public Window() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);
        setPreferredSize(new Dimension(600, 400));
        setVisible(true);

        initComponents();

        pack();
    }

    private void initComponents() {
        panel = new DrawingJPanel();
        setContentPane(panel);
    }

    public void addPoint(Point point) {
        panel.add(point);
    }

    public void clear() {
        panel.clear();
    }

    public void save() {
        panel.save();
    }

    public void setFileName(String name) {
        panel.setFileName(name);
    }

    public void drawPoints() {
        panel.drawImage();
    }

    class DrawingJPanel extends JPanel {

        private List<Point> points;
        private Point topLeft;
        private Point bottomLeft;
        private Point bottomRight;
        private BufferedImage paintImage;
        private String fileName;

        DrawingJPanel() {
            setLayout(null);
            setPreferredSize(new Dimension(600, 400));
            setVisible(true);
            initComponents();
        }

        private void initComponents() {
            topLeft = new Point(10, 10);
            bottomRight = new Point(570, 340);
            bottomLeft = new Point(10, 340);
            paintImage = getBufferedImage();
            points = new ArrayList<>();
        }

        private BufferedImage getBufferedImage() {
            BufferedImage image = new BufferedImage(600, 400, BufferedImage.TYPE_3BYTE_BGR);
            Graphics g = image.getGraphics();
            g.setColor(new Color(230, 230, 200));
            g.fillRect(0, 0, 600, 400);
            return image;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawImage();
            g.drawImage(paintImage, 0, 0, null);
        }

        private void drawImage() {
            paintImage = getBufferedImage();
            Graphics image = paintImage.getGraphics();
            image.setColor(Color.black);
            drawXOY(image);
            drawPoints(image);
        }

        private void drawPoints(Graphics image) {
            Point previousPoint = null;
            for (Point point : points) {
                if (previousPoint == null) {
                    previousPoint = point;
                    continue;
                }
                image.drawLine(previousPoint.x, previousPoint.y, point.x, point.y);
                previousPoint = point;
            }
        }

        private void drawXOY(Graphics image) {
            image.drawLine(topLeft.x, topLeft.y, bottomLeft.x, bottomLeft.y);
            image.drawLine(bottomLeft.x, bottomLeft.y, bottomRight.x, bottomRight.y);
            int distance = (bottomRight.x - bottomLeft.x) / 8;
            int x = 5;
            for (int i = 0; i <= 7; i++) {
                image.drawString(i + "", x + (i * distance), 350);
            }
            image.drawString("1", 2, 20);
        }

        public void add(Point point) {
            points.add(point);
        }

        public void clear() {
            points.clear();
            paintImage = getBufferedImage();
            repaint();
        }

        public void save() {
            try {
                ImageIO.write(paintImage, "PNG", new File(fileName + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }
}