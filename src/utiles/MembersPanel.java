package utiles;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MembersPanel extends JPanel {
    DefaultListModel<String> members;

    public MembersPanel(){
        JFrame f = new JFrame();
        f.setSize(420,660);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Let's chat");
        f.setLayout(new BorderLayout(2,2));

        JPanel top = new JPanel(new BorderLayout());
        MyButton backBtn = new MyButton("Back");
        backBtn.setBackground(new Color(183, 177, 85));
        top.add(backBtn,BorderLayout.LINE_START);

        members = new DefaultListModel<>();
      JList<String>  membersList= new JList<>(members);
      JScrollPane scrollPane = new JScrollPane(membersList);
      scrollPane.setViewportView(membersList);
      membersList.setFixedCellHeight(25);
      membersList.setFixedCellWidth(100);
      membersList.setBorder(new EmptyBorder(10,10, 10, 10));
      membersList.setFont(new Font("Arial",Font.BOLD, 14));
      for(int i = 0; i < 50; i++)
        addMember(i + "  Faroq");

       f.add(top, BorderLayout.NORTH);
      f.add(scrollPane,BorderLayout.CENTER);
      f.setVisible(true);
    }

    public void addMember(String name){
        members.addElement(name);
    }

    public static void main(String[] arg){

        new MembersPanel();
    }
}
