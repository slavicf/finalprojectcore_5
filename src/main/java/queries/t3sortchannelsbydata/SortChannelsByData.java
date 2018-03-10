package queries.t3sortchannelsbydata;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import queries.t1showglobalinfochannel.Channel.Channel;
import queries.t1showglobalinfochannel.YouTubeAPI;

import java.util.ArrayList;
import java.util.Comparator;

public class SortChannelsByData {
    private ArrayList<Channel> channels = new ArrayList<>();
    private String[] channelIds;

    public SortChannelsByData(String[] channelIds) throws UnirestException {
        this.channelIds = channelIds;
        initializationChannels();
    }

    private void initializationChannels() throws UnirestException {
        for (int i = 0; i < channelIds.length; i++) {
            String response = YouTubeAPI.Search(channelIds[i]);
            channels.add(JSON.parseObject(response, Channel.class));
        }
    }

    public void sortTitle(){
        channels.sort(Comparator.comparing(Channel::getTitle));
        showChannelsList();
    }

    public void sortPublishedAt(){
        channels.sort(Comparator.comparing(Channel::getPublishedAt));
        showChannelsList();
    }

    public void sortSubscriberCount(){
        channels.sort(Comparator.comparing(Channel::getSubscriberCount));
        showChannelsList();
    }

    public void sortVideoCount(){
        channels.sort(Comparator.comparing(Channel::getVideoCount));
        showChannelsList();
    }

    public void sortViewCount(){
        channels.sort(Comparator.comparing(Channel::getViewCount));
        showChannelsList();
    }

    public void showChannelsList(){
        for (int i = 0; i < channels.size(); i++) {
            System.out.println("\n\n" + channels.get(i));
        }
    }
}
