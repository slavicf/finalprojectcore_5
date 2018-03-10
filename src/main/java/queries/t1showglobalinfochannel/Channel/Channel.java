package queries.t1showglobalinfochannel.Channel;

import java.util.Arrays;

public class Channel {
    @SuppressWarnings("WeakerAccess")
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

    public long getCommentCount() {
        return items[0].statistics.commentCount;
    }

    public long getViewCount() {
        return items[0].statistics.viewCount;
    }
}
