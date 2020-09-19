package chapter01;

import chapter01.question14.OrderedCollection;
import chapter01.question15.Rectangle;

public class Solution {
    public static void main(String[] args) {
        OrderedCollection<Rectangle> areaOrdered = new OrderedCollection((r1, r2) -> {
            Rectangle rr1 = (Rectangle) r1;
            Rectangle rr2 = (Rectangle) r2;
            return Double.compare(rr1.getArea(), rr2.getArea());
        });

        areaOrdered.insert(new Rectangle(3.0, 5.0));
        areaOrdered.insert(new Rectangle(4.0, 4.0));
        areaOrdered.insert(new Rectangle(2.5, 5.5));
        areaOrdered.insert(new Rectangle(1.0, 7.0));

        Rectangle max = areaOrdered.findMax();
        Rectangle min = areaOrdered.findMin();
        System.out.println("max area===");
        max.show();
        System.out.println("min area===");
        min.show();

        OrderedCollection<Rectangle> longOrdered = new OrderedCollection((r1, r2) -> {
            Rectangle rr1 = (Rectangle) r1;
            Rectangle rr2 = (Rectangle) r2;
            return Double.compare(rr1.getLong(), rr2.getLong());
        });

        longOrdered.insert(new Rectangle(3.0, 5.0));
        longOrdered.insert(new Rectangle(4.0, 4.0));
        longOrdered.insert(new Rectangle(2.5, 5.5));
        longOrdered.insert(new Rectangle(1.0, 7.0));

        Rectangle max1 = longOrdered.findMax();
        System.out.println("max long===");
        max1.show();
        Rectangle min1 = longOrdered.findMin();
        System.out.println("min long===");
        min1.show();
    }
}
