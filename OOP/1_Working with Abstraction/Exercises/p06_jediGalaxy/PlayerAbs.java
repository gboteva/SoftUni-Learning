package jediGalaxy;

public abstract class PlayerAbs implements Player {
    private int row;
    private int col;

    public PlayerAbs() {
        this.row = 0;
        this.col = 0;
    }

    protected boolean isInBounds(int row, int col, int[][] galaxy) {
        return row >= 0 && row < galaxy.length && col >= 0 && col < galaxy[row].length;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
