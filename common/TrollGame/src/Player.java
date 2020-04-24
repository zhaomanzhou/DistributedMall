public class Player extends GamePiece{

    public Player(int initPoints) {
        super(initPoints);
    }

    public Player() {
        super();
    }

    @Override
    public String getType() {
        return "Player";
    }

    @Override
    public String show() {
        return "P";
    }


}
