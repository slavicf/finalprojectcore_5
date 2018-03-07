import com.mashape.unirest.http.exceptions.UnirestException;
import queries.showglobalinfochannel.ShowGlobalInfoChannel;
import queries.sortchannelsbydata.SortChannelsByData;

public class Main {
    public static void main(String[] args) throws UnirestException {
        System.out.println("final project core");
//        System.out.println("task1:");
//        ShowGlobalInfoChannel showGlobalInfoChannel = new ShowGlobalInfoChannel("UCuXYmUOJSbEH1x88WUV1aMg");
//        System.out.println("Имя канала " + showGlobalInfoChannel.getTitle() +
//                            "\nДата создания канала " + showGlobalInfoChannel.getPublishedAt() +
//                            "\nКол-во подписчиков " + showGlobalInfoChannel.getSubscriberCount() +
//                            "\nКол-во видео на канале " + showGlobalInfoChannel.getVideoCount() +
//                            "\nКол-во просмотров всех видео " + showGlobalInfoChannel.getViewCount());

        System.out.println("task2:");
        String[] channelIds = {"UCmBkHKLRjfHDfFyhrZhowcQ",
                "UCuXYmUOJSbEH1x88WUV1aMg",
                "UCuvrjFN3uRkBT3_VUlhKqcA",
                "UCC7qpnId5RIQruKDJOt2exw",
                "UCoICDijraUJjIZNrPy-mRDQ"};
        SortChannelsByData sortChannelsByData = new SortChannelsByData(channelIds);
        sortChannelsByData.sortViewCount();

    }
}
