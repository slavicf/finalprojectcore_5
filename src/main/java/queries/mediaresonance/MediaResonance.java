package queries.mediaresonance;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import queries.mediaresonance.videos.Video;
import queries.showglobalinfochannel.YouTubeAPI;

import java.util.ArrayList;

public class MediaResonance {
    private static ArrayList<Video> videos = new ArrayList<>();

    public static void search() throws UnirestException {
        Video response = YouTubeAPI.videos("PwgSwI2jc0s");
    }
}
