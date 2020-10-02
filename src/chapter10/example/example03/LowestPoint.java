package chapter10.example.example03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 最近点问题
 *      思路：
 *          1.将所有的点按照二维平面的一个轴排序
 *          2.将排序后的点从中间分割
 *          3.算出左边边长的最小值和右边边长的最小值，并去两者中小的那个值m
 *          4。中间分割处 ±m 的距离内找最小边，如果小于 m，则更新m
 *          5.求左右两侧最小边的过程递归的使用 2-4步。
 */
public class LowestPoint {
    static class Point implements Comparable {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object o) {
            if (!(o instanceof Point)) {
                throw new IllegalArgumentException();
            }
            Point p = (Point) o;
            return this.x - p.x;
        }
    }

    public List<Point> lowestPoint(List<Point> points) {
        Collections.sort(points);
        return lowestPoint(points, 0, points.size());
    }

    private List<Point> lowestPoint(List<Point> points, int start, int end) {
        if (end - start <= 3) {
            return findMin(points);
        }
        int mid = (end - start) / 2;
        List<Point> res = new ArrayList<>();
        List<Point> left = lowestPoint(points, start, mid + 1);
        List<Point> right = lowestPoint(points, mid + 1, end);
        double minLen = Double.MAX_VALUE;
        if (minLen(left) < minLen(right)) {
            minLen = minLen(left);
            res.addAll(left);
        } else {
            minLen = minLen(right);
            res.addAll(right);
        }
        int midX = points.get(mid).x;
        List<Point> midPoints = new ArrayList<>();
        for (int i = start; i < end; i++) {
            int curX = points.get(i).x;
            if (curX >= midX - minLen && curX <= minLen + midX) {
                midPoints.add(points.get(i));
            }
        }
        List<Point> minMid = findMin(midPoints);
        if (minMid.size() == 2 && minLen(minMid) < minLen) {
            res.clear();
            res.addAll(minMid);
        }
        return res;
    }

    private double minLen(List<Point> points) {
        if (points.size() != 2) throw new IllegalArgumentException();
        return len(points.get(0), points.get(1));
    }

    private double len(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    private List<Point> findMin(List<Point> points) {
        Point p1 = null;
        Point p2 = null;
        double minL = Double.MAX_VALUE;
        List<Point> res = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                if (len(points.get(i), points.get(j)) < minL) {
                    p1 = points.get(i);
                    p2 = points.get(j);
                }
            }
        }
        if (p1 != null || p2 != null) {
            res.add(p1);
            res.add(p2);
        }
        return res;
    }
}
