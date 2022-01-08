package com.transportManagement.lorryApplication.service;

import com.transportManagement.lorryApplication.requests.OwnerDataRequest;
import com.transportManagement.lorryApplication.response.GetAcceptedTripsResponse;

public interface OwnerDataService {

    public GetAcceptedTripsResponse getAcceptedTrips(OwnerDataRequest request);


}
