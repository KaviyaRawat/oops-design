
import entities.ParkingLot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DriverCls {

    public static void main(String[] args) {
        // If input file is provided as command line argument.
        if(args.length>0){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                String line = reader.readLine();
                while(line!=null){
                    CommandRunner.runCommand(line);
                    line = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please Check." + e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // If the commands are entered in the command prompt.
        else{
            Scanner in = new Scanner(System.in);
            while(true){
                String line = in.nextLine();
                CommandRunner.runCommand(line);
            }
        }
    }



}
