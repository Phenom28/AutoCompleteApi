package co.getdev.autocompleteapi.result;

/**
 *
 * @author Ogundipe Segun David
 */
public class CityResult implements Comparable<CityResult> {
    
    private String name;
    private Double latitude;
    private Double longitude;
    private Double score;

    public CityResult() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public int compareTo(CityResult cityResult) {
        if (score != null && cityResult.getScore() != null) {
            return score.compareTo(cityResult.getScore());
        }
        return 0;
    }
}
