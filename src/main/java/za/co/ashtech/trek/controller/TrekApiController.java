package za.co.ashtech.trek.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import za.co.ashtech.trek.model.Trail;
import za.co.ashtech.trek.service.TrekService;
import za.co.ashtech.trek.util.TrekException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-15T19:56:57.484Z[GMT]")
@RestController
public class TrekApiController implements TrekApi {

    private static final Logger log = LoggerFactory.getLogger(TrekApiController.class);
    
    @Autowired
    private TrekService service;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TrekApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Trail> getRandomTrail() throws TrekException{   	
    	log.debug("Enter getRandomTrail TrekApiController method");
        return new ResponseEntity<Trail>(service.getRandomHikeTrail(),HttpStatus.OK);
    }
    
    public ResponseEntity<List<Trail>> searchTrails(@Parameter(in = ParameterIn.PATH, description = "location to search hiking trails for", required=true, schema=@Schema()) @PathVariable("location") String location) throws TrekException{
    	log.debug("Enter searchTrails TrekApiController method");
        return new ResponseEntity<List<Trail>>(service.searchTrail(location),HttpStatus.OK);
    }
    
    public ResponseEntity<Void> addTrail(@Parameter(in = ParameterIn.DEFAULT, description = "Add hiking trail", schema=@Schema()) @Valid @RequestBody Trail body) throws TrekException{
    	log.debug("Enter addTrail TrekApiController method");
    	
    	service.addTrail(body);
    	
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    public ResponseEntity<Void> editTrail(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id,@NotNull @Parameter(in = ParameterIn.QUERY, description = "" ,required=true,schema=@Schema(allowableValues={ "name", "location", "length", "level", "description", "status" }
    										)) @Valid @RequestParam(value = "name", required = true) String name,@NotNull @Parameter(in = ParameterIn.QUERY, description = "" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "value", required = true) String value) throws TrekException {
        
    	service.editTrail(id, name, value);
    	
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
