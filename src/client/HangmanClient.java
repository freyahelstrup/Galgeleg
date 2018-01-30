package client;

import logic.LogicI;

import java.rmi.Naming;
import java.util.Scanner;

public class HangmanClient {

    public static void main(String[] args) throws Exception {

        LogicI logic = (LogicI) Naming.lookup("rmi://localhost:1099/hangmanlogic");
        logic.resetGame();

        logic.fetchWordsFromDR();
        logic.resetGame();

        Scanner scanner = new Scanner(System.in);

        while (!logic.hasGameEnded()) {
            System.out.println("Ordet er: " + logic.getVisibleWord());
            System.out.print("Gæt et bogstav: ");
            String guess = scanner.next();

            logic.guessLetter(guess);

            System.out.println("Bogstavet var " + (logic.isLastLetterCorrect() ? "korrekt" : "forkert"));
            System.out.println("----------------------");
        }

        if (logic.isGameWon()) {
            System.out.println("Tillykke, du gættede ordet!");
        } else {
            System.out.println("Desværre, du gættede ikke ordet");
        }

        System.out.println("Ordet var: " + logic.getWord());
    }
}
