package ru.vsu.cs.oop.lygina_p_s;

import java.util.ArrayList;
import java.util.List;

public class QuadTree extends QuadTreeBase<QuadTreeNodeParams> {
    private final int MAX_FIGURE_COUNT = 8;
    private final int MAX_LEVEL = 6;

    public QuadTree(Rectangle bounds) {
        super(null);
        root = new Node<>(
                new QuadTreeNodeParams(0, bounds)
        );
    }

    private void split(Node<QuadTreeNodeParams> node){
        int level = node.value.getLevel();
        Rectangle bounds = node.value.getBounds();
        node.children[0] = new Node<>(new QuadTreeNodeParams(level+1, new Rectangle(
                bounds.getX() + bounds.getWidth()/2, bounds.getY(),
                bounds.getWidth()/2, bounds.getHeight()/2
        )));
        node.children[1] = new Node<>(new QuadTreeNodeParams(level + 1, new Rectangle(bounds.getX(), bounds.getY(),
                bounds.getWidth()/2, bounds.getHeight()/2
        )));
        node.children[2] = new Node<>(new QuadTreeNodeParams(level + 1, new Rectangle(
                bounds.getX(), bounds.getY() + bounds.getHeight()/2,
                bounds.getWidth()/2, bounds.getHeight()/2
        )));
        node.children[3] = new Node<>(new QuadTreeNodeParams(level + 1, new Rectangle(
                bounds.getX() + bounds.getWidth()/2, bounds.getY() + bounds.getHeight()/2,
                bounds.getWidth()/2, bounds.getHeight()/2
        )));
    }

    public void insert(Figure figure) {
        insert(figure, root);
    }

    private boolean insert(Figure figure, Node<QuadTreeNodeParams> node) {
        if (!figure.isInRect(node.value.getBounds()))
            return false;
        if (node.children[0] != null){
            for (int i = 0; i < node.children.length; i++){
                insert(figure, node.children[i]);
            }
            return true;
        }
        node.value.getFigures().add(figure);
        if (node.value.getFigures().size() > MAX_FIGURE_COUNT &&
        node.value.getLevel() < MAX_LEVEL) {
            split(node);
            List<Figure> figures = node.value.getFigures();
            for (int i = 0; i < figures.size(); i++){
                for (int j = 0; j < node.children.length; j++) {
                    if (insert(figures.get(i), node.children[j])){
                        figures.remove(i);
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

    public List<Figure> findNeighbours(Figure object) {
        return null;
    }
}
