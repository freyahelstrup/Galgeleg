package logic;

import java.util.List;

public interface LogicI extends java.rmi.Remote {

    List<String> getUsedLetters();

    String getVisibleWord();

    String getWord();

    int getWrongLettersCounter();

    boolean isLastLetterCorrect();

    boolean isGameWon();

    boolean isGameLost();

    boolean hasGameEnded();

    void resetGame();

    void guessLetter(String letter);

    void logStatus();

    void fetchWordsFromDR() throws Exception;

}
