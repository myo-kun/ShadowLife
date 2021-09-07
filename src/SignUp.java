import bagel.util.Point;

/**
 * This is the SignUp class which inherit from the Actor class. Represents up sign in ShadowLife.
 */
public class SignUp extends Actor {
    /**
     * The name of this class
     */
    public static final String TYPE = "SignUp";

    /** This is constructor for SignUp class which represent up sign.
     * @param location This represent the location of the sign.
     */
    public SignUp(Point location) {
        super("res/images/up.png", TYPE, location);
    }

    /** This method is called when an instance of the Creature is on this Sign. It will follow the algorithm given for
     * gatherer or thief.
     * @param creature Creature that is interacting with this Sign.
     */
    @Override
    public void interact(Creature creature) {
        creature.setDirection(Direction.UP);
    }
}
