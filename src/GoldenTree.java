import bagel.util.Point;

/**
 * This is the GoldenTree class which inherit from the Actor class. Represents Golden Tree in ShadowLife.
 */
public class GoldenTree extends Actor{
    /**
     * The name of this class.
     */
    public static final String TYPE = "GoldenTree";

    /** This is constructor for the GoldenTree class.
     * @param location This represent location of the golden tree.
     */
    public GoldenTree(Point location) {
        super("res/images/gold-tree.png", TYPE, location);
    }

    /** This method is called when an instance of the Creature is on this GoldenTree. It will follow the algorithm given
     * for gatherer or thief.
     * @param creature Creature that is interacting with this GoldenTree.
     */
    @Override
    public void interact(Creature creature) {
        if (!creature.isCarrying()) {
            creature.setCarrying(true);
            // rotate creature if it is gatherer
            if (creature.type.equals(Gatherer.TYPE)) {
                creature.setDirection(Direction.rotate(creature.getDirection(), 180));
            }
        }
    }
}
