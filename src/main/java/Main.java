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

    private static void task2(String channelId1, String channelId2, Settings settings) throws ExecutionException, InterruptedException {
        System.out.println("task2:");

        Callable<CompareGlobalInfoChannels> channelCallable = new Callable<CompareGlobalInfoChannels>() {
            @Override
            public CompareGlobalInfoChannels call() throws Exception {
                return new CompareGlobalInfoChannels(channelId1, channelId2, settings);
            }
        };
        FutureTask<CompareGlobalInfoChannels> task = new FutureTask<CompareGlobalInfoChannels>(channelCallable);
        Thread t = new Thread(task);

        if (settings.getCalculateTimeForQuery().equals(true)) {
            long startTime = System.currentTimeMillis();
            t.start();
            CompareGlobalInfoChannels compareGlobalInfoChannels = task.get();
            long endTime = System.currentTimeMillis();
            System.out.println(compareGlobalInfoChannels);
            System.out.println("Время затраченного не выполнение: " + (endTime - startTime) + " миллисекунд \n");
        } else {
            t.start();
            CompareGlobalInfoChannels compareGlobalInfoChannels = task.get();
            System.out.println(compareGlobalInfoChannels);
        }
    }

    private static void task3(String[] channelIds, Settings settings) throws UnirestException, ExecutionException, InterruptedException {
        System.out.println("task3:");

        Callable<SortChannelsByData> channelCallable = new Callable<SortChannelsByData>() {
            @Override
            public SortChannelsByData call() throws Exception {
                return new SortChannelsByData(channelIds, settings);
            }
        };
        FutureTask<SortChannelsByData> task = new FutureTask<SortChannelsByData>(channelCallable);
        Thread t = new Thread(task);

        if (settings.getCalculateTimeForQuery().equals(true)) {
            long startTime = System.currentTimeMillis();
            t.start();
            SortChannelsByData sortChannelsByData = task.get();
            sortChannelsByData.sortSubscriberCount(); //тут нужно сделать выбор сортировки, это можно сделать интерфейсом
            long endTime = System.currentTimeMillis();
            System.out.println(sortChannelsByData);
            System.out.println("Время затраченного не выполнение: " + (endTime - startTime) + " миллисекунд \n");
        } else {
            t.start();
            SortChannelsByData sortChannelsByData = task.get();
            System.out.println(sortChannelsByData);
        }
    }

    private static void task4() throws UnirestException {
        System.out.println("\n\ntask4:");
        //task1();
//        System.out.println(MediaResonance.search("1111").toString());
    }

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
        String[] channelIds = {"UCmBkHKLRjfHDfFyhrZhowcQ",
                "UCuXYmUOJSbEH1x88WUV1aMg",
                "UCuvrjFN3uRkBT3_VUlhKqcA",
                "UCC7qpnId5RIQruKDJOt2exw",
                "UCoICDijraUJjIZNrPy-mRDQ"};
//        task3(channelIds, settings);

//        task4();
//        task5();
//        task6();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            SaveSettings.save(settings);
        }));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
