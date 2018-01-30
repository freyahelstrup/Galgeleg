package logic;

import java.rmi.RemoteException;
import java.util.List;

public interface LogicI extends java.rmi.Remote {

    List<String> getUsedLetters() throws RemoteException;

    String getVisibleWord() throws RemoteException;

    String getWord() throws RemoteException;

    int getWrongLettersCounter() throws RemoteException;

    boolean isLastLetterCorrect() throws RemoteException;

    boolean isGameWon() throws RemoteException;

    boolean isGameLost() throws RemoteException;

    boolean hasGameEnded() throws RemoteException;

    void resetGame() throws RemoteException;

    void guessLetter(String letter) throws RemoteException;

    void logStatus() throws RemoteException;

    void fetchWordsFromDR() throws Exception;

}
