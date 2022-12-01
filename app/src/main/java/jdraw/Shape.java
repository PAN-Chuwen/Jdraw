package jdraw;

import java.awt.Color;
import java.awt.Graphics;

// Model, interface for all shapes(e.g. line, rectangle, oval...)
abstract class Shape {
    protected int x;
    protected int y;

    public Shape(int mouseX, int mouseY) {
        this.x = mouseX;
        this.y = mouseY;
    }

    public Shape() {
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    abstract void setPoint(int mouse_X, int mouse_Y);
    abstract void draw(Graphics g);


}

class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    void setPoint(int mouse_X, int mouse_Y) {
        setWidth(mouse_X - x);
        setHeight(mouse_Y - y);
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

class PolyLine extends Shape {

    @Override
    void draw(Graphics g) {

    }
    @Override
    void setPoint(int mouse_X, int mouse_Y) {

    } 

}