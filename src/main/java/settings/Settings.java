package settings;


public class Settings {
    private Boolean saveCache;
    private String  pathToCache;
    private Boolean calculateTimeForQuery;

    @Override
    public String toString() {
        return "\nsaveCache: " + saveCache +
                "\npathToCache: " + pathToCache +
                "\ncalculateTimeForQuery: " + calculateTimeForQuery + "\n";
    }

    public Boolean getSaveCache() {
        return saveCache;
    }

    public String getPathToCache() {
        return pathToCache;
    }

    public Boolean getCalculateTimeForQuery() {
        return calculateTimeForQuery;
    }

    public void setSaveCache(Boolean saveCache) {
        this.saveCache = saveCache;
    }

    public void setPathToCache(String pathToCache) {
        this.pathToCache = pathToCache;
    }

    public void setCalculateTimeForQuery(Boolean calculateTimeForQuery) {
        this.calculateTimeForQuery = calculateTimeForQuery;
    }
}
