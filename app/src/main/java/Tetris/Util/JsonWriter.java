package Tetris.Util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonWriter {
    // current setting json file path
    private static final String SETTING_FILEPATH = "app/src/main/resources/json/CurrentSetting.json";
    // default setting json file path
    private static final String DEFAULT_SETTING_FILEPATH = "app/src/main/resources/json/DefaultSetting.json";
    // current scoreboard json file path
    private static final String SCOREBOARD_FILEPATH = "app/src/main/resources/json/Scoreboard.json";
    // default scoreboard json file path
    private static final String DEFAULT_SCOREBOARD_FILEPATH = "app/src/main/resources/json/DefaultScoreboard.json";

    private static JSONParser jsonParser = new JSONParser();

    private static void replaceJson(JsonFileType type, String key, Object value){
        FileReader fileReader;
        FileWriter fileWriter;
        try {
            switch (type) {
                case SETTING:
                    fileReader = new FileReader(SETTING_FILEPATH);
                    JSONObject currentSettingJsonObject = (JSONObject) jsonParser.parse(fileReader);

                    HashMap<String, Object> newSetting = new HashMap<>();
                    for (Object keyObj : currentSettingJsonObject.keySet()) {
                        String jsonKey = keyObj.toString();
                        if (Objects.equals(jsonKey, key)) continue;
                        newSetting.put(jsonKey, currentSettingJsonObject.get(jsonKey));
                    }
                    newSetting.put(key, value);

                    JSONObject jsonObject = new JSONObject(newSetting);
                    fileReader.close();

                    fileWriter = new FileWriter(SETTING_FILEPATH);
                    fileWriter.write(jsonObject.toJSONString());
                    fileWriter.flush();
                    fileWriter.close();
                    break;
                case SCORE_BOARD:
                    fileReader = new FileReader(SCOREBOARD_FILEPATH);
                    JSONObject scoreboardJsonObject = (JSONObject) jsonParser.parse(fileReader);

                    JSONObject newScoreboard = new JSONObject();
                    for (Object keyObj : scoreboardJsonObject.keySet()) {
                        String currentKey = keyObj.toString();
                        if (Objects.equals(currentKey, key)) continue;
                        JSONArray jsonArray = (JSONArray) scoreboardJsonObject.get(currentKey);
                        newScoreboard.put(currentKey, jsonArray);
                    }
                    newScoreboard.put(key, value);
                    fileReader.close();

                    fileWriter = new FileWriter(SCOREBOARD_FILEPATH);
                    fileWriter.write(newScoreboard.toJSONString());
                    fileWriter.flush();
                    fileWriter.close();
                    break;
                default:
                    break;
            }
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
    // 기본설정으로 되돌리기.
    public static void setResetSetting() {
        try (
                FileReader reader = new FileReader(DEFAULT_SETTING_FILEPATH);
                FileWriter writer = new FileWriter(SETTING_FILEPATH);
        ){
            JSONObject defaultFile = (JSONObject) jsonParser.parse(reader);
            HashMap<String, Object> hashMap = new HashMap<>();
            for(Object keyObj : defaultFile.keySet()) {
                String key = keyObj.toString();
                hashMap.put(key, defaultFile.get(key));
            }
            JSONObject jsonObject = new JSONObject(hashMap);

            writer.write(jsonObject.toJSONString());
            writer.flush();
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
    // 스코어보드 초기화
    public static void setResetScoreBoard(GameType type) {
        try (
                FileReader fileReader = new FileReader(DEFAULT_SCOREBOARD_FILEPATH);
                FileWriter fileWriter = new FileWriter(SCOREBOARD_FILEPATH);
        ){
            JSONObject scoreboardJsonObject = (JSONObject) jsonParser.parse(fileReader);
            JSONObject newScoreboard = new JSONObject();
            for (Object keyObj : scoreboardJsonObject.keySet()) {
                String currentKey = keyObj.toString();
                if (Objects.equals(currentKey, type.getKey())) continue;
                JSONArray jsonArray = (JSONArray) scoreboardJsonObject.get(currentKey);
                newScoreboard.put(currentKey, jsonArray);
            }
            newScoreboard.put(type.getKey(), scoreboardJsonObject.get(type.getKey()));
            fileWriter.write(newScoreboard.toJSONString());
            fileWriter.flush();
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
    public static void setSize(int width, int height){
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("width", width);
        hashMap.put("height", height);
        replaceJson(JsonFileType.SETTING, SettingJsonKeyType.RESOLUTION.getKey(), hashMap);
    }
    public static void setColorMode(String colorMode) {
        replaceJson(JsonFileType.SETTING, SettingJsonKeyType.COLOR_MODE.getKey(), colorMode);
    }
    public static void setKey(Map<String, Integer> keys){
        replaceJson(JsonFileType.SETTING, SettingJsonKeyType.KEY.getKey(), keys);
    }
    /**
     * 스코어보드 입력하는 메서드
     * @param scoreList 정렬된 스코어리스트
     */
    public static void setScoreBoard(List<Map<String, String>> scoreList, GameType type) {
        replaceJson(JsonFileType.SCORE_BOARD , type.getKey(), scoreList);
    }
}
