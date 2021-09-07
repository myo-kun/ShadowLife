import bagel.Font;

/**
 * This is the Printable interface that is used for objects that needs to print some detail.
 */
public interface Printable {
    Font font = new Font("res/VeraMono.ttf", 24);
    void print();
    void printOut();
}
