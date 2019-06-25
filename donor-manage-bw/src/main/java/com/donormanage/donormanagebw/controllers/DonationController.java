package com.donormanage.donormanagebw.controllers;

import com.donormanage.donormanagebw.models.Donation;
import com.donormanage.donormanagebw.models.Donor;
import com.donormanage.donormanagebw.services.DonationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class DonationController {

    private static final Logger logger = LoggerFactory.getLogger(DonationController.class);



    @Autowired
    private DonationService donationService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/api/donations/{donationid}", produces = {"application/json"})
    public ResponseEntity<?> getDonation(HttpServletRequest request, @PathVariable Long donationid)
    {
        logger.trace(request.getRequestURI() + " accessed");

        Donation donation = donationService.findDonationById(donationid);
        return new ResponseEntity<>(donation, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "update a donation")
    @ApiImplicitParam(name = "donationid", dataType = "integer", paramType = "query",
            value = "id of donation you wish to update. Include a json object with any donation field values")
    @PutMapping(value = "/api/donations/update/{donationid}")
    public ResponseEntity<?> updateDonation(HttpServletRequest request, @RequestBody Donation updateDonation, @PathVariable long donationid)
    {
        logger.trace(request.getRequestURI() + " accessed");

        donationService.update(updateDonation, donationid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "delete a specific donation")
    @ApiImplicitParam(name = "donationid", dataType = "integer", paramType = "query",
            value = "id of donation you wish to remove from the database")
    @DeleteMapping("/api/donations/delete/{donationid}")
    public ResponseEntity<?> deleteDonationById(HttpServletRequest request, @PathVariable long donationid)
    {
        logger.trace(request.getRequestURI() + " accessed");

        donationService.delete(donationid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ApiOperation(value = "adds a single donation", response = Donation.class, responseContainer = "Object")
    @PostMapping(value = "/api/donation/add", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewDonation(HttpServletRequest request, @Valid @RequestBody Donation newdonation) throws URISyntaxException {
        logger.trace(request.getRequestURI() + " accessed");

        newdonation = donationService.save(newdonation);

        Donation createdDonation = donationService.findDonationById(newdonation.getDonationid());

        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newUserURI = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{userid}")
//                .buildAndExpand(newuser.getUserid())
//                .toUri();
//        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(createdDonation, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/api/donations/donor/{donorid}", produces = {"application/json"})
    public ResponseEntity<?> getDonationsByDonor(HttpServletRequest request, @PathVariable Long donorid)
    {
        logger.trace(request.getRequestURI() + " accessed");

        List<Donation> donations = donationService.listDonationsByDonorId(donorid);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }
}
