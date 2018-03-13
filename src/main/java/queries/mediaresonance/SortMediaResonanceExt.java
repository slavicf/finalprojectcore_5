package queries.mediaresonance;

import com.mashape.unirest.http.exceptions.UnirestException;
import settings.Settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class SortMediaResonanceExt {
    private ArrayList<MediaResonanceExt> mediaResonanceExts = new ArrayList<>();

    public SortMediaResonanceExt(String[] channelIds, Settings settings) throws UnirestException, IOException {
        for (int i = 0; i < channelIds.length; i++)
            mediaResonanceExts.add(new MediaResonanceExt(channelIds[i], settings));
    }

    public void sortTitle(){
        mediaResonanceExts.sort(Comparator.comparing(MediaResonanceExt::getTitle));
    }

    public void sortPublishedAt(){
        mediaResonanceExts.sort(Comparator.comparing(MediaResonanceExt::getPublishedAt));
    }

    public void sortSubscriberCount(){
        mediaResonanceExts.sort(Comparator.comparing(MediaResonanceExt::getSubscriberCount));
    }

    public void sortVideoCount(){
        mediaResonanceExts.sort(Comparator.comparing(MediaResonanceExt::getVideoCount));
    }

    public void sortViewCount(){
        mediaResonanceExts.sort(Comparator.comparing(MediaResonanceExt::getViewCount));
    }

    public void sortCommentCount(){
        mediaResonanceExts.sort(Comparator.comparing(MediaResonanceExt::getTotalCommentCount));
    }

    @Override
    public String toString() {
        String channelList = "\n";
        for (int i = 0; i < mediaResonanceExts.size(); i++) channelList += mediaResonanceExts.get(i) + "\n";
        return channelList;
    }

}
