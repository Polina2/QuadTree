package ru.vsu.cs.oop.lygina_p_s.quadtree;

import ru.vsu.cs.oop.lygina_p_s.figures.Figure;
import ru.vsu.cs.oop.lygina_p_s.figures.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class RectSplitQuadTree extends QuadTreeBase<RectNodeParams> {
    public RectSplitQuadTree(Rectangle bounds) {
        super(new RectNodeParams(0, bounds));
    }

    private void split(Node node){
        int level = node.getValue().getLevel();
        Rectangle bounds = node.getValue().getBounds();
        double x = bounds.getX();
        double y = bounds.getY();
        double newWidth = bounds.getWidth()/2;
        double newHeight = bounds.getHeight()/2;
        node.createChildren();
        node.setChild(0, new Node(new RectNodeParams(level+1,
                new Rectangle(x + newWidth, y, newWidth, newHeight)
        )));
        node.setChild(1, new Node(new RectNodeParams(level + 1,
                new Rectangle(x, y, newWidth, newHeight)
        )));
        node.setChild(2, new Node(new RectNodeParams(level + 1,
                new Rectangle(x, y + newHeight, newWidth, newHeight)
        )));
        node.setChild(3, new Node(new RectNodeParams(level + 1,
                new Rectangle(x + newWidth, y + newHeight, newWidth, newHeight)
        )));
    }

    public void insertFigure(Figure figure) {
        insertFigure(figure, root);
    }

    private void insertFigure(Figure figure, Node node) {
        if (!figure.isInRect(node.getValue().getBounds()))
            return;
        if (node.hasChildren()){
            for (int i = 0; i < 4; i++){
                insertFigure(figure, node.getChild(i));
            }
            return;
        }

        node.getValue().addFigure(figure);
        int maxFigureCount = 4;
        int maxLevel = 6;
        if (node.getValue().getFigures().size() > maxFigureCount &&
        node.getValue().getLevel() < maxLevel) {
            split(node);
            List<Figure> toRemove = new ArrayList<>();
            for (Figure f : node.getValue().getFigures()) {
                for (int j = 0; j < 4; j++) {
                    if (f.isInRect(node.getChild(j).getValue().getBounds())) {
                        node.getChild(j).getValue().addFigure(f);
                        toRemove.add(f);
                    }
                }
            }
            node.getValue().getFigures().removeAll(toRemove);
        }
    }
/*
    private List<RectNodeParams> split(RectNodeParams value){
        int level = value.getLevel();
        Rectangle bounds = value.getBounds();
        double x = bounds.getX();
        double y = bounds.getY();
        double newWidth = bounds.getWidth()/2;
        double newHeight = bounds.getHeight()/2;
        return Arrays.asList(
                new RectNodeParams(level + 1,
                        new Rectangle(x + newWidth, y, newWidth, newHeight)),
                new RectNodeParams(level + 1,
                        new Rectangle(x, y, newWidth, newHeight)),
                new RectNodeParams(level + 1,
                        new Rectangle(x, y + newHeight, newWidth, newHeight)),
                new RectNodeParams(level + 1,
                        new Rectangle(x + newWidth, y + newHeight, newWidth, newHeight))
        );
    }

    public void insert(Figure figure){
        this.leavesDfs(new Visitor<RectNodeParams>() {
            @Override
            public List<RectNodeParams> visit(RectNodeParams value) {
                if (figure.isInRect(value.getBounds())) {
                    value.addFigure(figure);
                    int maxFigureCount = 4;
                    int maxLevel = 6;
                    if (value.getFigures().size() > maxFigureCount &&
                            value.getLevel() < maxLevel) {
                        List<RectNodeParams> values = split(value);
                        List<Figure> toRemove = new ArrayList<>();
                        for (Figure f : value.getFigures()) {
                            for (RectNodeParams r : values) {
                                if (f.isInRect(r.getBounds())) {
                                    r.addFigure(f);
                                    toRemove.add(f);
                                }
                            }
                        }
                        value.getFigures().removeAll(toRemove);
                        return values;
                    }
                }
                return null;
            }
        });
    }
*/
    public void removeFigure(Figure figure){
        //if all children are empty, node.children = new Node[]
    }
/*
    public List<Figure> findNeighbours(Figure figure){
        return findValueDfs(new Visitor<RectNodeParams>() {
            @Override
            public List<RectNodeParams> visit(RectNodeParams value) {
                if (figure.isInRect(value.getBounds()))
                    return List.of(value);
                return null;
            }
        }).getFigures();
    }
*/
    public List<Figure> findNeighbours(Figure figure) {
        return find(figure, root);
    }

    private List<Figure> find(Figure figure, Node node){
        if (!figure.isInRect(node.getValue().getBounds())){
            return null;
        } else {
            if (!node.hasChildren())
                return node.getValue().getFigures();
            else {
                for (int i = 0; i < 4; i++){
                    if (node.getChild(i) != null) {
                        if (figure.isInRect(node.getChild(i).getValue().getBounds()))
                            return find(figure, node.getChild(i));
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void insert(RectNodeParams value) {

    }

    @Override
    public void remove(RectNodeParams value) {

    }
}
