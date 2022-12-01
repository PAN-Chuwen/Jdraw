package jdraw;

import javax.swing.*;
import java.awt.*;



public class SideBar extends JPanel{
    private static String shapeType = "Rectangle";
    private int CANVAS_WIDTH = 200;
    private int CANVAS_HEIGHT = 600;
    public SideBar() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.BLUE);
    }    

    static String getShapeType() {
        return shapeType;
    }
}
