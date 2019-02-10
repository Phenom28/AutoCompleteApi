package co.getdev.autocompleteapi.resource;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.mapper.TypeRef;
import java.util.List;
import java.util.Map;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ogundipe Segun David
 */
public class CityResourceTest {
    
    public CityResourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 34815;
    }

    /**
     * Test of getSuggestions method, of class CityResource.
     */
    @Test
    public void testGetSuggestions() {
        //Extract
        List<Map<String, Object>> cities = get("/api/v1/suggestions?q=ibadan").as(
        new TypeRef<List<Map<String, Object>>>(){});
        
        //Do validations on the extracted objects
        assertThat(cities, hasSize(1));
        assertThat(cities.get(0).get("name"), equalTo("Ibadan, NG, Africa/Lagos"));
        assertThat(cities.get(0).get("latitude"), equalTo(7.37756));
        assertThat(cities.get(0).get("longitude"), equalTo(3.90591));
    }
    
}
