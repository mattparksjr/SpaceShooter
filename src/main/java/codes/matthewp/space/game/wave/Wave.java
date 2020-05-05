package codes.matthewp.space.game.wave;

import codes.matthewp.space.Main;
import codes.matthewp.space.game.object.EnemyBasic;
import codes.matthewp.space.game.object.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Wave {

    private int waveNum;
    private List<WaveObject> waveObjects;

    public Wave(int waveNum, List<WaveObject> waveObjects) {
        this.waveNum = waveNum;
        this.waveObjects = waveObjects;
    }

    public int getWaveNum() {
        return waveNum;
    }

    public List<GameObject> toGameObject() {
        List<GameObject> collect = new ArrayList<>();
        for (WaveObject waveObject : waveObjects) {
            GameObject obj = Main.getInstance().getObjectRegistry().getObject(waveObject.getGameObjectID());
            // NOTE: WHILE THIS WORKS, IT DEFEATS THE POURPOSE OF THE OBJECT REGISTRY, EACH OBJECT NEEDS TO DEFINE ITS OWN CREATE METHOD
            if (obj instanceof EnemyBasic) {
                collect.add(new EnemyBasic(waveObject.getX(), waveObject.getY()));
            } else {
                GameObject clone = new GameObject(obj.img, waveObject.getX(), waveObject.getY(), obj.height, obj.width, obj.health);
                collect.add(clone);
            }
        }
        return collect;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (WaveObject object : waveObjects) {
            builder.append("object: " + object.getX() + ", " + object.getY());
        }
        return "Wave{" +
                "waveNum=" + waveNum +
                ", waveObjects=" + builder.toString();
    }
}
