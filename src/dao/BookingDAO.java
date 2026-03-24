package dao;

import model.DBConnection;
import java.sql.*;

public class BookingDAO {

    public boolean book(int userId, int busId, int seatNumber) {

        try {
            String sql = "INSERT INTO bookings(user_id,bus_id,seat_number) VALUES(?,?,?)";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, busId);
            ps.setInt(3, seatNumber);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
}