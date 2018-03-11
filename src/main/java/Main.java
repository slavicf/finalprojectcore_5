import com.mashape.unirest.http.exceptions.UnirestException;
import queries.mediaresonance.MediaResonance;
import queries.showglobalinfochannel.ShowGlobalInfoChannel;
import queries.sortchannelsbydata.SortChannelsByData;

public class Main {

    private static void task1() throws UnirestException {
        System.out.println("task1:");
        ShowGlobalInfoChannel showGlobalInfoChannel = new ShowGlobalInfoChannel("UCuXYmUOJSbEH1x88WUV1aMg");
        System.out.println("Имя канала " + showGlobalInfoChannel.getTitle() +
                "\nДата создания канала " + showGlobalInfoChannel.getPublishedAt() +
                "\nКол-во подписчиков " + showGlobalInfoChannel.getSubscriberCount() +
                "\nКол-во видео на канале " + showGlobalInfoChannel.getVideoCount() +
                "\nКол-во просмотров всех видео " + showGlobalInfoChannel.getViewCount());
    }

    private static void task2() {

    }

    private static void task3() throws UnirestException {
        System.out.println("task3:");
        String[] channelIds = {"UCmBkHKLRjfHDfFyhrZhowcQ",
                "UCuXYmUOJSbEH1x88WUV1aMg",
                "UCuvrjFN3uRkBT3_VUlhKqcA",
                "UCC7qpnId5RIQruKDJOt2exw",
                "UCoICDijraUJjIZNrPy-mRDQ"};
        SortChannelsByData sortChannelsByData = new SortChannelsByData(channelIds);
        sortChannelsByData.sortViewCount();
    }

    private static void task4() throws UnirestException {
        System.out.println("\n\ntask3:");
        task1();
        System.out.println(MediaResonance.search().toString());;
    }

    private static void task5() {

    }

    public static void main(String[] args) throws UnirestException {
        System.out.println("final project core");

//        task1();
//        task2();
//        task3();
        task4();
//        task5();
    }
}
