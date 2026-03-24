package view;

import model.DBConnection;
import util.Session;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SeatBookingGUI extends JFrame {

    int busId;
    JButton[] seats = new JButton[40];
    int selectedSeat = -1;

    public SeatBookingGUI(int busId) {

        this.busId = busId;

        setTitle("Seat Booking");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        setContentPane(panel);

        // TITLE
        JLabel title = new JLabel("SELECT YOUR SEAT");
        title.setBounds(260, 30, 400, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(title);

        // CREATE SEATS
        int x = 80, y = 100;

        for (int i = 0; i < 40; i++) {

            int seatNo = i + 1;

            seats[i] = new JButton(String.valueOf(seatNo));
            seats[i].setBounds(x, y, 60, 40);

            style(seats[i]);

            seats[i].addActionListener(e -> {
                selectedSeat = seatNo;
                JOptionPane.showMessageDialog(this, "Seat Selected: " + seatNo);
            });

            panel.add(seats[i]);

            x += 70;

            if ((i + 1) % 5 == 0) {
                x = 80;
                y += 60;
            }
        }

        // MARK BOOKED SEATS
        loadBookedSeats();

        // BOOK BUTTON
        JButton bookBtn = new JButton("BOOK SEAT");
        bookBtn.setBounds(320, 650, 150, 40);
        style(bookBtn);
        panel.add(bookBtn);

        bookBtn.addActionListener(e -> bookSeat());

        setVisible(true);
    }

    // LOAD BOOKED SEATS FROM DB
    void loadBookedSeats() {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT seat_number FROM bookings WHERE bus_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, busId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int seat = rs.getInt("seat_number");

                seats[seat - 1].setEnabled(false);
                seats[seat - 1].setBackground(Color.RED);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading seats: " + e.getMessage());
        }
    }

    // BOOK SEAT
    void bookSeat() {

        if (selectedSeat == -1) {
            JOptionPane.showMessageDialog(this, "Please select a seat first!");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            // 🔥 CHECK IF SEAT ALREADY BOOKED (IMPORTANT FIX)
            String checkSql = "SELECT * FROM bookings WHERE bus_id=? AND seat_number=?";

            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, busId);
            checkPs.setInt(2, selectedSeat);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Seat already booked!");
                return;
            }

            // INSERT BOOKING (NOW WITH USER ID)
            String sql = "INSERT INTO bookings(user_id, bus_id, seat_number) VALUES(?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Session.userId);
            ps.setInt(2, busId);
            ps.setInt(3, selectedSeat);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Seat Booked Successfully!");

            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Booking Error: " + e.getMessage());
        }
    }

    void style(JButton b) {
        b.setBackground(new Color(50, 50, 80));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
    }
}