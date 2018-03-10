package queries.t1showglobalinfochannel;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class YouTubeAPI {
    @SuppressWarnings("WeakerAccess")
    public static final String API_KEY = "AIzaSyCrVQPr-LRlFYQkpipjrY0x1HGYAwoP7E8";

    public static String Search(String id) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://www.googleapis.com/youtube/v3/channels")
                .queryString("key", API_KEY)
                .queryString("id", id)
                .queryString("part", "snippet,statistics")
                .asString();
        return response.getBody();
    }
}
