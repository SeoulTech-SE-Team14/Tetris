package Tetris.Util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonReader {
    static final String SETTING_FILEPATH = "app/src/main/java/Tetris/Util/CurrentSetting.json";
    static JSONParser jsonParser = new JSONParser();

    public static Object getJson(JsonType type, String key) {
        JSONObject jsonObject = new JSONObject();
        try {
            switch (type){
                case SETTING:
                    FileReader reader = new FileReader(SETTING_FILEPATH);
                    jsonObject = (JSONObject) jsonParser.parse(reader);
                    reader.close();
                    break;
                case SCORE_BOARD:
                    break;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject.get(key);
    }
    // 데이터 가져오기.
    public static int getWidth(){
        JSONObject obj = (JSONObject) getJson(JsonType.SETTING,"size");
        return Integer.parseInt(obj.get("width").toString());
    }
    public static int getHeight(){
        JSONObject obj = (JSONObject) getJson(JsonType.SETTING,"size");
        return Integer.parseInt(obj.get("height").toString());
    }
    public static int getFontSize(){
        return Integer.parseInt(getJson(JsonType.SETTING, "font_size").toString());
    }
    public static int getKey(KeyEventType key){
        JSONObject obj = (JSONObject) getJson(JsonType.SETTING,"key");
        return Integer.parseInt(obj.get(key.toString().toLowerCase()).toString());
    }
    public static HashMap<String, Integer> getKeyMap(){
        JSONObject obj = (JSONObject) getJson(JsonType.SETTING,"key");
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(Object keyObj: obj.keySet()){
            String key = keyObj.toString();
            int value = Integer.parseInt(obj.get(key).toString());
            hashMap.put(key, value);
        }
        return hashMap;
    }
    public static String getColorMode(){
        return getJson(JsonType.SETTING, "color_mode").toString();
    }
    public static String getDifficulty(){
        return getJson(JsonType.SETTING, "difficulty").toString();
    }
}
