package ru.vsu.cs.oop.lygina_p_s;

import java.util.ArrayList;
import java.util.List;

public class QuadTreeNodeParams {
    private int level;
    private List<Figure> figures;
    private Rectangle bounds;

    public QuadTreeNodeParams(int level, List<Figure> figures, Rectangle bounds) {
        this.level = level;
        this.figures = figures;
        this.bounds = bounds;
    }

    public QuadTreeNodeParams(int level, Rectangle bounds) {
        new QuadTreeNodeParams(level, new ArrayList<>(), bounds);
    }

    public int getLevel() {
        return level;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
