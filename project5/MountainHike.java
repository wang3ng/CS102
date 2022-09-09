package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is the class that is the program. This means it has the main method.
 * This class is responsible for parsing and validating the command line arguments,
 * reading and parsing the input file, producing any error messages,
 * handling any exceptions thrown by other classes, and producing output.
 *
 * @author William Wang
 */
public class MountainHike {
    /**
     * The main() method of this program.
     * @param args an array that include the address for the output.
     */
    public static void main(String[] args) {

        //verify that the command line argument exists
        if (args.length == 0 ) {
            System.err.println("Usage Error: the program expects file name as an argument.\n");
            System.exit(1);
        }

        //verify that command line argument contains a name of an existing file
        File treeFile = new File(args[0]);  //locate the file with the import
        if (!treeFile.exists()){
            System.err.println("Error: the file "+treeFile.getAbsolutePath()+" does not exist.\n");
            System.exit(1);
        }
        if (!treeFile.canRead()){
            System.err.println("Error: the file "+treeFile.getAbsolutePath()+
                    " cannot be opened for reading.\n");
            System.exit(1);
        }

        Scanner inRestStop = null;
        try {
            inRestStop = new Scanner (treeFile) ;
        } catch (FileNotFoundException e) {
            System.err.println("Error: the file "+treeFile.getAbsolutePath()+
                    " cannot be opened for reading.\n");
            System.exit(1);
        }

        //read the content of the tree
        BSTMountain mountain = new BSTMountain();
        String line = null;
        String label;
        String suppliesAndObstacles = null;
        RestStop current;
        while (inRestStop.hasNextLine()) {
            try {
                line = inRestStop.nextLine();
                if(line.contains(" ")){
                    label = line.substring(0,line.indexOf(" "));
                    suppliesAndObstacles = line.substring(line.indexOf(" "));
                }
                else {
                    label = line;
                    suppliesAndObstacles = null;
                }
            }
            catch (Exception ex) {
                // This exception should not be caught.
                System.err.println(ex);
                continue;
            }
            try {
                current = new RestStop(label,suppliesAndObstacles);
                mountain.add(current);
            }
            catch (NullPointerException ex ) {
                //ignore this exception and skip to the next line
                //happens when having null input.
            }
        }
        //Run the walking down tree method
        mountain.startTrip();
    }
}
