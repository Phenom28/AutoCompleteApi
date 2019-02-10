package co.getdev.autocompleteapi.domain;

/**
 *
 * @author Ogundipe Segun David
 */
public class City {
    
    private String name;
    private String countryCode;
    private String timezone;
    private double latitude;
    private double longitude;
    
    public City(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    @Override
    public String toString(){
        return getName() + ", " + getCountryCode() + ", " + getTimezone();
    }
}
