package editor;

import shapes.CompoundShape;
import shapes.Shape;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageEditor {
    private EditorCanvas canvas;
    private CompoundShape allShapes = new CompoundShape();

    public ImageEditor() {
        canvas = new EditorCanvas();
    }

    public void loadShapes(Shape... shapes) {
        allShapes.clear();
        allShapes.add(shapes);
        canvas.refresh();
    }

    private class EditorCanvas extends Canvas {
        JFrame frame;

        private Boolean ctrl = false;

        private static final int PADDING = 50;

        EditorCanvas() {
            createFrame();
            refresh();
            addMouseListener(new MouseAdapter() {
                private Point old;
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (ctrl) {
                        allShapes.selectChildAt(e.getX(), e.getY());
                        e.getComponent().repaint();
                    }
                    else {
                        allShapes.unSelect();
                        allShapes.selectChildAt(e.getX(), e.getY());
                        e.getComponent().repaint();
                    }
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    old = e.getPoint();
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    Shape shape = allShapes.getSelected();
                    if (shape != null) {
                        shape.move((int) (e.getX() - old.getX()), (int) (e.getY() - old.getY()));
                    }
                    e.getComponent().repaint();
                }
            });

            addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (e.getKeyChar() == 'f') { //front
                            Shape b = allShapes.getSelected();
                            if (b != null) {
                                allShapes.upLayout(b);
                            }
                            e.getComponent().repaint();
                        } else if (e.getKeyChar() == 'b') { //back
                            Shape b = allShapes.getSelected();
                            if (b != null) {
                                allShapes.downLayout(b);
                            }
                            e.getComponent().repaint();
                        } else if (e.getKeyChar() == 'c') { //combine
                            List<Shape> newChildren  = allShapes.getAllSelected();
                            CompoundShape s = new CompoundShape();
                            for (Shape child : newChildren) {
                                allShapes.remove(child);
                                s.add(child);
                            }
                            allShapes.add(s);
                            e.getComponent().repaint();
                        }
                        else if (e.getKeyChar() == 'u') { //uncombine
                            Shape b = allShapes.getSelected();
                            if (b instanceof CompoundShape) {
                                for (Shape q : ((CompoundShape) b).getShapes()) {
                                    allShapes.add(q);
                                }
                                allShapes.remove(b);
                            }
                            e.getComponent().repaint();
                        }
                        else if (e.getKeyChar() == 's') {
                            Container content = frame.getContentPane();
                            BufferedImage img = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
                            Graphics2D g2d = img.createGraphics();
                            content.print(g2d);
                            content.printComponents(g2d);
                            try {
                                ImageIO.write(img, "png", new File("D:/img/test.png"));
                                ImageIO.write(img, "JPEG", new File("D:/img/test.JPG"));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.isControlDown()) {
                            ctrl = true;
                        }
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (!e.isControlDown()){
                            ctrl = false;
                        }
                    }

            });
        }
        void createFrame() {
            frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            JPanel contentPanel = new JPanel();
            Border padding = BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING);
            contentPanel.setBorder(padding);
            frame.setContentPane(contentPanel);
            frame.add(this);
            frame.setVisible(true);
            frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        }

        public int getWidth() {
            return allShapes.getX() + allShapes.getWidth() + PADDING;
        }

        public int getHeight() {
            return allShapes.getY() + allShapes.getHeight() + PADDING;
        }

        void refresh() {
            this.setSize(getWidth(), getHeight());
            frame.pack();
        }

        public void paint(Graphics graphics) {
            allShapes.paint(graphics);
        }
    }
}
