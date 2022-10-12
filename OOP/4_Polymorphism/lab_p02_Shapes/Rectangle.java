package lab_p02_Shapes;

public class Rectangle extends Shape{
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
        calculateArea();
        calculatePerimeter();
    }

    @Override
    public void calculatePerimeter(){
        this.setPerimeter(2*this.width + 2*this.height);
    }

    @Override
    public void calculateArea(){
        this.setArea(this.width * this.height);
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }
}
