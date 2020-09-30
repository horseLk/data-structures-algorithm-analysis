package chapter09.example.example01;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * 用于改进拓扑排序的一个新的图类
 */
public class GraphPro {
    private List<Vertex> vertices;
    private Queue<Vertex> q = new ArrayDeque<>(); // 用于保存入度为 0 的节点

    public GraphPro(List<Vertex> vertices) {
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
            if (v1.getIndegree() == 0) {
                q.add(v1);
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
        if (q.size() != 0) {
            return q.remove();
        }
        return null;
    }

    public void remove(Vertex v) {
        if (v == null) return;
        boolean flag = vertices.remove(v);
        if (flag) {
            for (Vertex ver : v.getVertices()) {
                int oldIndegree = ver.getIndegree();
                if (oldIndegree == 1) {
                    q.add(ver);
                }
                ver.setIndegree(oldIndegree - 1);
            }
        }
    }
}
