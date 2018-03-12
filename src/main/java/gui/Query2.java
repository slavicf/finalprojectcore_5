package gui;

import queries.compareglobalinfochannels.CompareGlobalInfoChannels;
import settings.Settings;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Query2 {
    public static String query2(String channelId1, String channelId2, Settings settings) throws ExecutionException, InterruptedException {
        String task2Str = "";

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
            task2Str += compareGlobalInfoChannels;
            task2Str += "\nВремя затраченного не выполнение: " + (endTime - startTime) + " миллисекунд";
        } else {
            t.start();
            CompareGlobalInfoChannels compareGlobalInfoChannels = task.get();
            task2Str += compareGlobalInfoChannels;
        }
        return task2Str;
    }
}
