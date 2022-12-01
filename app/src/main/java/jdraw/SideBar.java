package jdraw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.w3c.dom.events.Event;

import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;

public class SideBar extends JPanel {
    private static String shapeType;
    private static Color shapeColor = Color.BLACK;
    private static final Color SIDEBAR_BACKGROUND_COLOR = Color.CYAN;
    private int CANVAS_WIDTH = 200;
    private int CANVAS_HEIGHT = 600;
    private JButton rectangleButton = new JButton("Rectangle");
    private JButton ovalButton = new JButton("Oval");
    private JButton lineButton = new JButton("Line");
    private JButton pencilButton = new JButton("Pencil");
    private JButton textButton = new JButton("Text");
    private JButton select = new JButton("Select");
    private JButton undoButton = new JButton("Undo");
    private JButton redoButton = new JButton("Redo");
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");
    List<JButton> ShapeSelectButtonList = new ArrayList<>();

    private JButton colorChooserButton = new JButton("ColorChooser");
    private JColorChooser colorChooser = new JColorChooser();

    public SideBar() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(SIDEBAR_BACKGROUND_COLOR);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        ShapeSelectButtonList.add(rectangleButton);
        ShapeSelectButtonList.add(ovalButton);
        ShapeSelectButtonList.add(lineButton);
        ShapeSelectButtonList.add(pencilButton);
        ShapeSelectButtonList.add(colorChooserButton);

        ShapeSelectListener allBtnListener = new ShapeSelectListener();
        for (JButton btn : ShapeSelectButtonList) {
            add(btn);
            add(Box.createRigidArea(new Dimension(0, 5))); // add 5 pixel interval
            btn.addActionListener(allBtnListener);
        }

        // add button for color chooser
        add(colorChooserButton);
        add(Box.createRigidArea(new Dimension(0, 5))); // add 5 pixel interval
        colorChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Color newColor = JColorChooser.showDialog(
                        null,
                        "Choose Shape Color",
                        shapeColor);
                if (newColor != null) {
                    shapeColor = newColor;
                }
            }
        });

    }

    static String getNextShapeType() {
        return shapeType;
    }

    static Color getNextShapeColor() {
        return shapeColor;
    }

    private class ShapeSelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            shapeType = evt.getActionCommand();
        }
    }

}
