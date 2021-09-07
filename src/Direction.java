/**
 * This is the Direction class which is used to represent the direction of Creature.
 */
public class Direction {
    /**
     * UP, LEFT, RIGHT and DOWN represents the direction.
     */
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;


    /** This method is used to rotate the direction.
     * @param direction int The current direction.
     * @param angle int The angle of rotation, only multiple of 90 degrees is allowed.
     * @return int This returns new direction after rotating.
     */
    public static int rotate(int direction, int angle) {
        if (angle % 90 != 0) {
            throw new IllegalArgumentException("Only multiple of 90 degrees is allowed");
        }
        return (direction + (angle / 90)) % 4;
    }
}
