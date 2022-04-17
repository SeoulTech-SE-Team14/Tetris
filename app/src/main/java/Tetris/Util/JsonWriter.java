package Tetris.Util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonWriter {
    // current setting json file path
    private static final String CURRENT_SETTING_FILEPATH = "app/src/main/resources/json/CurrentSetting.json";
    // default setting json file path
    private static final String DEFAULT_SETTING_FILEPATH = "app/src/main/resources/json/defaultSetting.json";

    static JSONParser jsonParser = new JSONParser();
    public static void replaceJson(JsonType type, String key, Object value){
        HashMap<String, Object> hashMap = new HashMap<>();
        try{
            if (type == JsonType.SETTING) {
                FileReader currentFileReader = new FileReader(CURRENT_SETTING_FILEPATH);
                JSONObject currentFile = (JSONObject) jsonParser.parse(currentFileReader);
                for (Object keyObj : currentFile.keySet()) {
                    String currentKey = keyObj.toString();
                    if (Objects.equals(currentKey, key)) continue;
                    hashMap.put(currentKey, currentFile.get(currentKey));
                }
                hashMap.put(key, value);
                JSONObject jsonObject = new JSONObject(hashMap);
                currentFileReader.close();
                FileWriter currentFileWriter = new FileWriter(CURRENT_SETTING_FILEPATH);
                currentFileWriter.write(jsonObject.toJSONString());
                currentFileWriter.flush();
                currentFileWriter.close();
            } else if (type == JsonType.SCORE_BOARD) {
                // not yet
            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
    // 기본설정으로 되돌리기.
    public static void setDefaultSetting() {
        try (
                FileReader defaultFileReader = new FileReader(DEFAULT_SETTING_FILEPATH);
                FileWriter currentFileWriter = new FileWriter(CURRENT_SETTING_FILEPATH);
        ){
            JSONObject defaultFile = (JSONObject) jsonParser.parse(defaultFileReader);
            HashMap<String, Object> hashMap = new HashMap<>();
            for(Object keyObj : defaultFile.keySet()) {
                String key = keyObj.toString();
                hashMap.put(key, defaultFile.get(key));
            }
            JSONObject jsonObject = new JSONObject(hashMap);

            currentFileWriter.write(jsonObject.toJSONString());
            currentFileWriter.flush();
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
    public static void setSize(int width, int height){
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("width", width);
        hashMap.put("height", height);
        replaceJson(JsonType.SETTING, "size", hashMap);
    }
    public static void setColorMode(String colorMode) {
        replaceJson(JsonType.SETTING, "color_mode", colorMode);
    }
    public static void setKey(Map<String, Integer> keys){
        replaceJson(JsonType.SETTING, "key", keys);
    }
    public static void setDifficulty(String difficulty){ replaceJson(JsonType.SETTING, "difficulty", difficulty); }

}
