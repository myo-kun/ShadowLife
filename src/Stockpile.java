import bagel.util.Point;

/**
 * This is the Stockpile class which inherit from the Actor class. Represents stockpile in ShadowLife.
 */
public class Stockpile extends Actor implements Printable{
    /**
     * The name of this class.
     */
    public static final String TYPE = "Stockpile";
    private int fruitCount = 0;

    /** This is constructor for Stockpile class
     * @param location Point This represent location of the stockpile.
     */
    public Stockpile(Point location) {
        super("res/images/cherries.png", TYPE, location);
    }

    /** This method is called when an instance of the Creature is on this Stockpile. It will follow the algorithm given
     * for gatherer or thief.
     * @param creature Creature that is interacting with this Stockpile.
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
                if (!creature.isCarrying()) {
                    if (fruitCount > 0) {
                        creature.setCarrying(true);
                        creature.setConsuming(false);
                        fruitCount--;
                        creature.setDirection(Direction.rotate(creature.getDirection(), 90));
                    }
                } else { creature.setDirection(Direction.rotate(creature.getDirection(), 90)); }
                break;
        }
    }

    /**
     * This method renders current number of fruitCount to ShadowLife game at location of this Stockpile.
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
