package gui;

import queries.mediaresonance.MediaResonance;
import queries.mediaresonance.videos.Statistics;
import settings.Settings;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Query4 {
    public static String query4(String channelId, Settings settings) throws ExecutionException, InterruptedException {
        String task1Str = "";

        Callable<Statistics> resonanceCallable = () -> MediaResonance.search(channelId, settings);
        FutureTask<Statistics> task = new FutureTask<>(resonanceCallable);
        Thread t = new Thread(task);

        long startTime = System.currentTimeMillis();
        t.start();
        Statistics statistics = task.get();
        long endTime = System.currentTimeMillis();
        task1Str += statistics.toString();
        if (settings.getCalculateTimeForQuery().equals(true))
            task1Str += "\nВремя затраченного на выполнение: " + (endTime - startTime) + " миллисекунд";
        return task1Str;
    }
}
