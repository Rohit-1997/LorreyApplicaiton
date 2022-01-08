package com.transportManagement.lorryApplication.DAO;

import com.transportManagement.lorryApplication.requests.OwnerDataRequest;
import com.transportManagement.lorryApplication.response.GetAcceptedTripsResponse;

public interface OwnerDataDAO {
    public GetAcceptedTripsResponse getAcceptedTrips(OwnerDataRequest request);
}
