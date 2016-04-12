package model;

public class Field {

    /**
     * the cells of the field (3 x 3)
     */
    private Player[][] cells;
    /**
     * the winner, or null if there is none
     */
    private Player winner;
    /**
     * the players p1 and p2
     */
    private Player p1, p2;

    /**
     * the Constructor
     *
     * @param p1
     * @param p2
     */
    public Field(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
        this.winner = null;
        this.cells = new Player[3][3];
    }

    /**
     * this copies the conplete field
     *
     * @return
     */
    public Field copy() {
        Field field = new Field(p1, p2);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                field.setCell(x, y, this.getCell(x, y));
            }
        }

        return field;
    }

    /**
     * gets the cell at (x, y)
     *
     * @param x
     * @param y
     * @return
     */
    public Player getCell(int x, int y){
        return cells[x][y];
    }

    /**
     * whether the cell at (x, y) is occupied
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isOccupied(int x, int y) {
        return getCell(x, y) != null;
    }

    /**
     * whether the field is full
     *
     * @return
     */
    public boolean isFull() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (getCell(x, y) == null) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * sets the cell at (x, y)
     *
     * @param x
     * @param y
     * @param p
     */
    public void setCell(int x, int y, Player p){
        cells[x][y] = p;

        updateWinner();
    }

    /**
     * Gets the winner or null there is none
     *
     * @return
     */
    public Player getWinner(){
        if (this.winner != null){
            return winner;
        }
        return null;
    }

    /**
     * checks if there is a winner
     */
    private void updateWinner(){
        Player rowWinner = checkRowsForWin();
        Player columWinner = checkColumnsForWin();
        Player diagonalWinner = checkDiagonalsForWin();

        if (rowWinner != null) {
            this.winner = rowWinner;
        } else if (columWinner != null) {
            this.winner = columWinner;
        } else if (diagonalWinner != null) {
            this.winner = diagonalWinner;
        }
    }

    /**
     * checks all rows
     *
     * @return
     */
    private Player checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            Player w = getWinner(cells[i][0], cells[i][1], cells[i][2]);
            if (w != null) {
                return w;
            }
        }
        return null;
    }

    /**
     * checks all columns
     *
     * @return
     */
    private Player checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            Player w = getWinner(cells[0][i], cells[1][i], cells[2][i]);
            if (w != null) {
                return w;
            }
        }
        return null;
    }

    /**
     * checks all diagonals
     *
     * @return
     */
    private Player checkDiagonalsForWin() {
        Player w1 = getWinner(cells[0][0], cells[1][1], cells[2][2]);
        Player w2 = getWinner(cells[0][2], cells[1][1], cells[2][0]);

        if (w1 != null) {
            return w1;
        }
        if (w2 != null) {
            return w2;
        }

        return null;
    }

    /**
     * checks if these 3 cells return a winner
     *
     * @param c1
     * @param c2
     * @param c3
     * @return
     */
    private Player getWinner(Player c1, Player c2, Player c3) {
        boolean match = ((c1 != null) && (c1 == c2) && (c2 == c3));

        return match ? c1 : null;
    }

    /**
     * Getter
     *
     * @return
     */
    public Player getP1() {
        return p1;
    }

    /**
     * Getter
     *
     * @return
     */
    public Player getP2() {
        return p2;
    }
}
