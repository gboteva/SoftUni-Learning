package Lab.p02_PointInRectangle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        int x1 = Integer.parseInt(input[0]);
        int y1 = Integer.parseInt(input[1]);
        Point bottomLeftPointOfRectangle = new Point(x1, y1);

        int x2 = Integer.parseInt(input[2]);
        int y2 = Integer.parseInt(input[3]);
        Point topRightPointOfRectangle = new Point(x2, y2);

        Rectangle rectangle = new Rectangle(bottomLeftPointOfRectangle, topRightPointOfRectangle);

        int countInputLines = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < countInputLines; i++) {
            String [] coordinates = scanner.nextLine().split("\\s+");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            Point point = new Point(x, y);
            System.out.println(rectangle.contains(point));
        }
    }
}
