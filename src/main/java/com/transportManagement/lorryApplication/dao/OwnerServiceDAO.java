package com.transportManagement.lorryApplication.dao;

import com.transportManagement.lorryApplication.requests.OwnerDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OwnerServiceDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAcceptedTripsDAO(OwnerDataRequest request) {
        String query = "select * from (select TRIPS.TripId, TRIPS.MasterTripID, Lorries.RegistrationNumber, \n" +
                "TRIPINFO.Start, TRIPINFO.Destination from TRIPS inner join \n" +
                "TRIPINFO on TRIPS.TripId = TRIPINFO.TripId inner join Lorries on \n" +
                "TRIPS.LorryID = Lorries.LorryID where DateTime in (SELECT max(DateTime) \n" +
                "from TRIPS group by MasterTripID) and Lorries.ownerID= + " + request.getOwnerId() +") as a \n" +
                "\n" +
                "inner join\n" +
                "\n" +
                "(SELECT MasterTripID, sum(b.Amount * b.Debit) as balance from (SELECT TRIPS.MasterTripID, \n" +
                "Transactions.Amount, Transactions.Debit from TRIPS inner join Transactions on TRIPS.TripId \n" +
                "= Transactions.TripId inner join Lorries on Lorries.LorryID = TRIPS.LorryID where \n" +
                "Lorries.ownerID = " + request.getOwnerId() +") as b group by b.MasterTripID) as c\n" +
                "\n" +
                "on c.MasterTripID = a.MasterTripID;";



        return jdbcTemplate.queryForList(query);
    }



    public List<Map<String, Object>> getOwnerBalanceData(OwnerDataRequest request) {
        String query = "SELECT\n" +
                "SUM(CASE WHEN (Transactions.Debit > 0) THEN Transactions.Amount ELSE 0 END) as received,\n" +
                "SUM(CASE WHEN (Transactions.Debit < 0) THEN Transactions.Amount ELSE 0 END) as spent\n" +
                "FROM TRIPS\n" +
                "INNER JOIN Transactions on TRIPS.TripId = Transactions.TripId\n" +
                "INNER JOIN Lorries on Lorries.LorryID = TRIPS.LorryID\n" +
                "WHERE Lorries.ownerID = " + request.getOwnerId();

        return jdbcTemplate.queryForList(query);
    }


    public List<Map<String, Object>> getLorries(OwnerDataRequest request) {
        String query = "SELECT RegistrationNumber, RC, Insurance\n" +
                "FROM Lorries\n" +
                "WHERE ownerID = " + request.getOwnerId();

        return jdbcTemplate.queryForList(query);
    }


    public List<Map<String, Object>> getDriversAndCleaners(OwnerDataRequest request) {
        String query = "SELECT \n" +
                "Name, MobileNumber FROM Users\n" +
                "WHERE TransportationName IN\n" +
                "(SELECT\n" +
                "TransportationName\n" +
                "FROM Users\n" +
                "WHERE UserId= "+ request.getOwnerId() + ")" +
                " AND Role NOT IN ('owner');";

        return jdbcTemplate.queryForList(query);
    }


}
