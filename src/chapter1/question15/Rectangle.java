package chapter1.question15;

public class Rectangle {
    private double width;
    private double height;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLong() {
        return 2 * (width + height);
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
    }

    public double getArea() {
        return width * height;
    }

    public void show() {
        String s1 = "width = " + width + ", height = " + height;
        String s2 = "area = " + getArea() + ", long = " + getLong();
        System.out.println(s1);
        System.out.println(s2);
    }
}
