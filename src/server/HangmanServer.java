package server;

import logic.HangmanLogic;

import java.rmi.Naming;

public class HangmanServer {

    public static void main(String[] args) throws Exception {
        java.rmi.registry.LocateRegistry.createRegistry(1099);
        HangmanLogic logic = new HangmanLogic();
        Naming.rebind("rmi://localhost/hangmanlogic", logic);
    }
}
