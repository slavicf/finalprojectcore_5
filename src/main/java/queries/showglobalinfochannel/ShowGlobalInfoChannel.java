package queries.showglobalinfochannel;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import queries.showglobalinfochannel.Channel.Channel;

public class ShowGlobalInfoChannel {
    private String idChannel;
    private Channel channel;

    public ShowGlobalInfoChannel(String idChannel) throws UnirestException {
        this.idChannel = idChannel;
        String response = YouTubeAPI.Search(idChannel);
        channel = JSON.parseObject(response, Channel.class);
    }

    public String getIdChannel() {
        return idChannel;
    }

    public String getTitle() {
        return channel.getTitle();
    }

    public String getPublishedAt() {
        return channel.getPublishedAt();
    }

    public Long getSubscriberCount() {
        return channel.getSubscriberCount();
    }

    public Long getVideoCount() {
        return channel.getVideoCount();
    }

    public Long getViewCount() {
        return channel.getViewCount();
    }
}
