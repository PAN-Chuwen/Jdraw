package jdraw;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

// Model, interface for all shapes(e.g. line, rectangle, oval...)
abstract class Shape {



    abstract void setPoint(int x, int y);

    abstract void draw(Graphics g);

}

class Rectangle extends Shape {
    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    void setPoint(int x, int y) {
        setWidth(x - this.x);
        setHeight(y - this.y);
    }

    @Override
    void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    private void setWidth(int width) {
        this.width = width;
    }

    private void setHeight(int height) {
        this.height = height;
    }
}

/* an object of class Pencil consists of many points, we need to call g.drawLine() for every point
 * upon mousePressed(), create new Pencil()
 * upon mouseDragged(), add point to pointList (implemented in setPoint() method)
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