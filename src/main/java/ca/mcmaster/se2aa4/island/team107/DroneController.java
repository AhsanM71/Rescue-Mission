package ca.mcmaster.se2aa4.island.team107;

import org.json.JSONObject;

public class DroneController {
    private Drone drone;

    public DroneController(Drone drone) {
        this.drone = drone;
    }

    public Integer getBatteryLevel() {
        return drone.getBatteryLevel();
    }

    public Direction getHeading() {
        return drone.getHeading();
    }

    public int getX() {
        return drone.getX();
    }

    public int getY() {
        return drone.getY();
    }

    public String fly() {
        JSONObject decision = new JSONObject();
        decision.put("action", "fly");
        drone.flyForward();
        drone.drainBattery(2);
        return decision.toString();
    }

    public String heading(Direction dir) {
        JSONObject decision = new JSONObject();
        JSONObject params = new JSONObject();
        
        decision.put("action", "heading");
        params.put("direction", dir.getSymbol());
        decision.put("parameters", params);

        if (dir.equals(getHeading().getRight())) {
            drone.turnRight();
        } else if (dir.equals(getHeading().getLeft())) {
            drone.turnLeft();
        }

        drone.drainBattery(4);
        return decision.toString();
    }

    public String echo(Direction dir) {
        JSONObject decision = new JSONObject();
        JSONObject params = new JSONObject();
        
        decision.put("action", "echo");
        params.put("direction", dir.getSymbol());
        decision.put("parameters", params);

        drone.drainBattery(1);
        return decision.toString();
    }

    public String scan() {
        JSONObject decision = new JSONObject();
        decision.put("action", "scan");
        drone.drainBattery(2);
        return decision.toString();
    }

    public String stop() {
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        drone.drainBattery(3);
        return decision.toString();
    }
}
