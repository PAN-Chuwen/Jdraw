package jdraw;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

// Model, interface for all shapes(e.g. Line, Rectangle, Oval...)
abstract class Shape {

    abstract void setPoint(int x, int y);

    abstract void draw(Graphics g);

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

}

class Rectangle extends Geometric {

    public Rectangle(int x, int y) {
        super(x, y);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(topLeftPoint.x, topLeftPoint.y, width, height);
    }
}

class Oval extends Geometric {
    
    public Oval(int x, int y) {
        super(x, y);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(topLeftPoint.x, topLeftPoint.y, width, height);
    }
}

class Line extends Geometric {
    
    public Line(int x, int y) {
        super(x, y);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
    
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
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}