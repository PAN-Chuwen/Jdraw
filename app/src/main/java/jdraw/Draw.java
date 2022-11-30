package jdraw;

import javax.swing.SwingUtilities;

public class Draw {
    public static void main(String[] args) {
         // Run GUI construction on the Event-Dispatching Thread for thread safety
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DrawFrame(); // Let the constructor do the job
            }
        });
    }
}
