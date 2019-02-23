public class Utils {

    public static String ANSWER = "answer";
    public static String END = "end";

    public static String cleanSpaces(String string) {
        return string.replace(" ", "");
    }

    public static boolean  isEnd (String word) {
        return Utils.cleanSpaces(word).compareTo(END) == 0;
    }

    public static boolean isAnswer (String word) {
        return Utils.cleanSpaces(word).compareTo(ANSWER) == 0;
    }


}
