package chapter09.example.example02;

import chapter09.example.example01.Graph;
import chapter09.example.example01.Vertex;

import java.util.*;

/**
 * 最短路径算法
 */
public class ShortestPath {
    /**
     * 无权图的最短路径算法
     * @param v
     */
    public Map<Vertex, Integer> unweight(Graph graph, Vertex v) {
        Map<Vertex, Integer> res = new HashMap<>();
        if (!graph.contains(v)) return res;
        int dist = 0;
        res.put(v, dist);
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(v);
        while (res.size() < graph.getVertices().size()) {
            dist += 1;
            List<Vertex> temp = new ArrayList<>();
            for (Vertex v1 : vertices) {
                for (Vertex v2 : v1.getVertices()) {
                    if (!res.containsKey(v2)) {
                        res.put(v2, dist);
                        temp.add(v2);
                    }
                }
            }
            vertices.clear();
            vertices.addAll(temp);
        }
        return res;
    }
}
