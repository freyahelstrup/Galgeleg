package server;

import logic.LogicImpl;

import java.rmi.Naming;

public class HangmanServer {

    public static void main(String[] args) throws Exception {
        java.rmi.registry.LocateRegistry.createRegistry(1099);
        LogicImpl logic = new LogicImpl();
        Naming.rebind("rmi://localhost/hangmanlogic", logic);
        System.out.println("Server started.");
    }
}
