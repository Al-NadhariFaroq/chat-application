package utiles;

import javax.swing.*;

import java.awt.*;

public class StartPanel extends JPanel {
    JButton joinBtn;
    JTextField usernameTxt;
    JLabel errLbl;

    public StartPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 1, 1, 1);
        setLayout(new GridBagLayout());

        JLabel hostLbl = new JLabel("host : ",JLabel.RIGHT);
        JTextField hostTxt = new MyTextField("",6);
        hostTxt.setEditable(true);

        JLabel usernameLbl = new JLabel("username : ",JLabel.RIGHT);
        usernameTxt = new MyTextField("",6);
        usernameTxt.setEditable(true);

        joinBtn = new MyButton("Join");
        joinBtn.setBackground(new Color(231, 192, 32));

        JLabel wlcm = new JLabel("<html>  <font color='red'> Welcome </font> to RMI <font color='blue'> chat </font> </html>");
        wlcm.setFont(new Font("Arial",Font.BOLD,18));
        errLbl = new JLabel("");
       errLbl.setFont(new Font("Arial",Font.BOLD,11));

       gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.ipadx = 100;
        add(wlcm,gbc);


        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.ipadx = 10;
        add(hostLbl,gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.ipadx = 140;
        add(hostTxt,gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.ipadx = 10;
        add(usernameLbl,gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.ipadx = 140;
        add(usernameTxt,gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.ipadx = 20;
        add(joinBtn,gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.ipadx = 140;
        add(errLbl,gbc);

    }

    public JButton getJoinBtn() {
        return joinBtn;
    }

    public JTextField getUsernameTxt() {
        return usernameTxt;
    }

    public JLabel getErrLbl() {
        return errLbl;
    }
}
