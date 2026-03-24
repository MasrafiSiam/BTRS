package view;

import javax.swing.*;
import java.awt.*;

public class DashboardGUI extends JFrame {

    public DashboardGUI() {

        setTitle("Dashboard");
        setSize(800,800);
        setLocationRelativeTo(null);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        setContentPane(panel);

        JButton search = new JButton("SEARCH BUS");
        search.setBounds(300,300,200,60);

        panel.add(search);

        search.addActionListener(e -> new SearchBusGUI());

        setVisible(true);
    }
}