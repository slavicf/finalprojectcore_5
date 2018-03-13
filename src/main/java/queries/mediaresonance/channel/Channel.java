package queries.mediaresonance.channel;

import java.util.Arrays;

public class Channel extends queries.showglobalinfochannel.Channel.Channel{
    public long totalCommentCount;

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
