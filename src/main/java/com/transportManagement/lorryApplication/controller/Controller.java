package com.transportManagement.lorryApplication.controller;

import com.transportManagement.lorryApplication.requests.OwnerDataRequest;
import com.transportManagement.lorryApplication.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/office/owner")
public class Controller {

    @Autowired
    private OwnerService ownerService;

    @RequestMapping("/check")
    public String healthCheck() {
        return "hey, I am up";
    }

    @RequestMapping(value = "getAcceptedTrips")
    public List<Map<String, Object>> getAcceptedTrips(@RequestBody OwnerDataRequest request) {
        System.out.println("Inside the controller layer" + request);

        return ownerService.getAcceptedTrips(request);
    }

    @RequestMapping(value = "displayOwnerBalance")
    public List<Map<String, Object>> getOwnerBalanceData(@RequestBody OwnerDataRequest  balanceRequest) {
        System.out.println("getSpentRecievedData : " + balanceRequest);
        return  ownerService.getOwnerBalanceData(balanceRequest);
    }

    @RequestMapping(value = "getLorries")
    public  List<Map<String, Object>> getLorries(@RequestBody OwnerDataRequest lorriesRequest) {
        return ownerService.getLorries(lorriesRequest);
    }

    @RequestMapping(value = "getDriverAndCleanerInfo")
    public  List<Map<String, Object>> getDriversAndCleanersInfo(@RequestBody OwnerDataRequest ownerRequest) {
        return ownerService.getDriversAndCleaners(ownerRequest);
    }

}
