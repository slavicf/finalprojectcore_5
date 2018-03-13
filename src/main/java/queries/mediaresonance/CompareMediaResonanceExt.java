package queries.mediaresonance;

import com.mashape.unirest.http.exceptions.UnirestException;
import settings.Settings;

import java.io.IOException;

public class CompareMediaResonanceExt {
    MediaResonanceExt resonanceExt1, resonanceExt2;

    public CompareMediaResonanceExt(String channelId1, String channelId2, Settings settings) throws IOException, UnirestException {
        resonanceExt1 = new MediaResonanceExt(channelId1, settings);
        resonanceExt2 = new MediaResonanceExt(channelId2, settings);
    }

    @Override
    public String toString() {
        return "\nИмя канала 1: " + resonanceExt1.getTitle() + "\t\t\t\t\t\t\t| Имя канала 2: " + resonanceExt2.getTitle() +
                "\nДата создания канала 1: " + resonanceExt1.getPublishedAt() + "\t\t| Дата создания канала 2: " + resonanceExt2.getPublishedAt() +
                "\nКол-во подписчиков 1: " + resonanceExt1.getSubscriberCount() +"\t\t\t\t\t\t| Кол-во подписчиков канала 2: " + resonanceExt2.getSubscriberCount() +
                "\nКол-во видео на канале 1: " + resonanceExt1.getVideoCount() + "\t\t\t\t\t\t| Кол-во видео на канале 2: " + resonanceExt2.getVideoCount() +
                "\nКол-во просмотров всех видео 1: " + resonanceExt1.getViewCount() + "\t\t\t| Кол-во просмотров всех видео на канале 2 " + resonanceExt2.getViewCount() +
                "\nКол-во комментариев всех видео на канале 1: " + resonanceExt1.gettotalCommentCount() + "\t\t| Кол-во комментариев всех видео на канале 2: " + resonanceExt2.gettotalCommentCount();
    }
}
