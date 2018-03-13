package queries.mediaresonance.channel;

import queries.showglobalinfochannel.Channel.Item;

import java.util.Arrays;

public class Channel {
    public long totalCommentCount;
    public Item[] items;

    @Override
    public String toString() {
        return "" + Arrays.toString(items);
    }

    public String getTitle() {
        return items[0].snippet.title;
    }

    public String getPublishedAt() {
        return items[0].snippet.publishedAt;
    }

    public long getSubscriberCount() {
        return items[0].statistics.subscriberCount;
    }

    public long getVideoCount() {
        return items[0].statistics.videoCount;
    }

    public long getViewCount() {
        return items[0].statistics.viewCount;
    }

    public long getTotalCommentCount() {
        return totalCommentCount;
    }
}
