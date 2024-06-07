package ca.parimal.connectz.model.entities.dao;

import jakarta.persistence.*;
import org.json.simple.JSONObject;

@Entity(name = "entry")
public class Entry{

    @EmbeddedId
    EntryKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @MapsId("mediaId")
    @JoinColumn(name = "media_id")
    public Media media;


    @JoinColumn(name = "score")
    public Integer score;
    //range 0-100

    @JoinColumn(name = "status")
    public String status;

    public Entry() {
        this.id = new EntryKey();
    }

    public Entry(User user, Media media) {
        this.id= new EntryKey();
        this.user = user;
        this.media = media;
    }

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
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