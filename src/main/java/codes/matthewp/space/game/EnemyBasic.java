package codes.matthewp.space.game;

import codes.matthewp.space.Main;

public class EnemyBasic extends GameObject {

    public EnemyBasic(int x, int y) {
        super(Main.getImg("spaceShooterEnemyBasic.png"), x, y, 16, 16, 10);
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() >= (Main.getInstance().getWaveMaster().getLastMoveAt()) + 500) {
            y += 2;
            Main.getInstance().getWaveMaster().didMove = true;
        }

        if (System.currentTimeMillis() >= (Main.getInstance().badLastShotAt + 1000)) {
            Main.getInstance().didShoot = true;
            Main.getInstance().getBulletList().add(new Bullet(x + 7, y + 10, this));
        }

        super.update();
        if (y > 280) {
            Main.getInstance().endGame();
        }
    }
}
