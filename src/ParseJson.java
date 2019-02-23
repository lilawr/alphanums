import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;



public class ParseJson {

    public static JSONArray readFromJsonFile(String fileName){
        try{
            String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
            return new JSONArray(text);
        } catch(Exception ex) {
            System.out.println(ex.toString());
            return  new JSONArray("[Error]");
        }

    }
}
