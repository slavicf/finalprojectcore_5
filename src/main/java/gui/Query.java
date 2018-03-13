package gui;

import com.mashape.unirest.http.exceptions.UnirestException;
import queries.mediaresonance.CompareMediaResonanceExt;
import queries.mediaresonance.MediaResonanceExt;
import queries.mediaresonance.SortMediaResonanceExt;
import settings.Settings;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Query {
    public static String task4(String channelId, Settings settings) throws ExecutionException, InterruptedException, IOException, UnirestException {
        String string = "";

        Callable<MediaResonanceExt> resonanceExtCallable = () -> new MediaResonanceExt(channelId, settings);
        FutureTask<MediaResonanceExt> task4ft = new FutureTask<>(resonanceExtCallable);
        Thread thread4 = new Thread(task4ft);

        long startTime = System.currentTimeMillis();
        thread4.start();
        MediaResonanceExt resonanceExt = task4ft.get();
        long endTime = System.currentTimeMillis();
        string += resonanceExt;
        if (settings.getCalculateTimeForQuery().equals(true))
            string += "\n\nВремя затраченного на выполнение: " + (endTime - startTime) + " миллисекунд";
        return string;
    }

    public static String query5(String channelId1, String channelId2, Settings settings) throws ExecutionException, InterruptedException, IOException, UnirestException {
        String string = "";

        Callable<CompareMediaResonanceExt> channelCallable = () -> new CompareMediaResonanceExt(channelId1, channelId2, settings);
        FutureTask<CompareMediaResonanceExt> task5ft = new FutureTask<>(channelCallable);
        Thread thread5 = new Thread(task5ft);

        long startTime = System.currentTimeMillis();
        thread5.start();
        CompareMediaResonanceExt resonanceExt = task5ft.get();
        long endTime = System.currentTimeMillis();
        string += resonanceExt;
        if (settings.getCalculateTimeForQuery().equals(true))
            string += "\n\nВремя затраченного на выполнение: " + (endTime - startTime) + " миллисекунд";
        return string;
    }

    public static String query6(String[] channelIds, String sortSwitcher, Settings settings) throws ExecutionException, InterruptedException {
        String string = "";

        Callable<SortMediaResonanceExt> channelCallable = () -> new SortMediaResonanceExt(channelIds, settings);
        FutureTask<SortMediaResonanceExt> task6ft = new FutureTask<>(channelCallable);
        Thread thread6 = new Thread(task6ft);

            long startTime = System.currentTimeMillis();
            thread6.start();
            SortMediaResonanceExt resonanceExt = task6ft.get();
            sorting(sortSwitcher, resonanceExt);
            long endTime = System.currentTimeMillis();
            string += resonanceExt;
        if (settings.getCalculateTimeForQuery().equals(true))
            string += "\nВремя затраченного на выполнение: " + (endTime - startTime) + " миллисекунд";
        return string;
    }

    private static void sorting(String sortSwitcher, SortMediaResonanceExt sortChannelsByData) {
        switch (sortSwitcher) {
            case "title": {
                sortChannelsByData.sortTitle();
                break;
            }
            case "date": {
                sortChannelsByData.sortPublishedAt();
                break;
            }
            case "subscribers": {
                sortChannelsByData.sortSubscriberCount();
                break;
            }
            case "videos": {
                sortChannelsByData.sortVideoCount();
                break;
            }
            case "views": {
                sortChannelsByData.sortViewCount();
                break;
            }
            case "comments": {
                sortChannelsByData.sortCommentCount();
                break;
            }
        }
    }
}
