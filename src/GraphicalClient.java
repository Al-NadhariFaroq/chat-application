import utiles.MsgPanel;
import utiles.MyButton;
import utiles.MyTextField;
import utiles.StackLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GraphicalClient implements User, ActionListener, KeyListener, Serializable {
    JPanel topPanel,convPanel,bottomPanel;
    MyTextField msgField;
    JButton sendBtn, memBtn, exitBtn;
    Chat chat;
    String name;

    public GraphicalClient(String name, Chat chat){
        this.name = name;
        this.chat = chat;
        init();
    }

    private void init(){
        JFrame f = new JFrame();
        f.setSize(420,660);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Let's chat");
        f.setLayout(new BorderLayout());

        topPanel =  new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(4, 2, 2, 2));
        convPanel =  new JPanel(new StackLayout());
        convPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
        JScrollPane scrollPane =new JScrollPane(convPanel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        bottomPanel = new JPanel(new BorderLayout(2,2));
        bottomPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
        msgField = new MyTextField("Write here!");
        msgField.setEditable(true);
        msgField.addKeyListener(this);

        memBtn = newButton("Members");
        memBtn.setBackground(new Color(227, 180, 72));
        exitBtn = newButton("Exit");
        exitBtn.setBackground(new Color(204, 49, 61));
        sendBtn = newButton("Send");
        sendBtn.setBackground(new Color(227, 180, 72));



        topPanel.add(memBtn, BorderLayout.LINE_START);
        topPanel.add(exitBtn,BorderLayout.LINE_END);
        bottomPanel.add(msgField,BorderLayout.CENTER);
        bottomPanel.add(sendBtn, BorderLayout.LINE_END);

        f.add(topPanel,BorderLayout.NORTH);
        f.add(scrollPane,BorderLayout.CENTER);
        f.add(bottomPanel,BorderLayout.SOUTH);

        f.setVisible(true);
    }

    private JButton newButton(String txt){
        JButton btn = new MyButton(txt);
        btn.addActionListener(this);
        return btn;
    }

    private void repaint(){
        convPanel.revalidate();
        int height = (int)convPanel.getPreferredSize().getHeight();
        convPanel.scrollRectToVisible(new Rectangle(0,height,10,10));
    }

    private void addSntMsg() {
        String msg = msgField.getText();
        if(msg.trim().length() > 0 ){
            try {
                convPanel.add(new MsgPanel( name," " + msg ,MsgPanel.RIGHT));
                chat.broadcastMsg(this,msg);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            msgField.setText("");
            repaint();
        }
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void joined(String msg) throws RemoteException {
        convPanel.add(new MsgPanel("",msg,MsgPanel.CENTER));
        repaint();
    }

    @Override
    public void left(String msg) throws RemoteException {
        convPanel.add(new MsgPanel("",msg,MsgPanel.CENTER));
        repaint();
    }

    @Override
    public void talk(String who, String msg) throws RemoteException {
        convPanel.add(new MsgPanel(who,msg,MsgPanel.LEFT));
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sendBtn) {
            addSntMsg();
        }
        else if(e.getSource() == memBtn)
            System.out.println("Shows members");
        else if(e.getSource() == exitBtn) {
            try {
                chat.exit(this);
                msgField.setEditable(false);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
            addSntMsg();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args){
        if (args.length != 2) {
            System.out.println("Usage: java Client <rmiregistry host> <name>");
            System.exit(1);
        }

        try {
            String host = args[0];
            // Get remote object reference
            Registry registry = LocateRegistry.getRegistry(host);
           Chat chat = (Chat) registry.lookup("ChatService");

           if(chat == null){
               System.out.println("ChatService not found!");
               System.exit(1);
           }

            User user = new GraphicalClient(args[1],chat);
            UnicastRemoteObject.exportObject(user, 0);
            chat.join(user);


        } catch (Exception e) {
            System.err.println("Error on client: " + e);
            e.printStackTrace();
        }
    }

}

