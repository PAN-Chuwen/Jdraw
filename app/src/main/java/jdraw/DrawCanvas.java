package jdraw;

import javax.swing.*;
import java.awt.*;

public class DrawCanvas extends JPanel {

    private int CANVAS_WIDTH = 800;
    private int CANVAS_HEIGHT = 600;
    public DrawCanvas() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.GREEN);
    }
}