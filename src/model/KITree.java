package model;

import java.util.Random;

public class KITree {

    private static final Random random = new Random();

    /**
     * the inner class move
     */
    public class Move {

        /**
         * x and y of the move
         */
        private int x, y;
        /**
         * the player that does the move
         */
        private Player player;

        /**
         * Constructor
         *
         * @param x
         * @param y
         * @param player
         */
        public Move(int x, int y, Player player) {
            this.x = x;
            this.y = y;
            this.player = player;
        }

        /**
         * Getter
         *
         * @return
         */
        public int getX() {
            return x;
        }

        /**
         * Getter
         *
         * @return
         */
        public int getY() {
            return y;
        }

        /**
         * Getter
         *
         * @return
         */
        public Player getPlayer() {
            return player;
        }
    }

    /**
     * all following possibilities
     */
    private List<KITree> possibilities;
    /**
     * the moves regarding to the possibilities (same order)
     */
    private List<Move> moves;
    /**
     * the count of possibilities
     */
    private int count = 0;
    /**
     * the totalCount of possibilities
     */
    private int totalCount = 0;

    /**
     * the field
     */
    private Field field;

    /**
     * Constructor
     *
     * @param field
     */
    public KITree(Field field) {
        this.possibilities = new List<KITree>();
        this.moves = new List<Move>();
        this.field = field;
    }

    /**
     * calculates the next possibilities
     *
     * @param currentPlayer
     * @param otherPlayer
     * @return
     */
    public int calculate(Player currentPlayer, Player otherPlayer) {
        if(!field.isFull() && field.getWinner() == null) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (field.getCell(x, y) == null) {
                        Field copy = field.copy();
                        copy.setCell(x, y, currentPlayer);

                        KITree nextTree = new KITree(copy);
                        this.possibilities.append(nextTree);
                        this.moves.append(new Move(x, y, currentPlayer));
                        this.count++;

                        totalCount += nextTree.calculate(otherPlayer, currentPlayer);
                    }
                }
            }
        } else {
            totalCount = 1;
        }

        return totalCount;
    }

    /**
     * finds the best move the currentPlayer could do, or null if there is no best move
     *
     * @param currentPlayer
     * @return
     */
    public Move findBestMove(Player currentPlayer) {
        Move bestMove = null;

        int moveCount = -1;

        this.possibilities.toFirst();
        this.moves.toFirst();
        while(this.possibilities.hasAccess() && this.moves.hasAccess()) {
            KITree tree = this.possibilities.getContent();
            Move move = this.moves.getContent();

            boolean opponentWin = false;
            Player opponent = currentPlayer == field.getP1() ? field.getP2() : field.getP1();
            tree.possibilities.toFirst();
            opponentWhileLoop : while(tree.possibilities.hasAccess()) {
                KITree childTree = tree.possibilities.getContent();

                if (childTree.getField().getWinner() == opponent) {
                    opponentWin = true;
                    break opponentWhileLoop;
                }

                tree.possibilities.next();
            }

            int winMoves = tree.deternamineWinMoves(currentPlayer);
            if (moveCount == -1 || winMoves <= moveCount) {
                if (!opponentWin || winMoves == 1) {
                    moveCount = winMoves;
                    bestMove = move;
                }
            }

            this.possibilities.next();
            this.moves.next();
        }

        return bestMove;
    }

    /**
     * deternamines the count of moves in which the currentPlayer would win
     *
     * @param currentPlayer
     * @return
     */
    private int deternamineWinMoves(Player currentPlayer) {
        return deternamineWinMoves(currentPlayer, 1);
    }

    private int deternamineWinMoves(Player currentPlayer, int moves) {
        if (this.getField().getWinner() == currentPlayer) {
            return moves;
        } else {
            int bestMoves = -1;

            possibilities.toFirst();
            while(possibilities.hasAccess()) {
                KITree childTree = possibilities.getContent();

                int winMoves = childTree.deternamineWinMoves(currentPlayer, moves + 1);
                if (bestMoves == -1 || winMoves < bestMoves) {
                    bestMoves = winMoves;
                }

                possibilities.next();
            }

            return bestMoves;
        }
    }

    /**
     * a random move
     *
     * @return
     */
    public Move randomMove() {
        moves.toFirst();

        int i = 0;
        int r = (int) (Math.random() * count);

        while (i < r) {
            moves.next();
            i++;
        }

        return moves.getContent();
    }

    /**
     * Getter
     *
     * @return
     */
    public Field getField() {
        return field;
    }

    /**
     * Getter
     *
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * Getter
     *
     * @return
     */
    public int getTotalCount() {
        return totalCount;
    }
}
