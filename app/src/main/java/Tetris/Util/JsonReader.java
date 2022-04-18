package Tetris.Util;

import Tetris.Model.scoreboard.ScoreModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonReader {
    // current setting json file path
    private static final String SETTING_FILEPATH = "app/src/main/resources/json/CurrentSetting.json";
    // current scoreboard json file path
    private static final String SCOREBOARD_FILEPATH = "app/src/main/resources/json/Scoreboard.json";

    private static JSONParser jsonParser = new JSONParser();

    private static Object getJson(JsonFileType type, String key) {
        JSONObject jsonObject = new JSONObject();
        try {
            switch (type){
                case SETTING:
                    FileReader reader = new FileReader(SETTING_FILEPATH);
                    jsonObject = (JSONObject) jsonParser.parse(reader);
                    reader.close();
                    break;
                case SCORE_BOARD:
                    reader = new FileReader(SCOREBOARD_FILEPATH);
                    jsonObject = (JSONObject) jsonParser.parse(reader);
                    reader.close();
                    break;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject.get(key);
    }
    // 데이터 가져오기.
    public static int getWidth(){
        JSONObject obj = (JSONObject) getJson(JsonFileType.SETTING, SettingJsonKeyType.RESOLUTION.getKey());
        return Integer.parseInt(obj.get(SettingJsonKeyType.WIDTH.getKey()).toString());
    }
    public static int getHeight(){
        JSONObject obj = (JSONObject) getJson(JsonFileType.SETTING, SettingJsonKeyType.RESOLUTION.getKey());
        return Integer.parseInt(obj.get(SettingJsonKeyType.HEIGHT.getKey()).toString());
    }
    public static int getFontSize(){
        JSONObject obj = (JSONObject) getJson(JsonFileType.SETTING, SettingJsonKeyType.RESOLUTION.getKey());
        return Integer.parseInt(obj.get(SettingJsonKeyType.FONT_SIZE.getKey()).toString());
    }
    public static int getKey(BlockEventType type){
        JSONObject obj = (JSONObject) getJson(JsonFileType.SETTING, "key");
        return Integer.parseInt(obj.get(type.getKey()).toString());
    }
    public static Map<String, Integer> getKeyMap(){
        JSONObject obj = (JSONObject) getJson(JsonFileType.SETTING,SettingJsonKeyType.KEY.getKey());
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(Object keyObj: obj.keySet()){
            String key = keyObj.toString();
            int value = Integer.parseInt(obj.get(key).toString());
            hashMap.put(key, value);
        }
        return hashMap;
    }
    public static String getColorMode(){
        return getJson(JsonFileType.SETTING, SettingJsonKeyType.COLOR_MODE.getKey()).toString();
    }
    public static String getDifficulty(){
        return getJson(JsonFileType.SETTING, SettingJsonKeyType.DIFFICULTY.getKey()).toString();
    }
    public static List<ScoreModel> getScoreBoard(ScoreboardJsonKeyType type) {
        JSONArray jsonArray = (JSONArray) getJson(JsonFileType.SCORE_BOARD, type.getKey());
        List<ScoreModel> scoreboard = new ArrayList<>();
        for(Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject)obj;
            int score = Integer.parseInt(jsonObject.get(ScoreboardJsonKeyType.SCORE.getKey()).toString());
            String name = jsonObject.get(ScoreboardJsonKeyType.NAME.getKey()).toString();
            scoreboard.add(new ScoreModel(score, name));
        }
        return scoreboard;
    }
}
