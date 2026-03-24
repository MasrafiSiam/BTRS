package view;

import controller.BusController;
import model.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class SearchBusGUI extends JFrame {

    JTable table;
    DefaultTableModel model;

    JComboBox<String> fromBox, toBox;

    public SearchBusGUI() {

        setTitle("Search Buses");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        setContentPane(panel);

        // TITLE
        JLabel title = new JLabel("SEARCH BUS");
        title.setBounds(300, 30, 300, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        panel.add(title);

        // FROM LABEL
        JLabel fromL = new JLabel("From:");
        fromL.setBounds(120, 100, 100, 25);
        fromL.setForeground(Color.LIGHT_GRAY);
        panel.add(fromL);

        fromBox = new JComboBox<>(new String[]{
                "Dhaka", "Chittagong", "Sylhet", "Khulna", "Rajshahi", "Barisal", "Cox Bazar"
        });
        fromBox.setBounds(180, 100, 150, 30);
        panel.add(fromBox);

        // TO LABEL
        JLabel toL = new JLabel("To:");
        toL.setBounds(360, 100, 100, 25);
        toL.setForeground(Color.LIGHT_GRAY);
        panel.add(toL);

        toBox = new JComboBox<>(new String[]{
                "Dhaka", "Chittagong", "Sylhet", "Khulna", "Rajshahi", "Barisal", "Cox Bazar"
        });
        toBox.setBounds(400, 100, 150, 30);
        panel.add(toBox);

        // SEARCH BUTTON
        JButton searchBtn = new JButton("SEARCH");
        searchBtn.setBounds(580, 100, 120, 30);
        style(searchBtn);
        panel.add(searchBtn);

        // TABLE MODEL
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Bus Name");
        model.addColumn("From");
        model.addColumn("To");
        model.addColumn("Date");
        model.addColumn("Time");
        model.addColumn("Total Seats");

        // TABLE
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50, 180, 700, 500);
        panel.add(sp);

        // SEARCH ACTION
        searchBtn.addActionListener(e -> loadBuses());

        // 🔥 CLICK EVENT (OPEN SEAT BOOKING)
        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = table.getSelectedRow();

                if (row != -1) {

                    int busId = (int) model.getValueAt(row, 0);

                    new SeatBookingGUI(busId);
                }
            }
        });

        setVisible(true);
    }

    // LOAD BUSES FROM DATABASE
    void loadBuses() {

        model.setRowCount(0);

        String from = fromBox.getSelectedItem().toString();
        String to = toBox.getSelectedItem().toString();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM buses WHERE route_from=? AND route_to=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, from);
            ps.setString(2, to);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("bus_name"),
                        rs.getString("route_from"),
                        rs.getString("route_to"),
                        rs.getString("travel_date"),
                        rs.getString("travel_time"),
                        rs.getInt("total_seats")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // BUTTON STYLE
    void style(JButton b) {
        b.setBackground(new Color(60, 60, 90));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
    }
}