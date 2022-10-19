package ru.vsu.cs.oop.lygina_p_s;

import ru.vsu.cs.oop.lygina_p_s.figures.Figure;
import ru.vsu.cs.oop.lygina_p_s.figures.Point;
import ru.vsu.cs.oop.lygina_p_s.figures.Rectangle;
import ru.vsu.cs.oop.lygina_p_s.quadtree.IntegerQuadTree;
import ru.vsu.cs.oop.lygina_p_s.quadtree.RectSplitQuadTree;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        IntegerQuadTree tree1 = new IntegerQuadTree();
        for (int i = 1; i <= 20; i++){
            tree1.insert(i);
        }

        tree1.dfs(value -> {System.out.print("" + value + ' ');});
        System.out.println();
        tree1.bfs(value -> {System.out.print("" + value + ' ');});

        tree1.remove(2);
        tree1.remove(15);
        tree1.remove(1);

        RectSplitQuadTree tree = new RectSplitQuadTree(new Rectangle(0, 0, 10, 10));
        Point[] points = new Point[10];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < points.length; i++){
            points[i] = new Point(in.nextDouble(), in.nextDouble());
            tree.insertFigure(points[i]);
        }
        List<Figure> figures = tree.findNeighbours(points[9]);
        for (Figure figure : figures) {
            System.out.println(figure);
        }
    }
}
