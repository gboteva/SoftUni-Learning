package Lab.p02_PointInRectangle;

public class Rectangle {
    private Point bottomLeftPoint;
    private Point topRightPoint;

    public Rectangle(Point bottomLeftPoint, Point topRightPoint) {
        this.bottomLeftPoint = bottomLeftPoint;
        this.topRightPoint = topRightPoint;
    }

    public boolean contains(Point point) {
        return point.getX() >= bottomLeftPoint.getX()
                && point.getX() <= topRightPoint.getX()
                && point.getY() >= bottomLeftPoint.getY()
                && point.getY() <= topRightPoint.getY();
    }
}
