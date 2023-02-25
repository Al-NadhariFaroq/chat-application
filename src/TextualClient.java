import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;
import java.util.List;


public class TextualClient implements User, Serializable {
    String name;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Client <rmiregistry host>");
            System.exit(1);
        }

        try {
            String host = args[0];
            Registry registry = LocateRegistry.getRegistry(host);

            Chat chat = (Chat) registry.lookup("ChatService");
            if(chat == null){
                System.out.println("Service not found!");
                System.exit(1);
            }
            TextualClient user = new TextualClient();
            UnicastRemoteObject.exportObject(user, 0);


            String help= "Welcome to RMI Chat\n" +
                         " Type one of the commands below\n" +
                         ":join       : to join the chat\n" +
                         ":exit       : to leave the chat\n" +
                         ":help       : to show this message\n" +
                         ":members    : to show chat members\n" +
                         ":shutdown   : to exit this program\n" +
                         "------------------------------------\n";

            /* main loop*/
            BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(help);
            boolean joined =  false;
            while (true) {
                if(!joined)
                    System.out.print("\033[0;32m" +"cmd" +  "\033[0m" + ">" );
                String msg = inReader.readLine();
                switch(msg){
                    case ":join":
                        if(!joined){
                            System.out.print("Choose pseudo:");
                            String pseudo = inReader.readLine();
                            while ( !chat.nameAvail(pseudo)){
                                System.out.print(pseudo + " already taken! Choose another one:");
                                pseudo = inReader.readLine();
                            }
                            user.setName(pseudo);
                            chat.join(user);
                            if(chat.historyAvail()){
                                System.out.print("Chat history was found,show previous messages? yes/no : ");
                                String showHist = inReader.readLine();
                                if(showHist.equalsIgnoreCase("yes"))
                                    chat.showHistory(user);
                            }
                            joined = true;
                        }
                        break;
                    case ":exit":
                        if(joined){
                            chat.exit(user);
                            joined = false;
                        }
                        break;
                    case ":help":
                        System.out.println(help);
                        break;
                    case ":members":
                        if(joined){
                        List<User> members = chat.getMembers();
                       for(User mem : members)
                            System.out.println("- " + mem.getName());}
                        else{
                            System.out.println("You need to join the chat to see users");
                        }
                        break;
                    case ":shutdown":
                        if(joined)
                            chat.exit(user);
                        inReader.close();
                        UnicastRemoteObject.unexportObject(user,false);
                        System.exit(0);
                        break;
                    default:
                        if (joined && msg.trim().length() > 0)
                            chat.broadcastMsg(user, msg);
                        break;

                }
            }
        } catch (Exception e) {
            System.err.println("Error on client: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    @Override
    public void joined(String msg)  {
        System.out.println("\033[0;36m"   + msg + "\033[0m");
    }

    @Override
    public void left(String msg)  {
        System.out.println( "\033[0;36m" + msg+ "\033[0m");
    }

    @Override
    public void talk(String who, String msg)  {
        System.out.println("\033[0;32m" +  who + "\033[0m" + ": " + msg);
    }
}
