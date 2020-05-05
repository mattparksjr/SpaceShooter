package codes.matthewp.space.game.wave;

import codes.matthewp.space.Main;
import codes.matthewp.space.game.MenuState;
import codes.matthewp.space.game.object.EnemyBasic;
import codes.matthewp.space.game.object.GameObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WaveMaster {

    public long lastMoveAt = 0L;
    public boolean didMove = false;
    public boolean waveSpawned = false;
    // Totally not a good way to store waves but whatever!
    private List<Wave> waves;
    private int wave = 0;

    public WaveMaster() {
        waves = new ArrayList<>();
        readWaves();
    }

    public void reset() {
        waveSpawned = false;
        didMove = false;
        wave = 0;
    }

    public void startWaves() {
        Wave waveToSpawn = getWaves().get(wave);
        for (GameObject object : waveToSpawn.toGameObject()) {
            if (object instanceof EnemyBasic) {
                Main.getInstance().getCurrentEnemies().add((EnemyBasic) object);
            }
        }
        lastMoveAt = System.currentTimeMillis();
        Main.getInstance().badLastShotAt = System.currentTimeMillis();
        waveSpawned = true;
    }

    public void waveComplete() {
        wave++;
        if (waves.size() > wave) {
            Wave waveToSpawn = getWaves().get(wave);
            for (GameObject object : waveToSpawn.toGameObject()) {
                if (object instanceof EnemyBasic) {
                    Main.getInstance().getCurrentEnemies().add((EnemyBasic) object);
                }
            }
            lastMoveAt = System.currentTimeMillis();
        } else {
            Main.getInstance().runUpdate = false;
            Main.menuState = MenuState.REPLAY_WIN;
        }
    }

    private void readWaves() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("waves.json")) {
            Object obj = jsonParser.parse(reader);
            JSONObject waveList = (JSONObject) obj;
            for (Object o : waveList.keySet()) {
                String id = (String) o;
                JSONArray array = (JSONArray) waveList.get(id);
                List<WaveObject> waveObjects = new ArrayList<>();
                for (Object arrayOBJ : array.toArray()) {
                    JSONObject singleObj = (JSONObject) arrayOBJ;
                    waveObjects.add(parseGameObject(singleObj));
                }
                Wave wave = new Wave(Integer.parseInt(id), waveObjects);
                System.out.println(wave.toString());
                waves.add(new Wave(Integer.parseInt(id), waveObjects));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private WaveObject parseGameObject(JSONObject object) {
        return new WaveObject((String) object.get("type"), Math.toIntExact((long) object.get("x")), Math.toIntExact((long) object.get("y")));
    }

    public List<Wave> getWaves() {
        return waves;
    }

    public long getLastMoveAt() {
        return lastMoveAt;
    }

    public void setLastMoveAt(long lastMoveAt) {
        this.lastMoveAt = lastMoveAt;
    }

    public int getWave() {
        return wave;
    }
}
