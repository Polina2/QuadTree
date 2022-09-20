package ru.vsu.cs.oop.lygina_p_s.figures;

public class Point extends Figure {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean isInRect(Rectangle rect) {
        return x >= rect.getX() && x <= rect.getX()+ rect.getWidth() &&
                y >= rect.getY() && y <= rect.getY()+ rect.getHeight();
    }
}
