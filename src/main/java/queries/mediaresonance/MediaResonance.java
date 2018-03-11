package queries.mediaresonance;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import queries.mediaresonance.search.Item;
import queries.mediaresonance.search.Search;
import queries.mediaresonance.videos.Statistics;
import queries.mediaresonance.videos.Video;
import queries.showglobalinfochannel.YouTubeAPI;

import java.util.ArrayList;

public class MediaResonance {

    public static Statistics search(String channelId) throws UnirestException {
        int results = 0;
        int index = 0;
        int maxResults = 50;
        Statistics statistics = new Statistics();
        String response;
        String pageToken = "";
        String query = "";
        Video video;

        do {
            response = YouTubeAPI.search(channelId, query, maxResults, pageToken);
            Search search = JSON.parseObject(response, Search.class);
            results = search.items.length;
            pageToken = search.nextPageToken;
            ArrayList<String> items = new ArrayList<>();

//            for (int i = 0; i < results; i++) {
//                if (search.items[i].id.videoId != null) {
//                    items.add(search.items[i].id.videoId);
//                } else System.out.println(index + " skipped");
//            }

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
