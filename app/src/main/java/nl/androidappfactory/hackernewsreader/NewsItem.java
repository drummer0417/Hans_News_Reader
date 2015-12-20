package nl.androidappfactory.hackernewsreader;

/**
 * Created by HA256157 on 20/12/2015.
 */
public class NewsItem {

    private String description;
    private String url;
    private int id;

    public NewsItem(String description, String url, int id) {
        this.description = description;
        this.url = url;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return description;
    }
}
