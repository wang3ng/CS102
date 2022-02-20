package project2;

/**
 * This class is for storing Feature objects.
 * It inherits all of its properties from an ArrayList<Feature>.
 * It can also search through itself with name, state, or class
 * provided, returning a list of feature sorted that fits the
 * condition.
 *
 * @author William Wang
 */

import java.util.ArrayList;

public class FeatureList extends ArrayList<Feature> {

    /**
     * The constructor that builds an empty list.
     */
    public FeatureList(){}

    /**
     * Get a sorted list of feature sorting through the featureList's names.
     * @param keyword
     * @return a list containing the features
     * @throws IllegalArgumentException
     */
    public FeatureList getByName ( String keyword ) throws IllegalArgumentException{
        //Throw error if input with blank or null.
        if(keyword == null) throw new IllegalArgumentException("Can not get a feature with null import.");
        if(keyword.isBlank()) throw new IllegalArgumentException("Can not get a feature with empty import.");

        FeatureList std = new FeatureList();
        String formatKeyword = keyword.toLowerCase();
        Boolean hasValue = false;

        //Search
        for (Feature f:this){
            String featureName = f.getFeatureName().toLowerCase();
            if(featureName.contains(formatKeyword)) {
                std.add(f);
                hasValue = true;
            }
        }
        if(!hasValue) return null;
        else{
            std.sort(Feature::compareTo);
            return std;
        }
    }

    /**
     * Get a sorted list of feature sorting through the featureList's class names.
     * @param keyword
     * @return a list containing the features
     * @throws IllegalArgumentException
     */
    public FeatureList getByClass ( String keyword ) throws IllegalArgumentException{
        //Throw error if input with blank or null.
        if(keyword == null) throw new IllegalArgumentException("Can not get a feature with null import.");
        if(keyword.isBlank()) throw new IllegalArgumentException("Can not get a feature with empty import.");

        FeatureList std = new FeatureList();
        Boolean hasValue = false;
        String formatKeyword = keyword.toLowerCase();

        //Search
        for (Feature f:this){
            String featureClass = f.getFeatureClass().toLowerCase();
            if(featureClass.contains(formatKeyword)) {
                std.add(f);
                hasValue = true;
            }
        }
        if(!hasValue) return null;
        else{
            std.sort(Feature::compareTo);
            return std;
        }
    }

    /**
     * Get a sorted list of feature sorting through the featureList's states.
     * @param state
     * @return a list containing the features
     * @throws IllegalArgumentException
     */
    public FeatureList getByState ( String state ) throws IllegalArgumentException{
        ////Throw error if input with blank or null.
        if(state == null) throw new IllegalArgumentException("Can not get a feature with null import.");
        if(state.isBlank()) throw new IllegalArgumentException("Can not get a feature with empty import.");

        FeatureList std = new FeatureList();
        Boolean hasValue = false;

        //Search
        for (Feature f:this){
            Location featureLocation = f.getFeatureLocation();
            if(featureLocation.compareStateName(state)) {
                std.add(f);
                hasValue = true;
            }
        }

        if(!hasValue) return null;
        else{
            std.sort(Feature::compareTo);
            return std;
        }
    }
}
