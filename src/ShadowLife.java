import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This is the ShadowLife class which is contains main method for ShadowLife game.
 */
public class ShadowLife extends AbstractGame {
    /**
     * The tiles size in the ShadowLife game.
     */
    public static final int TILE_SIZE = 64;
    private long lastTick = 0;
    private int tickCount = 0;
    private static long tickTime = 0;
    private static long maxTicks = 0;
    private static String worldFile;

    private ArrayList<Actor> actors;
    private ArrayList<Creature> gatherers;
    private ArrayList<Creature> thieves;

    private final Image background = new Image("res/images/background.png");

    /** The main method that creates ShadowLife game.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        args = argsFromFile();
        // Validating command line arguments
        try {
            assert args != null;
            if (args.length != 3) {
                System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
                System.exit(-1);
            }
            tickTime = Integer.parseInt(args[0]);
            maxTicks = Integer.parseInt(args[1]);
            worldFile = args[2];
        } catch (NumberFormatException e) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // create new ShadowLife and run
        new ShadowLife().run();
    }

    /**
     * The main game method.
     */
    public ShadowLife() {
        loadActors();
    }

    private static String[] argsFromFile() {
        try {
            return Files.readString(Path.of("args.txt"), Charset.defaultCharset()).split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void loadActors() {
        int lineNum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(worldFile))) {
            lineNum++;
            String line;
            actors = new ArrayList<>();
            gatherers = new ArrayList<>();
            thieves = new ArrayList<>();
            while (((line = br.readLine())) != null) {
                String[] cells = line.split(",");
                // Check that there are 2 commas.
                if (cells.length != 3) {
                    throw new IllegalArgumentException();
                }
                String type = cells[0];
                int x = Integer.parseInt(cells[1]);
                int y = Integer.parseInt(cells[2]);
                // Check that the coordinate is greater than 0.
                if (x < 0 || y < 0) {
                    throw new IllegalArgumentException();
                }
                switch (type) {
                    case Tree.TYPE:
                        actors.add(new Tree(new Point(x, y)));
                        break;
                    case GoldenTree.TYPE:
                        actors.add(new GoldenTree(new Point(x, y)));
                        break;
                    case Stockpile.TYPE:
                        actors.add(new Stockpile(new Point(x, y)));
                        break;
                    case Hoard.TYPE:
                        actors.add(new Hoard(new Point(x, y)));
                        break;
                    case Pad.TYPE:
                        actors.add(new Pad(new Point(x, y)));
                        break;
                    case Fence.TYPE:
                        actors.add(new Fence(new Point(x, y)));
                        break;
                    case SignLeft.TYPE:
                        actors.add(new SignLeft(new Point(x, y)));
                        break;
                    case SignRight.TYPE:
                        actors.add(new SignRight(new Point(x, y)));
                        break;
                    case SignUp.TYPE:
                        actors.add(new SignUp(new Point(x, y)));
                        break;
                    case SignDown.TYPE:
                        actors.add(new SignDown(new Point(x, y)));
                        break;
                    case Pool.TYPE:
                        actors.add(new Pool(new Point(x, y)));
                        break;
                    case Gatherer.TYPE:
                        gatherers.add(new Gatherer(new Point(x, y)));
                        break;
                    case Thief.TYPE:
                        thieves.add(new Thief(new Point(x, y)));
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            System.out.println("error: file " + worldFile + " not found");
            System.exit(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("error: in file " + worldFile + " at line " + lineNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void update(Input input) {
        checkGameState();
        // If enough time has passed, run the next tick.
        if (System.currentTimeMillis() - lastTick > tickTime) {
            tickCount++;
            lastTick = System.currentTimeMillis();
            updateTick();
            updateGatherers();
            updateThieves();
        }
        // Draw all elements
        background.drawFromTopLeft(0, 0);
        drawAll();
    }

    private void updateTick() {
        // update location for all instance of Creature.
        for (Creature gatherer : gatherers) {
            if (gatherer != null && gatherer.isActive()) {
                gatherer.tick();
            }
        }
        for (Creature thief : thieves) {
            if (thief != null && thief.isActive()) {
                thief.tick();
            }
        }
    }

    private void updateGatherers() {
        // Iterate over ArrayList of instance of Gatherer and interact with Actor if possible.
        for (Creature gatherer : new ArrayList<>(gatherers)) {
            if ((gatherer == null) || !gatherer.isActive()) {
                continue;
            }
            for (Actor actor : actors) {
                if (gatherer.isOn(actor)) {
                    if (actor.type.equals(Pool.TYPE)) {
                        ((Pool)actor).interact(gatherer, gatherers);
                    }
                    else {
                        actor.interact(gatherer);
                    }
                }
            }
        }
    }

    private void updateThieves() {
        // Iterate over ArrayList of instance of Thief and interact with Actor if possible.
        for (Creature thief : new ArrayList<>(thieves)) {
            if ((thief == null) || !thief.isActive()) {
                continue;
            }
            for (Actor actor : actors) {
                if (thief.isOn(actor)) {
                    if (actor.type.equals(Pool.TYPE)) {
                        ((Pool)actor).interact(thief, thieves);
                    }
                    else {
                        actor.interact(thief);
                    }
                }
            }
            for (Creature gatherer : gatherers) {
                if (gatherer != null && thief.isOn(gatherer)) {
                    thief.setDirection(Direction.rotate(thief.getDirection(), 270));
                }
            }
        }
    }

    private void drawAll() {
        // Render all object in ShadowLife.
        for (Actor actor : actors) {
            if (actor != null) {
                if (actor instanceof Printable) {
                    ((Printable) actor).print();
                }
                actor.render();
            }
        }
        for (Creature gatherer : gatherers) {
            if (gatherer != null) {
                gatherer.render();
            }
        }
        for (Creature thief : thieves) {
            if (thief != null) {
                thief.render();
            }
        }
    }

    private void checkGameState() {
        boolean gatherersState = false;
        boolean thievesState = false;
        // Time out if the tick exceed max ticks.
        if (tickCount >= maxTicks) {
            System.out.println("Timed out");
            System.exit(-1);
        }
        // Check if at least one instance of Creature is active.
        for (Creature gatherer : gatherers) {
            if (gatherer != null && gatherer.isActive()) {
                gatherersState = true;
                break;
            }
        }
        for (Creature thief : thieves) {
            if (thief != null && thief.isActive()) {
                thievesState = true;
                break;
            }
        }
        // End ShadowLife when simulation halts.
        if (!(gatherersState || thievesState)) {
            System.out.println(tickCount + " ticks");
            for (Actor actor : actors) {
                if (actor instanceof Printable) {
                    ((Printable) actor).printOut();
                }
            }
            System.exit(0);
        }
    }
}
