package project2;

/**
 * This class stores identities of a Location.
 * It inherits all of its properties from a Comparable<Location>.
 * It would compare itself with another feature base on their
 * alphabetical and numeric order of their different identities.
 * It can set and return values of its identities.
 *
 * @author William Wang
 */

import java.lang.Math;

class Location implements Comparable<Location> {

    private double latitude = 0;
    private double longitude = 0;
    private int elevation = 0;
    private String state;
    private String county;

    /**
     * Constructs a new Location Object with specified state and county.
     * @param state
     * @param county
     * @throws IllegalArgumentException
     */
    public Location (String state, String county) throws IllegalArgumentException{
        if(state == null || county == null || state.isBlank() || county.isBlank()) throw new IllegalArgumentException("The state and county " +
                "shouldn't be null");
        this.state = state;
        this.county = county;
    }

    /**
     * Returns the latitude of the Location.
     * @return latitude
     */
    public double getLatitude(){
        return latitude;
    }

    /**
     * Validates and sets the Latitude.
     * @param latitude
     * @throws IllegalArgumentException
     */
    public void setLatitude( double latitude ) throws IllegalArgumentException {
        if(latitude>=-90 && latitude<=90){
            this.latitude = latitude;
        }
        else throw new IllegalArgumentException("Invalid value for latitude, " +
                "should be in the range of from -90 to +90");
    }

    /**
     * Returns the longitude of the Location.
     * @return longitude
     */
    public double getLongitude(){
        return longitude;
    }

    /**
     * Validates and sets the Longitude.
     * @param longitude
     * @throws IllegalArgumentException
     */
    public void setLongitude( double longitude ) throws  IllegalArgumentException{
        if(longitude>=-180 && longitude<=180){
            this.longitude = longitude;
        }
        else throw new IllegalArgumentException("Invalid value for longitude," +
                "should be in the range of from -180 to +180");
    }

    public int getElevation(){
        return  elevation;
    }

    public void setElevation( int elevation ) throws  IllegalArgumentException{
        this.elevation = elevation;
    }

    /**
     * Two locations are compared according to their alphabetical and numeric order.
     * State has the highest priority, along following county, latitude, longitude, elevation.
     * An int is provided as the result.
     * @param location
     * @return An int bigger than 0 zero if the primary is bigger, an int less than zero if the primary is
     * smaller, zero if two locations are equal.
     */
    @Override
    public int compareTo(Location location) {
        if(this == location) return 0;
        if(this.state.compareToIgnoreCase(location.state)!=0){
            return this.state.compareToIgnoreCase(location.state);
        }
        if(this.county.compareToIgnoreCase((location.county))!=0){
            return this.county.compareToIgnoreCase(location.county);
        }
        if(Double.compare(this.latitude,location.latitude)!=0){
            return Double.compare(this.latitude, location.latitude);
        }
        if(Double.compare(this.longitude,location.longitude)!=0){
            return Double.compare(this.longitude,location.longitude);
        }
        return this.elevation-location.elevation;
    }

    public boolean compareStateName(String name){
        if(name == null) return false;
        if(state.compareToIgnoreCase(name)==0) return true;
        return false;
    }

    /**
     * Compare two double, return true if those are same in within the given digit.
     * @param x double variable 1
     * @param y double variable 2
     * @param digit
     * @return True if equal, false if not.
     */
    private boolean compareDouble(double x, double y, int digit){
        return Math.abs(x-y) < Math.pow(10,-digit);
    }

    /**
     * Two locations are compared based on their State county, latitude, longitude, elevation.
     * Return true if identical.
     * @param obj
     * @return True if equal, false if not.
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Location)) return false;
        Location location = (Location) obj;
        if(this.state.compareToIgnoreCase(location.state)!=0)
            return false;
        if(this.county.compareToIgnoreCase((location.county))!=0)
            return false;
        if(!compareDouble(this.latitude,location.latitude,7))
            return false;
        if(!compareDouble(this.longitude,location.longitude,7))
            return false;
        if(this.elevation!=location.elevation) return false;
        return true;
    }

    /**
     *
     * @return a string that contains following:
     *   COUNTY, STATE
     *   LATITUDE, LONGITUDE, ELEVATION
     */
    @Override
    public String toString(){
        return String.format("%s, %s\n%f, %f, %d",county, state, latitude, longitude, elevation);
    }
}
