import bagel.Image;
import bagel.util.Point;

/**
 *  This is the Actor class which represents non-moving object in the ShadowLife.
 */
public abstract class Actor{
    protected Point location;
    private final Image image;
    /**
     * The name/type of the actor.
     */
    public final String type;

    /** This is the constructor for the Actor class. When called it initialise instance of Actor with given image file
     * path, type and location.
     * @param filename The file name of the actors' image.
     * @param type The specific name/type of the actor.
     * @param location The location of actor on the x-y plane.
     */
    public Actor(String filename, String type,Point location) {
        image = new Image(filename);
        this.type = type;
        this.location = location;
    }

    /**
     * This method renders image of the Actor at location (x,y) when called.
     */
    public void render() { image.drawFromTopLeft(location.x, location.y);}

    /** This method is overridden by the child of Actor class and used for instance of actors with instance of creature.
     * @param creature An instance of Creature that the Actor is interacting with.
     */
    public abstract void interact(Creature creature);

    /** This is getter method for location of the actor.
     * @return Point The location of the actor.
     */
    public Point getLocation() {
        return location;
    }
}
