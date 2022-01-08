package com.transportManagement.lorryApplication.controller;

import com.transportManagement.lorryApplication.requests.OwnerDataRequest;
import com.transportManagement.lorryApplication.response.GetAcceptedTripsResponse;
import com.transportManagement.lorryApplication.service.OwnerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ownerData/")
public class OwnerDataController {

    /*
        Service For OwnerData
     */
    @Autowired
    OwnerDataService ownerDataService;

    @RequestMapping("/getAcceptedTrips")
    public GetAcceptedTripsResponse getAcceptedTrips(@RequestBody OwnerDataRequest request) {
        System.out.println("In Accepted Trips Controller");
        return ownerDataService.getAcceptedTrips(request);
    }
}
