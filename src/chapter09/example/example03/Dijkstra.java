package chapter09.example.example03;

import java.util.*;

public class Dijkstra {
    // 定点类
    public static class Vertex {
        private static final int infinite_dis = Integer.MAX_VALUE;

        private List<Vertex> adjacent;
        private List<Integer> distances;
        private int val;
        private int adjDist; // 此节点距离
        private Vertex pre; // 从起始节点到这个节点的最短路径下的上一个节点

        public Vertex() {
            this.adjacent = new ArrayList<>();
            this.distances = new ArrayList<>();
            this.adjDist = infinite_dis;
            this.pre = null;
        }

        public List<Vertex> getAdjacent() {
            return adjacent;
        }

        public List<Integer> getDists() {
            return distances;
        }

        public void setAdjacent(List<Vertex> adjacent, List<Integer> dists) {
            this.adjacent = adjacent;
            this.distances = dists;
        }

        public void addAdjacent(Vertex[] vertices, Integer[] dists) {
            if (vertices.length != dists.length)
                throw new IllegalArgumentException();
            adjacent.addAll(Arrays.asList(vertices));
            distances.addAll(Arrays.asList(dists));
        }

        public Vertex(int _v) {
            this();
            this.val = _v;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getAdjDist() {
            return adjDist;
        }

        public void setAdjDist(int adjDist) {
            this.adjDist = adjDist;
        }

        public Vertex getPre() {
            return pre;
        }

        public void setPre(Vertex pre) {
            this.pre = pre;
        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof Vertex)){
                throw new ClassCastException("an object to compare with a Vertex");
            }
            return this.val == ((Vertex) o).val;
        }
    }

    // 图类
    public static class Graph {
        private List<Vertex> vertices;

        public Graph(List<Vertex> vertices) {
            this.vertices = vertices;
        }
    }

    /**
     * 邻接表的dijkstra算法
     * @param start
     * @return
     */
    public Map<Vertex, Integer> dijkstra(Vertex start) {
        Map<Vertex, Integer> res = new HashMap<>();
        Map<Vertex, Integer> temp = new HashMap<>();
        res.put(start, 0);
        int len = start.getAdjacent().size();
        for (int i = 0; i < len; i++) {
            temp.put(start.getAdjacent().get(i), start.getDists().get(i));
            start.getAdjacent().get(i).setPre(start);
        }
        while (!temp.isEmpty()) {
            Vertex minKey = null;
            int minDist = Integer.MAX_VALUE;
            for (Vertex vertex : temp.keySet()) {
                if (temp.get(vertex) < minDist) {
                    minDist = temp.get(vertex);
                    minKey = vertex;
                }
            }
            res.put(minKey, minDist);
            temp.remove(minKey);
            len = minKey.getAdjacent().size();
            for (int i = 0; i < len; i++) {
                Vertex cur = minKey.getAdjacent().get(i);
                if ((!temp.containsKey(cur) && !res.containsKey(cur))) {
                    temp.put(cur, res.get(minKey) + minKey.getDists().get(i));
                    cur.setPre(minKey);
                }
                else if (temp.containsKey(cur) && res.get(minKey) + minKey.getDists().get(i) < temp.get(cur)) {
                    temp.put(cur, res.get(minKey) + minKey.getDists().get(i));
                    cur.setPre(minKey);
                }
            }
        }
        return res;
    }

    /**
     * 邻接矩阵的dijkstra算法
     */
    public Map<Integer, Integer> dijkstra(int[][] M, int start) {
        int len = M.length;
        int mxValue = Integer.MAX_VALUE;
        if (start >=len || start < 0)
            throw new IndexOutOfBoundsException();
        Map<Integer, Integer> res = new HashMap<>();
        Map<Integer, Integer> temp = new HashMap<>();
        res.put(start, 0);
        for (int i = 0; i < len; i++) {
            if (M[start][i] < mxValue) {
                temp.put(i, M[start][i]);
            }
        }
        while (!temp.isEmpty()) {
            int minKey = -1;
            int minValue = Integer.MAX_VALUE;
            for (int key : temp.keySet()) {
                if (temp.get(key) < minValue) {
                    minKey = key;
                    minValue = temp.get(key);
                }
            }
            res.put(minKey, minValue);
            temp.remove(minKey);

            for (int i = 0; i < len; i++) {
                int cur;
                if ((cur = M[minKey][i]) == mxValue) continue;
                if (!temp.containsKey(i) && !res.containsKey(i)) {
                    temp.put(i, minValue + cur);
                }
                if (temp.containsKey(i) && minValue + cur < temp.get(i)) {
                    temp.put(i, minValue + cur);
                }
            }
        }
        return res;
    }

/*
    public static void main(String[] args) {
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);

        List<Vertex> vertices1 = new ArrayList<>();
        vertices1.add(v2);
        vertices1.add(v4);
        List<Integer> dist1 = new ArrayList<>();
        dist1.add(2);
        dist1.add(1);
        v1.setAdjacent(vertices1, dist1);

        List<Vertex> vertices2 = new ArrayList<>();
        vertices2.add(v4);
        vertices2.add(v5);
        List<Integer> dist2 = new ArrayList<>();
        dist2.add(3);
        dist2.add(10);
        v2.setAdjacent(vertices2, dist2);

        List<Vertex> vertices3 = new ArrayList<>();
        vertices3.add(v1);
        vertices3.add(v6);
        List<Integer> dist3 = new ArrayList<>();
        dist3.add(4);
        dist3.add(5);
        v3.setAdjacent(vertices3, dist3);

        List<Vertex> vertices4 = new ArrayList<>();
        vertices4.add(v3);
        vertices4.add(v5);
        vertices4.add(v6);
        vertices4.add(v7);
        List<Integer> dist4 = new ArrayList<>();
        dist4.add(2);
        dist4.add(2);
        dist4.add(8);
        dist4.add(4);
        v4.setAdjacent(vertices4, dist4);


        List<Vertex> vertices5 = new ArrayList<>();
        vertices5.add(v7);
        List<Integer> dist5 = new ArrayList<>();
        dist5.add(6);
        v5.setAdjacent(vertices5, dist5);

        List<Vertex> vertices7 = new ArrayList<>();
        vertices7.add(v6);
        List<Integer> dist7 = new ArrayList<>();
        dist7.add(1);
        v7.setAdjacent(vertices7, dist7);

        List<Vertex> vvv = new ArrayList<>();
        vvv.add(v1);
        vvv.add(v2);
        vvv.add(v3);
        vvv.add(v4);
        vvv.add(v5);
        vvv.add(v6);
        vvv.add(v7);

        Graph graph = new Graph(vvv);
        Map<Vertex, Integer> dijkstra = new Dijkstra().dijkstra(v1);
        for (Vertex v : dijkstra.keySet()) {
            System.out.println("v" + v.getVal() + " : " + dijkstra.get(v));
        }
        System.out.println();
        int IMAX = Integer.MAX_VALUE;
        int[][] M = {{0, 2, IMAX, 1, IMAX, IMAX, IMAX},
                {IMAX, 0, IMAX, 3, 10, IMAX, IMAX},
                {4, IMAX, 0, IMAX, IMAX, 5, IMAX},
                {IMAX, IMAX, 2, 0, 2, 8, 4},
                {IMAX, IMAX, IMAX, IMAX, 0, IMAX, 6},
                {IMAX, IMAX, IMAX, IMAX, IMAX, 0, IMAX},
                {IMAX, IMAX, IMAX, IMAX, IMAX, 1, 0}};
        System.out.println(new Dijkstra().dijkstra(M, 0));
    }
*/
}
