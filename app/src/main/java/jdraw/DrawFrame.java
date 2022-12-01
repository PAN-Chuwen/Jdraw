package jdraw;

import java.awt.*;
import javax.swing.*;

public class DrawFrame extends JFrame {
    private DrawCanvas canvas;
    private SideBar sidebar;

    public DrawFrame() {
        canvas = new DrawCanvas();
        sidebar = new SideBar();
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);
        cp.add(sidebar, BorderLayout.WEST);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("JDraw");
        pack(); // pack all the components in the JFrame
        setVisible(true); // show it
        requestFocus(); // "super" JFrame requests focus to receive KeyEvent
    }
}