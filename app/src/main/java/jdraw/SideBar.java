package jdraw;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;

public class SideBar extends JPanel {
    private static String shapeType;
    private Boolean isSelect = false;
    private static final Color SIDEBAR_BACKGROUND_COLOR = Color.CYAN;
    private static final int CANVAS_WIDTH = 200;
    private static final int CANVAS_HEIGHT = 600;

    // shape Buttons
    private JButton rectangleButton = new JButton("Rectangle");
    private JButton ovalButton = new JButton("Oval");
    private JButton lineButton = new JButton("Line");
    private JButton pencilButton = new JButton("Pencil");
    
    public JButton saveButton = new JButton("Save");
    public JButton loadButton = new JButton("Load");
    List<JButton> ShapeSelectButtonList = new ArrayList<>();

    // textPanel
    TextPanel textPanel = new TextPanel();

    // colorChooser Button
    private static final Color SHAPE_DEFAULT_COLOR = Color.BLACK;
    private static Color shapeColor = SHAPE_DEFAULT_COLOR;
    private JButton colorChooserButton = new JButton("ColorChooser");

    // JSlider for shape stroke(thickness)
    private static final int STROKE_INIT = 2;
    private static final int STROKE_MIN = 0;
    private static final int STROKE_MAX = 10;
    private static Stroke shapeStroke = new BasicStroke((float) STROKE_INIT);

    private JSlider strokeSlider = new JSlider(JSlider.VERTICAL, STROKE_MIN, STROKE_MAX, STROKE_INIT);

    // undo + redo 
    public JButton undoButton = new JButton("Undo");
    public JButton redoButton = new JButton("Redo");

    // select Button
    public JButton selectButton = new JButton("Select");


    public SideBar() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(SIDEBAR_BACKGROUND_COLOR);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        ShapeSelectButtonList.add(rectangleButton);
        ShapeSelectButtonList.add(ovalButton);
        ShapeSelectButtonList.add(lineButton);
        ShapeSelectButtonList.add(pencilButton);

        // add buttons for shapes
        ShapeSelectListener shapeSelectBtnListener = new ShapeSelectListener();
        for (JButton btn : ShapeSelectButtonList) {
            add(btn);
            add(Box.createRigidArea(new Dimension(0, 5))); // add 5 pixel interval
            btn.addActionListener(shapeSelectBtnListener);
        }
        // add textPanel
        add(textPanel);
        JButton TextButton = textPanel.getTextButton();
        TextButton.addActionListener(shapeSelectBtnListener);

        // add listener/dialog pop for colorChooserButton
        add(colorChooserButton);
        add(Box.createRigidArea(new Dimension(0, 5))); // add 5 pixel interval
        colorChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Color newColor = JColorChooser.showDialog(
                        SideBar.this,
                        "Choose Shape Color",
                        shapeColor);
                if (newColor != null) {
                    shapeColor = newColor;
                }
            }
        });

        // add listener for strokeSlider
        JLabel sliderLabel = new JLabel("Stroke Slider", JLabel.CENTER);
        add(sliderLabel);
        add(Box.createRigidArea(new Dimension(0, 5))); // add 5 pixel interval

        strokeSlider.setMajorTickSpacing(5);
        strokeSlider.setMinorTickSpacing(1);
        strokeSlider.setPaintTicks(true);
        strokeSlider.setPaintLabels(true);

        add(strokeSlider);
        add(Box.createRigidArea(new Dimension(0, 5))); // add 5 pixel interval
        strokeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                JSlider source = (JSlider) evt.getSource();
                if (!source.getValueIsAdjusting()) {
                    int stroke = source.getValue();
                    shapeStroke = new BasicStroke((float) stroke);
                }
            }
        });

        add(undoButton); // listener in DrawCanvas.java
        add(redoButton); // listener in DrawCanvas.java

        add(selectButton);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                isSelect = true;
            }
        });

    }

    Boolean getIsSelect() {
        return isSelect;
    }

    static String getNextShapeType() {
        return shapeType;
    }

    static Color getNextShapeColor() {
        return shapeColor;
    }

    static Stroke getNextShapeStroke() {
        return shapeStroke;
    }

    /*
     * Controller, listen to event when mouse-click happens
     * this class is not anonymous since we need to reuse it (add listner to multiple buttons)
     */


    private class ShapeSelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            shapeType = evt.getActionCommand();
            isSelect = false;
        }
    }

}
