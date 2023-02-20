package utiles;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MyButton extends JButton {
    int radian = 15;
    public MyButton(String label) {
        super(label);
        setFocusable(false);
        Dimension size = getPreferredSize();
        //size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(new Color(238, 217, 113));
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getSize().width-1,getSize().height-1,radian, radian);
        super.paintComponent(g);
    }

    // Paint the border of the button using a simple stroke.
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getSize().width-1,getSize().height-1,radian,radian);
    }

    // Hit detection.
    Shape shape;
    public boolean contains(int x, int y) {
        if (shape == null ||!shape.getBounds().equals(getBounds()))
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        return shape.contains(x, y);
    }

    // Test routine.
    public static void main(String[] args) {
        JButton button = new MyButton("Jackpot");
        button.setBackground(Color.green);
        JFrame frame = new JFrame();
        frame.getContentPane().add(button);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setSize(150, 150);
        frame.setVisible(true);
    }
}