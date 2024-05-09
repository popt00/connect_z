package ca.parimal.connectz.model;

public class UserSeriesRating {
    private String name;
    private Integer rating;
    private String url;
    private String status;

    @Override
    public String toString() {
        return "UserSeriesRating{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public UserSeriesRating(Integer rating, String name, String url, String status) {
        this.rating = rating;
        this.name = name;
        this.url = url;
        this.status = status;
    }
    public UserSeriesRating() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
