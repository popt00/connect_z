package ca.parimal.connectz.model.dao.entites;

import jakarta.persistence.*;

@Entity(name = "media")
public class Media {
//    @EmbeddedId
//    private MediaKey id;

    @Id
    @Column(name = "media_id")
//    @MapsId("mediaId")
    private Integer mediaId;

    @Column(name = "title")
    private String title;

//    @OneToMany(mappedBy = "media")
//    Set<Entry> entries;


    public Media() {
    }

    public Media(Integer mediaId, String title) {
        this.mediaId = mediaId;
        this.title = title;
    }

//    public MediaKey getId() {
//        return id;
//    }
//
//    public void setId(MediaKey id) {
//        this.id = id;
//    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer anilistMediaId) {
        this.mediaId = anilistMediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Media{" +
                "anilistId=" + mediaId +
                ", title='" + title + '\'' +
                '}';
    }
}
