package FundamentalsCourse.objectsAndClasses_6.MoreEx.RawData_02;

public class Cargo {
    private int weight;
    private String type;

    public Cargo(int weight, String type){
        this.weight = weight;
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
