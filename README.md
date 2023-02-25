- Compile the code by running
```bash
ant 
cd classes
```

- Then, start the rmi registry
```bash
rmiregistry&
```
- or , if you want to use another port
```bash
rmiregistry <port number> &
```

 - Then, start the server on localhost
```bash
java ChatServer
```

- or, if you want to use another host
```bash
java ChatServer <host>
```

- Two version of client exist, textual and graphical
to run the textual version run
```bash
java TextualClient <host>
```

- to run the Graphical version run
```bash
java GraphicalClient <host>
```