package project5;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a hiker object which record the supplies that the hiker has.
 */
public class Hiker {
    private List<String> supplies = new ArrayList<String>();

    /**
     * An empty constructor
     */
    public Hiker(){}

    /**
     * a constructor class that builds a hiker with inputting supplies.
     * @param supplies a list of string
     */
    public Hiker(List<String> supplies){
        addSupplies(supplies);
    }

    /**
     * Adding a list of supplies to the variable.
     * @param supply a list of string.
     */
    public void addSupplies(List<String> supply){
        if(supply==null) return;
        for (String s:supply
             ) {supplies.add(s);

        }
    }

    /**
     * This class will consume the supplies that hiker has as going down the mountain, giving a list of obstacles.
     * @param obstacles
     * @return True if there are enough supplies, False if there aren't
     * @throws IllegalArgumentException Can't be run with a obstacle that can't be recognized.
     */
    public boolean consumeSupplies(List<String> obstacles) throws IllegalArgumentException{
        if(obstacles==null) return true;
        for (String obstacle:obstacles
             ) {
            if(obstacle.equals("fallen tree")){
                if(supplies.contains("axe")){
                    supplies.remove(("axe"));
                }
                else return false;
            }
            else if(obstacle.equals("river")){
                if(supplies.contains("raft")){
                    supplies.remove("raft");
                }
                else return false;
            }
            else throw new IllegalArgumentException();
        }
        return true;
    }

    /**
     * @return The supply list.
     */
    public List<String> getSupplies() {
        return supplies;
    }

    /**
     * reduce one food supply in the list
     * @return True if Hiker has food, false if hiker doesn't have food.
     */
    public boolean consumeFood(){
        if(supplies.contains("food")){
            supplies.remove("food");
            return true;
        }
        return false;
    }
}
