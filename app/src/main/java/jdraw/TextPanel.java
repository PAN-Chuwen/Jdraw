package jdraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextPanel extends JPanel {
    private JComboBox<Integer> textFontSizeList = new JComboBox<>();
    private JComboBox<String> textFontList;
    private JTextArea textArea = new JTextArea();
    private JButton textButton = new JButton("Text");
    private String[] fonts;

    public TextPanel() {
        setLayout(new FlowLayout());

        // get fonts
        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        textFontList = new JComboBox<>(fonts); // add fonts to font combobox(list)
        textFontList.setRenderer(new FontCellRenderer()); // set rederer for text fonts

        add(textFontSizeList);
        add(textFontList);
        add(textArea);
        add(textButton);
    }
}

/*
 * Reference: https://stackoverflow.com/questions/18704022/set-text-size-of-jcombobox-in-swing
 */

class FontCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(
        JList<?> list,
        Object value,
        int index,
        boolean isSelected,
        boolean cellHasFocus) {
            JLabel label = (JLabel)super.getListCellRendererComponent(
                list,value,index,isSelected,cellHasFocus);
            Font font = new Font((String)value, Font.PLAIN, 20);
            label.setFont(font);
            return label;

    }
}