package queries.mediaresonance.videos;

public class Statistics {
//    public long viewCount;
    public long commentCount;

    @Override
    public String toString() {
        return "Всего комментариев: " + commentCount;
//        return "Просмотров: " + viewCount + "\nКомментариев: " + commentCount;
    }
}
