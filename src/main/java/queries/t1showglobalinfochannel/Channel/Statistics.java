package queries.t1showglobalinfochannel.Channel;

public class Statistics {
    public long viewCount;
    public long subscriberCount;
    public long videoCount;

    @Override
    public String toString() {
        return "\nsubscriberCount " + subscriberCount +
                "\nvideoCount " + videoCount +
                "\nviewCount " + viewCount;


    }
}
