package utiles;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public class MyTextField extends JTextField implements Serializable {
    private Shape shape;
    private int radian;

    public MyTextField(String txt){
        super(txt);
        init();
    }
    public MyTextField(String txt,int col) {
        super(txt,col);
       init();
    }
    private void init(){
        setEditable(false);
        setMargin(new Insets(2,4,2,0));
        setFont(new Font("Ariel", Font.BOLD, 12));
        radian = 12;
        setOpaque(false);
    }
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radian, radian);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radian, radian);
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, radian, radian);
        }
        return shape.contains(x, y);
    }
}