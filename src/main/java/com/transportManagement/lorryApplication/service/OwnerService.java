package com.transportManagement.lorryApplication.service;


import com.transportManagement.lorryApplication.dao.OwnerServiceDAO;
import com.transportManagement.lorryApplication.requests.OwnerDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OwnerService {

    @Autowired
    OwnerServiceDAO ownerServiceDao;

    public List<Map<String, Object>> getAcceptedTrips(OwnerDataRequest request) {
        return ownerServiceDao.getAcceptedTripsDAO(request);
    }

    public List<Map<String, Object>> getOwnerBalanceData(OwnerDataRequest request) {
        // Note: Update Balance (Recieved - Spent) along with response
        return ownerServiceDao.getOwnerBalanceData(request);
    }

    public List<Map<String, Object>> getLorries(OwnerDataRequest request) {
        return  ownerServiceDao.getLorries(request);
    }

    public List<Map<String, Object>> getDriversAndCleaners(OwnerDataRequest request) {
        return  ownerServiceDao.getDriversAndCleaners(request);
    }
}
