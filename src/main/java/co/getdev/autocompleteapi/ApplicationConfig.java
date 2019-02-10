package co.getdev.autocompleteapi;

import co.getdev.autocompleteapi.resource.CityResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Ogundipe Segun David
 */
@ApplicationPath("api/v1")
public class ApplicationConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>> resources = new HashSet<>();
        resources.add(CityResource.class);
        resources.add(OpenApiResource.class);
        return resources;
    }
}
