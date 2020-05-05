package codes.matthewp.space.game;

import codes.matthewp.space.game.object.GameObject;

import java.util.HashMap;

public class ObjectRegistry {

    private HashMap<String, GameObject> idToObject;

    public ObjectRegistry() {
        idToObject = new HashMap<>();
    }

    public void register(String id, GameObject object) {
        idToObject.put(id, object);
    }

    public GameObject getObject(String id) {
        // TODO, error checking
        return idToObject.get(id);
    }
}
