package jdraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextPanel extends JPanel {
    private static final int FONTSIZE_MIN = 1;
    private static final int FONTSIZE_MAX = 20;

    // Ref: https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html#style
    private static String textInput;
    private static FontInfo fontInfo;
    private JComboBox<Integer> textFontSizeList;
    private JComboBox<String> textFontNameList;
    private JTextField textField = new JTextField("Your text here", 20);
    private JButton textButton = new JButton("Text");
    private String[] fontNameList;
    private Integer[] fontSizeList = new Integer[FONTSIZE_MAX];

    public TextPanel() {
        setLayout(new FlowLayout());

        // init static member variable, note that we can't init top because the class
        // hasn't been created yet
        fontInfo = new FontInfo();
        // textField, already done
        textInput = textField.getText();

        // fontSize comboBox, 1-20
        for (int fontsize = FONTSIZE_MIN; fontsize < FONTSIZE_MAX; fontsize++) {
            fontSizeList[fontsize] = fontsize;
        }
        textFontSizeList = new JComboBox<>(fontSizeList);

        // fontName comboBox
        fontNameList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        textFontNameList = new JComboBox<>(fontNameList); // add fonts to font combobox(list)
        textFontNameList.setRenderer(new FontCellRenderer()); // set rederer for text fonts

        add(textFontSizeList);
        add(textFontNameList);
        add(textField);
        add(textButton);

        // add listener for swing components in TextPanel
        // listener for textField
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

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

        // listener for textButton
        textButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                textInput = textField.getText(); // update textInput
            }
        });

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
        int fontSize = 3; // e.g. 3
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