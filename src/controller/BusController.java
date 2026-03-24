package controller;

import dao.BusDAO;
import java.sql.ResultSet;

public class BusController {

    BusDAO dao = new BusDAO();

    public ResultSet getBuses() {
        return dao.getAllBuses();
    }
}