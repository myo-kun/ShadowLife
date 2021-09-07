import bagel.util.Point;

/**
 * This is the SignDown class which inherit from the Actor class. Represents down sign in ShadowLife.
 */
public class SignDown extends Actor{
    /**
     * The name of this class.
     */
    public static final String TYPE = "SignDown";

    /** This is constructor for SignDown class which represent down sign.
     * @param location This represent the location of the sign.
     */
    public SignDown(Point location) {
        super("res/images/down.png", TYPE, location);
    }

    /** This method is called when an instance of the Creature is on this Sign. It will follow the algorithm given for
     * gatherer or thief.
     * @param creature Creature that is interacting with this Sign.
     */
    @Override
    public void interact(Creature creature) {
        creature.setDirection(Direction.DOWN);
    }
}
