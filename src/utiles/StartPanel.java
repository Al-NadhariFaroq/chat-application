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

        JLabel serviceLbl = new JLabel("service : ",JLabel.RIGHT);
        JTextField serviceTxt = new MyTextField("ChatService",6);
        serviceTxt.setEditable(true);

        JLabel usernameLbl = new JLabel("username : ",JLabel.RIGHT);
        usernameTxt = new MyTextField("",6);
        usernameTxt.setEditable(true);

        joinBtn = new MyButton("Join");
        joinBtn.setBackground(new Color(227, 180, 72));

        JLabel wlcm = new JLabel("<html>  <font color='red'> Welcome </font> to RMI <font color='blue'> chat </font> </html>",JLabel.CENTER);
        wlcm.setFont(new Font("Arial",Font.BOLD,18));
        errLbl = new JLabel("",JLabel.CENTER);
       errLbl.setFont(new Font("Arial",Font.BOLD,11));

       gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.ipadx = 120;
        add(wlcm,gbc);


        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.ipadx = 10;
        add(serviceLbl,gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.ipadx = 140;
        add(serviceTxt,gbc);

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
        gbc.ipadx = 10;
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

    public static void main(String[] args){
        JFrame f = new JFrame("Test");
        f.setSize(new Dimension(420,660));
        f.setMinimumSize(new Dimension(420,660));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new StartPanel());
        f.setVisible(true);
    }
}
