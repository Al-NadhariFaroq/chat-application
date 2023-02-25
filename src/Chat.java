import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface Chat extends Remote {
    void join(User user)throws RemoteException;
    boolean nameAvail(String name) throws RemoteException;
    boolean historyAvail() throws RemoteException;
    void showHistory(User to) throws RemoteException;
    void broadcastMsg(User frm, String msg) throws RemoteException;
    List<User> getMembers()throws RemoteException;
    void exit(User user)throws RemoteException;
}
