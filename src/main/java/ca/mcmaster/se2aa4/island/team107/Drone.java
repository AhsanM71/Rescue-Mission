package ca.mcmaster.se2aa4.island.team107;

public class Drone {
    private Integer batteryLevel;
    private Direction heading;
    private Coordinate location;

    public Drone(Integer batteryLevel, Direction heading) {
        this.batteryLevel = batteryLevel;
        this.heading = heading;
        this.location.setX(0);
        this.location.setY(0);
    }

    public Integer getBatteryLevel() {
        return this.batteryLevel;
    }

    public Direction getHeading() {
        return this.heading;
    }

    public int getX() {
        return this.location.getX();
    }

    public int getY() {
        return this.location.getY();
    }

    public void flyForward() {
        switch (this.heading) {
            case Direction.North:
                location.setY(getY() + 1);
                break;
            case Direction.East:
                location.setX(getX() + 1); 
                break;
            case Direction.South:
                location.setY(getY() - 1);
                break;
            case Direction.West:
                location.setX(getX() - 1);
                break;
        }
    }

    private void turnRight() {
        switch (this.heading) {
            case Direction.North: 
                location.setY(getY() + 1);
                location.setX(getX() + 1); 
                break;
            case Direction.East:
                location.setY(getY() - 1);
                location.setX(getX() + 1); 
                break;
            case Direction.South:
                location.setY(getY() - 1);
                location.setX(getX() - 1); 
                break;
            case Direction.West:
                location.setY(getY() + 1);
                location.setX(getX() - 1); 
                break;
        }
        heading = heading.getRight();
    }

    private void turnLeft() {
        switch (this.heading) {
            case Direction.North: 
                location.setY(getY() + 1);
                location.setX(getX() - 1); 
                break;
            case Direction.East:
                location.setY(getY() + 1);
                location.setX(getX() + 1); 
                break;
            case Direction.South:
                location.setY(getY() - 1);
                location.setX(getX() + 1); 
                break;
            case Direction.West:
                location.setY(getY() - 1);
                location.setX(getX() - 1); 
                break;
        }
        heading = heading.getLeft();
    }
}
