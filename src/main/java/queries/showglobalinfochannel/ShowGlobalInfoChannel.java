package queries.showglobalinfochannel;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import queries.showglobalinfochannel.Channel.Channel;
import settings.Settings;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShowGlobalInfoChannel {
    private String idChannel;
    private Channel channel;
    private Settings settings;

    public ShowGlobalInfoChannel(String idChannel, Settings settings) throws IOException, UnirestException {
        this.idChannel = idChannel;
        this.settings = settings;
        searchInCacheAndLoad();
    }

    private void saveToCache(){
        String settingsJSON = JSON.toJSONString(channel);
        try (PrintWriter out = new PrintWriter(settings.getPathToCache() + "channel" + idChannel)) {
            out.println(settingsJSON);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Boolean searchInCacheAndLoad() throws IOException, UnirestException {
        File dir = new File(settings.getPathToCache() + "channel" + idChannel);

        if (!dir.exists()) {
            System.out.println("Either dir does not exist or is not a directory");
            parseJSON();
            if (settings.getSaveCache().equals(true)){
                saveToCache();
            }
            return false;
        } else {
            String settingsJSON = new String(Files.readAllBytes(Paths.get(settings.getPathToCache() + "channel" + idChannel)), Charset.defaultCharset());
            channel = JSON.parseObject(settingsJSON, Channel.class);
            return true;
        }
    }

    private void parseJSON() throws UnirestException {
        String response = YouTubeAPI.channel(idChannel);
        channel = JSON.parseObject(response, Channel.class);
    }

    @Override
    public String toString() {
        return "Имя канала " + getTitle() +
                "\nДата создания канала " + getPublishedAt() +
                "\nКол-во подписчиков " + getSubscriberCount() +
                "\nКол-во видео на канале " + getVideoCount() +
                "\nКол-во просмотров всех видео " + getViewCount();
    }

    public String getIdChannel() {
        return idChannel;
    }

    public String getTitle() {
        return channel.getTitle();
    }

    public String getPublishedAt() {
        return channel.getPublishedAt();
    }

    public Long getSubscriberCount() {
        return channel.getSubscriberCount();
    }

    public Long getVideoCount() {
        return channel.getVideoCount();
    }

    public Long getViewCount() {
        return channel.getViewCount();
    }
}