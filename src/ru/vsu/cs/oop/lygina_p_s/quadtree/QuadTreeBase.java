package ru.vsu.cs.oop.lygina_p_s.quadtree;

public class QuadTreeBase<T> {
    protected Node root;

    protected class Node {
        private T value;
        private Node[] children;

        public Node(T value) {
            this.value = value;
            this.children = null;
        }

        public T getValue() {
            return value;
        }

        public Node[] getChildren() {
            return children;
        }

        private void clear(){
            for (int i = 0; i < children.length; i++){
                children[i].clear();
                children[i] = null;
            }
        }
    }

    public QuadTreeBase(Node root) {
        this.root = root;
    }

    public QuadTreeBase(){
    }

    public void clear(){
        root.clear();
    }
}
