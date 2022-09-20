package ru.vsu.cs.oop.lygina_p_s.quadtree;

import ru.vsu.cs.oop.lygina_p_s.figures.Figure;
import ru.vsu.cs.oop.lygina_p_s.figures.Rectangle;

import java.util.List;

public class QuadTree extends QuadTreeBase<QuadTreeNodeParams> {
    public QuadTree(Rectangle bounds) {
        QuadTreeNodeParams params = new QuadTreeNodeParams(0, bounds);
        root = new Node(params);
    }

    private void split(Node node){
        int level = node.getValue().getLevel();
        Rectangle bounds = node.getValue().getBounds();
        double x = bounds.getX();
        double y = bounds.getY();
        double newWidth = bounds.getWidth()/2;
        double newHeight = bounds.getHeight()/2;
        node.getChildren()[0] = new Node(new QuadTreeNodeParams(level+1,
                new Rectangle(x + newWidth, y, newWidth, newHeight)
        ));
        node.getChildren()[1] = new Node(new QuadTreeNodeParams(level + 1,
                new Rectangle(x, y, newWidth, newHeight)
        ));
        node.getChildren()[2] = new Node(new QuadTreeNodeParams(level + 1,
                new Rectangle(x, y + newHeight, newWidth, newHeight)
        ));
        node.getChildren()[3] = new Node(new QuadTreeNodeParams(level + 1,
                new Rectangle(x + newWidth, y + newHeight, newWidth, newHeight)
        ));
    }

    public void insert(Figure figure) {
        insert(figure, root);
    }

    private boolean insert(Figure figure, Node node) {
        if (!figure.isInRect(node.getValue().getBounds()))
            return false;
        if (node.getChildren()[0] != null){
            for (int i = 0; i < node.getChildren().length; i++){
                insert(figure, node.getChildren()[i]);
            }
            return true;
        }

        node.getValue().addFigure(figure);
        int maxFigureCount = 4;
        int maxLevel = 6;
        if (node.getValue().getFigures().size() > maxFigureCount &&
        node.getValue().getLevel() < maxLevel) {
            split(node);
            for (int i = 0; i < node.getValue().getFiguresCount(); i++){
                for (int j = 0; j < node.getChildren().length; j++) {
                    if (insert(node.getValue().getFigures().get(i), node.getChildren()[j])){
                        node.getValue().removeFigure(i);
                        i--;
                        break;
                    }
                }
            }
        }
        return false;
    }

    public void remove(Figure object){
        //if all children are empty, node.children = new Node[]
    }

    public List<Figure> findNeighbours(Figure figure) {
        return find(figure, root);
    }

    private List<Figure> find(Figure figure, Node node){
        if (!figure.isInRect(node.getValue().getBounds())){
            return null;
        } else {
            if (node.getChildren()[0] == null)
                return node.getValue().getFigures();
            else {
                for (Node child : node.getChildren()){
                    if (figure.isInRect(child.getValue().getBounds()))
                        return find(figure, child);
                }
            }
        }
        return null;
    }
}
