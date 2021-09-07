import bagel.util.Point;

/**
 * This is the SignLeft class which inherit from the Actor class. Represents left sign in ShadowLife.
 */
public class SignLeft extends Actor {
    /**
     * The name of this class.
     */
    public static final String TYPE = "SignLeft";

    /** This is constructor for SignLeft class which represent left sign.
     * @param location This represent the location of the sign.
     */
    public SignLeft(Point location) {
        super("res/images/left.png", TYPE, location);
    }

    /** This method is called when an instance of the Creature is on this Sign. It will follow the algorithm given for
     * gatherer or thief.
     * @param creature Creature that is interacting with this Sign.
     */
    @Override
    public void interact(Creature creature) {
        creature.setDirection(Direction.LEFT);
    }
}
