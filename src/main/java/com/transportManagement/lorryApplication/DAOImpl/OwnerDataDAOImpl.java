package com.transportManagement.lorryApplication.DAOImpl;

import com.transportManagement.lorryApplication.DAO.OwnerDataDAO;
import com.transportManagement.lorryApplication.requests.OwnerDataRequest;
import com.transportManagement.lorryApplication.response.AcceptedTripsData;
import com.transportManagement.lorryApplication.response.GetAcceptedTripsResponse;
import com.transportManagement.lorryApplication.utils.DBUtils;
import com.transportManagement.lorryApplication.utils.Utils;
import com.transportManagement.lorryApplication.Loggers.Loggers;
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
                    tripData.setTripId(rs.getInt("TripId"));
                    tripData.setMasterTripId(rs.getInt("MasterTripID"));
                    tripData.setRegistrationNumber(rs.getString("RegistrationNumber"));
                    tripData.setStratLocation(rs.getString("start"));
                    tripData.setDestination(rs.getString("Destination"));

                    response.getAcceptedTripsData().add(tripData);

                }
            } else {
                System.out.println("Failure status from DB: " + rs.getString(3));
            }
        } catch(Exception e) {
            System.out.println("Exception in getAcceptedTrips Dao impl: " + e);
        } finally {
            DBUtils.closeDBConnections(stmt, connection, rs);
        }

        Loggers.OWNERDATA_LOGGER.info("The response fetched from the database: {}", response);
        return response;
    }
}
