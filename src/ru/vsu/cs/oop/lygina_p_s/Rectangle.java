package ru.vsu.cs.oop.lygina_p_s;

public class Rectangle extends Figure {
    private final double x;
    private final double y;
    private final double width;
    private final double height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isInRect(Rectangle rect){
        return x >= rect.getX() && y >= rect.getY() &&
                x + width <= rect.getX() + rect.getWidth() &&
                y + height <= rect.getY() + rect.getHeight();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
