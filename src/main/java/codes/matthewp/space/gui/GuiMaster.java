package codes.matthewp.space.gui;

import codes.matthewp.space.Main;
import codes.matthewp.space.game.MenuState;
import codes.matthewp.space.gui.mainmenu.MainMenu;
import codes.matthewp.space.gui.replay.ReplayGUI;
import codes.matthewp.space.gui.replay.ReplayReason;

import java.util.HashMap;

public class GuiMaster {

    private IGUI currentGUI;
    private HashMap<MenuState, IGUI> stateToGUI;

    public GuiMaster() {
        stateToGUI = new HashMap<>();
        register(new MainMenu());
        register(new ReplayGUI(ReplayReason.LOSS, MenuState.REPLAY_LOSS));
        register(new ReplayGUI(ReplayReason.WIN, MenuState.REPLAY_WIN));
        currentGUI = stateToGUI.get(Main.menuState);
    }

    public IGUI getCurrentGUI() {
        currentGUI = stateToGUI.get(Main.menuState);
        return currentGUI;
    }

    private void register(IGUI gui) {
        stateToGUI.put(gui.getMenuState(), gui);
    }
}
