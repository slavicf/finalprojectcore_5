package queries.compareglobalinfochannels;

import com.mashape.unirest.http.exceptions.UnirestException;
import queries.showglobalinfochannel.ShowGlobalInfoChannel;
import settings.Settings;

import java.io.IOException;

public class CompareGlobalInfoChannels {
    private String channelId1;
    private String channelId2;
    private Settings settings;
    private ShowGlobalInfoChannel showGlobalInfoChannel1;
    private ShowGlobalInfoChannel showGlobalInfoChannel2;

    public CompareGlobalInfoChannels(String channelId1, String channelId2, Settings settings) throws IOException, UnirestException {
        this.channelId1 = channelId1;
        this.channelId2 = channelId2;
        this.settings   = settings;
        loadChannels();
    }

    private void loadChannels() throws IOException, UnirestException {
        showGlobalInfoChannel1 = new ShowGlobalInfoChannel(channelId1, settings);
        showGlobalInfoChannel2 = new ShowGlobalInfoChannel(channelId2, settings);
    }

    @Override
    public String toString() {
        return "\nИмя канала 1 " + showGlobalInfoChannel1.getTitle() + " | Имя канала 2 " + showGlobalInfoChannel2.getTitle() +
                "\nДата создания канала " + showGlobalInfoChannel1.getPublishedAt() + " | Дата создания канала 2 " + showGlobalInfoChannel2.getPublishedAt() +
                "\nКол-во подписчиков " + showGlobalInfoChannel1.getSubscriberCount() +" | Кол-во подписчиков канала 2 " + showGlobalInfoChannel2.getSubscriberCount() +
                "\nКол-во видео на канале " + showGlobalInfoChannel1.getVideoCount() + " | Кол-во видео на канале 2 " + showGlobalInfoChannel2.getVideoCount() +
                "\nКол-во просмотров всех видео " + showGlobalInfoChannel1.getViewCount() + " | Кол-во просмотров всех видео на канале 2 " + showGlobalInfoChannel2.getViewCount();
    }
}
