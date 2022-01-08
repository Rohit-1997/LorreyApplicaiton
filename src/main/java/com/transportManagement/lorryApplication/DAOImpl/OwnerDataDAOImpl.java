package com.transportManagement.lorryApplication.DAOImpl;

import com.transportManagement.lorryApplication.DAO.OwnerDataDAO;
import com.transportManagement.lorryApplication.requests.OwnerDataRequest;
import com.transportManagement.lorryApplication.response.AcceptedTripsData;
import com.transportManagement.lorryApplication.response.GetAcceptedTripsResponse;
import com.transportManagement.lorryApplication.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

@Component
public class OwnerDataDAOImpl implements OwnerDataDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public GetAcceptedTripsResponse getAcceptedTrips(OwnerDataRequest request) {
        System.out.println("In the DAO layer: " + request);
        GetAcceptedTripsResponse response = new GetAcceptedTripsResponse();
        Connection connection = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {

            connection = jdbcTemplate.getDataSource().getConnection();
            stmt = connection.prepareCall("{CALL proc_getAcceptedTrips_v1(?, ?, ?)}");

            stmt.setInt(1, request.getOwnerId());
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.registerOutParameter(3, Types.VARCHAR);

            rs = stmt.executeQuery();

            if (rs.getInt(2) == Utils.boSuccessRespose) {

                while(rs.next()) {
                    System.out.println("In side the result set" + rs);
                    AcceptedTripsData tripData = new AcceptedTripsData();
                    tripData.setTripId(rs.getString("TripId"));
                    tripData.setMasterTripId(rs.getString("MasterTripID"));
                    tripData.setRegistrationNumber(rs.getString("RegistrationNumber"));
                    tripData.setStratLocation(rs.getString("start"));
                    tripData.setDestination(rs.getString("Destination"));

                }
            } else {
                System.out.println("Failure status from DB: " + rs.getString(3));
            }
        } catch(Exception e) {
            System.out.println("Exception in getAcceptedTrips Dao impl: " + e);
        } finally {
             // Closing DB connections
            try {
                if (rs != null) rs.close();
                if (connection != null) connection.close();
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                System.out.println("Exception while closing the connections getAcceptedTrips: "+ e);
            }
        }
        return null;
    }
}
