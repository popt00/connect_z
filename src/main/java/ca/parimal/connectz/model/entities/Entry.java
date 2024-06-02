package ca.parimal.connectz.model.entities;

import org.json.simple.JSONObject;

public class Entry{
    public User user;
    public Media media;
    public int score;
    //range 0-100
    public String status;

    public Entry(Media media) {}
    public Entry(JSONObject obj, User user) {
        media = new Media((JSONObject) obj.get("media"));
        score = Integer.parseInt(obj.get("score").toString());
        status = obj.get("status").toString();
        this.user = user;
    }
    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public double getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "user=" + user +
                ", media=" + media +
                ", score=" + score +
                ", status='" + status + '\'' +
                '}';
    }
}