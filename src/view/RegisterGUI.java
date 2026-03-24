package view;

import controller.AuthController;

import javax.swing.*;
import java.awt.*;

public class RegisterGUI extends JFrame {

    public RegisterGUI() {

        setTitle("Register");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        setContentPane(panel);

        // TITLE
        JLabel title = new JLabel("REGISTER");
        title.setBounds(320, 120, 300, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(title);

        // LABELS
        JLabel nameL = new JLabel("Name:");
        JLabel emailL = new JLabel("Email:");
        JLabel passL = new JLabel("Password:");

        nameL.setBounds(200, 240, 200, 30);
        emailL.setBounds(200, 300, 200, 30);
        passL.setBounds(200, 360, 200, 30);

        nameL.setForeground(Color.LIGHT_GRAY);
        emailL.setForeground(Color.LIGHT_GRAY);
        passL.setForeground(Color.LIGHT_GRAY);

        panel.add(nameL);
        panel.add(emailL);
        panel.add(passL);

        // INPUTS
        JTextField name = new JTextField();
        JTextField email = new JTextField();
        JPasswordField pass = new JPasswordField();

        name.setBounds(320, 240, 250, 35);
        email.setBounds(320, 300, 250, 35);
        pass.setBounds(320, 360, 250, 35);

        panel.add(name);
        panel.add(email);
        panel.add(pass);

        // BUTTONS
        JButton registerBtn = new JButton("REGISTER");
        registerBtn.setBounds(320, 440, 200, 50);
        style(registerBtn);
        panel.add(registerBtn);

        JButton backBtn = new JButton("BACK TO LOGIN");
        backBtn.setBounds(320, 510, 200, 50);
        style(backBtn);
        panel.add(backBtn);

        AuthController c = new AuthController();

        // REGISTER ACTION
        registerBtn.addActionListener(e -> {
            boolean ok = c.register(
                    name.getText(),
                    email.getText(),
                    new String(pass.getPassword())
            );

            if (ok) {
                JOptionPane.showMessageDialog(this, "Registration Success");
                new LoginGUI();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Registration Failed");
            }
        });

        // BACK
        backBtn.addActionListener(e -> {
            new LoginGUI();
            dispose();
        });

        setVisible(true);
    }

    void style(JButton b) {
        b.setBackground(new Color(58, 58, 92));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
    }
}