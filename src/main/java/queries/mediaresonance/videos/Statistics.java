package queries.mediaresonance.videos;

public class Statistics {
    public long viewCount;
    public long commentCount;

    @Override
    public String toString() {
        return "Просмотров: " + viewCount + "\nКомментариев: " + commentCount;
    }
}
