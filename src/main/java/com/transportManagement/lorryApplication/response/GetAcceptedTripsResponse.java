package com.transportManagement.lorryApplication.response;

import java.util.List;

public class GetAcceptedTripsResponse extends BaseResponse {
    private List<AcceptedTripsData> acceptedTripsData;

    public List<AcceptedTripsData> getAcceptedTripsData() {
        return acceptedTripsData;
    }

    public void setAcceptedTripsData(List<AcceptedTripsData> acceptedTripsData) {
        this.acceptedTripsData = acceptedTripsData;
    }

    @Override
    public String toString() {
        return "GetAcceptedTripsResponse{" +
                "acceptedTripsData=" + acceptedTripsData +
                '}';
    }
}
