package com.transportManagement.lorryApplication.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import com.transportManagement.lorryApplication.Loggers.Loggers;

public class DBUtils {

    /*
       Utility Method to close the Database connections
     */
    public static void closeDBConnections(CallableStatement stmt, Connection conn, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (conn != null) conn.close();
            if (stmt != null) stmt.close();
        }catch(Exception e) {
            Loggers.OWNERDATA_LOGGER.error("Exception in Closing the DB Connections: {}", e);
        }
    }
}
