package com.transportManagement.lorryApplication.response;

public class AcceptedTripsData {
    private int TripId;
    private int masterTripId;
    private String registrationNumber;
    private String stratLocation;
    private String destination;
    private long balance;

    public int getTripId() {
        return TripId;
    }

    public void setTripId(int tripId) {
        TripId = tripId;
    }

    public int getMasterTripId() {
        return masterTripId;
    }

    public void setMasterTripId(int masterTripId) {
        this.masterTripId = masterTripId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getStratLocation() {
        return stratLocation;
    }

    public void setStratLocation(String stratLocation) {
        this.stratLocation = stratLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "GetAcceptedTrips{" +
                "TripId=" + TripId +
                ", masterTripId=" + masterTripId +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", stratLocation='" + stratLocation + '\'' +
                ", destination='" + destination + '\'' +
                ", balance=" + balance +
                '}';
    }

}
