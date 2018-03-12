package queries.mediaresonance;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import queries.mediaresonance.search.Item;
import queries.mediaresonance.search.Search;
import queries.mediaresonance.videos.Statistics;
import queries.mediaresonance.videos.Video;
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

public class MediaResonance {

    private static Settings settings;
    private static String channelId;
    private static Statistics statistics;

    public static Statistics search(String chanId, Settings sett) throws IOException, UnirestException {
        channelId = chanId;
        settings = sett;
        searchInCache();
        return statistics;
    }

    public static void searchInCache() throws UnirestException, IOException {
        File file = new File(settings.getPathToCache() + "comments" + channelId);

        if (!file.exists()) {
            System.out.println("Either dir does not exist or is not a directory");
            statistics = searchInWeb(channelId);
            if (settings.getSaveCache().equals(true)){
                saveToCache();
            }
        } else {
            String statisticsJSON = new String(Files.readAllBytes(Paths.get(settings.getPathToCache() + "comments" + channelId)), Charset.defaultCharset());
            statistics = JSON.parseObject(statisticsJSON, Statistics.class);
        }
    }

    private static void saveToCache(){
        String statisticsJSON = JSON.toJSONString(statistics);
        try (PrintWriter out = new PrintWriter(settings.getPathToCache() + "comments" + channelId)) {
            out.println(statisticsJSON);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Statistics searchInWeb(String channelId) throws UnirestException {
        int maxResults = 50;
        Statistics statistics = new Statistics();
        String response;
        String pageToken = "";
        String query = "";
        Video video;

        do {
            response = YouTubeAPI.search(channelId, query, maxResults, pageToken);
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
            for (Item searchItem : search.items) {
                if (searchItem.id.videoId != null) {
                    items.add(searchItem.id.videoId);
                }
            }

            String temp = items.toString();
            String videoID = temp.substring(1, temp.length() - 1);

            response = YouTubeAPI.videos(videoID);
            video = JSON.parseObject(response, Video.class);
            for (queries.mediaresonance.videos.Item videoItem: video.items) {
                statistics.viewCount += videoItem.statistics.viewCount;
                statistics.commentCount += videoItem.statistics.commentCount;
            }
        } while (pageToken != null);
        return statistics;
    }
}
