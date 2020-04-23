package codes.matthewp.space.game;

import codes.matthewp.space.Main;

public class Bullet extends GameObject {

    private GameObject shooter;

    public Bullet(int x, int y, GameObject shooter) {
        super(Main.getImg("spaceShooterBullet.png"), x, y, 2, 2, 1);
        this.shooter = shooter;
    }

    @Override
    public void update() {
        if (shooter instanceof Player) {
            y--;
        } else {
            y++;
        }

        if(hitbox.intersects(Main.getInstance().getPlayer().hitbox) && shooter != Main.getInstance().getPlayer()) {
            Main.getInstance().getPlayer().health =  Main.getInstance().getPlayer().health - 10;
            Main.getInstance().getDeadBullets().add(this);
            Main.getInstance().getPlayer().checkHealth();
        }

        boolean remove = Main.getInstance().getCurrentEnemies().removeIf(enemyBasic -> hitbox.intersects(enemyBasic.hitbox) && enemyBasic != shooter);

        if(remove) {
            Main.getInstance().getDeadBullets().add(this);
        }

        if(Main.getInstance().getCurrentEnemies().isEmpty()) {
            Main.getInstance().getWaveMaster().waveComplete();
        }
        if (y < 0 || y > 300) {
            Main.getInstance().getDeadBullets().add(this);
        }
        super.update();
    }
}
