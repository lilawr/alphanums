
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private Scanner scanner = new Scanner(System.in);
    private AlphaBet alphabet = new AlphaBet();
    private Dictionary dictionary = new Dictionary();

    private long bestTime = 0;
    private int highScore = 0;
    private int score = 0;
    private int gameSetting = 0;

    public void start(){
        boolean quit = false;
        int choice = 0;
        Instructions.menu();

        while (!quit) {
                Instructions.enterChoice();
                choice = scanner.nextInt();
                scanner.nextLine();
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
                        Timer timer = new Timer();
                        try {
                            play60Seconds(timer);
                        }catch(InterruptedException e) {
                            timer.cancel();
                        }
                        break;
                    case 7:
                        quit = true;
                        scanner.close();
                        System.out.println("GoodBye!");
                        System.exit(0);
                }
        }
    }

    private void convertToLetter() {
        Instructions.enterNumberToConvert();
        if(!scanner.hasNextInt()) {
            String word = scanner.nextLine();
            if(Utils.isEnd(word)) {
                Instructions.menu();
                return;
            }
            convertToLetter();
            return;
        }
        int letterIndex = scanner.nextInt();
        System.out.println(alphabet.getLetter(letterIndex));
    }

    private void converter(){
        Instructions.enterWord();
        String word = scanner.nextLine();
        if(Utils.isEnd(word)) {
            Instructions.menu();
            return;
        }
        String cleaned = Utils.cleanUnsupported(word);
        System.out.println(alphabet.getNumberWord(cleaned));
    }

    private void playWithTimer() {
        String word = dictionary.getWord();
        long startTime = System.currentTimeMillis();
        System.out.println("%%%----------- CLOCK STARTED -------%%%");
        boolean resp = play(word);
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

    private void play60Seconds(Timer timer) throws InterruptedException {
        final Runnable playTask = new Runnable() {
            public void run() {
                score = 0;
                try{
                    playWithDictionaryTimed();
                } catch(InterruptedException e) {
                    Instructions.endGame();
                }
            }
        };

        final Thread t = new Thread(playTask);

        TimerTask closeGame = new TimerTask()
        {
            public void run()
            {
                t.interrupt();
                System.out.println("");
                Instructions.breakLine();
                System.out.println("You did: " + score + " in 60 seconds");
                if(score > highScore) {
                    System.out.println("You broke your record of " + highScore);
                    highScore = score;
                }
                Instructions.breakLine();
            }
        };

        t.start();
        timer.schedule(closeGame, 60*1000);
        Thread.sleep(60200);
    }


    private void playTest() {
        Instructions.enterWord();
        String word = scanner.nextLine();
        boolean keepGoing = false;
        if(Utils.isEnd(word)) {
            Instructions.endGame();
            Instructions.menu();
        } else {
            String cleaned = Utils.cleanUnsupported(word);
            keepGoing = play(cleaned);
        }
        if(keepGoing) playTest();
    }
    private void playWithDictionaryTimed() throws InterruptedException {
        boolean keepGoing = false;
        String word = dictionary.getWord();
        keepGoing = playThread(word);
        if(keepGoing) {
            score ++;
            playWithDictionaryTimed();
        }
    }
    private void playWithDictionary(){
        boolean keepGoing = false;
        String word = dictionary.getWord();
        keepGoing = play(word);
        if(keepGoing) {
            playWithDictionary();
        }
    }

    private boolean play(String word) {
        Instructions.word(word);
        Instructions.playResponse();
        String response = scanner.nextLine();
        String answer = alphabet.getNumberWord(word);

        if(Utils.isEnd(response)) {
            Instructions.endGame();
            Instructions.menu();
            return false;
        }

        if (Utils.isAnswer(response)) {
            Instructions.showAnswer(answer);
            return false;
        }

        if (Utils.cleanSpaces(answer).compareTo(Utils.cleanSpaces(response)) == 0) {
            Instructions.correct();
            return true;
        }

        Instructions.wrong(word, response);
        return play(word);
    }

    private boolean playThread(String word) throws InterruptedException {
        Instructions.word(word);
        Instructions.playResponse();
        ConsoleInput console = new ConsoleInput(1,60);

        String response = console.readLine();
        String answer = alphabet.getNumberWord(word);

        if(Utils.isEnd(response)) {
            Instructions.endGame();
            Instructions.menu();
            return false;
        }

        if (Utils.isAnswer(response)) {
            Instructions.showAnswer(answer);
            return false;
        }

        if (Utils.cleanSpaces(answer).compareTo(Utils.cleanSpaces(response)) == 0) {
            Instructions.correct();
            return true;
        }

        Instructions.wrong(word, response);
        return playThread(word);
    }
}
