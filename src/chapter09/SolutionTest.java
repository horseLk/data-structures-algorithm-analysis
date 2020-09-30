package chapter09;

import chapter09.example.example01.Graph;
import chapter09.example.example01.GraphPro;
import chapter09.example.example01.TopSort;
import chapter09.example.example01.Vertex;
import chapter09.example.example02.ShortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolutionTest {
    public static void main(String[] args) {
//        Vertex v1 = new Vertex(1);
//        Vertex v2 = new Vertex(2);
//        Vertex v3 = new Vertex(3);
//        Vertex v4 = new Vertex(4);
//        Vertex v5 = new Vertex(5);
//        Vertex v6 = new Vertex(6);
//        Vertex v7 = new Vertex(7);
//
//        v1.addToVertices(v2, v3, v4);
//        v2.addToVertices(v4, v5);
//        v3.addToVertices(v6);
//        v4.addToVertices(v3, v6, v7);
//        v5.addToVertices(v4, v7);
//        v7.addToVertices(v6);
//
//        List<Vertex> vertices = new ArrayList<>();
//        vertices.add(v1);
//        vertices.add(v2);
//        vertices.add(v3);
//        vertices.add(v4);
//        vertices.add(v5);
//        vertices.add(v6);
//        vertices.add(v7);

        // 拓扑排序测试
//        Graph graph = new Graph(vertices);
//        new TopSort().topSort(graph);

        // 拓扑排序加强版测试
//        GraphPro graphPro = new GraphPro(vertices);
//        new TopSort().topSortPro(graphPro);



        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);

        v1.addToVertices(v2, v4);
        v2.addToVertices(v4, v5);
        v3.addToVertices(v1, v6);
        v4.addToVertices(v3, v5, v6, v7);
        v5.addToVertices(v7);
        v7.addToVertices(v6);

        List<Vertex> vertices = new ArrayList<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        vertices.add(v7);

        // 无权图最短路径测试
        Graph graph = new Graph(vertices);
        Map<Vertex, Integer> res = new ShortestPath().unweight(graph, v3);
        for (Map.Entry<Vertex, Integer> entry : res.entrySet()) {
            System.out.println("v" + entry.getKey().getVal() + " : " + entry.getValue());
        }
    }
}