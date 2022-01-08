package com.transportManagement.lorryApplication.serviceImpl;


import com.transportManagement.lorryApplication.DAO.OwnerDataDAO;
import com.transportManagement.lorryApplication.requests.OwnerDataRequest;
import com.transportManagement.lorryApplication.response.GetAcceptedTripsResponse;
import com.transportManagement.lorryApplication.service.OwnerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerDataServiceImpl implements OwnerDataService {

    @Autowired
    OwnerDataDAO ownerDataDAO;

    @Override
    public GetAcceptedTripsResponse getAcceptedTrips(OwnerDataRequest request) {
        return  ownerDataDAO.getAcceptedTrips(request);
    }
}
