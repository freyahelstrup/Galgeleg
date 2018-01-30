package server;

import logic.LogicI;
import logic.LogicImpl;

import java.rmi.Naming;

public class HangmanServer {

    public static void main(String[] args) throws Exception {
        java.rmi.registry.LocateRegistry.createRegistry(1099);
        LogicI logic = new LogicImpl();
        Naming.rebind("rmi://localhost/hangmanlogic", logic);
        System.out.println("Server started.");
    }
}
