package co.getdev.autocompleteapi.resource;

import co.getdev.autocompleteapi.result.CityResult;
import co.getdev.autocompleteapi.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Ogundipe Segun David
 */
@Path("suggestions")
@RequestScoped
public class CityResource {

    @EJB
    private CityService cityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get suggestions",
            description = "Returns a list of all suggestions matching the supplied search term."
                    + "The caller's location can optionally be supplied via querystring parameters 'latitude' and "
                    + "'longitude' to help improve relative scores",
            tags = {"Suggestions"},
            responses = {
                @ApiResponse(content = @Content(schema = @Schema(implementation = CityResult.class),
                        mediaType = "application/json"), description = "A list of suggested cities in json format")
            })
    public Response getSuggestions(@Parameter(required = true, description = "Partial or complete search term")
            @QueryParam("q") String partialName,
            @Parameter(required = false, description = "Latitude of the caller") @QueryParam("latitude") Double lat,
            @Parameter(required = false, description = "Longitude of the caller") @QueryParam("longitude") Double lon) {
        List<CityResult> results;

        results = cityService.findAndScore(partialName, lat, lon);

        return Response.ok(results).build();
    }
}
