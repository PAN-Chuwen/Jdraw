package jdraw;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class TextPanel extends JPanel {
    private static final int FONTSIZE_MIN = 1;
    private static final int FONTSIZE_MAX = 30;
    private static final int FONTSIZE_DEFAULT = 15;

    private final int TEXT_FIELD_COL = 10;

    // Ref: https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html#style
    private static String textInput;
    private static FontInfo fontInfo;
    private JComboBox<Integer> textFontSizeList;
    private JComboBox<String> textFontNameList;
    private JTextField textField = new JTextField("Your text here", TEXT_FIELD_COL);
    private JButton textButton = new JButton("Text");
    private String[] fontNameList;
    private Integer[] fontSizeList = new Integer[FONTSIZE_MAX];

    public TextPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // init static member variable, note that we can't init top because the class
        // hasn't been created yet
        fontInfo = new FontInfo();
        // textField, already done
        textInput = textField.getText();

        // fontSize comboBox, FONTSIZE_MIN - FONTSIZE_MAX
        for (int fontsize = FONTSIZE_MIN; fontsize < FONTSIZE_MAX; fontsize++) {
            fontSizeList[fontsize] = fontsize;
        }
        textFontSizeList = new JComboBox<>(fontSizeList);
        textFontSizeList.setSelectedIndex(FONTSIZE_DEFAULT - FONTSIZE_MIN + 1); // need +1 since first item is empty

        // fontName comboBox
        fontNameList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        textFontNameList = new JComboBox<>(fontNameList); // add fonts to font combobox(list)
        textFontNameList.setRenderer(new FontCellRenderer()); // set rederer for text fonts


        add(textFontSizeList);
        add(textFontNameList);
        add(textField);
        add(textButton);
        setPreferredSize(getPreferredSize());

        // add listener for swing components in TextPanel
        // listener for textField
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent evt) {
                updateText();
            }
            @Override
            public void removeUpdate(DocumentEvent evt) {
                updateText();
            }
            @Override
            public void insertUpdate(DocumentEvent evt) {
                updateText();
            }
            public void updateText() {
                textInput = textField.getText();
            }
        });

        // listener for textFontSizeList
        textFontSizeList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JComboBox<?> fontSizeComboBox = (JComboBox<?>) evt.getSource();
                fontInfo.fontSize = (int) fontSizeComboBox.getSelectedItem();
            }
        });
        // listener for textFontNameList
        textFontNameList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JComboBox<?> fontNameComboBox = (JComboBox<?>) evt.getSource();
                fontInfo.fontName = (String) fontNameComboBox.getSelectedItem();
            }
        });

        // listener for textButton is added in DrawCanvas.java

    }

    JButton getTextButton() {
        return textButton;
    }

    static String getTextInput() {
        return textInput;
    }

    static FontInfo getFontInfo() {
        return fontInfo;
    }

    /*
     * subclass FontInfo
     * to get font info in DrawCanvas.java, need to use SideBar.FontInfo.fontXXX
     */
    class FontInfo {
        String fontName = "TimesRoman"; // e.g. TimesRoman
        int fontStyle = Font.PLAIN; // e.g. Font.PLAIN
        int fontSize = 15; // e.g. 10
    }

}

/*
 * Renderer class for fontName
 * Reference:
 * https://stackoverflow.com/questions/18704022/set-text-size-of-jcombobox-in-
 * swing
 */

class FontCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(
            JList<?> list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        Font font = new Font((String) value, Font.PLAIN, 20);
        label.setFont(font);
        return label;
    }
}