package utiles;

import javax.swing.*;

import java.awt.*;

public class StartPanel extends JPanel {
    public static void main(String[] arg){
        JFrame f = new JFrame();
        f.setSize(420,660);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Let's chat");
        f.setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 1);
        JPanel panel = new JPanel(new GridBagLayout());


        JLabel hostLbl = new JLabel("host : ");
        JTextField hostTxt = new MyTextField("",5);
        hostTxt.setEditable(true);

        JLabel pseudoLbl = new JLabel("pseudo : ");
        JTextField pseudoTxt = new MyTextField("",5);
        pseudoTxt.setEditable(true);

        JButton joinBtn = new MyButton("Join");
        joinBtn.setBackground(new Color(206, 218, 42));


        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.ipadx = 20;
        panel.add(hostLbl,gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.ipadx = 150;
        panel.add(hostTxt,gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.ipadx = 20;
        panel.add(pseudoLbl,gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.ipadx = 150;
        panel.add(pseudoTxt,gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.ipadx = 25;
        panel.add(joinBtn,gbc);




        f.add(panel);
        f.setVisible(true);
    }
}
