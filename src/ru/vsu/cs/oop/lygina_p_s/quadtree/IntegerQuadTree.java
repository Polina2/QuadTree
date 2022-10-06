package ru.vsu.cs.oop.lygina_p_s.quadtree;

import java.util.LinkedList;
import java.util.Queue;

public class IntegerQuadTree extends QuadTreeBase<Integer>{

    @Override
    public void insert(Integer value) {
        if (root == null){
            root = new Node(value);
        } else {
            insert(value, root);
        }
    }

    private void insert(Integer value, Node node){
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node curr = queue.poll();
            if (!curr.hasChildren()){
                curr.createChildren();
                curr.setChild(0, new Node(value));
                return;
            }
            for (int i = 0; i < 4; i++){
                if (curr.getChild(i) == null) {
                    curr.setChild(i, new Node(value));
                    return;
                }
                queue.add(curr.getChild(i));
            }
        }
    }

    @Override
    public void remove(Integer value) {
        remove(value, root);
    }

    private Node findNode(Integer value, Node node){
        if (node.getValue().equals(value))
            return node;
        else {
            for (int i = 0; i < 4; i++){
                if (node.getChild(i) != null){
                    return findNode(value, node.getChild(i));
                }
            }
        }
        return null;
    }

    private void remove(Integer value, Node node){
        if (node.getValue().equals(value)){
            node.setValue(node.getChild(0).getValue());
            if (!node.getChild(0).hasChildren()){
                node.removeChild(0);
            } else {
                remove(node.getValue(), node.getChild(0));
            }
        } else {
            if (node.hasChildren()) {
                for (int i = 0; i < 4; i++) {
                    if (node.getChild(i) != null) {
                        if (node.getChild(i).getValue().equals(value) &&
                        !node.getChild(i).hasChildren())
                            node.removeChild(i);
                        else
                            remove(value, node.getChild(i));
                    }
                }
            }
        }
    }
}
