import bagel.util.Point;

/**
 * This is the SignRight class which inherit from the Actor class. Represents right sign in ShadowLife.
 */
public class SignRight extends Actor {
    /**
     * The name of this class.
     */
    public static final String TYPE = "SignRight";

    /** This is constructor for SignRight class which represent right sign.
     * @param location This represent the location of the sign.
     */
    public SignRight(Point location) {
        super("res/images/right.png", TYPE, location);
    }

    /** This method is called when an instance of the Creature is on this Sign. It will follow the algorithm given for
     * gatherer or thief.
     * @param creature Creature that is interacting with this Sign.
     */
    @Override
    public void interact(Creature creature) {
        creature.setDirection(Direction.RIGHT);
    }
}
