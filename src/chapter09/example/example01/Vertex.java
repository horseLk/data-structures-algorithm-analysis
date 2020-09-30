package chapter09.example.example01;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的节点类
 */
public class Vertex {
    private int val;
    private int indegree; // 入度
    private List<Vertex> vertices;

    public Vertex(int val) {
        this(val, new ArrayList<>());
    }

    public Vertex(int value, List<Vertex> vertices) {
        this.indegree = 0;
        this.val = value;
        this.vertices = vertices;
    }

    public void setIndegree(int indegree) {
        this.indegree = indegree;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void addToVertices(Vertex... vertices) {
        for (Vertex v : vertices) {
            this.vertices.add(v);
        }
    }

    public boolean removeFromVertices(Vertex vertex) {
        return this.vertices.remove(vertex);
    }

    public int getIndegree() {
        return indegree;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public int getVal() {
        return val;
    }
}

