import bagel.util.Point;

/**
 * This is the Hoard class which inherit from the Actor class. Represents Hoard in ShadowLife.
 */
public class Hoard extends Actor implements Printable{
    /**
     * The name of this class.
     */
    public static final String TYPE = "Hoard";
    private int fruitCount = 0;

    /** This is the constructor for Hoard class.
     * @param location This represent location of the hoard.
     */
    public Hoard(Point location) {
        super("res/images/hoard.png", TYPE, location);
    }

    /** This method is called when an instance of the Creature is on this Hoard. It will follow the algorithm given for
     * gatherer or thief.
     * @param creature Creature that is interacting with this Hoard.
     */
    @Override
    public void interact(Creature creature) {
        switch (creature.type) {
            case Gatherer.TYPE:
                if (creature.isCarrying()) {
                    creature.setCarrying(false);
                    fruitCount++;
                }
                creature.setDirection(Direction.rotate(creature.getDirection(), 180));
                break;

            case Thief.TYPE:
                if (creature.isConsuming()) {
                    creature.setConsuming(false);
                    if (!creature.isCarrying()) {
                        if (fruitCount > 0) {
                            creature.setCarrying(true);
                            fruitCount--;
                        } else { creature.setDirection(Direction.rotate(creature.getDirection(), 90)); }
                    }
                } else if (creature.isCarrying()){
                    creature.setCarrying(false);
                    fruitCount++;
                    creature.setDirection(Direction.rotate(creature.getDirection(), 90));
                }
                break;
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
    public void printOut() {
        System.out.println(fruitCount);
    }
}
