package queries;

import com.mashape.unirest.http.exceptions.UnirestException;
import queries.t1showglobalinfochannel.ShowGlobalInfoChannel;
import queries.t3sortchannelsbydata.SortChannelsByData;

public class Tasks {

    public static void task1() throws UnirestException {
        System.out.println("task1:");
        ShowGlobalInfoChannel showGlobalInfoChannel = new ShowGlobalInfoChannel("UCuXYmUOJSbEH1x88WUV1aMg");
        System.out.println("Имя канала " + showGlobalInfoChannel.getTitle() +
                "\nДата создания канала " + showGlobalInfoChannel.getPublishedAt() +
                "\nКол-во подписчиков " + showGlobalInfoChannel.getSubscriberCount() +
                "\nКол-во видео на канале " + showGlobalInfoChannel.getVideoCount() +
                "\nКол-во просмотров всех видео " + showGlobalInfoChannel.getViewCount());
    }

    public static void task2() {

    }

    public static void task3() throws UnirestException {
        System.out.println("task2:");
        String[] channelIds = {"UCmBkHKLRjfHDfFyhrZhowcQ",
                "UCuXYmUOJSbEH1x88WUV1aMg",
                "UCuvrjFN3uRkBT3_VUlhKqcA",
                "UCC7qpnId5RIQruKDJOt2exw",
                "UCoICDijraUJjIZNrPy-mRDQ"};
        SortChannelsByData sortChannelsByData = new SortChannelsByData(channelIds);
        sortChannelsByData.sortViewCount();
    }

    public static void task4() {

    }

    public static void task5() {

    }

}
