import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class ChatImp implements Chat, Serializable {
    private final String FILE_PATH= System.getProperty("user.home") + File.separator + ".chathistory" ;
    private final List<User> members ;
    private final List<String> messages;

    public ChatImp(){
        members =  synchronizedList(new ArrayList<>());
        messages = synchronizedList(new ArrayList<>());
        restoreMessages();
    }

    @Override
    public void join(User who) throws RemoteException {
        if(!members.contains(who)) {
            members.add(who);
            for (User user : members) {
                if (!user.getName().equals(who.getName()))
                    user.joined(who.getName() + " has joined the chat!");
                else
                    user.joined("You have joined the chat!");
            }
        }
    }

    @Override
    public boolean nameAvail(String name) throws RemoteException {
        for(User user: members){
            if(name.replaceAll("\\s+","").equalsIgnoreCase(user.getName().replaceAll("\\s+","")))
                return false;
        }
        return true;
    }

    @Override
    public List<User> getMembers(){
        return members;
    }

    @Override
    public void broadcastMsg(User frm , String msg) throws RemoteException {
        messages.add(frm.getName() + " : " + msg);
        addToBackup(frm.getName() + " : " + msg);
        for(User user: members) {
            try {
                if(!user.getName().equals(frm.getName()))
                    user.talk(frm.getName(), msg);
            }catch (RemoteException e){
                    members.remove(user);
            }
        }
    }

    @Override
    public void exit(User who) throws RemoteException{
        for (User user : members) {
            if (!user.getName().equals(who.getName()))
                user.left(who.getName() + " has left the chat!");
            else
                user.left("You have left the chat!");
        }
         members.remove(who);
    }

    @Override
    public void showAllMessages(User to) throws RemoteException {
        for(String msg: messages){
            to.talk(msg.substring(0,msg.indexOf(':')-1),msg.substring(msg.indexOf(':') + 2));
        }
    }

    @Override
    public boolean historyAvail(){
        return messages.size() >=1 ;
    }

    public void restoreMessages(){
        File f = new File(FILE_PATH);
        if(f.exists() && !f.isDirectory()) {
            BufferedReader br;
            String rep;
            try{
                br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Messages Backup found! ");
                System.out.println("Restore messages? yes/no : ");
                rep = br.readLine();
                if(rep.equalsIgnoreCase("yes")) {
                    br = new BufferedReader(new FileReader(FILE_PATH));
                    String st;
                    while ((st = br.readLine()) != null)
                        messages.add(st);
                }
                br.close();
                deleteBackup();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void addToBackup(String msg){
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(FILE_PATH,true));
            output.append(msg).append("\n");
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBackup(){
        File f = new File(FILE_PATH);
        f.delete();
    }

}


