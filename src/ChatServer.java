import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer {
    public static void main(String[] args) {
        try {
            // Create a Chat remote object
            ChatImp chat = new ChatImp();
            Chat chat_stub = (Chat) UnicastRemoteObject.exportObject(chat, 0);

            // Register the remote object in RMI registry with a given identifier
            Registry registry;
            if(args.length > 0)
                registry = LocateRegistry.getRegistry(args[0]);
            else
                registry =  LocateRegistry.getRegistry();

            registry.rebind("ChatService", chat_stub);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Error on server :" + e);
            e.printStackTrace();
        }
    }
}
