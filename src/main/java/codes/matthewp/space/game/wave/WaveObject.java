package codes.matthewp.space.game.wave;

public class WaveObject {

    private String gameObjectID;
    private int x;
    private int y;

    public WaveObject(String gameObjectID, int x, int y) {
        this.gameObjectID = gameObjectID;
        this.x = x;
        this.y = y;
    }

    public String getGameObjectID() {
        return gameObjectID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
