DELIMITER #
USE LorryData
DROP PROCEDURE IF EXISTS proc_getAcceptedTrips_v1#

CREATE PROCEDURE proc_getAcceptedTrips_v1 (
    IN in_owner_id INT,
    OUT out_status INT,
    OUT out_error_message VARCHAR(250)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION BEGIN
        GET DIAGNOSTICS CONDITION 1
        out_error_message = MESSAGE_TEXT;

        SET out_status=0;
        SET out_error_message = CONCAT("Exception in procedure getAcceptedTrips ", MESSAGE_TEXT);
        END;

        SET out_status = 0;

        -- Query For Accepted Trips

        SELECT * FROM (SELECT TRIPS.TripId, TRIPS.MasterTripID, Lorries.RegistrationNumber,
        TRIPINFO.Start, TRIPINFO.Destination from TRIPS inner join
        TRIPINFO on TRIPS.TripId = TRIPINFO.TripId inner join Lorries on
        TRIPS.LorryID = Lorries.LorryID where DateTime in (SELECT max(DateTime)
        from TRIPS group by MasterTripID) and Lorries.ownerID=in_owner_id) as a
        inner join
        (SELECT MasterTripID, sum(b.Amount * b.Debit) as balance from (SELECT TRIPS.MasterTripID,
        Transactions.Amount, Transactions.Debit from TRIPS inner join Transactions on TRIPS.TripId
        = Transactions.TripId inner join Lorries on Lorries.LorryID = TRIPS.LorryID where
        Lorries.ownerID = in_owner_id) as b group by b.MasterTripID) as c
        on c.MasterTripID = a.MasterTripID;

        SET out_status = 1;

END #
DELIMITER ;

