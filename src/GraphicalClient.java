import utiles.*;

import javax.swing.*;
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
    MembersPanel membersPanel;
    StartPanel startPanel;
    ChatPanel chatPanel;
    Chat chat;
    String name;
    CardLayout cl;
    Container c;

    public GraphicalClient(String name, Chat chat){
        this.name = name;
        this.chat = chat;
        cl = new CardLayout();
        init();
    }

    private void init(){
       JFrame f = new JFrame();
        f.setSize(420,660);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Let's chat");
        f.setLayout(cl);
        c = f.getContentPane();

        chatPanel = new ChatPanel();
        membersPanel = new MembersPanel();
        startPanel = new StartPanel();
        addListeners();

        f.add("start",startPanel);
        f.add("chat",chatPanel);
        f.add("members",membersPanel);

        f.setVisible(true);
    }

    private void addListeners(){
        chatPanel.getExitBtn().addActionListener(this);
        chatPanel.getMemBtn().addActionListener(this);
        chatPanel.getSendBtn().addActionListener(this);
        chatPanel.getMsgField().addKeyListener(this);
        membersPanel.getBackBtn().addActionListener(this);
        startPanel.getJoinBtn().addActionListener(this);

    }

    private void repaint(){
        chatPanel.getConvPanel().revalidate();
        int height = (int)chatPanel.getConvPanel().getPreferredSize().getHeight();
        chatPanel.getConvPanel().scrollRectToVisible(new Rectangle(0,height,10,10));
    }

    public void changeMenu(String menu){
        cl.show(c,menu);
    }

    private void addSntMsg() {
        String msg = chatPanel.getMsgField().getText();
        if(msg.trim().length() > 0 ){
            try {
                chatPanel.getConvPanel().add(new MsgPanel( name," " + msg ,MsgPanel.RIGHT));
                chat.broadcastMsg(this,msg);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            chatPanel.getMsgField().setText("");
            repaint();
        }
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void joined(String msg) throws RemoteException {
        chatPanel.getConvPanel().add(new MsgPanel("",msg,MsgPanel.CENTER));
        repaint();
    }

    @Override
    public void left(String msg) throws RemoteException {
        chatPanel.getConvPanel().add(new MsgPanel("",msg,MsgPanel.CENTER));
        repaint();
    }

    @Override
    public void talk(String who, String msg) throws RemoteException {
        chatPanel.getConvPanel().add(new MsgPanel(who,msg,MsgPanel.LEFT));
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chatPanel.getSendBtn()) {
            addSntMsg();
        }
        else if(e.getSource() == chatPanel.getMemBtn()){
            System.out.println("Shows members");
            changeMenu("members");
        }
        else if(e.getSource() == startPanel.getJoinBtn()){
            changeMenu("chat");
        }
        else if(e.getSource() == membersPanel.getBackBtn()){
            changeMenu("chat");
        }
        else if(e.getSource() == chatPanel.getExitBtn()) {
            try {
                chat.exit(this);
                changeMenu("start");
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

            User user = new GraphicalClient("far",new ChatImp());
            UnicastRemoteObject.exportObject(user, 0);
            chat.join(user);


        } catch (Exception e) {
            System.err.println("Error on client: " + e);
            e.printStackTrace();
        }
    }

}

