package lab_p02_Shapes;

public class Circle extends Shape{
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
        this.calculateArea();
        this.calculatePerimeter();
    }

    @Override
    public void calculateArea(){
        setArea(Math.PI * Math.pow(this.radius,2));
    }

    @Override
    public void calculatePerimeter(){
        setPerimeter(2*Math.PI * this.radius);
    }

    final Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
