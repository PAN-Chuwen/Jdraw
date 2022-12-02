package jdraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class DrawCanvas extends JPanel {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 800;
    private int x;
    private int y;
    private int savedMousePositionX; // used for moving shapes when selected
    private int savedMousePositionY; // used for moving shapes when selected

    private Stack<Shape> shapeStack = new Stack<>(); // store shapes already drawn on canvas
    private Stack<Shape> trashBin = new Stack<>(); // store undo shapes. When redo, pop from trashBin and push to
                                                   // shapeStack
    private Shape curShape;
    private static final Color CANVAS_COLOR = Color.GRAY;

    public DrawCanvas() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(CANVAS_COLOR);
    }

    // Controller

    public void addListeners() {
        DrawFrame drawFrame = (DrawFrame) SwingUtilities.getWindowAncestor(this);
        JButton redoButton = drawFrame.sideBar.redoButton;
        JButton undoButton = drawFrame.sideBar.undoButton;

        /*
         * Add listener for redo/undo button in SideBar
         * note that we can not add this piece of code to constructor,
         * since SwingUtilities.getWindowAncestor will return null before drawCanvas was
         * added to drawFrame
         * (in DrawFrame.java, note that the constructor will be called before
         * add(drawCanvas))
         */
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!shapeStack.isEmpty()) {
                    Shape poppedShape = shapeStack.pop();
                    trashBin.push(poppedShape);
                    repaint();
                }
            }
        });

        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!trashBin.isEmpty()) {
                    Shape restoredShape = trashBin.pop();
                    shapeStack.push(restoredShape);
                    repaint();
                }
            }
        });

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
                Boolean isSelected = drawFrame.sideBar.getIsSelect();
                if (!isSelected) {
                    if (curShapeType.equals("Rectangle")) {
                        curShape = new Rectangle(x, y);
                    } else if (curShapeType.equals("Oval")) {
                        curShape = new Oval(x, y);
                    } else if (curShapeType.equals("Line")) {
                        curShape = new Line(x, y);
                    } else if (curShapeType.equals("Pencil")) {
                        curShape = new Pencil();
                    } else if (curShapeType.equals("Text")) {
                        String inputText = TextPanel.getTextInput();
                        curShape = new Text(x, y, inputText);
                        // do Text-specific jobs
                        Text curText = (Text) curShape;
                        curText.setFont(TextPanel.getFontInfo());
                    } else {
                        curShape = new Rectangle(x, y);
                    }
                    curShape.setColor(curShapeColor);
                    curShape.setStroke(curShapeStroke);
                    shapeStack.push(curShape);
                } else {
                    // choose selected shape if there're multiple shapes stacked
                    for (Shape shape : shapeStack) {
                        if (shape.containPoint(x, y)) {
                            curShape = shape;
                        }
                    }
                    // and save current mouse position
                    saveMousePosition();
                }
            }
        });

        /*
         * mouseDragged:
         * -> modify data of current Shape in ShapeStack
         * -> OR move selected Shape if SelectButton was pressed
         */
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                x = evt.getX();
                y = evt.getY();
                Boolean isSelected = drawFrame.sideBar.getIsSelect();
                if (isSelected && curShape != null) { // curShape might be null
                    int xDelta = x - savedMousePositionX;
                    int yDelta = y - savedMousePositionY;
                    saveMousePosition();
                    curShape.updataPosition(xDelta, yDelta);
                } else {
                    curShape = shapeStack.peek();
                    curShape.setPoint(x, y);
                }
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

    void saveMousePosition() {
        savedMousePositionX = x;
        savedMousePositionY = y;
    }
}