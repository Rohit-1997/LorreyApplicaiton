package com.transportManagement.lorryApplication.requests;

public class OwnerDataRequest {

    private int ownerId;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "GetAcceptedTrips{" +
                "ownerId=" + ownerId +
                '}';
    }
}
