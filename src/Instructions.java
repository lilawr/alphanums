class Instructions {

    public static String ANSWER = "answer";
    public static String END = "end";

    public static void menu() {
        breakLine();
        System.out.println("\nOptions Menu: ");
        System.out.println("\t 0 - Print Menu.");
        System.out.println("\t 1 - Convert word to Number");
        System.out.println("\t 2 - Get letter from Number");
        System.out.println("\t 3 - Test yourself.");
        System.out.println("\t 4 - Play");
        System.out.println("\t 5 - Play time challenge");
        System.out.println("\t 6 - Quit the application.");
        breakLine();
    }
    private static void breakLine() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }
    public static void enterWord() {
        System.out.println("Enter you word:");
    }

    public static void word(String word) {
        breakLine();
        System.out.println("Word To Convert: " + word);
    }

    public static void enterChoice() {
        System.out.println("Enter your choice: ");
    }

    public static void endGame() {
        System.out.println("Ending Game.");
        breakLine();
    }

    public static void correct() {
        System.out.println("Correct!! :)");
    }

    public static void wrong(String word, String response) {
        System.out.println("Sorry .. try again for word: "+ word+", you entered "+response );
    }

    public static void showAnswer(String answer) {
        System.out.println("The answer is " + answer);
        breakLine();
    }

    public static void playResponse() {
        breakLine();
        System.out.println("Please enter your numbers (separated by a space) or (answer to see the reponse, end to close the game): ");
    }

    public static void enterNumberToConvert() {
        System.out.println("Enter your number x (0<x<26):");
    }
}
