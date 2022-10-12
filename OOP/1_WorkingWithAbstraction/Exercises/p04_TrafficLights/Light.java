package Exercises.p04_TrafficLights;

public class Light {
    private Colors color;

    public Light(Colors color) {
        this.color = color;
    }

    public Colors getColor() {
        return this.color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public void changeColor(){
        if (this.color == Colors.RED){
            this.color = Colors.GREEN;
        }else if (this.color==Colors.GREEN){
            this.color = Colors.YELLOW;
        }else if (this.color==Colors.YELLOW){
            this.color = Colors.RED;
        }
    }
}
