package gui;

import queries.compareglobalinfochannels.CompareGlobalInfoChannels;
import queries.mediaresonance.CompareMediaResonance;
import queries.mediaresonance.MediaResonance;
import queries.mediaresonance.videos.Statistics;
import queries.showglobalinfochannel.ShowGlobalInfoChannel;
import settings.Settings;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Query {
    public static String task4(String channelId, Settings settings) throws ExecutionException, InterruptedException {
        String string = "";

        Callable<ShowGlobalInfoChannel> channelCallable = () -> new ShowGlobalInfoChannel(channelId, settings);
        FutureTask<ShowGlobalInfoChannel> task1ft = new FutureTask<>(channelCallable);
        Thread thread1 = new Thread(task1ft);

        Callable<Statistics> resonanceCallable = () -> MediaResonance.search(channelId, settings);
        FutureTask<Statistics> task4ft = new FutureTask<>(resonanceCallable);
        Thread thread4 = new Thread(task4ft);

        long startTime = System.currentTimeMillis();
        thread1.start();
        thread4.start();
        ShowGlobalInfoChannel showGlobalInfoChannel = task1ft.get();
        Statistics statistics = task4ft.get();
        long endTime = System.currentTimeMillis();
        string += showGlobalInfoChannel;
        string += "\n" + statistics.toString();
        if (settings.getCalculateTimeForQuery().equals(true))
            string += "\nВремя затраченного на выполнение: " + (endTime - startTime) + " миллисекунд";
        return string;
    }

    public static String query5(String channelId1, String channelId2, Settings settings) throws ExecutionException, InterruptedException {
        String string = "";

        Callable<CompareGlobalInfoChannels> channelCallable = () -> new CompareGlobalInfoChannels(channelId1, channelId2, settings);
        FutureTask<CompareGlobalInfoChannels> task2ft = new FutureTask<>(channelCallable);
        Thread thread2 = new Thread(task2ft);

        Callable<String> resonanceCallable = (Callable) () -> CompareMediaResonance.search(channelId1, channelId2, settings);
        FutureTask<String> task5ft = new FutureTask<>(resonanceCallable);
        Thread thread5 = new Thread(task5ft);

        long startTime = System.currentTimeMillis();
        thread2.start();
        thread5.start();
        CompareGlobalInfoChannels compareGlobalInfoChannels = task2ft.get();
        String temp = task5ft.get();
        long endTime = System.currentTimeMillis();
        string += compareGlobalInfoChannels;
        string += temp;
        if (settings.getCalculateTimeForQuery().equals(true))
            string += "\nВремя затраченного не выполнение: " + (endTime - startTime) + " миллисекунд";
        return string;
    }
}
