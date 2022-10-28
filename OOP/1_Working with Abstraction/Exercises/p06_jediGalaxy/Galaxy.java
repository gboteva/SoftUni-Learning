package jediGalaxy;

public class Galaxy {
    private int[][] field;
    private Jedi jedi;
    private EvilPower evilPower;

    public Galaxy(int[] dimensions) {
        this.field = new int[dimensions[0]][dimensions[1]];
        fillGalaxy(dimensions[0], dimensions[1], field);
        jedi = new Jedi();
        evilPower = new EvilPower();
    }

    private void fillGalaxy(int rows, int cols, int[][] galaxy) {
        int value = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                galaxy[row][col] = value++;
            }
        }
    }

    public int[][] getField() {
        return field;
    }

    public Jedi getJedi() {
        return jedi;
    }

    public EvilPower getEvilPower() {
        return evilPower;
    }
}
