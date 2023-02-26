- Compile the code by running
```bash
ant 
```

- Then, go to directory classes and  start the rmi registry
```bash
cd classes
rmiregistry&
```
- or , if you want to use another port
```bash
rmiregistry <port number> &
```

 - Now, start the server on localhost
```bash
java ChatServer
```

- you can use another host
```bash
java ChatServer <host>
```

- Finally, run the client. <br>
Two versions of client exist textual and graphical<br>
To run the textual version
```bash
java TextualClient <host>
```

- To run the Graphical version
```bash
java GraphicalClient <host>
```