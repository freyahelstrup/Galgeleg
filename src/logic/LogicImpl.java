package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class LogicImpl extends UnicastRemoteObject implements LogicI {
  /** AHT afprøvning er possibleWords synlig på pakkeniveau */
  ArrayList<String> possibleWords = new ArrayList<String>();
  private String word;
  private ArrayList<String> usedLetters = new ArrayList<String>();
  private String visibleWord;
  private int wrongLettersCounter;
  private boolean isLastLetterCorrect;
  private boolean isGameWon;
  private boolean isGameLost;

  @Override
  public ArrayList<String> getUsedLetters() {
    return usedLetters;
  }

  @Override
  public String getVisibleWord() {
    return visibleWord;
  }

  @Override
  public String getWord() {
    return word;
  }

  @Override
  public int getWrongLettersCounter() {
    return wrongLettersCounter;
  }

  @Override
  public boolean isLastLetterCorrect() {
    return isLastLetterCorrect;
  }

  @Override
  public boolean isGameWon() {
    return isGameWon;
  }

  @Override
  public boolean isGameLost() {
    return isGameLost;
  }

  @Override
  public boolean hasGameEnded() {
    return isGameLost || isGameWon;
  }


  public LogicImpl() throws java.rmi.RemoteException {
    possibleWords.add("bil");
    possibleWords.add("computer");
    possibleWords.add("programmering");
    possibleWords.add("motorvej");
    possibleWords.add("busrute");
    possibleWords.add("gangsti");
    possibleWords.add("skovsnegl");
    possibleWords.add("solsort");
    possibleWords.add("seksten");
    possibleWords.add("sytten");
    resetGame();
  }

  @Override
  public void resetGame() {
    usedLetters.clear();
    wrongLettersCounter = 0;
    isGameWon = false;
    isGameLost = false;
    word = possibleWords.get(new Random().nextInt(possibleWords.size()));
    updateVisibleWord();
  }


  private void updateVisibleWord() {
    visibleWord = "";
    isGameWon = true;
    for (int n = 0; n < word.length(); n++) {
      String bogstav = word.substring(n, n + 1);
      if (usedLetters.contains(bogstav)) {
        visibleWord = visibleWord + bogstav;
      } else {
        visibleWord = visibleWord + "*";
        isGameWon = false;
      }
    }
  }

  @Override
  public void guessLetter(String letter) {
    if (letter.length() != 1) return;
    System.out.println("Der gættes på bogstavet: " + letter);
    if (usedLetters.contains(letter)) return;
    if (isGameWon || isGameLost) return;

    usedLetters.add(letter);

    if (word.contains(letter)) {
      isLastLetterCorrect = true;
      System.out.println("Bogstavet var korrekt: " + letter);
    } else {
      // Vi gættede på et letter der ikke var i word.
      isLastLetterCorrect = false;
      System.out.println("Bogstavet var IKKE korrekt: " + letter);
      wrongLettersCounter = wrongLettersCounter + 1;
      if (wrongLettersCounter > 6) {
        isGameLost = true;
      }
    }
    updateVisibleWord();
  }

  @Override
  public void logStatus() {
    System.out.println("---------- ");
    System.out.println("- ordet (skjult) = " + word);
    System.out.println("- synligtOrd = " + visibleWord);
    System.out.println("- forkerteBogstaver = " + wrongLettersCounter);
    System.out.println("- brugteBogstaver = " + usedLetters);
    if (isGameLost) System.out.println("- SPILLET ER TABT");
    if (isGameWon) System.out.println("- SPILLET ER VUNDET");
    System.out.println("---------- ");
  }


  public static String fetchUrl(String url) throws IOException {
    System.out.println("Henter data fra " + url);
    BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
    StringBuilder sb = new StringBuilder();
    String linje = br.readLine();
    while (linje != null) {
      sb.append(linje + "\n");
      linje = br.readLine();
    }
    return sb.toString();
  }

  @Override
  public void fetchWordsFromDR() throws Exception {
    String data = fetchUrl("https://dr.dk");
    //System.out.println("data = " + data);

    data = data.substring(data.indexOf("<body")). // fjern headere
            replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
            replaceAll("&#198;", "æ"). // erstat HTML-tegn
            replaceAll("&#230;", "æ"). // erstat HTML-tegn
            replaceAll("&#216;", "ø"). // erstat HTML-tegn
            replaceAll("&#248;", "ø"). // erstat HTML-tegn
            replaceAll("&oslash;", "ø"). // erstat HTML-tegn
            replaceAll("&#229;", "å"). // erstat HTML-tegn
            replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
            replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
            replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

    System.out.println("data = " + data);
    System.out.println("data = " + Arrays.asList(data.split("\\s+")));
    possibleWords.clear();
    possibleWords.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

    System.out.println("possibleWords = " + possibleWords);
    resetGame();
  }
}
