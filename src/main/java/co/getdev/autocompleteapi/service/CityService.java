package co.getdev.autocompleteapi.service;

import co.getdev.autocompleteapi.domain.City;
import co.getdev.autocompleteapi.poi.ExcelReader;
import co.getdev.autocompleteapi.result.CityResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Ogundipe Segun David
 */
@Stateless
public class CityService {
    
    @EJB
    private ExcelReader reader;

    public CityService() {

    }

    public LinkedList<City> findCities(String partialName) {
        LinkedList<City> matches = new LinkedList<>();
        partialName = partialName.toLowerCase();

        for (City city : reader.getAllCities()) {
            if (city.getName().toLowerCase().contains(partialName)) {
                matches.add(city);
            }
        }
        return matches;
    }

    public List<CityResult> findAndScore(String partialName, Double latitude, Double longitude) {
        List<CityResult> cityResults = new ArrayList<>();

        findCities(partialName).forEach((city) -> {
            cityResults.add(toCityResult(city, latitude, longitude, city.getLatitude(), city.getLongitude()));
        });
        
        Collections.sort(cityResults);
        Collections.reverse(cityResults);
        return cityResults;
    }

    public CityResult toCityResult(City city, Double lat1, Double lon1, Double lat2, Double lon2) {
        CityResult cityResult = new CityResult();
        boolean posAvailable = lat1 != null && lon1 != null;
        Double distance = null;

        if (posAvailable) {
            distance = roundDistance(distance(lat1, lon2, lat2, lon2));
        }

        cityResult.setName(city.toString());
        cityResult.setLatitude(city.getLatitude());
        cityResult.setLongitude(city.getLongitude());
        cityResult.setScore(scoreDistance(distance));

        return cityResult;
    }
    
    public double distance(double lat1, double lon1, double lat2, double lon2){
        final int R = 6371; //Radius of the earth
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = (R * c) / 10000;
        
        distance = Math.pow(distance, 2);
        
        return Math.sqrt(distance);
    }

    private double roundDistance(Double value) {

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    private Double scoreDistance(Double distance){
        
        Double score;
        if (distance == null) {
            score = null;
        } else if (distance == 0.0) {
            score = 1.0;
        } else if (distance == 0.1) {
            score = 0.9;
        } else if (distance == 0.2) {
            score = 0.8;
        } else if (distance == 0.3) {
            score = 0.7;
        } else if (distance == 0.4) {
            score = 0.6;
        } else if (distance == 0.5) {
            score = 0.5;
        } else if (distance == 0.6) {
            score = 0.4;
        } else if (distance == 0.7) {
            score = 0.3;
        } else if (distance == 0.8) {
            score = 0.2;
        } else if (distance == 0.9) {
            score = 0.1;
        } else {
            score = 0.0;
        }
        
        return score;
    }
}
