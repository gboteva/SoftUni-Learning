package jediGalaxy;

public class Jedi extends PlayerAbs {

    private long collectedStars;

    public Jedi() {
        super();
        this.collectedStars = 0;
    }

    public void changeRow(int row) {
        setRow(row - 1);
    }

    public void changeCol(int col) {
        setCol(col + 1);
    }

    public long getCollectedStars() {
        return collectedStars;
    }

    private void setPosition(int[] dimensions) {
        setRow(dimensions[0]);
        setCol(dimensions[1]);
    }

    @Override
    public void move(int[][] galaxy, int[] dimensions) {

        setPosition(dimensions);

        while (getRow() >= 0 && getCol() < galaxy[1].length) {

            if (isInBounds(getRow(), getCol(), galaxy)) {

                this.collectedStars += galaxy[getRow()][getCol()];
            }

            changeRow(getRow());
            changeCol(getCol());

        }
    }

}
