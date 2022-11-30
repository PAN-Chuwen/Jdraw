package learningExamples.awtExamples;

import java.awt.*;  // Using Frame class in package java.awt

// A GUI program is written as a subclass of Frame - the top-level container
// This subclass inherits all properties from Frame, e.g., title, icon, buttons, content-pane
public class MyGUIProgram extends Frame {

   // private variables
    Button btn;
    Label lbl;

   // Constructor to setup the GUI components and event handlers
    public MyGUIProgram() { 
        this.setLayout(new FlowLayout());
        lbl = new Label("hi!");




        this.add(lbl);
        this.setVisible(true);

        this.pack();

    }

   // The entry main() method
    public static void main(String[] args) {
      // Invoke the constructor (to setup the GUI) by allocating an instance
        new MyGUIProgram();
    }
}