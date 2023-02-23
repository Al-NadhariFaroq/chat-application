package utiles;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatPanel extends JPanel {
    JButton sendBtn, memBtn, exitBtn;
    JPanel topPanel,bottomPanel,convPanel;
    JScrollPane scrollPane;
    JTextField msgField;
    JLabel numMembers;

    public ChatPanel(){
        super();
        setLayout(new BorderLayout(2,2));
        topPanel =  new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(4, 2, 2, 2));
        convPanel =  new JPanel(new StackLayout());
        convPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
        scrollPane =new JScrollPane(convPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        bottomPanel = new JPanel(new BorderLayout(2,2));
        bottomPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
        msgField = new MyTextField("Hello here!");
        msgField.setEditable(true);

        numMembers = new JLabel("0 users online",JLabel.CENTER);
        numMembers.setFont(new Font("Arial",Font.BOLD,12));

        memBtn = new MyButton("Members");
        memBtn.setBackground(new Color(227, 180, 72));
        exitBtn = new MyButton("Exit");
        exitBtn.setBackground(new Color(204, 49, 61));
        sendBtn = new MyButton("Send");
        sendBtn.setBackground(new Color(227, 180, 72));



        topPanel.add(memBtn, BorderLayout.LINE_START);
        topPanel.add(exitBtn,BorderLayout.LINE_END);
        topPanel.add(numMembers,BorderLayout.CENTER);
        bottomPanel.add(msgField,BorderLayout.CENTER);
        bottomPanel.add(sendBtn, BorderLayout.LINE_END);

        add(topPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
    }

    public JButton getSendBtn() {
        return sendBtn;
    }

    public JButton getMemBtn() {
        return memBtn;
    }

    public JButton getExitBtn() {
        return exitBtn;
    }


    public JTextField getMsgField() {
        return msgField;
    }

    public JPanel getConvPanel() {
        return convPanel;
    }

    public void setNumMembers(int n){
      numMembers.setText(n + " users online");
      topPanel.revalidate();
    }
}
