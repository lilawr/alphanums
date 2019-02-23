import org.json.JSONArray;

import java.util.Scanner;

public class Game {

    private Scanner scanner = new Scanner(System.in);
    private AlphaBet alphabet = new AlphaBet();
    private long bestTime = 0;

    public void start() {
        start(false, 0);
    }

    public void start(boolean automatic, int autoChoice){
        boolean quit = false;
        int choice = 0;

        if(!automatic) {
            Instructions.menu();
        } else {
            choice = autoChoice;
        }

        while (!quit) {
            if(!automatic) {
                Instructions.enterChoice();
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                automatic = false;
            }
            switch (choice) {
                case 0:
                    Instructions.menu();
                    break;
                case 1:
                    converter();
                    break;
                case 2:
                    convertToLetter();
                    break;
                case 3:
                    playTest();
                    break;
                case 4:
                    playWithDictionary();
                    break;
                case 5:
                    playWithTimer();
                    break;
                case 6:
                    quit = true;
                    scanner.close();
                    break;
            }
        }
    }

    private void convertToLetter() {
        Instructions.enterNumberToConvert();
        if(!scanner.hasNextInt()) {
            String word = scanner.nextLine();
            if(word.compareTo(Instructions.END) == 0) {
                Instructions.menu();
                return;
            }
            convertToLetter();
            return;
        }
        int letterIndex = scanner.nextInt();
        System.out.println(alphabet.getLetter(letterIndex));
    }

    public void converter(){
        Instructions.enterWord();
        String word = scanner.nextLine();
        if(word.compareTo(Instructions.END) == 0) {
            Instructions.menu();
            return;
        }
        System.out.println(alphabet.getNumberWord(word));
    }

    private void playWithTimer() {
        JSONArray dictionary = ParseJson.readFromJsonFile("src/dictionary.json");
        String word = dictionary.getString((int) Math.round(Math.random() * (dictionary.length() - 1)));
        long startTime = System.currentTimeMillis();
        System.out.println("%%%----------- TIMER STARTED -------%%%");
        boolean resp = play(word, 5);
        if (!resp){ return; }

        long endTime = System.currentTimeMillis();
        long time = (endTime - startTime)/1000;

        if(bestTime > time) {
            System.out.println("You broke your record of " + bestTime);
            bestTime = time;
        }

        System.out.println("That took " + (endTime - startTime)/1000 + " seconds");
        if(bestTime != 0) System.out.println("Your best time is: " + bestTime);
    }


    public void playTest() {
        Instructions.enterWord();
        String word = scanner.nextLine();
        play(word, 3);
    }

    public void playWithDictionary(){
        JSONArray dictionary = ParseJson.readFromJsonFile("src/dictionary.json");
        String word = dictionary.getString((int) Math.round(Math.random() * (dictionary.length() - 1)));
        play(word, 4);
    }

    public boolean play(String word, int option) {
        Instructions.word(word);
        Instructions.playResponse();
        String response = scanner.nextLine();
        String answer = alphabet.getNumberWord(word);

        if(response.compareTo(Instructions.END) == 0) {
            Instructions.endGame();
            Instructions.menu();
            return false;
        }

        if (response.compareTo(Instructions.ANSWER) == 0) {
            Instructions.showAnswer(answer);
            if(option != 5) {
                start(true, option);
            }
            return false;
        }

        if (alphabet.cleanSpaces(answer).compareTo(alphabet.cleanSpaces(response)) == 0) {
            Instructions.correct();
            if(option != 5) {
                start(true, option);
            }
            return true;
        }

        Instructions.wrong(word, response);
        play(word, option);
        return false;
    }
}

