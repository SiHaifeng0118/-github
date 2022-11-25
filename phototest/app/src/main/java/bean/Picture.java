package bean;

import java.util.concurrent.CompletableFuture;

public class Picture implements Comparable<Picture> {
    private String path;
    private String date;

    public Picture(String path, String date) {
        this.path = path;
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "path='" + path + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int compareTo(Picture picture) {
        return picture.getDate().compareTo(this.date);
    }
}
