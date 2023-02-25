package utiles;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MembersPanel extends JPanel {
    DefaultListModel<String> members;
    MyButton backBtn;

    public MembersPanel(){
        setLayout(new BorderLayout(2,2));
        JPanel top = new JPanel(new BorderLayout());
        top.setBorder(new EmptyBorder(4, 2, 2, 2));
        backBtn = new MyButton("Back");
        backBtn.setBackground(new Color(227, 180, 72));
        top.add(backBtn,BorderLayout.LINE_START);

        members = new DefaultListModel<>();
        JList<String> membersList= new JList<>(members);
        JScrollPane scrollPane = new JScrollPane(membersList);
        scrollPane.setViewportView(membersList);
        membersList.setFixedCellHeight(25);
        membersList.setFixedCellWidth(100);
        membersList.setBorder(new EmptyBorder(5,5, 5, 5));
        membersList.setFont(new Font("Arial",Font.BOLD, 14));

        add(top, BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
    }

    public void addMember(String name){
        members.addElement(name);
    }

    public MyButton getBackBtn() {
        return backBtn;
    }

    public void removeALLMembers() {
        members.clear();
    }
}
