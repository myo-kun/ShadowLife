import bagel.util.Point;

public class Pad extends Actor{
    /**
     * The name of this class.
     */
    public static final String TYPE = "Pad";

    /** This is constructor for the Pad class.
     * @param location This represent location of the pad.
     */
    public Pad(Point location) { super("res/images/pad.png", TYPE, location); }

    /** This method is called when an instance of the Creature is on this Pad. It will follow the algorithm given for
     * gatherer or thief.
     * @param creature Creature that is interacting with this Pad.
     */
    @Override
    public void interact(Creature creature) {
        if (creature.type.equals(Thief.TYPE)) {
            creature.setConsuming(true);
        }
    }
}
