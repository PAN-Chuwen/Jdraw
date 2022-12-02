package jdraw;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.AttributeSet.FontAttribute;

import java.util.ArrayList;
import java.util.List;

// Model, abstract class for all shapes(e.g. Line, Rectangle, Oval...)
abstract class Shape {
    // color and stroke are set everytime mousePressed() is called (after
    // constructor)
    protected Color color;
    protected Stroke stroke;

    abstract void setPoint(int x, int y);

    abstract boolean containPoint(int x, int y);

    abstract void updataPosition(int x, int y);

    abstract void draw(Graphics g);

    void setColor(Color color) {
        this.color = color;
    }

    void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    Color getColor() {
        return color;
    }

    Stroke getStroke() {
        return stroke;
    }

    // drag shape
    
}

abstract class Geometric extends Shape {
    protected Point topLeftPoint;
    protected Point startPoint;
    protected Point endPoint;
    protected int width;
    protected int height;

    public Geometric(int x, int y) {
        startPoint = new Point(x, y);
        endPoint = new Point(x, y);
        // support Geometric drawing in all directions, not only from top-left to
        // bottom-right.
        topLeftPoint = new Point(x, y);

    }

    @Override
    void setPoint(int x, int y) {
        endPoint.x = x;
        endPoint.y = y;
        width = Math.abs(startPoint.x - endPoint.x);
        height = Math.abs(startPoint.y - endPoint.y);
        topLeftPoint.x = Math.min(startPoint.x, endPoint.x);
        topLeftPoint.y = Math.min(startPoint.y, endPoint.y);
    }

    @Override
    boolean containPoint(int x, int y) {
        boolean containsPoint = false;
        // if in Rectangle(applied to Oval as well)
        if (x >= topLeftPoint.x && x <= topLeftPoint.x + width)
            if (y >= topLeftPoint.y && y <= topLeftPoint.y + height)
                containsPoint = true;
        return containsPoint;
    }
    
    @Override
    void updataPosition(int xDelta, int yDelta) {
        // update topLeftPoint
        topLeftPoint.x += xDelta;
        topLeftPoint.y += yDelta;
    }
}

class Rectangle extends Geometric {

    public Rectangle(int x, int y) {
        super(x, y);
    }

    @Override
    void draw(Graphics g) {
        g.drawRect(topLeftPoint.x, topLeftPoint.y, width, height);
    }
}

class Oval extends Geometric {

    public Oval(int x, int y) {
        super(x, y);
    }

    @Override
    void draw(Graphics g) {
        g.drawOval(topLeftPoint.x, topLeftPoint.y, width, height);
    }
}

class Line extends Geometric {

    public Line(int x, int y) {
        super(x, y);
    }

    @Override
    void draw(Graphics g) {
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }

    // roughly calculated 

}

/*
 * an object of class Pencil consists of many points, we need to call
 * g.drawLine() for every point
 * upon mousePressed(), create new Pencil()
 * upon mouseDragged(), add point to pointList (implemented in setPoint()
 * method
 */
class Pencil extends Shape {
    List<Point> pointList;

    public Pencil() {
        pointList = new ArrayList<>();
    }

    @Override
    void draw(Graphics g) {
        for (Point point : pointList) {
            g.drawLine(point.x, point.y, point.x, point.y);
        }
    }

    @Override
    void setPoint(int x, int y) {
        pointList.add(new Point(x, y));
    }

    @Override
    boolean containPoint(int x, int y) {
        boolean containsPoint = false;
        BasicStroke basicStroke = (BasicStroke) stroke;
        // check every dot(point) in pointList
        for (Point point : pointList) {
            int radius = (int) basicStroke.getLineWidth();
            if (x >= point.x - radius && x <= point.x + radius)
                if (y >= point.y - radius && y <= point.y + radius) {
                    containsPoint = true;
                    break;
                }           
        }
        return containsPoint;
    }

    @Override
    void updataPosition(int xDelta, int yDelta) {
        // update topLeftPoint FOR EVERY DOT
        for (Point point : pointList) {
            point.x += xDelta;
            point.y += yDelta;
        }
       
    }
}

// Text shape is like rectangle
class Text extends Geometric {
    private String textContent;
    private Font font;
    private Point textStartPoint = new Point(0, 0);

    public Text(int x, int y, String s) {
        super(x, y);
        textContent = s;
    }

    public void setFont(TextPanel.FontInfo fontInfo) {
        font = new Font(fontInfo.fontName, fontInfo.fontStyle, fontInfo.fontSize);
    }

    @Override
    void setPoint(int x, int y) {
        textStartPoint.x = x;
        textStartPoint.y = y;
    }

    @Override
    void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        // RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(font);
        g2d.drawString(textContent, textStartPoint.x, textStartPoint.y);
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}