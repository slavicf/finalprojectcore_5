package queries.sortchannelsbydata;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import queries.showglobalinfochannel.Channel.Channel;
import queries.showglobalinfochannel.ShowGlobalInfoChannel;
import queries.showglobalinfochannel.YouTubeAPI;
import settings.Settings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SortChannelsByData {
    private String[] channelIds;
    private Settings settings;
    private ArrayList<ShowGlobalInfoChannel> showGlobalInfoChannels = new ArrayList<>();

    public SortChannelsByData(String[] channelIds, Settings settings) throws UnirestException, IOException {
        this.channelIds = channelIds;
        this.settings   = settings;
        loadChannels();
    }

    private void loadChannels() throws UnirestException, IOException {
        for (int i = 0; i < channelIds.length; i++) {
            showGlobalInfoChannels.add(new ShowGlobalInfoChannel(channelIds[i], settings));
        }
    }

    public void sortTitle(){
        showGlobalInfoChannels.sort(Comparator.comparing(ShowGlobalInfoChannel::getTitle));
    }

    public void sortPublishedAt(){
        showGlobalInfoChannels.sort(Comparator.comparing(ShowGlobalInfoChannel::getPublishedAt));
    }

    public void sortSubscriberCount(){
        showGlobalInfoChannels.sort(Comparator.comparing(ShowGlobalInfoChannel::getSubscriberCount));
    }

    public void sortVideoCount(){
        showGlobalInfoChannels.sort(Comparator.comparing(ShowGlobalInfoChannel::getVideoCount));
    }

    public void sortViewCount(){
        showGlobalInfoChannels.sort(Comparator.comparing(ShowGlobalInfoChannel::getViewCount));
    }

    @Override
    public String toString() {
        String channelList = "\n";
        for (int i = 0; i < showGlobalInfoChannels.size(); i++) {
            channelList += showGlobalInfoChannels.get(i) + "\n";
        }
        return channelList;
    }
}
