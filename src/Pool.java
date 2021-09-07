import bagel.util.Point;
import java.util.ArrayList;

public class Pool extends Actor {
    /**
     * The name of this class.
     */
    public static final String TYPE = "Pool";

    /** This is constructor for Pool class.
     * @param location This represent location of the pool.
     */
    public Pool(Point location) {
        super("res/images/pool.png", TYPE, location);
    }

    /** Does nothing as Pool has special implementation.
     * @param creature An instance of Creature that the Actor is interacting with.
     */
    @Override
    public void interact(Creature creature) {}
    /** This method is called when an instance of the Creature is on this Pool. It will follow the algorithm given for
     * gatherer or thief.
     * @param creature An instance of Creature that the Actor is interacting with.
     * @param creatures The list of Creature that contains single class type that inherit from Creature.
     */
    public void interact(Creature creature, ArrayList<Creature> creatures) {
        switch (creature.type) {
            case Gatherer.TYPE:
                Gatherer newGatherer1 = new Gatherer(creature.getLocation());
                Gatherer newGatherer2 = new Gatherer(creature.getLocation());
                newGatherer1.setDirection(Direction.rotate(creature.getDirection(), 270));
                newGatherer1.update();
                newGatherer2.setDirection(Direction.rotate(creature.getDirection(), 90));
                newGatherer2.update();
                creatures.add(newGatherer1);
                creatures.add(newGatherer2);
                creatures.remove(creature);
                break;
            case Thief.TYPE:
                Thief newThief1 = new Thief(creature.getLocation());
                Thief newThief2 = new Thief(creature.getLocation());
                newThief1.setDirection(Direction.rotate(creature.getDirection(), 270));
                newThief1.update();
                newThief2.setDirection(Direction.rotate(creature.getDirection(), 90));
                newThief2.update();
                creatures.add(newThief1);
                creatures.add(newThief2);
                creatures.remove(creature);
                break;
        }
    }
}