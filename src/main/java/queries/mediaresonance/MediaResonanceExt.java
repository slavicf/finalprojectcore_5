package queries.mediaresonance;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import queries.mediaresonance.channel.Channel;
import queries.mediaresonance.search.Search;
import queries.mediaresonance.videos.Statistics;
import queries.mediaresonance.videos.Video;
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
import java.util.ArrayList;

public class MediaResonanceExt extends ShowGlobalInfoChannel {
    private Channel channel = new Channel();
    String path;

    public MediaResonanceExt(String idChannel, Settings settings) throws IOException, UnirestException {
        super(idChannel, settings);
        this.channel.items = super.channel.items;
        searchInCacheAndLoad();
    }

    private void searchInCacheAndLoad() throws IOException, UnirestException {
        path = settings.getPathToCache() + "comments" + idChannel;
        File file = new File(path);

        if (!file.exists()) {
//            System.out.println("Either dir does not exist or is not a directory");
            commentCount();
            if (settings.getSaveCache().equals(true)){
                saveToCache();
            }
        } else {
            String settingsJSON = new String(Files.readAllBytes(Paths.get(path)), Charset.defaultCharset());
            channel = JSON.parseObject(settingsJSON, Channel.class);
        }
    }

    private void saveToCache(){
        String settingsJSON = JSON.toJSONString(channel);
        try (PrintWriter out = new PrintWriter(path)) {
            out.println(settingsJSON);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void commentCount() throws UnirestException {
        int maxResults = 50;
        Statistics statistics = new Statistics();
        String response;
        String pageToken = "";
        String query = "";
        Video video;

        do {
            response = YouTubeAPI.search(idChannel, query, maxResults, pageToken);
            Search search = JSON.parseObject(response, Search.class);
            pageToken = search.nextPageToken;
            ArrayList<String> items = new ArrayList<>();
//
//            for (int i = 0; i < results; i++) {
//                if (search.items[i].id.videoId != null) {
//                    items.add(search.items[i].id.videoId);
//                } else System.out.println(index + " skipped");
//            }
//
            for (queries.mediaresonance.search.Item searchItem : search.items) {
                if (searchItem.id.videoId != null) {
                    items.add(searchItem.id.videoId);
                }
            }

            String temp = items.toString();
            String videoID = temp.substring(1, temp.length() - 1);

            response = YouTubeAPI.videos(videoID);
            video = JSON.parseObject(response, Video.class);
            for (queries.mediaresonance.videos.Item videoItem: video.items) {
                statistics.commentCount += videoItem.statistics.commentCount;
            }
        } while (pageToken != null);
        channel.totalCommentCount = statistics.commentCount;
        System.out.println(channel.totalCommentCount);
    }

    public long gettotalCommentCount() {
        return channel.totalCommentCount;
    }

    @Override
    public String toString() {
        return super.toString() + "\nВсего комментариев: " + channel.totalCommentCount;
    }
}
