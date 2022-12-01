package jdraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class DrawCanvas extends JPanel {

    private int CANVAS_WIDTH = 800;
    private int CANVAS_HEIGHT = 600;
    private int x;
    private int y;

    private Stack<Shape> shapeStack = new Stack<>();
    private static final Color CANVAS_COLOR = Color.GRAY;

    public DrawCanvas() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(CANVAS_COLOR);

        // Controller

        /*
         * mousePressed -> create new Shape() according to SideBar.ShapeType
         * (e.g. if we press Rectangle on sidebar and then click on canvas, peek of
         * shapeStack would be Rectangle)
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                x = evt.getX();
                y = evt.getY();
                String curShapeType = SideBar.getNextShapeType();
                Color curShapeColor = SideBar.getNextShapeColor();
                Stroke curShapeStroke = SideBar.getNextShapeStroke();
                Shape curShape;
                if (curShapeType.equals("Rectangle")) {
                    curShape = new Rectangle(x, y);
                } else if (curShapeType.equals("Oval")) {
                    curShape = new Oval(x, y);
                } else if (curShapeType.equals("Line")) {
                    curShape = new Line(x, y);
                } else if (curShapeType.equals("Pencil")) {
                    curShape = new Pencil();
                } else {
                    curShape = new Rectangle(x, y);
                }
                curShape.setColor(curShapeColor);
                curShape.setStroke(curShapeStroke);
                shapeStack.push(curShape);
            }
        });
        /*
         * mouseDragged -> modify data of current Shape in ShapeStack
         */
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                x = evt.getX();
                y = evt.getY();
                Shape curShape = shapeStack.peek();
                curShape.setPoint(x, y);
                repaint();
            }
        });
    }

    // View
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Shape shape : shapeStack) {
            g2d.setColor(shape.getColor());
            g2d.setStroke(shape.getStroke());
            shape.draw(g);
        }
    }
}