package ru.vsu.cs.oop.lygina_p_s.quadtree;

import ru.vsu.cs.oop.lygina_p_s.figures.Figure;
import ru.vsu.cs.oop.lygina_p_s.figures.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class RectNodeParams {
    private int level;
    private List<Figure> figures;
    private Rectangle bounds;

    public RectNodeParams(int level, Rectangle bounds) {
        this.level = level;
        this.bounds = bounds;
        this.figures = new ArrayList<>();
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

    public void addFigure(Figure figure){
        figures.add(figure);
    }

    public void removeFigure(int index){
        figures.remove(index);
    }

    public int getFiguresCount(){
        return figures.size();
    }
}
