import entities.AutomatedSystem;
import org.junit.*;

import java.io.*;
import java.util.ArrayList;

public class CommandRunnerTest {
    private static ArrayList<String> commands = new ArrayList();
    private static ArrayList<String> outputs = new ArrayList();
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Before
    public void preSetup() {
        System.out.println("Running tests for all the commands; the output is bypassed for assertion");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(
                    getClass().getClassLoader()
                            .getResource("inputFile.txt").getFile()));

            String line = reader.readLine();
            while (line != null) {
                commands.add(line);
                line = reader.readLine();
            }
            reader = new BufferedReader(new FileReader(
                    getClass().getClassLoader()
                            .getResource("output.txt").getFile()));

            line = reader.readLine();
            while (line != null) {
                outputs.add(line);
                line = reader.readLine();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void creationTest(){

        // Parking Lot Creation test
        CommandRunner.runCommand(commands.get(0));
        Assert.assertEquals(outputs.get(0)+"\n", outContent.toString());
        Assert.assertEquals(6, AutomatedSystem.getParkingLot().getParkingSpotsCount());
        //Assert.assertEquals("Jai Sai Ram ", outContent.toString());


        for(int i=1; i<=15; i++){
            outContent.reset();
            CommandRunner.runCommand(commands.get(i));
            Assert.assertEquals(outputs.get(i)+"\n", outContent.toString());
        }

        outContent.reset();
        CommandRunner.runCommand(commands.get(16));
        String output_status = "Slot No.\tRegistration No\tColour\n";

        for(int i=17; i < outputs.size(); i++){
            output_status = output_status + (outputs.get(i).replaceAll("[ ]", "\t")+"\n");
        }
        Assert.assertEquals(output_status, outContent.toString());

    }

}
