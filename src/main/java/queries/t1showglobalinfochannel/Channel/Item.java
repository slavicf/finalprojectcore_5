package queries.t1showglobalinfochannel.Channel;

public class Item {
    public Snippet snippet;
    public Statistics statistics;

    @Override
    public String toString() {
        return "" + snippet + statistics;
    }
}
