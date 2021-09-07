import bagel.util.Point;

/**
 * This is the Fence class which inherit from the Actor class. Represents fence in ShadowLife.
 */
public class Fence extends Actor{
    /**
     * The name of this class.
     */
    public static final String TYPE = "Fence";

    /** This is constructor for the Fence class.
     * @param location Point This represent location of the fence.
     */
    public Fence(Point location) {
        super("res/images/fence.png", TYPE, location);
    }

    /** This method is called when an instance of the Creature is on this Fence. It will follow the algorithm given for
     * gatherer or thief.
     * @param creature Creature that is interacting with this Fence.
     */
    @Override
    public void interact(Creature creature) {
        creature.setActive(false);
        // move creature to the previous tick
        creature.setDirection(Direction.rotate(creature.getDirection(), 180));
        creature.update();
    }
}
