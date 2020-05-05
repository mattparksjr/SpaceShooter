package codes.matthewp.space;

import codes.matthewp.space.game.MenuState;
import codes.matthewp.space.game.ObjectRegistry;
import codes.matthewp.space.game.object.Bullet;
import codes.matthewp.space.game.object.EnemyBasic;
import codes.matthewp.space.game.object.Player;
import codes.matthewp.space.game.wave.WaveMaster;
import codes.matthewp.space.gui.GuiMaster;
import codes.matthewp.space.input.KeyListen;
import codes.matthewp.space.input.MouseListen;
import codes.matthewp.space.thread.UpdateThread;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    private Player player;
    private Image img;
    private ObjectRegistry objectRegistry;
    private Graphics graphics;
    private static Main instance;
    private List<Bullet> bulletList;
    private List<Bullet> deadBullets;
    private List<EnemyBasic> currentEnemies;
    public UpdateThread updateThread;
    private static WaveMaster waveMaster;
    private static GuiMaster guiMaster;
    public boolean runUpdate;
    public static MenuState menuState = MenuState.MAIN_MENU;
    public long badLastShotAt;
    public boolean didShoot;
    public boolean showGameObjColliders;

    public Main() {
        instance = this;
        setTitle("Space Shooter!");
        setResizable(false);
        setSize(300, 300);
        setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - 150, ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(Color.BLACK);
        player = new Player(getImg("spaceShooterPlayer.png"), 150, 280, 16, 16, 20);
        objectRegistry = new ObjectRegistry();
        registerObjects();
        bulletList = new ArrayList<>();
        deadBullets = new ArrayList<>();
        addKeyListener(new KeyListen());
        addMouseListener(new MouseListen());
        currentEnemies = new ArrayList<>();
        runUpdate = true;

        while (true) {
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        img = createImage(getWidth(), getHeight());
        graphics = img.getGraphics();

        paintComponents(graphics);
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void paintComponents(Graphics g) {
        if (runUpdate && menuState != MenuState.MAIN_MENU) {
            player.draw(g);
            for (Bullet bull : bulletList) {
                bull.draw(g);
            }
            for (EnemyBasic enemyBasic : currentEnemies) {
                enemyBasic.draw(g);
            }
        }
        // Temp null check, once main game is move to gui system, will be removed
        if (guiMaster.getCurrentGUI() != null) {
            guiMaster.getCurrentGUI().draw(graphics);
        }
    }

    public void update() {
        for (Bullet bull : deadBullets) {
            bulletList.remove(bull);
        }

        if (!getWaveMaster().waveSpawned) {
            getWaveMaster().startWaves();
        }

        player.update();

        for (Bullet bull : bulletList) {
            bull.update();
        }

        for (EnemyBasic enemyBasic : currentEnemies) {
            enemyBasic.update();
        }

        if (waveMaster.didMove) {
            Main.getInstance().getWaveMaster().setLastMoveAt(System.currentTimeMillis());
            waveMaster.didMove = false;
        }

        if (didShoot) {
            badLastShotAt = System.currentTimeMillis();
            didShoot = false;
        }
    }

    private void registerObjects() {
        objectRegistry.register("eBasic", new EnemyBasic(0, 0));
    }

    public static Image getImg(String path) {
        return Toolkit.getDefaultToolkit().getImage(path);
    }

    public static void main(String[] args) {
        waveMaster = new WaveMaster();
        guiMaster = new GuiMaster();
        new Main();
    }

    public void endGame() {
        runUpdate = false;
        menuState = MenuState.REPLAY_LOSS;
    }

    public ObjectRegistry getObjectRegistry() {
        return objectRegistry;
    }

    public static Main getInstance() {
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public List<Bullet> getDeadBullets() {
        return deadBullets;
    }

    public WaveMaster getWaveMaster() {
        return waveMaster;
    }

    public List<EnemyBasic> getCurrentEnemies() {
        return currentEnemies;
    }

    public GuiMaster getGuiMaster() {
        return guiMaster;
    }

    public UpdateThread getUpdateThread() {
        return updateThread;
    }
}
