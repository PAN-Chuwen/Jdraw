package jdraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.util.ArrayList;

public class SideBar extends JPanel {
    private static String shapeType;
    private int CANVAS_WIDTH = 200;
    private int CANVAS_HEIGHT = 600;
    JButton rectangleButton = new JButton("Rectangle");
    JButton ovalButton = new JButton("Oval");
    JButton lineButton = new JButton("Line");
    JButton pencilButton = new JButton("Pencil");
    JButton textButton = new JButton("Text");
    JButton select = new JButton("Select");
    JButton undoButton = new JButton("Undo");
    JButton redoButton = new JButton("Redo");
    JButton saveButton = new JButton("Save");
    JButton loadButton = new JButton("Load");
    List<JButton> buttonList = new ArrayList<>();

    public SideBar() {
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.CYAN);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        buttonList.add(rectangleButton);
        buttonList.add(ovalButton);
        buttonList.add(lineButton);
        buttonList.add(pencilButton);


        AllButtonsListener allBtnListener = new AllButtonsListener();
        for (JButton btn : buttonList) {
            add(btn);
            add(Box.createRigidArea(new Dimension(0,5))); // add 5 pixel interval 
            btn.addActionListener(allBtnListener);
        }
    }

    static String getShapeType() {
        return shapeType;
    }

    private class AllButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            shapeType = evt.getActionCommand();
        }
    }

}
