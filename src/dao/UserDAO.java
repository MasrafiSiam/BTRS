package dao;

import model.DBConnection;
import java.sql.*;

public class UserDAO {

    public boolean register(String name, String email, String password) {

        try {
            String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean login(String email, String password) {

        try {
            String sql = "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            return false;
        }
    }
}