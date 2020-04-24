public class Treasure extends GamePiece {

    public Treasure(int initPoints) {
        super(initPoints);
    }

    @Override
    public String getType() {
        return "Treasure";
    }

    @Override
    public String show() {
        return "$";
    }
}
