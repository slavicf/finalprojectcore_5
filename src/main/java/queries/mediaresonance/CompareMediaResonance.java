package queries.mediaresonance;

import com.mashape.unirest.http.exceptions.UnirestException;
import queries.mediaresonance.videos.Statistics;
import settings.Settings;

import java.io.IOException;

public class CompareMediaResonance {

    public static String search(String channelId1, String channelId2, Settings settings) throws IOException, UnirestException {
        Statistics statistics1 = MediaResonance.search(channelId1, settings);
        Statistics statistics2 = MediaResonance.search(channelId2, settings);
        return "\nКол-во комментариев всех видео на канале 1: " + statistics1.commentCount + " | Кол-во комментариев всех видео на канале 2: " + statistics2.commentCount;
    }
}
