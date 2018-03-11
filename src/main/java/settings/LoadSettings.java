package settings;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadSettings {

    public static Settings load() throws IOException {
        String settingsJSON = new String(Files.readAllBytes(Paths.get("files/settings")), Charset.defaultCharset());
        return JSON.parseObject(settingsJSON, Settings.class);
    }
}
