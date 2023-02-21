package utiles;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MsgPanel extends JPanel {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int CENTER = 2;
    int align;
    String frm;

    public MsgPanel(String who, String msg,int algin){
        super();
        frm = who;
        this.align = algin;
        addMsg(msg);
        if(align == LEFT)
            setLayout(new FlowLayout(FlowLayout.LEFT));
        else if (align ==CENTER)
            setLayout(new FlowLayout(FlowLayout.CENTER));
        else
            setLayout(new FlowLayout(FlowLayout.RIGHT));

        setBorder(new EmptyBorder(new Insets(1,7,3,7)));
    }

    private void addMsg(String msg){
        MyTextField msgField = new MyTextField(msg);
        msgField.setMaximumSize(new Dimension(225,55));
        msgField.setMargin(new Insets(3,2,3,2));
        if(align== RIGHT) {
            msgField.setBackground(new Color(83, 115, 213));
            add(new JLabel("<html><small>" + frm + "</small></html> "));
            add(msgField);
        }else if(align ==LEFT){
            add(msgField);
            add(new JLabel("<html><small>" + frm + "</small></html> "));
        }
        else{
            msgField.setBackground(new Color(141, 218, 239));
            add(msgField);
        }
    }
}
