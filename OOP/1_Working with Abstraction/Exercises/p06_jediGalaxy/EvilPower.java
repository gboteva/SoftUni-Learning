package jediGalaxy;

public class EvilPower extends PlayerAbs {

    public EvilPower() {
        super();
    }

    private void setPosition(int[] dimensions){
        setRow(dimensions[0]);
        setCol(dimensions[1]);
    }

    public void changeRow(int row) {
        setRow(row-1);
    }

    public void changeCol(int col) {
        setCol(col-1);
    }


    @Override
    public void move(int[][] galaxy, int[] dimensions) {

        setPosition(dimensions);
        while (getRow() >= 0 && getCol() >= 0) {

            if (isInBounds(getRow(), getCol(), galaxy)) {
                galaxy[getRow()][getCol()] = 0;
            }

            changeRow(getRow());
            changeCol(getCol());
        }

    }
}
