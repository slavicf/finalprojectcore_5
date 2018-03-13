package gui;

import com.mashape.unirest.http.exceptions.UnirestException;
import queries.compareglobalinfochannels.CompareGlobalInfoChannels;
import queries.mediaresonance.CompareMediaResonance;
import queries.mediaresonance.CompareMediaResonanceExt;
import queries.mediaresonance.MediaResonanceExt;
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
        MediaResonanceExt showGlobalInfoChannel = task4ft.get();
        long endTime = System.currentTimeMillis();
        string += showGlobalInfoChannel;
        if (settings.getCalculateTimeForQuery().equals(true))
            string += "\nВремя затраченного на выполнение: " + (endTime - startTime) + " миллисекунд";
        return string;
    }

    public static String query5(String channelId1, String channelId2, Settings settings) throws ExecutionException, InterruptedException, IOException, UnirestException {
        String string = "";

        Callable<CompareMediaResonanceExt> channelCallable = () -> new CompareMediaResonanceExt(channelId1, channelId2, settings);
        FutureTask<CompareMediaResonanceExt> task5ft = new FutureTask<>(channelCallable);
        Thread thread5 = new Thread(task5ft);

        long startTime = System.currentTimeMillis();
        thread5.start();
        CompareMediaResonanceExt compareGlobalInfoChannels = task5ft.get();
        long endTime = System.currentTimeMillis();
        string += compareGlobalInfoChannels;
        if (settings.getCalculateTimeForQuery().equals(true))
            string += "\nВремя затраченного на выполнение: " + (endTime - startTime) + " миллисекунд";
        return string;
    }
}
