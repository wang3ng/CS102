package project2;

/**
 * This class stores identities of a feature.
 * It inherits all of its properties from a Comparable<Feature>.
 * It would compare itself with another feature base on their
 * alphabetical and numeric order of their different identities.
 * It can return values of its identities.
 *
 * @author William Wang
 */

public class Feature implements Comparable<Feature>{

    private String featureName;
    private String featureClass;
    private Location featureLocation;

    public Feature (String featureName, String featureClass, Location featureLocation)
            throws IllegalArgumentException{
        //Throw error if input with blank or null.
        if(featureClass==null || featureName==null || featureClass.isBlank() || featureName.isBlank())
            throw new IllegalArgumentException("FeatureClass and FeatureName " +
                    "cannot be provided as null or empty");
        if(featureLocation == null)
            throw new IllegalArgumentException("FeatureLocation should be a " +
                    "valid Location");
        this.featureName = featureName;
        this.featureClass = featureClass;
        this.featureLocation = featureLocation;
    }

    public String getFeatureName(){
        return featureName;
    }

    public String getFeatureClass(){
        return  featureClass;
    }

    public Location getFeatureLocation(){
        return  featureLocation;
    }

    /**
     * Two features are compared according to their alphabetical and numeric order.
     * FeatureName has the highest priority, along following location and class.
     * An int is provided as the result.
     * @param feature
     * @return An int bigger than 0 zero if the primary is bigger, an int less than zero if the primary is
     * smaller, zero if two locations are equal.
     */
    @Override
    public int compareTo(Feature feature) {
        int std = 0;
        std = this.featureName.compareToIgnoreCase(feature.featureName);
        if(std!=0) return std;
        std = this.featureLocation.compareTo(feature.featureLocation);
        if(std!=0) return std;
        std = this.featureClass.compareToIgnoreCase(feature.featureClass);
        return std;
    }

    /**
     * Check if two features are identical
     * @param obj
     * @return true if identical, false if not.
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Feature)) return false;
        Feature feature = (Feature) obj;
        return this.compareTo(feature) == 0;
    }

    /**
     *
     * @return a string contains following:
     *   NAME, CLASS
     *   COUNTY, STATE
     *   LATITUDE, LONGITUDE, ELEVATION
     */
    @Override
    public String toString(){
        return String.format("%s, %s\n%s",featureName,featureClass,featureLocation);
    }
}
