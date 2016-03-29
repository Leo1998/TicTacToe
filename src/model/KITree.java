package model;

public class KITree {

    private List<KITree> possibilities;

    private Field field;

    public KITree(Field field) {
        this.possibilities = new List<KITree>();
        this.field = field;
    }

    private void calcNextTrees() {
        if(!field.isFull()) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (field.getCell(x, y) == null) {



                    }
                }
            }
        }
    }

}
