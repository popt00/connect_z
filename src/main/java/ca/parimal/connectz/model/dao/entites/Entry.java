package ca.parimal.connectz.model.dao.entites;

import jakarta.persistence.*;

@Entity(name = "entry")
public class Entry{

    @EmbeddedId
    EntryKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("mediaId")
    @JoinColumn(name = "media_id")
    private Media media;


    @JoinColumn(name = "score")
    private Integer score;
    //range 0-100

    @JoinColumn(name = "status")
    private String status;

    public Entry() {
        this.id = new EntryKey();
    }

    public Entry(User user, Media media) {
        this.id= new EntryKey();
        this.user = user;
        this.media = media;
    }

    public EntryKey getId() {
        return id;
    }

    public void setId(EntryKey id) {
        this.id = id;
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