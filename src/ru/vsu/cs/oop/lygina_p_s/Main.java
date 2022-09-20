package ru.vsu.cs.oop.lygina_p_s;

import ru.vsu.cs.oop.lygina_p_s.figures.Figure;
import ru.vsu.cs.oop.lygina_p_s.figures.Point;
import ru.vsu.cs.oop.lygina_p_s.figures.Rectangle;
import ru.vsu.cs.oop.lygina_p_s.quadtree.QuadTree;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        QuadTree tree = new QuadTree(new Rectangle(0, 0, 10, 10));
        Point[] points = new Point[10];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < points.length; i++){
            points[i] = new Point(in.nextDouble(), in.nextDouble());
            tree.insert(points[i]);
        }
        List<Figure> figures = tree.findNeighbours(points[9]);

    }
}
