package settings;

import com.alibaba.fastjson.JSON;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveSettings {

    public static void save(Settings settings) {
        String settingsJSON = JSON.toJSONString(settings);
        try (PrintWriter out = new PrintWriter("files/settings")) {
            out.println(settingsJSON);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
