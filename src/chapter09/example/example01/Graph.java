package chapter09.example.example01;

import java.util.List;

/**
 * 图类
 */
public class Graph {
    private List<Vertex> vertices;

    public Graph(List<Vertex> vertices) {
        this.vertices = vertices;
        initIndegree();
    }

    private void initIndegree() {
        for (int i = 0; i < vertices.size(); i++) {
            Vertex v1 = vertices.get(i);
            for (int j = 0; j < vertices.size(); j++) {
                if (i == j) continue;
                if (vertices.get(j).getVertices().contains(v1)) {
                    v1.setIndegree(v1.getIndegree() + 1);
                }
            }
        }
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public int getVerticesCount() {
        return vertices.size();
    }

    public Vertex findNewVertexOfIndegreeZero() {
        for (Vertex v : vertices) {
            if (v.getIndegree() == 0) {
                return v;
            }
        }
        return null;
    }

    public void remove(Vertex v) {
        if (v == null) return;
        boolean flag = vertices.remove(v);
        if (flag) {
            for (Vertex ver : v.getVertices()) {
                ver.setIndegree(ver.getIndegree() - 1);
            }
        }
    }

    public boolean contains(Vertex v) {
        return vertices.contains(v);
    }

    public Vertex getVertex(int val) {
        for (Vertex v : vertices) {
            if (v.getVal() == val)
                return v;
        }
        return null;
    }
}
