package chapter09.example.example04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 最小生成树的Prim算法
 */
public class Prim {
    static class Vertex {
        int val;
        List<Vertex> vertices; // 邻接表
        List<Integer> distances; // 节点与其连接点的距离
        Vertex parent; // 最小生成树的父节点

        public Vertex(int v) {
            this.val = v;
            parent = null;
            vertices = new ArrayList<>();
            distances = new ArrayList<>();
        }

        public List<Vertex> getVertices() {
            return vertices;
        }

        public void setVertices(List<Vertex> vertices) {
            this.vertices = vertices;
        }

        public List<Integer> getDistances() {
            return distances;
        }

        public void setDistances(List<Integer> distances) {
            this.distances = distances;
        }

        public Vertex getParent() {
            return parent;
        }

        public void setParent(Vertex parent) {
            this.parent = parent;
        }
    }

    static class Graph {
        List<Vertex> vertices;
        public Graph(List<Vertex> ver) {
            this.vertices = ver;
        }

        public int nodesCount() {
            return vertices.size();
        }

        public int findMin() {
            Vertex s = null;
            Vertex t = null;
            int minDist = Integer.MAX_VALUE;
            for (Vertex v : vertices) {
                int len = v.getVertices().size();
                for (int i = 0; i < len; i++) {
                    if (v.getDistances().get(i) < minDist) {
                        s = v;
                        t = v.getVertices().get(i);
                        minDist = v.getDistances().get(i);
                    }
                }
            }
            return minDist;
        }

        public List<Vertex> findMinTwoNOde() {
            List<Vertex> res = new ArrayList<>();
            int minDist = Integer.MAX_VALUE;
            Vertex s = null;
            Vertex t = null;
            for (Vertex v : vertices) {
                int len = v.getVertices().size();
                for (int i = 0; i < len; i++) {
                    if (v.getDistances().get(i) < minDist) {
                        s = v;
                        t = v.getVertices().get(i);
                        minDist = v.getDistances().get(i);
                    }
                }
            }
            res.add(s);
            res.add(t);
            return res;
        }
    }

    /**
     * 邻接表的prim求最小生成树
     * @param graph
     * @return
     */
    public List<Vertex> prim(Graph graph) {
        List<Vertex> res = graph.findMinTwoNOde();
        res.get(1).setParent(res.get(0));
        int count = 1; // 边的条数
        int len = graph.nodesCount() - 1;
        while (count < len) {
            Vertex t = null;
            int minDist = Integer.MAX_VALUE;
            for (Vertex hasSure : res) {
                for (int i = 0; i < hasSure.getVertices().size(); i++) {
                    if (res.contains(hasSure.getVertices().get(i))) continue;
                    if (hasSure.getDistances().get(i) < minDist) {
                        t = hasSure.getVertices().get(i);
                        t.setParent(hasSure);
                        minDist = hasSure.getDistances().get(i);
                    }
                }
            }
            res.add(t);
            count++;
        }
        return res;
    }
    
    public List<String> prim(int[][] M) {
        int len = M.length;
        List<Integer> sureNodes = new ArrayList<>();
        List<String> res = new ArrayList<>();
        int s = -1;
        int t = -1;
        int minVal = 1000;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) continue;
                if (M[i][j] < minVal) {
                    s = i;
                    t = j;
                    minVal = M[i][j];
                }
            }
        }
        sureNodes.add(s);
        sureNodes.add(t);
        res.add((s + 1) + "-" + (t + 1));
        while (res.size() < len - 1) {
            minVal = 1000;
            s = -1;
            t = -1;
            for (int start : sureNodes) {
                for (int i = 0; i < len; i++) {
                    if (start == i) continue;
                    if (M[start][i] < minVal && !sureNodes.contains(i)) {
                        s = start;
                        t = i;
                        minVal = M[start][i];
                    }
                }
            }
            sureNodes.add(t);
            res.add((s + 1) + "-" + (t + 1));
        }
        return res;
    }

    public static void main(String[] args) {
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);

        List<Vertex> vertices1 = Arrays.asList(v2, v3, v4);
        List<Integer> dists1 = Arrays.asList(2, 4, 1);
        v1.setVertices(vertices1);
        v1.setDistances(dists1);

        List<Vertex> vertices2 = Arrays.asList(v1, v4, v5);
        List<Integer> dists2 = Arrays.asList(2, 3, 10);
        v2.setVertices(vertices2);
        v2.setDistances(dists2);

        List<Vertex> vertices3 = Arrays.asList(v1, v4, v6);
        List<Integer> dists3 = Arrays.asList(4, 2, 5);
        v3.setVertices(vertices3);
        v3.setDistances(dists3);

        List<Vertex> vertices4 = Arrays.asList(v1, v2, v3, v5, v6, v7);
        List<Integer> dists4 = Arrays.asList(1, 3, 2, 7, 8, 4);
        v4.setVertices(vertices4);
        v4.setDistances(dists4);

        List<Vertex> vertices5 = Arrays.asList(v2, v4, v7);
        List<Integer> dists5 = Arrays.asList(10, 7, 6);
        v5.setVertices(vertices5);
        v5.setDistances(dists5);

        List<Vertex> vertices6 = Arrays.asList(v3, v4, v7);
        List<Integer> dists6 = Arrays.asList(5, 8, 1);
        v6.setVertices(vertices6);
        v6.setDistances(dists6);

        List<Vertex> vertices7 = Arrays.asList(v4, v5, v6);
        List<Integer> dists7 = Arrays.asList(4, 6, 1);
        v7.setVertices(vertices7);
        v7.setDistances(dists7);
        
        List<Vertex> vv = Arrays.asList(v1, v2, v3, v4, v5, v6, v7);
        Graph gg = new Graph(vv);
        List<Vertex> res = new Prim().prim(gg);

        int MAX_V = 1000;
        int[][] M = {
                {0, 2, 4, 1, MAX_V, MAX_V, MAX_V},
                {2, 0, MAX_V, 3, 10, MAX_V, MAX_V},
                {4, MAX_V, 0, 2, MAX_V, 5, MAX_V},
                {1, 3, 2, 0, 7, 8, 4},
                {MAX_V, 10, MAX_V, 7, 0, MAX_V, 6},
                {MAX_V, MAX_V, 5, 8, MAX_V, 0, 1},
                {MAX_V, MAX_V, MAX_V, 4, 6, 1, 0}
        };

        List<String> prim = new Prim().prim(M);
        System.out.println(prim);
    }
}
