import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ChatImp implements Chat, Serializable {
    String FILE_PATH= System.getProperty("user.home") + File.separator + ".chathistory" ;
    List<User> members ;
    List<String> msgHistory;

    public ChatImp(){
        members =  new ArrayList<>();
        msgHistory = new ArrayList<>();
    }

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

    public boolean nameAvail(String name) throws RemoteException {
        for(User user: members){
            if(name.equals(user.getName()))
                return false;
        }
        return true;
    }

    public List<User> getMembers(){
        return members;
    }

    @Override
    public void broadcastMsg(User frm , String msg) throws RemoteException {
        System.out.println("Broadcast");
        msgHistory.add(frm.getName() + " : " + msg);
        storeHistory(frm.getName() + " : " + msg);
        for(User user: members) {
            try {
                if(!user.getName().equals(frm.getName()))
                    user.talk(frm.getName(), msg);
            }catch (RemoteException e){
                    members.remove(user);
            }
        }
    }

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
    public void showHistory(User to) throws RemoteException {
        for(String msg: msgHistory){
            to.talk(msg.substring(0,msg.indexOf(':')),msg.substring(msg.indexOf(':') + 2));
        }
    }

    public void loadHistory(){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(FILE_PATH));
            String st;
            while ((st = br.readLine()) != null)
               msgHistory.add(st);
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void storeHistory(String msg){
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(FILE_PATH,true));
            output.append(msg).append("\n");
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean backUpAvail(){
        File f = new File(FILE_PATH);
        return f.exists() && !f.isDirectory();
    }

    @Override
    public boolean historyAvail(){
       return msgHistory.size() >=1 ;
    }

    @Override
    public boolean deleteHistory(){
        File f = new File(FILE_PATH);
        return f.delete();
    }
}


