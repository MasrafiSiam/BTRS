package view;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        Color c1 = new Color(18,18,18);
        Color c2 = new Color(40,40,60);

        GradientPaint gp = new GradientPaint(0,0,c1,getWidth(),getHeight(),c2);

        g2d.setPaint(gp);
        g2d.fillRect(0,0,getWidth(),getHeight());
    }
}