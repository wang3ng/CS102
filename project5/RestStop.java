package project5;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a data field that stores the information of a stop on the mountain.
 * Information includes supplies, obstacles, and label of the stop.
 */
public class RestStop implements Comparable<RestStop>{
    private String Label;
    private List supplies = new ArrayList<String>(),
            obstacles = new ArrayList<String>();

    /**
     * A constructor Class that use the input of a string of list of supplies and obstacles and put them into the lists
     * It would check if the obstacles and supplies are legal.
     * @param label a string that contains a label.
     * @param suppliesAndObstacles a string containing a list of supplies and obstacles.
     * @throws NullPointerException when input label is null
     */
    public RestStop(String label, String suppliesAndObstacles) throws NullPointerException{
        // a label can't be null
        if(label == null) throw new NullPointerException();
        Label = label;
        // Skip the process if suppliesAndObstacles is null
        if(suppliesAndObstacles == null) return;
        String[] stuff = suppliesAndObstacles.split(" ");
        //tag is false when handling supply, true when handling obstacles
        boolean tag = false;

        // Inputting the obstacles and supplies.
        for (int i=0; i<stuff.length; i++) {
            if(tag){
                if(stuff[i].equals("river")) {
                    obstacles.add(stuff[i]);
                }
                if(stuff[i].equals("fallen")&&stuff[i+1].equals("tree")){
                    obstacles.add("fallen tree");
                }
            }
            else {
                if(stuff[i].equals("food")||stuff[i].equals("raft")||stuff[i].equals("axe")){
                    supplies.add(stuff[i]);
                }
                else if(stuff[i].equals("river")){
                    obstacles.add(stuff[i]);
                    tag = true;
                }
                else if(stuff[i].equals("fallen")&&stuff[i+1].equals("tree")){
                    obstacles.add("fallen tree");
                    tag = true;
                }
            }
        }
    }

    /**
     * @return label of the stop
     */
    public String getLabel() {
        return Label;
    }

    /**
     * @return a list containing the supplies of teh stop
     */
    public List getSupplies() {
        return supplies;
    }

    /**
     * @return a list containing the obstacles of the stop.
     */
    public List getObstacles() {
        return obstacles;
    }

    /**
     * Make the compare method depends on alphabetical order.
     * @param o another rest stop.
     * @return >1 if bigger, 0 if equal, <1 if smaller
     */
    @Override
    public int compareTo(RestStop o) {
        return Label.compareTo(o.Label);
    }

    @Override
    public String toString(){
        return Label + " " + supplies + " " + obstacles;
    }
}
