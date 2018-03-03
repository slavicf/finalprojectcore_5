import com.mashape.unirest.http.exceptions.UnirestException;
import queries.showglobalinfochannel.ShowGlobalInfoChannel;

public class Main {
    public static void main(String[] args) throws UnirestException {
        System.out.println("final project core");
        System.out.println("task1:");
        ShowGlobalInfoChannel showGlobalInfoChannel = new ShowGlobalInfoChannel("UCuXYmUOJSbEH1x88WUV1aMg");
        System.out.println("Имя канала " + showGlobalInfoChannel.getTitle() +
                            "\nДата создания канала " + showGlobalInfoChannel.getPublishedAt() +
                            "\nКол-во подписчиков " + showGlobalInfoChannel.getSubscriberCount() +
                            "\nКол-во видео на канале " + showGlobalInfoChannel.getVideoCount() +
                            "\nКол-во просмотров всех видео " + showGlobalInfoChannel.getViewCount());

    }
}
