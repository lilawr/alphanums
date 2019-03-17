class Instructions {

    static void menu() {
        breakLine();
        System.out.println("\nOptions Menu: ");
        System.out.println("\t 0 - Print Menu.");
        System.out.println("\t 1 - Convert word to Number");
        System.out.println("\t 2 - Get letter from Number");
        System.out.println("\t 3 - Test yourself.");
        System.out.println("\t 4 - Play");
        System.out.println("\t 5 - Play Reverse");
        System.out.println("\t 6 - Timed play");
        System.out.println("\t 7 - 60 second trial");
        System.out.println("\t 8 - Quit the application.");
        breakLine();
    }

    static void breakLine() {
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    static void enterWord() {
        System.out.println("Enter your word:");
    }

    static void word(String word) {
        breakLine();
        System.out.println("Word To Convert: " + word);
    }

    static void enterChoice() {
        System.out.println("Enter your menu choice: ");
    }

    static void endGame() {
        breakLine();
        System.out.println("Ending Game.");
    }

    static void correct() {
        System.out.println("Correct!! :)");
    }

    static void wrong(String word, String response) {
        System.out.println("Sorry .. try again for word: " + word + ", you entered " + response);
    }

    static void showAnswer(String answer) {
        breakLine();
        System.out.println("The answer is " + answer);
        breakLine();
    }

    static void playResponse() {
        breakLine();
        System.out.println("Please enter your numbers (separated by a space) or (answer to see the reponse, end to close the game): ");
    }


    static void enterNumberToConvert() {
        System.out.println("Enter your number x (0<x<26):");
    }

    public static void playReverseResponse() {
        breakLine();
        System.out.println("Please enter your converted word or (answer to see the reponse, end to close the game): ");
    }

    public static void reverseWord(String numbers) {
        breakLine();
        System.out.println("Numbers To Convert: " + numbers);
    }
}
