package ca.mcmaster.se2aa4.island.team107.phase;

import ca.mcmaster.se2aa4.island.team107.drone.Controller;
import ca.mcmaster.se2aa4.island.team107.position.Direction;

import org.json.JSONObject;

public class UTurn implements Phase {

    private Controller controller;

    private Direction direction;

    private boolean hasTurned;
    private boolean turnLeft;

    private Integer turnCount;
    private Integer FLY;
    private Integer TURN_OPPOSITE;

    public UTurn(Controller controller, Direction dir, boolean turnLeft, boolean outward) {

        this.controller = controller;
        this.direction = dir;

        this.hasTurned = false;
        this.turnLeft = turnLeft;
        this.turnCount = 0;

        if (outward) {
            FLY = 3;
            TURN_OPPOSITE = 0;
        } 
        else {
            FLY = 1;
            TURN_OPPOSITE = 4;
        }
    }

    public String getDroneCommand() {
        String command;

        if (turnCount.equals(TURN_OPPOSITE)) {
            command = turnCommand(!turnLeft);
        }
        else if (turnCount.equals(FLY)) {
            command = controller.fly();
        }
        else {
            command = turnCommand(turnLeft);
        }

        if (turnCount >= 4) {
            hasTurned = true;
        }

        turnCount++;

        return command;
    }

    public void processInfo(JSONObject info) { 
        // Phase does need to process any information from JSON response. Drone U-turn logic is fixed.
    }


    public Phase getNextPhase() {
        return new ScanLine(controller, direction, !turnLeft);
    }

    public boolean isFinished() {
        return hasTurned;
    }

    public boolean isLastPhase() {
        return false;
    }

    private String turnCommand(boolean dirLeft) {
        direction = (dirLeft) ? direction.getLeft() : direction.getRight();
        return controller.heading(direction);
    }
}
