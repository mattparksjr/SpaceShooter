package codes.matthewp.space.game;

import codes.matthewp.space.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WaveMaster {

    // Totally not a good way to store waves but whatever!
    private List<List<Coord>> waves;

    public long lastMoveAt = 0L;
    public boolean didMove = false;
    private int wave = 0;
    public boolean waveSpawned = false;

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
        for (Coord loc : getWaves().get(wave)) {
            Main.getInstance().getCurrentEnemies().add(new EnemyBasic(loc.getX(), loc.getY()));
        }
        lastMoveAt = System.currentTimeMillis();
        Main.getInstance().badLastShotAt = System.currentTimeMillis();
        waveSpawned = true;
    }

    public void waveComplete() {
        wave++;
        if (waves.size() > wave) {
            for (Coord loc : getWaves().get(wave)) {
                Main.getInstance().getCurrentEnemies().add(new EnemyBasic(loc.getX(), loc.getY()));
            }
            lastMoveAt = System.currentTimeMillis();
        } else {
            Main.getInstance().runUpdate = false;
            Main.menuState = MenuState.REPLAY_WIN;
        }
    }

    private void readWaves() {
        File f = new File("waves.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while (line != null) {
                if (!line.contains("#") && !line.equals("")) {
                    String[] enemies = line.split("/");
                    List<Coord> locations = new ArrayList<>();
                    for (String s : enemies) {
                        String[] parts = s.split(",");
                        locations.add(new Coord(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
                    }
                    getWaves().add(locations);
                }
                line = reader.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<List<Coord>> getWaves() {
        return waves;
    }

    public void setLastMoveAt(long lastMoveAt) {
        this.lastMoveAt = lastMoveAt;
    }

    public long getLastMoveAt() {
        return lastMoveAt;
    }

    public int getWave() {
        return wave;
    }
}
