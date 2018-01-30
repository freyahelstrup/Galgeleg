package server;

import logic.HangmanLogic;

import java.rmi.RemoteException;

public class HangmanServer {

    private static HangmanLogic logic;

    public static void main(String[] args) throws RemoteException {
        java.rmi.registry.LocateRegistry.createRegistry(1099);
        logic = new HangmanLogic();
    }

    public void startGame(){
        try {
            logic.fetchWordsFromDR();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logic.reset();
    }

    public void guessLetter(String letter){
        logic.guessLetter(letter);
    }

    public String getVisibleWord(){
        return logic.getVisibleWord();
    }

    public boolean isLastLetterCorrect(){
        return logic.isLastLetterCorrect();
    }

    public boolean isGameOver(){
        return logic.hasGameEnded();
    }

    public boolean isGameWon(){
        return logic.isGameWon();
    }



}
