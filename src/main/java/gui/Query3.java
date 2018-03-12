package gui;

import com.mashape.unirest.http.exceptions.UnirestException;
import queries.sortchannelsbydata.SortChannelsByData;
import settings.Settings;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Query3 {
    public static String query3(String[] channelIds, String sortSwitcher, Settings settings) throws UnirestException, ExecutionException, InterruptedException {
        String task3Str = "";

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
            sorting(sortSwitcher, sortChannelsByData);
            long endTime = System.currentTimeMillis();
            task3Str += sortChannelsByData;
            task3Str += "\nВремя затраченного не выполнение: " + (endTime - startTime) + " миллисекунд";
        } else {
            t.start();
            SortChannelsByData sortChannelsByData = task.get();
            task3Str += sortChannelsByData;
        }
        return task3Str;
    }

    private static void sorting(String sortSwitcher, SortChannelsByData sortChannelsByData){
        switch (sortSwitcher){
            case "title":{
                sortChannelsByData.sortTitle();
                break;
            }
            case "date":{
                sortChannelsByData.sortPublishedAt();
                break;
            }
            case "subscribers":{
                sortChannelsByData.sortSubscriberCount();
                break;
            }
            case "videos":{
                sortChannelsByData.sortVideoCount();
                break;
            }
            case "views":{
                sortChannelsByData.sortViewCount();
                break;
            }
        }
    }
}
