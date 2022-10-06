package ru.vsu.cs.oop.lygina_p_s.quadtree;

import java.util.ArrayList;
import java.util.List;

public abstract class QuadTreeBase<T> {
    protected Node root;

    protected class Node {
        private T value;
        private List<Node> children;

        public Node(T value) {
            this.value = value;
            this.children = null;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void removeChild(int index){
            children.remove(index);
            children.add(null);
        }

        public Node getChild(int index){
            return children.get(index);
        }

        public void setChild(int index, Node value){
            children.set(index, value);
        }

        public void createChildren(){
            this.children = new ArrayList<>();
            for (int i = 0; i < 4; i++){
                children.add(null);
            }
        }

        public boolean hasChildren(){
            return children != null;
        }

        private void clear(){
            for (int i = 0; i < children.size(); i++){
                children.get(i).clear();
                children.set(i, null);
            }
        }
    }

    public QuadTreeBase(T value) {
        this.root = new Node(value);
    }

    public QuadTreeBase(){}

    public abstract void insert(T value);

    public abstract void remove(T value);
    //dfs, bfs!!!!!!!!!!!!!!!

    /*
    @FunctionalInterface
    public interface Visitor<T> {
        List<T> visit(T value);
    }

    public void leavesDfs(Visitor<T> visitor){
        class Inner{
            void dfs(Node node, Visitor<T> visitor1){
                if (node.children != null){
                    for (Node child : node.children){
                        dfs(child, visitor1);
                    }
                } else {
                    List<T> list = visitor1.visit(node.getValue());
                    if (list != null) {
                        node.createChildren();
                        for (int i = 0; i < node.children.size(); i++) {
                            node.children.set(i, new Node(list.get(i)));
                        }
                    }
                }
            }
        }
        new Inner().dfs(root, visitor);
    }

    public T findValueDfs(Visitor<T> visitor){
        class Inner{
            T dfs(Node node, Visitor<T> visitor1){
                if (node.children != null){
                    for (Node child : node.children){
                        dfs(child, visitor1);
                    }
                }
                List<T> list = visitor1.visit(node.getValue());
                return list.get(0);
            }
        }
        return new Inner().dfs(root, visitor);
    }
*/
    public void clear(){
        root.clear();
    }
}
