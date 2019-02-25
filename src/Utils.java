public class Utils {

    public static String ANSWER = "answer";
    public static String END = "end";

    public static String cleanSpaces(String string) {
        return string.replaceAll(" ", "");
    }

    public static String cleanUnsupported(String string) {
        return string.toLowerCase().replaceAll("[^a-z ]", "").replaceAll("( )+", " ").trim();
    }

    public static boolean  isEnd (String word) {
        return Utils.cleanSpaces(word).compareTo(END) == 0;
    }

    public static boolean isAnswer (String word) {
        return Utils.cleanSpaces(word).compareTo(ANSWER) == 0;
    }


}
