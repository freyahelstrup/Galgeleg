package client;

import logic.LogicI;

import java.rmi.Naming;
import java.util.Scanner;

public class HangmanClient {

    public static void main(String[] args) throws Exception {

        LogicI logic = (LogicI) Naming.lookup("rmi://localhost:1099/hangmanlogic");
        logic.fetchWordsFromDR();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1 Spil Galgeleg");
            System.out.println("2 Afslut programmet");

            String enteredValue;
            do {
                System.out.print("Skriv valg: ");
                enteredValue = scanner.next();
            } while (!enteredValue.matches("[12]"));

            int menuItem = Integer.parseInt(enteredValue);

            if (menuItem == 1) {
                logic.resetGame();

                while (true) {
                    System.out.println("Ordet er: " + logic.getVisibleWord().toUpperCase());

                    String guess;
                    do {
                        System.out.print("Skriv et bogstav: ");
                        guess = scanner.next();
                        guess = guess.toLowerCase();
                    } while (guess.length() != 1 || !guess.matches("[a-zæøå]"));

                    logic.guessLetter(guess);

                    if (logic.hasGameEnded())
                        break;

                    System.out.println("----------------------");
                    System.out.println("Bogstavet var " + (logic.isLastLetterCorrect() ? "korrekt" : "forkert") + ".");
                    System.out.println("Du har nu " + (7 - logic.getWrongLettersCounter()) + " gæt tilbage.");
                }

                System.out.println("----------------------\n");
                if (logic.isGameWon()) {
                    System.out.println("Tillykke, du gættede ordet!");
                } else {
                    System.out.println("Desværre, du gættede ikke ordet");
                }

                System.out.println("Ordet var: " + logic.getWord().toUpperCase());
            }

            else if (menuItem == 2)
                System.exit(0);
        }
    }
}
