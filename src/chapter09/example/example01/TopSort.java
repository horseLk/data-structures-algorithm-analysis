package chapter09.example.example01;

import java.util.ArrayList;
import java.util.List;

/**
 * 拓扑排序
 */
public class TopSort {
    /**
     * 拓扑排序
     * @param graph
     */
    public void topSort(Graph graph) {
        List<Integer> res = new ArrayList<>();
        int len = graph.getVerticesCount();
        for (int counter = 0; counter < len; counter++) {
            Vertex v = graph.findNewVertexOfIndegreeZero();
            if (v == null)
                throw new NullPointerException();
            res.add(v.getVal());
            graph.remove(v);
        }
        System.out.println(res);
    }

    /**
     * 使用队列改进版的拓扑排序
     * @param graph
     */
    public void topSortPro(GraphPro graph) {
        List<Integer> res = new ArrayList<>();
        int len = graph.getVerticesCount();
        for (int counter = 0; counter < len; counter++) {
            Vertex v = graph.findNewVertexOfIndegreeZero();
            if (v == null)
                throw new NullPointerException();
            res.add(v.getVal());
            graph.remove(v);
        }
        System.out.println(res);
    }
}
