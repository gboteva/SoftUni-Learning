package generics.tuple;

public class ThreeTuple<F, S, T> {
    private  F item1;
    private S item2;
    private T item3;

    public ThreeTuple(F first, S second, T third) {
        this.item1 = first;
        this.item2 = second;
        this.item3 = third;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s -> %s", item1.toString(), item2.toString(), item3.toString());
    }
}
