import bagel.Image;
import bagel.util.Point;

/**
 * This is the Creature class which represents moving object in the ShadowLife.
 */
public abstract class Creature {
    private boolean active = true;
    private Point location;
    private int direction;
    private final Image image;
    /**
     * The name/type of the actor.
     */
    public final String type;
    private boolean carrying = false;
    private boolean consuming = false;


    /** This is the constructor for the Creature class. When called it initialise instance of Creature with given image
     * file path, type, location and direction.
     * @param filename The file name of the actors' image.
     * @param type The specific name/type of the actor.
     * @param location The location of actor on the x-y plane.
     * @param direction The direction that the actor is facing.
     */
    public Creature(String filename, String type, Point location, int direction) {
        image = new Image(filename);
        this.type = type;
        this.location = location;
        this.direction = direction;
    }

    /**
     * This is method is called when a tick elapsed in ShadowLife game. Calls update to update the location of the
     * creature.
     */
    public final void tick() {
        update();
    }

    /**
     * This method renders image of the Creature at location (x,y) when called.
     */
    public void render() { image.drawFromTopLeft(location.x, location.y); }

    /** This method moves creature from a point to another point.
     * @param deltaX Change in x.
     * @param deltaY Change in y.
     */
    public void move(int deltaX, int deltaY) {
        this.location = new Point(location.x + deltaX, location.y + deltaY);
    }

    /**
     * This method will update the location of Creature in the direction of up, down, left or right.
     */
    public void update() {
        // move in the direction that the gather is facing.
        switch (direction) {
            case Direction.UP:
                move(0, -ShadowLife.TILE_SIZE);
                break;
            case Direction.DOWN:
                move(0, ShadowLife.TILE_SIZE);
                break;
            case Direction.LEFT:
                move(-ShadowLife.TILE_SIZE, 0);
                break;
            case Direction.RIGHT:
                move(ShadowLife.TILE_SIZE, 0);
                break;
        }
    }

    /** This method will check if the Creature is on the given Actor.
     * @param actor An instance of Actor that we are checking against.
     * @return boolean If the Creature is on the given Actor then return true else false.
     */
    public boolean isOn(Actor actor) {
        Point actorLocation = actor.getLocation();
        return (actorLocation.x <= location.x && location.x <= actorLocation.x)
                && (actorLocation.y <= location.y && location.y <= actorLocation.y);
    }

    /** This method will check if the Creature is on the given Creature.
     * @param creature An instance of Creature that we are checking against.
     * @return boolean If the Creature is on the given Creature then return true else false.
     */
    public boolean isOn(Creature creature) {
        Point actorLocation = creature.getLocation();
        return (actorLocation.x <= location.x && location.x <= actorLocation.x)
                && (actorLocation.y <= location.y && location.y <= actorLocation.y);
    }

    /** This is the getter for carrying attribute which describe if this Creature is carrying.
     * @return boolean Returns true if the Creature is carrying else false.
     */
    public boolean isCarrying() {
        return carrying;
    }

    /** This is the setter for carrying attribute.
     * @param carrying Set carrying to true or false.
     */
    public void setCarrying(boolean carrying) {
        this.carrying = carrying;
    }

    /** This is the getter for consuming attribute which describe if this Creature is consuming.
     * @return boolean Returns true if the Creature is consuming else false.
     */
    public boolean isConsuming() {
        return consuming;
    }

    /** This is the setter for consuming attribute.
     * @param consuming Set consuming to true or false.
     */
    public void setConsuming(boolean consuming) {
        this.consuming = consuming;
    }

    /** This is the getter for active attribute which describe if this Creature is active.
     * @return boolean Returns true if the Creature is active else false.
     */
    public boolean isActive() {
        return active;
    }

    /** This is the setter for active attribute.
     * @param active Set active to true or false.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /** This is the getter for direction attribute which represent the direction of Creature.
     * @return int The direction which is implemented in Direction class.
     */
    public int getDirection() {
        return direction;
    }

    /** This is the setter for direction attribute.
     * @param direction The direction which the Creature will face.
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /** This is the getter for location of the Creature
     * @return Point The location of the Creature.
     */
    public Point getLocation() { return location; }
}
