package queries.mediaresonance;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import queries.mediaresonance.search.Item;
import queries.mediaresonance.search.Search;
import queries.mediaresonance.videos.Video;
import queries.showglobalinfochannel.YouTubeAPI;

import java.util.ArrayList;

public class MediaResonance {
    private static ArrayList<Video> videos = new ArrayList<>();

    public static void search() throws UnirestException {

        String response;
        response = YouTubeAPI.search("UCuXYmUOJSbEH1x88WUV1aMg", "", 50);
        Search search = JSON.parseObject(response, Search.class);
        int viewCount = 0;
        for (Item item: search.items) {
            response = YouTubeAPI.videos(item.id.videoId);
            Video video = JSON.parseObject(response, Video.class);
            viewCount += video.items[0].statistics.viewCount;
        }

    }
}
