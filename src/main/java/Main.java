import com.mashape.unirest.http.exceptions.UnirestException;
import gui.GUI;
import javafx.application.Application;
import javafx.stage.Stage;
import queries.compareglobalinfochannels.CompareGlobalInfoChannels;
import queries.sortchannelsbydata.SortChannelsByData;
import settings.LoadSettings;
import settings.SaveSettings;
import settings.Settings;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main extends Application {

    public static Settings settings;

    private static void task5() {

    }

    private static void task6() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        settings = LoadSettings.load();
        System.out.println(settings);

        GUI gui = new GUI(primaryStage, settings);

        System.out.println("final project core");


//        task1("UCmBkHKLRjfHDfFyhrZhowcQ", settings);
//        task2("UCuvrjFN3uRkBT3_VUlhKqcA", "UCC7qpnId5RIQruKDJOt2exw",settings);
//        String[] channelIds = {"UCmBkHKLRjfHDfFyhrZhowcQ",
//                "UCuXYmUOJSbEH1x88WUV1aMg",
//                "UCuvrjFN3uRkBT3_VUlhKqcA",
//                "UCC7qpnId5RIQruKDJOt2exw",
//                "UCoICDijraUJjIZNrPy-mRDQ"};
//        task3(channelIds, settings);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            SaveSettings.save(settings);
        }));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
