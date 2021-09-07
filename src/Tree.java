import bagel.util.Point;

/**
 * This is the Tree class which inherit from the Actor class. Represents tree in ShadowLife.
 */
public class Tree extends Actor implements Printable{
    /**
     * The name of this class.
     */
    public static final String TYPE = "Tree";
    private int fruitCount = 3;

    /** This is constructor for the Tree class.
     * @param location This represent location of the tree.
     */
    public Tree(Point location) {
        super("res/images/tree.png", TYPE, location);
    }

    /** This method is called when an instance of the Creature is on this Tree. It will follow the algorithm given for
     * gatherer or thief.
     * @param creature Creature that is interacting with this Tree.
     */
    @Override
    public void interact(Creature creature) {
        if (fruitCount > 0 && !creature.isCarrying()) {
            fruitCount--;
            creature.setCarrying(true);
            // rotate creature if it is gatherer
            if (creature.type.equals(Gatherer.TYPE)) {
                creature.setDirection(Direction.rotate(creature.getDirection(), 180));
            }
        }
    }

    /**
     * This method renders current number of fruitCount to ShadowLife game at location of this Hoard.
     */
    @Override
    public void print() {
        font.drawString(String.valueOf(fruitCount), location.x, location.y);
    }

    /**
     * This method will print out number of fruitCount when the game ends successfully.
     */
    @Override
    public void printOut() {}
}
