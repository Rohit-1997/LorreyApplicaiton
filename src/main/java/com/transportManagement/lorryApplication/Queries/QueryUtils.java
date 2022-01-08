package com.transportManagement.lorryApplication.Queries;

public class QueryUtils {

    public static String acceptedTripsQuery = "select * from (select TRIPS.TripId, TRIPS.MasterTripID, Lorries.RegistrationNumber, \n" +
            "TRIPINFO.Start, TRIPINFO.Destination from TRIPS inner join \n" +
            "TRIPINFO on TRIPS.TripId = TRIPINFO.TripId inner join Lorries on \n" +
            "TRIPS.LorryID = Lorries.LorryID where DateTime in (SELECT max(DateTime) \n" +
            "from TRIPS group by MasterTripID) and Lorries.ownerID= + " +") as a \n" +
            "\n" +
            "inner join\n" +
            "\n" +
            "(SELECT MasterTripID, sum(b.Amount * b.Debit) as balance from (SELECT TRIPS.MasterTripID, \n" +
            "Transactions.Amount, Transactions.Debit from TRIPS inner join Transactions on TRIPS.TripId \n" +
            "= Transactions.TripId inner join Lorries on Lorries.LorryID = TRIPS.LorryID where \n" +
            "Lorries.ownerID = "  +") as b group by b.MasterTripID) as c\n" +
            "\n" +
            "on c.MasterTripID = a.MasterTripID;";
}
