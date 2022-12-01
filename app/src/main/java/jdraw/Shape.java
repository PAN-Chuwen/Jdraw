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

class Rectangle extends Shape {
    private Point startPoint;
    private Point endPoint;
    private int width;
    private int height;

    // create Rectangle whose size = 0
    public Rectangle(int x, int y) {
        startPoint = new Point(x, y);
        endPoint = new Point(x, y);
    }

    private void drawRectUsingDiagnol(Graphics g) {
        Point upperLeftPoint = new Point(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y));
        width = Math.abs(startPoint.x - endPoint.x);
        height = Math.abs(startPoint.y - endPoint.y);
        g.drawRect(upperLeftPoint.x, upperLeftPoint.y, width, height);
    }

    @Override
    void setPoint(int x, int y) {
        endPoint.x = x;
        endPoint.y = y;
    }

    @Override
    void draw(Graphics g) {
        g.setColor(Color.BLACK);
        drawRectUsingDiagnol(g);
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

class Oval extends Shape {
    @Override
    void draw(Graphics g) {

    }

    @Override
    void setPoint(int x, int y) {

    }
}

class Line extends Shape {
    @Override
    void draw(Graphics g) {

    }

    @Override
    void setPoint(int x, int y) {

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