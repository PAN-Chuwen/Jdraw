package jdraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawCanvas extends JPanel {

    private int CANVAS_WIDTH = 800;
    private int CANVAS_HEIGHT = 600;

    public DrawCanvas() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.GREEN);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                // Begin a new line

            }
        });

        // Controller
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {

            }
        });
    }

    // View
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(20, 30, 50, 50);
    }
}