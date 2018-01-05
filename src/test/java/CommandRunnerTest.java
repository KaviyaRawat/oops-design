import entities.AutomatedSystem;
import org.junit.*;

import java.io.*;
import java.util.ArrayList;

public class CommandRunnerTest {
    private static ArrayList<String> commands = new ArrayList();
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Before
    public void preSetup() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(
                    getClass().getClassLoader()
                    .getResource("inputFile.txt").getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String line = reader.readLine();
            while(line!=null) {
                commands.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @BeforeClass
    public static void runBeforeClass(){
        CommandRunner.runCommand(commands.get(0));
        outContent.reset();
    }

    @Test
    public void creationTest(){
        CommandRunner.runCommand(commands.get(0));
        Assert.assertEquals(6, AutomatedSystem.getParkingLot().getParkingSpotsCount());
        Assert.assertEquals("Jai Sai Ram ", outContent.toString());
    }

    @Test
    public void parkCommandTest(){
        CommandRunner.runCommand(commands.get(0));
        for(int i=1; i<=6; i++){
            outContent.reset();
            CommandRunner.runCommand(commands.get(i));
            Assert.assertEquals("Allocated spot number : " + i + "\n", outContent.toString());
        }
    }

    @Test
    public void leaveCommandTest(){
        CommandRunner.runCommand(commands.get(0));
        outContent.reset();
        CommandRunner.runCommand(commands.get(7));
        Assert.assertEquals("Slot number 4 is free" + "\n", outContent.toString());

    }

    @Test
    public void runSlotNumberForRegNumCommandTest(){

    }

}
