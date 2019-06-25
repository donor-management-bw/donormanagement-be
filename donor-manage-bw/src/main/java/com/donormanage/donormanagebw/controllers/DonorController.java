package com.donormanage.donormanagebw.controllers;

import com.donormanage.donormanagebw.models.Donor;
import com.donormanage.donormanagebw.services.DonorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class DonorController {

    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    private DonorService donorService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "returns all donors", response = Donor.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/api/donors/all", produces = {"application/json"})
    public ResponseEntity<?> listAllDonors(
            @PageableDefault(page = 0,
                    size = 5)
                    Pageable pageable) {
        List<Donor> donors = donorService.findAll(pageable);
        return new ResponseEntity<>(donors, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping(value = "/api/donors/add", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewDonor(HttpServletRequest request, @Valid @RequestBody Donor newdonor) throws URISyntaxException {
        logger.trace(request.getRequestURI() + " accessed");

        newdonor = donorService.save(newdonor);

        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newUserURI = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{userid}")
//                .buildAndExpand(newuser.getUserid())
//                .toUri();
//        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, HttpStatus.CREATED);

    }
}
