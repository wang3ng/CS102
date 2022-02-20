package project2;

/**
 * This class will find the feature.
 * This program is interactive.
 * The program is executed with the input file containing a list of features, which
 * will be stored in an class extend from arrayList.
 * In the interactive part, the user can enter in query in a format to get a return
 * of data fitting the condition given after search through the file.
 *
 * @author William Wang
 */

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class EveryPlaceHasAName {
    public static void main(String[] arg) {

        //Verify their is a command line input
        if (arg.length == 0) {
            System.err.println("Error：The program expect a valid filename as an argument\n");
            System.exit(1);
        }

        //Verify the file exists.
        File featureFile = new File(arg[0]);
        if (!featureFile.exists()){
            System.err.println("Error: the file "+featureFile.getAbsolutePath()+" doesn't exist.\n");
            System.exit(1);
        }
        if (!featureFile.canRead()){
            System.err.println("Error: the file "+featureFile.getAbsolutePath()+" cannot be read.\n");
            System.exit(1);
        }

        //Verify the file is readable.
        Scanner FeatureSc = null;
        try{
            FeatureSc = new Scanner(featureFile);
        }
        catch (FileNotFoundException e){
            System.err.println("Error: the file "+featureFile.getAbsolutePath()+" cannot be read.\n");
            System.exit(1);
        }

        FeatureList list = new FeatureList();
        Feature NewFeature;
        Location loc;
        String line = FeatureSc.nextLine();
        Scanner Phrase = null;
        String Part = null;
        String Id = null;
        String Name = null;
        String Class = null;
        String State = null;
        String County = null;
        int seg = 0;

        //Read through the files.
        while(FeatureSc.hasNextLine()){
            //Get the data from the line, if not in correct format, ignore the line.
            try {
                line = FeatureSc.nextLine();
                Phrase = new Scanner(line);
                Phrase.useDelimiter("\\|");
                Id = Phrase.next();
                Name = Phrase.next();
                Class = Phrase.next();
                State = Phrase.next();
                Phrase.next();
                County = Phrase.next();
                seg = 6;
            }
            catch (NoSuchElementException e){
                System.err.println(line);
                System.err.println(e);
                continue;
            }

            //Record and check if the 5 essential data fields exist.
            try{
                if(Id.isBlank()) throw new IllegalArgumentException("An id cannot be null or empty.");
                for (char c: Id.toCharArray()){
                    if(!Character.isDigit(c)) throw new IllegalArgumentException("This id for line " +
                            "can't be read.");
                }
                loc = new Location(State,County);
                NewFeature = new Feature(Name,Class,loc);
                list.add(NewFeature);
            }
            catch (IllegalArgumentException e){
                System.err.println(line);
                System.err.println(e);
                continue;
            }

            //Record the latitude, longitude, and elevation if that is provided.
            while(Phrase.hasNext()){
                try {
                    Part = Phrase.next();
                    seg+=1;
                    if(!Part.isBlank()){
                        if(seg==10){
                            loc.setLatitude(Double.parseDouble(Part));
                        }
                        if(seg==11){
                            loc.setLongitude(Double.parseDouble(Part));
                        }
                        if(seg==17){
                            loc.setElevation(Integer.parseInt(Part));
                        }
                    }
                }
                catch (IllegalArgumentException e){
                }
            }
        }

        //User Inputting

        Scanner userInput = new Scanner(System.in);
        String userValue = "";
        System.out.println("Please enter query in the below format\n"+"name KEYWORD\n" +
                "name KEYWORD class CLASS\n" +
                "name KEYWORD state STATE\n" +
                "name KEYWORD class CLASS state STATE\n" +
                "name KEYWORD state STATE class CLASS\n" +
                "To terminate the program, enter\n" +
                "\tquit");
        String format = "\nEnter your search query:\n";

        do{
            System.out.println(format);
            userValue = userInput.nextLine();

            if(!userValue.isEmpty() && !userValue.equalsIgnoreCase("quit")){
                FeatureList std = new FeatureList();

                Scanner phrase = new Scanner(userValue);
                phrase.useDelimiter(" ");

                if(!phrase.hasNext())continue;

                String part = phrase.next();
                String inp = "";

                //current represent which part of search is being working on.
                // 0 = name, 1 = class, 2 = state.
                int current;

                //Read the input
                try{
                    if(!part.equals("name") || !phrase.hasNext()) {
                        System.out.println("This is not a " +
                                "valid query. Try again.");
                    }
                    current = 0;
                    //Loop through the words.
                    while(phrase.hasNext() && (std !=null || current == 0)){
                        part = phrase.next();
                        if(part.equals("class")){
                            if(inp == "") throw new IllegalArgumentException("This is not a " +
                                    "valid query. Try again.");
                            if(current == 0){
                                current = 1;
                                std = list.getByName(inp);
                                inp = "";
                                continue;
                            }
                            if(current == 2){
                                current = 1;
                                std = std.getByState(inp);
                                inp = "";
                                continue;
                            }
                            throw new IllegalArgumentException("This is not a " +
                                    "valid query. Try again.");
                        }
                        if(part.equals("state")){
                            if(inp == "") throw new IllegalArgumentException("This is not a " +
                                    "valid query. Try again.");
                            if(current == 0){
                                current = 2;
                                std = list.getByName(inp);
                                inp = "";
                                continue;
                            }
                            if(current == 1){
                                current = 2;
                                std = std.getByClass(inp);
                                inp = "";
                                continue;
                            }
                            throw new IllegalArgumentException("This is not a " +
                                    "valid query. Try again.");
                        }
                        if(inp=="") inp = part;
                        else inp = inp+" "+part;
                    }
                    if(current == 0) std = list.getByName(inp);
                    if(std==null){
                        System.out.println("No matches found. Try again.");
                        continue;
                    }
                    if(current == 1) std = std.getByClass(inp);
                    if(current == 2) std = std.getByState(inp);
                    if(std==null){
                        System.out.println("No matches found. Try again.");
                        continue;
                    }

                    printFeatureList(std);
                }
                catch (IllegalArgumentException e){
                    System.err.println(e);
                    continue;
                }
            }
        }while (!userValue.equalsIgnoreCase("quit"));

        userInput.close();
    }

    /**
     * Print the given Feature list.
     * @param list
     */
    private static void printFeatureList(FeatureList list){
        for (Feature feature:list) {
            System.out.println(feature+"\n");
            System.out.println("-----");
        }
    }
}
