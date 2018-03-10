package queries.showglobalinfochannel;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.exceptions.UnirestException;
import queries.showglobalinfochannel.Channel.Channel;

public class ShowGlobalInfoChannel {
    private String idChannel;
    private Channel channel;

    public ShowGlobalInfoChannel(String idChannel) throws UnirestException {
        this.idChannel = idChannel;
        String response = YouTubeAPI.channel(idChannel);
        channel = JSON.parseObject(response, Channel.class);
    }

    public String getIdChannel() {
        return idChannel;
    }

    public String getTitle() {
        return channel.items[0].snippet.title;
    }

    public String getPublishedAt() {
        return channel.items[0].snippet.publishedAt;
    }

    public Long getSubscriberCount() {
        return channel.items[0].statistics.subscriberCount;
    }

    public Long getVideoCount() {
        return channel.items[0].statistics.videoCount;
    }

    public Long getViewCount() {
        return channel.items[0].statistics.viewCount;
    }
}
