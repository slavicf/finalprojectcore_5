package gui;

import com.mashape.unirest.http.exceptions.UnirestException;
import queries.showglobalinfochannel.ShowGlobalInfoChannel;
import settings.Settings;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Query1 {
    public static String query1(String channelId, Settings settings) throws UnirestException, IOException, ExecutionException, InterruptedException {
        String task1Str = "";

        Callable<ShowGlobalInfoChannel> channelCallable = new Callable<ShowGlobalInfoChannel>() {
            @Override
            public ShowGlobalInfoChannel call() throws Exception {
                return new ShowGlobalInfoChannel(channelId, settings);
            }
        };
        FutureTask<ShowGlobalInfoChannel> task = new FutureTask<ShowGlobalInfoChannel>(channelCallable);
        Thread t = new Thread(task);

        if (settings.getCalculateTimeForQuery().equals(true)) {
            long startTime = System.currentTimeMillis();
            t.start();
            ShowGlobalInfoChannel showGlobalInfoChannel = task.get();
            long endTime = System.currentTimeMillis();
            task1Str += showGlobalInfoChannel;
            task1Str += "\nВремя затраченного не выполнение: " + (endTime - startTime) + " миллисекунд";
        } else {
            t.start();
            ShowGlobalInfoChannel showGlobalInfoChannel = task.get();
            task1Str += showGlobalInfoChannel;
        }
        return task1Str;
    }
}
