package view;

import controller.AuthController;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    public LoginGUI() {

        setTitle("Login");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        setContentPane(panel);

        // TITLE
        JLabel title = new JLabel("LOGIN");
        title.setBounds(350, 120, 200, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(title);

        // EMAIL LABEL
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 260, 200, 30);
        emailLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(emailLabel);

        // PASSWORD LABEL
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(200, 320, 200, 30);
        passLabel.setForeground(Color.LIGHT_GRAY);
        panel.add(passLabel);

        // INPUTS
        JTextField email = new JTextField();
        JPasswordField pass = new JPasswordField();

        email.setBounds(320, 260, 250, 35);
        pass.setBounds(320, 320, 250, 35);

        panel.add(email);
        panel.add(pass);

        // LOGIN BUTTON
        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(320, 400, 200, 50);
        style(loginBtn);
        panel.add(loginBtn);

        // REGISTER BUTTON (NEW FIX)
        JButton registerBtn = new JButton("CREATE ACCOUNT");
        registerBtn.setBounds(320, 470, 200, 50);
        style(registerBtn);
        panel.add(registerBtn);

        AuthController c = new AuthController();

        // LOGIN ACTION
        loginBtn.addActionListener(e -> {
            if (c.login(email.getText(), new String(pass.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Login Success");
                new DashboardGUI();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        });

        // GO TO REGISTER
        registerBtn.addActionListener(e -> {
            new RegisterGUI();
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