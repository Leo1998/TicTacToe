package model;

import java.util.Random;

public class KITree {

    private static final Random random = new Random();

    public class Move {

        private int x, y;
        private Player player;

        public Move(int x, int y, Player player) {
            this.x = x;
            this.y = y;
            this.player = player;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Player getPlayer() {
            return player;
        }
    }

    private List<KITree> possibilities;
    private List<Move> moves;
    private int count = 0;
    private int totalCount = 0;

    private Field field;

    public KITree(Field field) {
        this.possibilities = new List<KITree>();
        this.moves = new List<Move>();
        this.field = field;
    }

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

    public Move findBestMove(Player currentPlayer) {
        List<Move> bestMoves = new List<Move>();
        int bestMovesCount = 0;

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
                    if (moveCount == -1 || winMoves < moveCount) {
                        bestMoves = new List<Move>();
                        bestMovesCount = 0;
                    }

                    moveCount = winMoves;

                    bestMoves.append(move);
                    bestMovesCount++;
                }
            }

            this.possibilities.next();
            this.moves.next();
        }

        System.out.println(bestMovesCount);

        int r = random.nextInt(bestMovesCount);
        int i = 0;
        bestMoves.toFirst();
        while(bestMoves.hasAccess()) {
            if (r == i) {
                return bestMoves.getContent();
            }

            bestMoves.next();
            i++;
        }
        return null;
    }

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

    public Field getField() {
        return field;
    }

    public int getCount() {
        return count;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
