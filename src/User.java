import java.rmi.Remote;
import java.rmi.RemoteException;

public interface User extends Remote {
   String getName() throws RemoteException;
   void joined(String msg) throws RemoteException;
   void left(String msg) throws RemoteException;
   void talk(String who, String msg) throws RemoteException;
}
