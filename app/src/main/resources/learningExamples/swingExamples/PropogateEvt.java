package learningExamples.swingExamples;

import javax.swing.*;
import javax.swing.event.EventListenerList;

import java.awt.event.*;

public class PropogateEvt extends JPanel implements KeyListener {
    private JTextField text;
    private EventListenerList listenerList = new EventListenerList();

    PropogateEvt() {
        text = new JTextField();
        text.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // doesn't create a new array, used for performance reasons
        Object[] listeners = listenerList.getListenerList();
        // Array of pairs listeners[i] is Class, listeners[i + 1] is EventListener
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == KeyListener.class) {
                ((KeyListener) listeners[i + 1]).keyPressed(e);
            }
        }
    }

    @Override
    public void addKeyListener(KeyListener l) {
        listenerList.add(KeyListener.class, l);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // idem as for keyPressed
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // idem as for keyPressed
    }
}
