package dao;

import model.DBConnection;
import java.sql.*;

public class BusDAO {

    public ResultSet getAllBuses() {

        try {
            String sql = "SELECT * FROM buses";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            return ps.executeQuery();

        } catch (Exception e) {
            return null;
        }
    }
}