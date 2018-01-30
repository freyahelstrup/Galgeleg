package client;

import logic.HangmanLogic;

public class HangmanClient {

  public static void main(String[] args) {

    HangmanLogic spil = new HangmanLogic();
    spil.reset();

    try {
      spil.fetchWordsFromDR();
    } catch (Exception e) {
      e.printStackTrace();
    }
    spil.logStatus();

    spil.guessLetter("e");
    spil.logStatus();

    spil.guessLetter("a");
    spil.logStatus();
    System.out.println("" + spil.getWrongLettersCounter());
    System.out.println("" + spil.getVisibleWord());
    if (spil.hasGameEnded()) return;

    spil.guessLetter("i");
    spil.logStatus();
    if (spil.hasGameEnded()) return;

    spil.guessLetter("s");
    spil.logStatus();
    if (spil.hasGameEnded()) return;

    spil.guessLetter("r");
    spil.logStatus();
    if (spil.hasGameEnded()) return;

    spil.guessLetter("l");
    spil.logStatus();
    if (spil.hasGameEnded()) return;

    spil.guessLetter("b");
    spil.logStatus();
    if (spil.hasGameEnded()) return;

    spil.guessLetter("o");
    spil.logStatus();
    if (spil.hasGameEnded()) return;

    spil.guessLetter("t");
    spil.logStatus();
    if (spil.hasGameEnded()) return;

    spil.guessLetter("n");
    spil.logStatus();
    if (spil.hasGameEnded()) return;

    spil.guessLetter("m");
    spil.logStatus();
    if (spil.hasGameEnded()) return;

    spil.guessLetter("y");
    spil.logStatus();
    if (spil.hasGameEnded()) return;

    spil.guessLetter("p");
    spil.logStatus();
    if (spil.hasGameEnded()) return;

    spil.guessLetter("g");
    spil.logStatus();
    if (spil.hasGameEnded()) return;
  }
}
