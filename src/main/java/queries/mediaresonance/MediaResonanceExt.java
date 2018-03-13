package queries.mediaresonance;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import queries.mediaresonance.channel.Channel;
import queries.showglobalinfochannel.ShowGlobalInfoChannel;
import queries.showglobalinfochannel.YouTubeAPI;
import settings.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MediaResonanceExt extends ShowGlobalInfoChannel {
    private String idChannel;
    private Channel channel;
    private Settings settings;

    public MediaResonanceExt(String idChannel, Settings settings) throws IOException, UnirestException {
        this.idChannel = idChannel;
        this.settings = settings;
        searchInCacheAndLoad();
    }

    private void searchInCacheAndLoad() throws IOException, UnirestException {
        File dir = new File(settings.getPathToCache() + "channel" + idChannel);

        if (!dir.exists()) {
//            System.out.println("Either dir does not exist or is not a directory");
            parseJSON();
            if (settings.getSaveCache().equals(true)){
                saveToCache();
            }
        } else {
            String settingsJSON = new String(Files.readAllBytes(Paths.get(settings.getPathToCache() + "comments" + idChannel)), Charset.defaultCharset());
            channel = JSON.parseObject(settingsJSON, Channel.class);
        }
    }

    private void saveToCache(){
        String settingsJSON = JSON.toJSONString(channel);
        try (PrintWriter out = new PrintWriter(settings.getPathToCache() + "comments" + idChannel)) {
            out.println(settingsJSON);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parseJSON() throws UnirestException {
        String response = YouTubeAPI.channel(idChannel);
        channel = JSON.parseObject(response, Channel.class);
    }
}
