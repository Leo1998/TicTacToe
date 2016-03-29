package model;

public class Field {

    private Player[][] cells;
    private Player winner;
    private Player p1, p2;

    public Field(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
        this.winner = null;
        this.cells = new Player[3][3];
    }

    public Player getCell(int x, int y){
        return cells[x][y];
    }

    public boolean isOccupied(int x, int y) {
        return getCell(x, y) != null;
    }

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

    public void setCell(int x, int y, Player p){
        cells[x][y] = p;

        updateWinner();
    }

    public Player getWinner(){
        if (this.winner != null){
            return winner;
        }
        return null;
    }

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


    private Player checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            Player w = getWinner(cells[i][0], cells[i][1], cells[i][2]);
            if (w != null) {
                return w;
            }
        }
        return null;
    }

    private Player checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            Player w = getWinner(cells[0][i], cells[1][i], cells[2][i]);
            if (w != null) {
                return w;
            }
        }
        return null;
    }

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

    private Player getWinner(Player c1, Player c2, Player c3) {
        boolean match = ((c1 != null) && (c1 == c2) && (c2 == c3));

        return match ? c1 : null;
    }
}
