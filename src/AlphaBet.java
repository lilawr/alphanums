import java.util.HashMap;

public class AlphaBet {
    private HashMap<Character, Integer> list;
    private char[] alphabet ;

    public AlphaBet () {
        alphabet = " abcdefghijklmnopqrstuvwxyz".toCharArray();
        setUpAlphaBet();
    }

    public void setUpAlphaBet(){
        this.list = new HashMap<>();
        for (int i = 0; i<26; i++) {
            this.list.put(this.alphabet[i], i);
        }
    }


    public String getNumberWord (String word){
        char[] chars = Utils.cleanUnsupported(word).toCharArray();
        String stringNum = "";
        for(char c : chars) {
            if(list.get(c) != null) {
                stringNum += list.get(c) + " ";
            }
        }
        return stringNum;
    }

    public char getLetter (int index){
        if(index < 1 || index > alphabet.length) return '?';
        return alphabet[index -1];
    }
}
