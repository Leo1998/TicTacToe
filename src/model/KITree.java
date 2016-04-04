package model;

public class KITree {

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
        possibilities.toFirst();
        moves.toFirst();
        while(possibilities.hasAccess() && moves.hasAccess()) {
            KITree tree = possibilities.getContent();
            Move move = moves.getContent();

            if (tree.getField().getWinner() == currentPlayer) {
                return move;
            }

            possibilities.next();
            moves.next();
        }



        return null;
    }

    private Move findNextWinMove(Player currentPlayer) {
        KITree tree = this;

        while(tree != null) {
            tree.possibilities.toFirst();
            while(tree.possibilities.hasAccess()) {
                KITree nextTree = tree.possibilities.getContent();

                tree.possibilities.next();
            }
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
