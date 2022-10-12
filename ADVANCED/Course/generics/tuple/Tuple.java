package generics.tuple;

public class Tuple <F, S>{
    private  F item1;
    private S item2;

    public Tuple(F first, S second) {
        this.item1 = first;
        this.item2 = second;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", item1.toString(), item2.toString());
    }
}
