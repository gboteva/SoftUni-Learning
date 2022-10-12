package lab_p02_Shapes;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(2.0, 5.0);
        System.out.println(rectangle.getPerimeter());
        System.out.println(rectangle.getArea());

        Circle circle = new Circle(5.0);
        System.out.println(circle.getArea());
        System.out.println(circle.getPerimeter());

    }
}
