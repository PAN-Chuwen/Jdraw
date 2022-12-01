package jdraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class DrawCanvas extends JPanel {

    private int CANVAS_WIDTH = 800;
    private int CANVAS_HEIGHT = 600;
    private int mouse_X;
    private int mouse_Y;

    private Stack<Shape> shapeStack = new Stack<>();

    public DrawCanvas() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.GREEN);

        // Controller

        /*
         * mousePressed -> create new Shape() according to SideBar.ShapeType
         * (e.g. if we press Rectangle on sidebar and then click on canvas, peek of
         * shapeStack would be Rectangle)
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                mouse_X = evt.getX();
                mouse_Y = evt.getY();
                String curShapeType = SideBar.getShapeType();
                if (curShapeType.equals("Rectangle")) {
                    Rectangle curRectangle = new Rectangle(mouse_X, mouse_Y, 0, 0);
                    shapeStack.push(curRectangle);
                }
            }
        });
        /*
         * mouseDragged -> modify data of current Shape in ShapeStack
         */
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                mouse_X = evt.getX();
                mouse_Y = evt.getY();
                Shape curShape = shapeStack.peek();
                String curShapeType = curShape.getClass().getSimpleName();
                if (curShapeType.equals("Rectangle")) {
                    curShape.setPoint(mouse_X, mouse_Y);
                }
                repaint();
            }
        });
    }

    // View
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapeStack) {
            shape.draw(g);
        }
    }
}