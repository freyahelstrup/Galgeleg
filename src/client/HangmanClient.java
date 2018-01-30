package client;

import logic.LogicI;

import java.rmi.Naming;

public class HangmanClient {

  public static void main(String[] args) throws Exception {

    LogicI logic = (LogicI) Naming.lookup("rmi://localhost:1099/hangmanlogic");
    logic.resetGame();

    try {
      logic.fetchWordsFromDR();
    } catch (Exception e) {
      e.printStackTrace();
    }
    logic.logStatus();

    logic.guessLetter("e");
    logic.logStatus();

    logic.guessLetter("a");
    logic.logStatus();
    System.out.println("" + logic.getWrongLettersCounter());
    System.out.println("" + logic.getVisibleWord());
    if (logic.hasGameEnded()) return;

    logic.guessLetter("i");
    logic.logStatus();
    if (logic.hasGameEnded()) return;

    logic.guessLetter("s");
    logic.logStatus();
    if (logic.hasGameEnded()) return;

    logic.guessLetter("r");
    logic.logStatus();
    if (logic.hasGameEnded()) return;

    logic.guessLetter("l");
    logic.logStatus();
    if (logic.hasGameEnded()) return;

    logic.guessLetter("b");
    logic.logStatus();
    if (logic.hasGameEnded()) return;

    logic.guessLetter("o");
    logic.logStatus();
    if (logic.hasGameEnded()) return;

    logic.guessLetter("t");
    logic.logStatus();
    if (logic.hasGameEnded()) return;

    logic.guessLetter("n");
    logic.logStatus();
    if (logic.hasGameEnded()) return;

    logic.guessLetter("m");
    logic.logStatus();
    if (logic.hasGameEnded()) return;

    logic.guessLetter("y");
    logic.logStatus();
    if (logic.hasGameEnded()) return;

    logic.guessLetter("p");
    logic.logStatus();
    if (logic.hasGameEnded()) return;

    logic.guessLetter("g");
    logic.logStatus();
    if (logic.hasGameEnded()) return;
  }
}
