//207795030 Daniel Michaelshvili

import biuoop.GUI;
import biuoop.Sleeper;
import game.AnimationRunner;
import game.Counter;
import game.GameFlow;
import game.levels.DirectHit;
import game.levels.FinalFour;
import game.levels.WideEasy;
import game.levels.ThingsGettingReal;
import game.levels.LevelInformation;
import java.util.ArrayList;

/**
 * @author Daniel Michaelshvili
 * The 'Ass6Game' class is the class that have the 'main' method that runs the game.
 */
public class Ass6Game {

    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * A method that runs the game.
     * @param args ('String[]' type) - the array of arguments to the main that needs to get numbers from '1' to '4' that
     * indicates of the levels that will be played in the game, according to the order of the insertion of the
     * arguments.
     */
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner(new GUI("Arkanoid", GUI_WIDTH, GUI_HEIGHT), new Sleeper());
        GameFlow gameFlow = new GameFlow(runner, runner.getGui().getKeyboardSensor(), new Counter());
        ArrayList<LevelInformation> levelInformations = new ArrayList<>();
        for (String arg : args) {
            switch (arg) {
                case "1" : levelInformations.add(new DirectHit());
                           break;
                case "2" : levelInformations.add(new WideEasy());
                           break;
                case "3" : levelInformations.add(new ThingsGettingReal());
                           break;
                case "4" : levelInformations.add(new FinalFour());
                           break;
                default :  break;
            }
        }
        if (levelInformations.isEmpty()) {
            levelInformations.add(new DirectHit());
            levelInformations.add(new WideEasy());
            levelInformations.add(new ThingsGettingReal());
            levelInformations.add(new FinalFour());
        }
        //if no appropriate arguments have been inserted in args the game will include each level from first to 4th.

        gameFlow.runLevels(levelInformations);
        runner.getGui().close();
    }
}
