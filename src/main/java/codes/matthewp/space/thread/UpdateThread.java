package codes.matthewp.space.thread;

import codes.matthewp.space.Main;

public class UpdateThread extends Thread {

    @Override
    public void run() {
        while (Main.getInstance().runUpdate) {
            Main.getInstance().update();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
