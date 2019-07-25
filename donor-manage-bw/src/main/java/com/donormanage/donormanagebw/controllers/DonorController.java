package com.donormanage.donormanagebw.controllers;

import com.donormanage.donormanagebw.models.Donation;
import com.donormanage.donormanagebw.models.Donor;
import com.donormanage.donormanagebw.models.User;
import com.donormanage.donormanagebw.services.DonorService;
import com.donormanage.donormanagebw.views.TotalDonationCount;
import com.donormanage.donormanagebw.views.TotalDonorCount;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class DonorController {

    private static final Logger logger = LoggerFactory.getLogger(DonorController.class);

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
    @ApiOperation(value = "adds a single donor", response = Donor.class, responseContainer = "Object")
    @PostMapping(value = "/api/donors/add", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewDonor(HttpServletRequest request, @Valid @RequestBody Donor newdonor) throws URISyntaxException {
        logger.trace(request.getRequestURI() + " accessed");

        newdonor = donorService.save(newdonor);

        Donor createdDonor = donorService.findDonorById(newdonor.getDonorid());

        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newUserURI = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{userid}")
//                .buildAndExpand(newuser.getUserid())
//                .toUri();
//        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(createdDonor, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "update a donor")
    @ApiImplicitParam(name = "donorid", dataType = "integer", paramType = "query",
            value = "id of donor you wish to update. Include a json object with any donor field values")
    @PutMapping(value = "/api/donors/update/{donorid}")
    public ResponseEntity<?> updateDonor(HttpServletRequest request, @RequestBody Donor updateDonor, @PathVariable long donorid)
    {
        logger.trace(request.getRequestURI() + " accessed");

        donorService.update(updateDonor, donorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "get a specific donor", response = Donor.class, responseContainer = "Object")
    @ApiImplicitParam(name = "donorid", dataType = "integer", paramType = "query",
            value = "id of donor you wish to recieve a json object of")
    @GetMapping(value = "/api/donor/{donorid}", produces = {"application/json"})
    public ResponseEntity<?> getDonor(HttpServletRequest request, @PathVariable Long donorid)
    {
        logger.trace(request.getRequestURI() + " accessed");

        Donor donor = donorService.findDonorById(donorid);
        return new ResponseEntity<>(donor, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "delete a specific donor")
    @ApiImplicitParam(name = "donorid", dataType = "integer", paramType = "query",
            value = "id of donor you wish to remove from the database")
    @DeleteMapping("/api/donor/delete/{donorid}")
    public ResponseEntity<?> deleteDonorById(HttpServletRequest request, @PathVariable long donorid)
    {
        logger.trace(request.getRequestURI() + " accessed");

        donorService.delete(donorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "returns the total of count of all donors", response = Donation.class, responseContainer = "Object")
    @GetMapping(value = "/api/donor/count", produces = {"application/json"})
    public ResponseEntity<?> getTotalDonationCount(HttpServletRequest request)
    {
        TotalDonorCount totalDonorCount = donorService.totalDonorCount();
        return new ResponseEntity<>(totalDonorCount, HttpStatus.OK);
    }
}
