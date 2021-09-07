import bagel.util.Point;

/**
 * This is the Thief class which inherit from the Creature class. Represents thief in ShadowLife.
 */
public class Thief extends Creature {
    /**
     * The name of this class.
     */
    public static final String TYPE = "Thief";

    /** This is constructor for the Thief class.
     * @param location The location of thief.
     */
    public Thief(Point location) {
        super("res/images/thief.png", TYPE, location, Direction.UP);
    }
}
