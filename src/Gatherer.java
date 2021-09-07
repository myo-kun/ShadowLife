import bagel.util.Point;

/**
 * This is the Gatherer class which inherit from the Creature class. Represents gatherer in ShadowLife.
 */
public class Gatherer extends Creature {
    /**
     * The name of this class.
     */
    public static final String TYPE = "Gatherer";

    /** This is constructor for the Gatherer class.
     * @param location The location of gatherer.
     */
    public Gatherer(Point location) {
        super("res/images/gatherer.png", TYPE, location, Direction.LEFT);
    }

}
