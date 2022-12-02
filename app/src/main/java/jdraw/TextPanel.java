package jdraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextPanel extends JPanel {
    private static final int FONTSIZE_MIN = 1;
    private static final int FONTSIZE_MAX = 20;

    // check https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html#style for Font


    private JComboBox<Integer> textFontSizeList;
    private JComboBox<String> textFontNameList;
    private JTextArea textArea = new JTextArea();
    private JButton textButton = new JButton("Text");
    private String[] fontNameList;
    private Integer[] fontSizeList = new Integer[FONTSIZE_MAX];

    public TextPanel() {
        setLayout(new FlowLayout());
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
        add(textArea);
        add(textButton);
    }
    //subclass FontInfo
    class FontInfo {
        private String fontName; // e.g. TimesRoman
        private int fontStyle; // e.g. Font.PLAIN
        private int fontSize; // e.g. 3
    
        public FontInfo(String fontName, int fontStyle, int fontSize) {
            this.fontName = fontName;
            this.fontStyle = fontStyle;
            this.fontSize = fontSize;
        }
    
        public FontInfo() {
            this.fontName = "TimesRoman";
            this.fontStyle = Font.PLAIN;
            this.fontSize = 3;
        }
        String getFontName() {
            return fontName;
        }
        void setFontName(String fontName) {
            this.fontName = fontName;
        }
        int getFontStyle() {
            return fontStyle;
        }
        void setFontStyle(int fontStyle) {
            this.fontStyle = fontStyle;
        }
        int getFontSize() {
            return fontSize;
        }
        void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }
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