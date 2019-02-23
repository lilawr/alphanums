import java.util.HashMap;

public class AlphaBet {
    private HashMap<Character, Integer> list;
    private char[] alphabet ;

    public AlphaBet () {
        alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        setUpAlphaBet();
    }

    public void setUpAlphaBet(){
        this.list = new HashMap<>();
        for (int i = 0; i<26; i++) {
            this.list.put(this.alphabet[i], i+1);
        }
    }


    public String getNumberWord (String word){
        char[] chars = word.toLowerCase().toCharArray();
        String stringNum = "";
        for(char c : chars) {
            stringNum += list.get(c) + " ";
        }
        return stringNum;
    }

    public char getLetter (int index){
        if(index < 1 || index > alphabet.length) return '?';
        return alphabet[index -1];
    }
}
