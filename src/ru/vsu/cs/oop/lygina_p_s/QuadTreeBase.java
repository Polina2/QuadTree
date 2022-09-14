package ru.vsu.cs.oop.lygina_p_s;

public class QuadTreeBase<T> {
    protected Node<T> root;

    protected static class Node<K> {
        K value;
        Node<K>[] children;

        public Node(K value) {
            this.value = value;
            this.children = new Node[4];
        }

        void clear(){
            for (int i = 0; i < children.length; i++){
                children[i].clear();
                children[i] = null;
            }
        }
    }

    public QuadTreeBase(Node<T> root) {
        this.root = root;
    }

    public void clear(){
        root.clear();
    }
}
