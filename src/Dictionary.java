import org.json.JSONArray;

import java.util.ArrayList;

public class Dictionary {

    JSONArray dictionary;

    public Dictionary () {
        this.dictionary = ParseJson.readFromJsonFile("dictionary.json");
    }

    //Ready for level setting..
    public JSONArray limitWordLength(int lowlimit, int highLimit) {
        JSONArray list = new JSONArray();
        for(Object s: dictionary){
            String string = s.toString();
            if(string.length() <= highLimit && string.length() >= lowlimit ) {
                list.put(string);
            }
        }
        return list;
    }

    public String getWord(){
        return dictionary.getString((int) Math.round(Math.random() * (dictionary.length() - 1)));
    }

}

