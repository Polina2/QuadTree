package ru.vsu.cs.oop.lygina_p_s.quadtree;

@FunctionalInterface
public interface Visitor<T>{
    void visit(T value);
}
