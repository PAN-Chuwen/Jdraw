package jdraw;
import java.awt.*;        // Using AWT containers and components
import java.awt.event.*;  // Using AWT events classes and listener interfaces

// An AWT GUI program inherits the top-level container java.awt.Frame
public class WindowEventDemo extends Frame {

   private TextField tfCount;  // Declare a TextField component
   private Button btnCount;    // Declare a Button component
   private int count = 0;      // Counter's value

   // Constructor to setup the GUI components and event handlers
   public WindowEventDemo() {
      setLayout(new FlowLayout()); // "super" Frame sets to FlowLayout

      add(new Label("Counter"));   // "super" Frame adds an anonymous Label

      tfCount = new TextField("0", 10); // Construct the TextField
      tfCount.setEditable(false);       // read-only
      add(tfCount);                     // "super" Frame adds TextField

      btnCount = new Button("Count");  // Construct the Button
      add(btnCount);                   // "super" Frame adds Button

      btnCount.addActionListener(new BtnCountListener());
        // btnCount (source object) fires ActionEvent upon clicking
        // btnCount adds an anonymous instance of BtnCountListener
        //   as an ActionEvent listener

      addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0);  // Terminate the program
            }
            });
        // "super" Frame (source object) fires WindowEvent.
        // "super" Frame adds an anonymous instance of MyWindowListener
        //   as a WindowEvent listener.

      setTitle("WindowEvent Demo"); // "super" Frame sets title
      setSize(300, 100);            // "super" Frame sets initial size
      setVisible(true);             // "super" Frame shows
   }

   // The entry main() method
   public static void main(String[] args) {
      new WindowEventDemo();  // Let the construct do the job
   }

   // Define an inner class to handle ActionEvent of btnCount
   private class BtnCountListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent evt) {
         ++count;
         tfCount.setText(count + "");
      }
   }

   
}